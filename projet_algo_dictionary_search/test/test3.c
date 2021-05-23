#include "fonction_base.h"

// ces tests permettent de tester les focnction récursives d'ajout , de suprression , et  d'affichage du dico

int main(void){

printf("\033[1;31m");
printf("********* ON CREE DEUX DICOS CORRESPONDANT A L'EXEMPLE EN AJOUTANT LES MOTS DE MANIERE RECUSRSIVE***********\n");
printf("\033[0m");

 dico d=create_dico();
 add_rec(d, "bateau", 6);
 add_rec(d, "bordeau", 7);
 add_rec(d, "brille", 6);
 add_rec(d, "brule", 5);
 add_rec(d, "ours", 4);
 add_rec(d, "ourse", 5);
 add_rec(d, "ourson",6);
 add_rec(d, "oursonne",8);

  dico d1=create_dico();
  add_rec(d1, "bateau", 6);
  add_rec(d1, "bordeau", 7);
  add_rec(d1, "brille", 6);
  add_rec(d1, "brule", 5);
  add_rec(d1, "ours", 4);
  add_rec(d1, "ourse", 5);
  add_rec(d1, "ourson",6);
  add_rec(d1, "oursonne",8);



  printf("\033[1;31m");
  printf("********* ON VERIFIE QUE LES DICOS SONT EGAUX***********\n");
  printf("\033[0m");
  printf("Est-ce que 2 dico sont égaux? => %d\n",equals(d,d1));


 printf("\033[1;31m");
 printf("*********AFFICHAGE PREFIXE DU DICO CREE***********\n");
 printf("\033[0m");
 print_prefix(d);

 printf("\033[1;31m");
 printf("*********COMPTONS LE NOMBRE DE MOTS DANS CE DICO***********\n");
 printf("\033[0m");
 printf("Ce dico contient %d mots \n", nb_words(d));


 printf("\033[1;31m");
 printf("*********AJOUTONS NOS PRENOMS DE MANIERE RECURSIVE A CE DICO \n");
 printf("\033[0m");

 add_rec(d,"jiayun",6) ;
 add_rec(d,"corinne",7) ;

 printf("\033[1;31m");
 printf("*********ESSAYONS DE RAJOUTER UNE NOUVELLE FOIS NOS PRENOMS DE MANIERE RECURSIVE A CE DICO \n");
 printf("\033[0m");

 add_rec(d,"jiayun",6) ;
 add_rec(d,"corinne",7) ;

 printf("*********AFFICHAGE PREFIXE DU DICO MODIFIE ***********\n");
 print_prefix(d);


 printf("\033[1;31m");
 printf("*********COMPTONS A NOUVEAU LE NOMBRE DE MOTS DANS CE DICO***********\n");
 printf("\033[0m");
 printf("Ce dico contient %d mots \n", nb_words(d));


 printf("\033[1;31m");
 printf("*********3.SUPPRIMONS LE MOT BATEAU DE MANIERE RECUSRSIVE  \n");
 printf("\033[0m");

 remove_rec(d,"bateau", 6);
 printf("*********AFFICHAGE PREFIXE DU DICO MODIFIE ***********\n");
 print_prefix(d);

 printf("\033[1;31m");
 printf("*********SUPPRIMONS UNE NOUVELLE FOIS LE MOT BATEAU DE MANIERE RECUSRSIVE  \n");
 printf("\033[0m");
 remove_rec(d, "bateau", 6);

 printf("Est-ce que les 2 dicos sont toujours égaux? => %d\n",equals(d,d1));

 printf("*********COMPTONS A NOUVEAU LE NOMBRE DE MOTS DANS CE DICO***********\n");
 printf("Ce dico contient %d mots \n", nb_words(d));


 printf("\033[1;31m");
 printf("*********4.AJOUTEZ LE MOT QUE VOUS VOULEZ  \n");
 printf("\033[0m");

 printf("Quelle est la longeur de votre mot ?\n");
 unsigned int n = 0 ;
 scanf("%d",&n );

 printf("veuillez entrer le mot de votre choix \n");
 char * word = malloc( n*sizeof(char)) ;

 scanf("%s",word );

 printf("Vérifions que le mot est de la bonne longueur \n" );

 while  (strlen(word )!= n ){
   printf("le mot n'est pas de la bonne longueur, veuillez entrer un autre mot\n");
   scanf("%s",word );
 }

 printf("Le mot est de la bonne longueur \n");
 add_rec(d,word,n) ;
 printf("*********AFFICHAGE PREFIXE DU DICO MODIFIE ***********\n");
 print_prefix(d);


 printf("\033[1;31m");
 printf("*********5.AFFICHONS LES MOTS DU DICO*********\n");
 printf("\033[0m");

 printf("Ce dico contient %d mots \n", nb_words(d));
 printf("ces mots sont : ");
 print_dico(d)  ;

}
