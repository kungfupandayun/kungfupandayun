#include "synchro.h"
#include "ensitheora.h"
#include <pthread.h>

extern struct TextureDate texturedate[NBTEX];
bool fini;

/* les variables pour la synchro, ici */
pthread_mutex_t m_Fenetre= PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t m_Texture= PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t m_Producteur=PTHREAD_MUTEX_INITIALIZER;
//pthread_mutex_t m_Consommateur=PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t c_Fenetre= PTHREAD_COND_INITIALIZER;;
pthread_cond_t c_Texture= PTHREAD_COND_INITIALIZER;
pthread_cond_t c_Producteur= PTHREAD_COND_INITIALIZER;
pthread_cond_t c_Consommateur= PTHREAD_COND_INITIALIZER;

int nbCaseVides = NBTEX;

int ok=0;
int okTexture =0;

/* l'implantation des fonctions de synchro ici */
void envoiTailleFenetre(th_ycbcr_buffer buffer) {
  // pthread_mutex_init(&mutex, NULL);
  // printf("%d\n",pthread_cond_init(&c,NULL));
  windowsx = buffer[0].width;
	windowsy= buffer[0].height;

   printf("%d\n",pthread_cond_signal(&c_Fenetre));

}

void attendreTailleFenetre() {
  pthread_mutex_lock(&m_Fenetre);

  while(windowsx==0 || windowsy==0){
    printf("on est en train d'attendre la taille de la fenetre\n");
    printf("%d\n",pthread_cond_wait(&c_Fenetre,&m_Fenetre));
    printf("on a fini d'attendre la taille de la fenetre\n");

  }
  pthread_mutex_unlock(&m_Fenetre);

}

void signalerFenetreEtTexturePrete() {
    pthread_cond_signal(&c_Texture);
    okTexture=1;
    printf("hello");

}

void attendreFenetreTexture() {
  pthread_mutex_lock(&m_Texture);

  while(okTexture==0)
      pthread_cond_wait(&c_Texture,&m_Texture);
  //while(){}
  printf("on a fini l'attente de la texture \n");
  pthread_mutex_unlock(&m_Texture);

}

void debutConsommerTexture() {
  pthread_mutex_lock(&m_Producteur);
  while(nbCaseVides==NBTEX) {
    pthread_cond_wait(&c_Consommateur,&m_Producteur);
  }
  //pthread_mutex_unlock(&m_Producteur);
}

void finConsommerTexture() {
  //pthread_mutex_lock(&m_Producteur);
  nbCaseVides++ ;
  pthread_cond_signal(&c_Producteur);
  pthread_mutex_unlock(&m_Producteur);

}


void debutDeposerTexture() {
  pthread_mutex_lock(&m_Producteur);
  while(nbCaseVides==0) {
    printf("attente dans debutDeposerTexture\n");

    pthread_cond_wait(&c_Producteur,&m_Producteur);
  }
  pthread_mutex_unlock(&m_Producteur);

}

void finDeposerTexture() {
  pthread_mutex_lock(&m_Producteur);
  nbCaseVides++ ;
  pthread_cond_signal(&c_Consommateur);
  pthread_mutex_unlock(&m_Producteur);
}
