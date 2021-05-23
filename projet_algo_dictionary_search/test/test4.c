#include "fonction_base.h"

// ces tests permettent de tester les focnction récursives d'ajout , de suprression , et  d'affichage du dico

int main(void){

printf("\033[1;31m");
printf("*********1. ON CREE LE DICO DE LEXEMPLE EN AJOUTANT LES MOTS DE MANIERE iterUSRSIVE***********\n");
printf("\033[0m");

dico d=create_dico();
 add_iter(d, "bateau", 6);
 add_iter(d, "bordeau", 7);
 add_iter(d, "brille", 6);
 add_iter(d, "brule", 5);
 add_iter(d, "ours", 4);
 add_iter(d, "ourse", 5);
 add_iter(d, "ourson",6);
 add_iter(d, "oursonne",8);

 printf("Le nombre de noeuds du dictionnaire est: %d\n",nb_nodes(d));
 printf("La hauteur  est: %d\n",height(d));



 printf("\033[1;31m");
 printf("*********AFFICHAGE PREFIXE DU DICO CREE***********\n");
 printf("\033[0m");
 print_prefix(d);

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

 printf("*********AFFICHAGE PREFIXE DU DICO MODIFIE ***********\n");
 print_prefix(d);


 printf("\033[1;31m");
 printf("*********COMPTONS A NOUVEAU LE NOMBRE DE MOTS DANS CE DICO***********\n");
 printf("\033[0m");
 printf("Ce dico contient %d mots \n", nb_words(d));


 printf("\033[1;31m");
 printf("*********3.SUPPRIMONS LE MOT BATEAU DE MANIERE ITERATIVE \n");
 printf("\033[0m");

 remove_iter(d,"bateau", 6);
 printf("*********AFFICHAGE PREFIXE DU DICO MODIFIE ***********\n");
 print_prefix(d);

 printf("\033[1;31m");
 printf("*********SUPPRIMONS UNE NOUVELLE FOIS LE MOT BATEAU DE MANIERE ITERATIVE \n");
 printf("\033[0m");
 remove_iter(d, "bateau", 6);



 printf("*********COMPTONS A NOUVEAU LE NOMBRE DE MOTS DANS CE DICO***********\n");
 printf("Ce dico contient %d mots \n", nb_words(d));

}
