// Test sur l'égalité structurelle entre deux dictionnaires, qui renvoie vrai quand
  //l'ensemble des racines des arbres de l'un est égal à l'ensemble des racines des arbres de l'autre et
  //pour toute racine r dans l'ensemble des racines des arbres, la forêt des enfants de l'arbre de racine r dans l'une est égale à la forêt des enfants de l'arbre de racine r dans l'autre.

#include "fonction_base.h"


int main(void){

  printf("\033[1;31m");
  printf("*********1. DEUX DICO VIDE***********\n");
  printf("\033[0m");
  dico dico1=create_dico();
  dico dico2=create_dico();
  print_prefix(dico1);
  print_prefix(dico2);
  printf("Est-ce que 2 dico sont égaux? => %d\n",equals(dico1,dico2));
  printf("\n");


  printf("\033[1;31m");
  printf("*********2. UN DICO VIDE, L'AUTRE AVEC UN ELEMENT***********\n");
  printf("\033[0m");
  tree a=creer_node('a',false);
  dico1[get_index(a->first)]=a;
  print_prefix(dico1);
  print_prefix(dico2);
  printf("Est-ce que 2 dico sont égaux? => %d\n",equals(dico1,dico2));
  printf("\n");


  printf("\033[1;31m");
  printf("*********3. DEUX DICO AVEC UN MEME ELEMENT ***********\n");
  printf("\033[0m");
  tree a2=creer_node('a',false);
  dico2[get_index(a2->first)]=a2;
  print_prefix(dico1);
  print_prefix(dico2);
  printf("Est-ce que 2 dico sont égaux? => %d\n",equals(dico1,dico2));
  printf("\n");


  printf("\033[1;31m");
  printf("*********4. UN DICO AVEC UN ELEMENT, L'AUTRE AVEC DEUX ***********\n");
  printf("\033[0m");
  tree b=creer_node('b',false);
  dico2[get_index(b->first)]=b;
  print_prefix(dico1);
  print_prefix(dico2);
  printf("Est-ce que 2 dico sont égaux? => %d\n",equals(dico1,dico2));
  printf("\n");

  printf("\033[1;31m");
  printf("*********5. DEUX DICO AVEC DEUX DIFF ELEMENTS ***********\n");
  printf("\033[0m");
  tree c=creer_node('c',false);
  dico1[get_index(c->first)]=c;
  print_prefix(dico1);
  print_prefix(dico2);
  printf("Est-ce que 2 dico sont égaux? => %d\n",equals(dico1,dico2));
  printf("\n");


  printf("\033[1;31m");
  printf("*********6. DEUX DICO AVEC DEUX MEME ELEMENTS, MAIS DIFFERENTS FILS À 'c' ***********\n");
  printf("\033[0m");
  tree c2=creer_node('c',false);
  tree b2=creer_node('b',false);
  dico1[get_index(b2->first)]=b2;
  dico2[get_index(c2->first)]=c2;
  tree d=creer_node('d',false);
  tree e=creer_node('e',false);
  dico1[get_index(c->first)]->children[get_index(d->first)]=d;
  dico2[get_index(c2->first)]->children[get_index(e->first)]=e;
  print_prefix(dico1);
  print_prefix(dico2);
  printf("Est-ce que 2 dico sont égaux? => %d\n",equals(dico1,dico2));
  printf("\n");


  printf("\033[1;31m");
  printf("*********7. DEUX DICO AVEC DEUX MEME ELEMENTS, MAIS DIFFERENTS TERMINAISON ***********\n");
  printf("\033[0m");
  tree d2=creer_node('d',true);
  tree e2=creer_node('e',false);
  dico1[get_index(c->first)]->children[get_index(e2->first)]=e2;
  dico2[get_index(c2->first)]->children[get_index(d2->first)]=d2;
  print_prefix(dico1);
  print_prefix(dico2);
  printf("Est-ce que 2 dico sont égaux? => %d\n",equals(dico1,dico2));
  printf("\n");


  printf("\033[1;31m");
  printf("*********8. DEUX DICO AVEC DEUX MEME ELEMENTS**********\n");
  printf("\033[0m");
  dico2[get_index(c2->first)]->children[get_index(d2->first)]->end_of_word=false;
  print_prefix(dico1);
  print_prefix(dico2);
  printf("Est-ce que 2 dico sont égaux? => %d\n",equals(dico1,dico2));
  printf("\n");


  printf("\033[1;31m");
  printf("*********8. DESTROY 2**********\n");
  printf("\033[0m");
  destroy_dico(&dico1);
  destroy_dico(&dico2);
  printf("\n");




}
