#include "fonction_base.h"



int main(void){

    unsigned i;
    dico d=create_dico();


    printf("\033[1;31m");
    printf("*********1. LE DICO EST VIDE***********\n");
    printf("\033[0m");

    iterator* it=start_iterator(d);
    while (has_next(it)) {
      printf("-%s", next(it));
      //on supprime les lettres qui ne sont pas dans le mot
        for( i=it->stack[0]->index_word; i<height(d) ; i++){
          it->word[i]=0;
        }
    }
    printf("\n");
    destroy_iterator(&it);



    printf("\033[1;31m");
    printf("*********2. LE DICO REMPLE AVEC UN MOT***********\n");
    printf("\033[0m");
    add_rec(d, "bateau", 6);
     it=start_iterator(d);
    while (has_next(it)) {
      printf("-%s", next(it));
        for( i=it->stack[0]->index_word; i<height(d) ; i++){
          it->word[i]=0;
        }
    }
    printf("\n");
    destroy_iterator(&it);


    printf("\033[1;31m");
    printf("*********3. LE DICO REMPLI AVEC TOUS LES MOTS***********\n");
    printf("\033[0m");
    add_iter(d, "bateau", 6);
    add_iter(d, "bordeau", 7);
    add_iter(d, "brille", 6);
    add_iter(d, "brule", 5);
    add_iter(d, "ours", 4);
    add_iter(d, "ourse", 5);
    add_iter(d, "ourson",6);
    add_iter(d, "oursonne",8);
    it=start_iterator(d);
    while (has_next(it)) {

      printf("-%s", next(it));
        for( i=it->stack[0]->index_word; i<height(d) ; i++){
          it->word[i]=0;
        }
    }
    printf("\n");
    destroy_iterator(&it);


    printf("\033[1;31m");
    printf("*********4. VOIR DESROY=> MAKE DESTROY5***********\n");
    printf("\033[0m");
    destroy_dico(&d);

  }
