//  Ici, construction du teste sur les fonctions: nb_children, nb_nodes et height
//  et eventuellement sur les fonctions creer_dico, destroy_dico et print_prefix


#include "fonction_base.h"


int main(void){

    // 1. Si le dictionnaire n'est pas encore créé,
        // RESULTAT ATTENDU => affiche "le dico n'est pas créé"
    printf("\033[1;31m");
    printf("*********1. LE DICO N'EST PAS CRÉÉ ***********\n");
    printf("\033[0m");
    dico d=NULL;
    print_prefix(d);
    printf("\n");

    // 2. Si le dico est créé mais qu'il est vide
        // RESULTAT ATTENDU => on affiche rien avec print_prefix, nb_nodes=0 et height=0
    printf("\033[1;31m");
    printf("*********2. LE DICO EST CRÉÉ MAIS VIDE ********\n");
    printf("\033[0m");
    d=create_dico();
    print_prefix(d);
    printf("Le nombre de noeuds du  dictionnaire est: %d\n",nb_nodes(d));
    printf("La hauteur est: %d\n",height(d));
    printf("\n");


    // 3. Le dico est rempli avec un noeud 'a'
      // RESULTAT ATTENDU => on affiche 'a' avec print_prefix, nb_nodes=1, height=1 et nb_children=0
    printf("\033[1;31m");
    printf("****3. LE DICO EST REMPLI AVEC UN NOEUD 'a' ****\n");
    printf("\033[0m");
    tree t=creer_node('a',true);
    d[get_index(t->first)]=t;
    print_prefix(d);
    printf("Le nombre de noeuds du dictionnaire est: %d\n",nb_nodes(d));
    printf("La hauteur  est: %d\n",height(d));
    printf("Le nombre de fils du noeud est: %d\n",nb_children(t));
    printf("\n");



    printf("\033[1;31m");
    printf("****4. LE DICO EST REMPLI AVEC 2 NOEUDS 'a' ET 'z'****\n");
    printf("\033[0m");
      // R;A=> affiche 'a' et 't*' ,  nb_nodes=2, height=1 et nb_children=0
    tree t1=creer_node('z',true);
    d[get_index(t1->first)]=t1;
    print_prefix(d);
    printf("Le nombre de noeuds du dictionnaire est: %d\n",nb_nodes(d));
    printf("La hauteur  est: %d\n",height(d));
    printf("Le nombre de fils du noeud est: %d\n",nb_children(t));
    printf("\n");




    printf("\033[1;31m");
    printf("****6. DESTROY DU DICTIONAIRE****\n");
    printf("\033[0m");
    destroy_dico(&d);
    print_prefix(d);

}
