#include "fonction_base.h"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
int main(void){

  FILE* fichier = NULL;
  dico d = create_dico() ;
  unsigned int n = 0 ;
  char* word = malloc(NB_LETTRESMAX *sizeof(char)) ;
  /* nous disposons de 2 fichiers textes pour les tests, l'autre etant vraiment grand on l'utilise pour vérifier
  que nos fonctions fonctionent a grande echelle */

  //fichier = fopen("test/words.txt", "r+");
  fichier = fopen("test/engmix.txt", "r+");

  if (fichier != NULL){
    printf("fichier ouvert\n");
    while(fgets(word , NB_LETTRESMAX , fichier) != NULL){
    n = strlen(word) ;
    *(word+n-1) =  0 ;
    // ici le \n est compté comme un caractère , le mot en lui même est donc de longuer de n-1
    add_rec(d,word, n-1) ;
    }
  }

  else{
    printf("Impossible d'ouvrir le fichier ");
  }



   // printf("\033[1;31m");
   // printf("*********AFFICHAGE PREFIXE DU DICO CREE***********\n");
   // printf("\033[0m");
   // print_prefix(d);

   printf("\033[1;31m");
   printf("*********COMPTONS LE NOMBRE DE MOTS DANS CE DICO***********\n");
   printf("\033[0m");
   printf("Ce dico contient %d mots \n", nb_words(d));


   printf("\033[1;31m");
   printf("*********2.AJOUTONS NOS PRENOMS DE MANIERE ITERATIVE A CE DICO \n");
   printf("\033[0m");

   add_iter(d,"jiayun",6) ;
   add_iter(d,"corinne",7) ;

   printf("\033[1;31m");
   printf("*********ESSAYONS DE RAJOUTER UNE NOUVELLE FOIS NOS PRENOMS DE MANIERE ITERATIVE A CE DICO \n");
   printf("\033[0m");

   add_iter(d,"jiayun",6) ;
   add_iter(d,"corinne",7) ;

   // printf("*********AFFICHAGE PREFIXE DU DICO MODIFIE ***********\n");
   // print_prefix(d);


   printf("\033[1;31m");
   printf("*********COMPTONS A NOUVEAU LE NOMBRE DE MOTS DANS CE DICO***********\n");
   printf("\033[0m");
   printf("Ce dico contient %d mots \n", nb_words(d));


  free(word) ;
  fclose(fichier) ;
  // a decommenter si on veut afficher le grand dico
  //print_dico(d);
  destroy_dico(&d) ;


}
