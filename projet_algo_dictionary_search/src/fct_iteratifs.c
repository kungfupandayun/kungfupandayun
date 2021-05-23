#include "fonction_base.h"

//CHERCHER DANS LE DICO EXISTANCE D'UN MOT
bool contains_iter(dico d, char * word, unsigned size){
  unsigned i;
  dico pt=d;
  assert(word!=NULL);
  assert(strlen(word)==size);
  if(d==NULL) return false;
  for(i=0;i<size;i++){
    if(!(pt[get_index(word[i])])) return false;
    if(i==size-1){
      //  La lettre existe ssi la derniere lettre est marqué avec "end of word"
       if(pt[get_index(word[i])]->end_of_word==true) return true;
       else return false;
    }
    pt=pt[get_index(word[i])]->children;
  }
  return true;
}

//AJOUTER UN MOT DANS LE DICO
bool add_iter(dico d, char * word, unsigned size){
  assert((d)!=NULL);
  assert(word!=NULL);
  assert(strlen(word)==size);
  dico pt=d;
  //si le mot est déja dans le dico, retourne directe
  if(contains_iter((d),word,size)) {
  printf("ce mot existe déjà dans le dictionnaire\n");
  return false;
  } else{
        unsigned i;
        for(i=0;i<size;i++){
          //on verifie si la lettre dans le rang existe déja
            //si oui on fait rien
            //si non on cree un nouveau noeud
          if((pt[get_index(word[i])])==NULL){
            tree a=creer_node(word[i],false);
            pt[get_index(word[i])]=a;
          }
          if(i==size-1) pt[get_index(word[i])]->end_of_word=true;
          //on avance au rang suivant
          pt=pt[get_index(word[i])]->children;
        }
  }
  return true;
}

//RETOURNE SI UNE LETTRE EST PARTAGE APART LA LETTRE CIBLE
//ENLEVER UN MOT DE DICO

bool remove_iter(dico d, char * word, unsigned size){
  if(!contains_iter(d,word,size)){
    printf("ce mot n'exsite pas\n");
    return false;
  }else{
    unsigned i,j;
    dico pt=d;
    j=1;
    while(j<=size){
      pt=d;
      // j enregistre le rang du dernièr characatèr acquité
      // i est utilisé pour parcourir l'arbre jusquèà le dernièr char acquité
      for(i=0;i<(size-j);i++){
        pt=pt[get_index(word[i])]->children;
      }
      // pt est pointé sur la feuille ou la lettre est situé
        //pour acceder à la lettre on doit faire pt[get_index(word[i])]->first
      //si la lettre a encore des fillules, on le touche pas, assure que la lettre n'est plus fin du mot et return directe
      if(nb_nodes(pt[get_index(word[i])]->children)>0){pt[get_index(word[i])]->end_of_word=false; return true;}
      //si il exist un deuxième mot qui cache dans le mot , on retourne directe
        //par exemple : ourson et oursonne
      else if(j>=2 && pt[get_index(word[i])]->end_of_word==true )return true;
      else{
        //sinon on free la table d'enfant qui a été reservé pendant l'allocation du noeud
        destroy_dico(&(pt[get_index(word[i])]->children));
        //on free le noeud
        //et met l'addresse dans la table à zero
        free(pt[get_index(word[i])]);
        pt[get_index(word[i])]=NULL;
      }
      j++;
    }
    return true;
  }
}
