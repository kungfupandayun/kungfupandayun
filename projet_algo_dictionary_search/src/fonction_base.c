#include "fonction_base.h"

unsigned get_index(char c) { return c - 'a'; }
char get_char(unsigned index) { return index + 'a'; }


dico create_dico(){
  //creer un dico
  dico d=malloc(NB_KEYS*sizeof(tree));
  assert(d!=NULL);
  return d;
}


tree creer_node(char c,bool eow){
  //creer un node
  tree a=malloc(sizeof(node));
  assert(a!=NULL);
  a->first=c;
  a->end_of_word=eow;
  a->children=create_dico();
  return a;
}

void destroy_dico(dico *d){
  //destroy le d=it->stack+(i+1)ico
  if((*d)==NULL) return ;
  int i;
  for(i=0;i<NB_KEYS;i++)
  {
    tree t=(*d)[i];
    if(t!=NULL) {
      destroy_dico(&(t->children));
    }
    free(t);
  }
  free(*d);
  *d=NULL;
}

unsigned nb_children(tree t){
  unsigned i,nb;
  nb=0;
  if (t==NULL) return 0;
  for(i=0 ; i<NB_KEYS ; i++)
    {if(t->children[i]!=NULL)  nb++;}
  return nb ;
}

unsigned nb_nodes(dico d){
  unsigned i,nb;
  nb=0;
  if(d==NULL) return 0;
  for(i=0;i<NB_KEYS;i++)
      {if(d[i]!=NULL) nb+=1+nb_nodes(d[i]->children);}
  return nb;
}

//CHERHCER HAUTEUR Max
unsigned max_height(dico d,int *j,int * max){
  int i;
  if(d==NULL) return 0;
  for(i=0;i<NB_KEYS;i++)
  { // Si il existe un char dans le tableau
    if(d[i]!=NULL)
    { //on ajoute la hauteur, et executer la fonction encore avec ses fils
      (*j)++;
      max_height(d[i]->children,j,max);
      //on compare le resultat de j avec n'importe quel max qu'on a retrouvé pendant récursion
      if((*j)>(*max)) (*max)=(*j);
      //on diminue la hauteur apres la recursion, car on a diminue dans le niveau du programme
      (*j)--;
    }
  }
  return (*max);
}
unsigned height(dico d){
  if(d==NULL) return 0;
  int j=0;
  int max=0;
  return max_height(d,&j,&max);
}

void print(dico d , int j){
  //afiicher le dico en prefix
  int i;
  if(d==NULL) return;
  for(i=0;i<NB_KEYS;i++)
  {
    if(d[i]!=NULL)
    {
      int z;
      // j enregistre l'emplacent de la lettre sur un mot, donc combien '+' on a besoin avant la lettre
      for(z=j;z>0;z--) printf("+");
      printf("%c",d[i]->first);
      if(d[i]->end_of_word) printf("*");
      printf("\n");
      // on inspecte la lettre suivante
      j++;
      print(d[i]->children,j);
      // on diminue la valeur j qui corresponde à la hauteur de la lettre à chaque niveau
      j--;
    }
  }
}

void print_prefix(dico d){
  if(d==NULL)
  {  printf("dico n'est pas créé\n");
      return;
  }
  printf("Voici le dictionaire: \n");
  int j=0;
  print(d,j);
}


bool equals_tree(tree t1 ,tree t2){

  int i = 0 ;
  // si les deux arbres sont vides
  if(!t1 && !t2){return true ;}
  // si l'un des arbres est vide et l'autre non
  if (!(t1&&t2) ) {return false ;}

  if(t1 && t2){
    if (t1->first != t2->first || t1->end_of_word != t2->end_of_word )
    {return false ;}

    for(i=0 ; i<NB_KEYS ;i++){
      if(!equals_tree(t1->children[i] , t2->children[i] )) return false ;
    }
  }

  return true ;




}

bool equals (dico d1 ,dico d2){
  // si les deux dicos sont vides
  if(!d1 && !d2){return true ;}

  // si l'un des dicos est vide et l'autre non
  if (!(d1&&d2) ) {return false ;}

  int  i ;
  tree t1 ;
  tree t2 ;
  for(i=0 ; i<NB_KEYS ; i++){
    t1 = d1[i];
    t2 = d2[i] ;
    if(!equals_tree(t1,t2)) return false;
  }
  return true ;
}
