#include "fonction_base.h"

void mark_end_of_word(node* t ){
  t->end_of_word = true ;
}

bool contains_rec_tree(tree t  , char* word , unsigned size) {
  //size est la taille du mot
  assert(strlen(word)==size);

  if(size ==0) {printf("mot vide\n");return false; }
  //si l'arbre est vide , on retourne false
  if( t== NULL ) return false ;

  // si la racine de l'arbre ne corrsepond pas à l a premiere lettre, on retourne faux
  if(t->first != *word) return false ;


  // si la premiere lettre corrsepond à la racine de l'arbe et le mot ne contient qu'une seule lettre
  if(t->first == *word && size == 1 && t->end_of_word) return true ;


  if (t->first == *word && size != 1){
  int i = 0 ;
  for (i=0 ; i<NB_KEYS ; i++){
      if(contains_rec_tree(t->children[i] , word +1 , size -1 ) ) return true ;
  }

}
  return false ;
}



bool contains_rec(dico d, char* word , unsigned size){
  assert(strlen(word)==size);
  int i = 0 ;
  for(i=0 ; i<NB_KEYS ;i++){
    if ( contains_rec_tree( *(d+i),word , size ) ) return true ;
  }

  return false ;
}

bool add_rec(dico d   , char*word , unsigned size){
  assert(strlen(word)==size);
  assert(strlen(word)<NB_LETTRESMAX );
  if (d == NULL) {return false ;}

  if(size == 0 ) return true ;
  //on cherche l'arbre commeçant par la preière lettre du mot



  //Si il n'y a pas d'arbre correspondant , on créé l'arbre auquel on ajoutear au fur et à mesure le reste du mot
  if(*( d + get_index(*word) )== NULL) {
    *( d + get_index(*word) ) = creer_node(*word , size == 1) ;
    if(size == 1){
          return true ;

    }
    return add_rec ( (*( d + get_index(*word) )) ->children , word +1 , size-1) ;

  };

// si la premiere lettre est deja presente dans le dico...
  if(*( d + get_index(*word) )!=NULL){
      // si le mot n'est pas composé d'une seule lettre on rajoute le reste du mot à t
      if( size !=1){
        return add_rec ( (*( d + get_index(*word) ))->children , word +1 , size-1)  ;
      }
      else if ((*( d + get_index(*word) ))->first == *word && size==1){

        if( (*( d + get_index(*word) ))->end_of_word ){
          printf("mot deja present\n");
          return true ;
        }
        mark_end_of_word(*( d + get_index(*word) ));

        return true ;
      }


  }



      return true ;

}


bool remove_rec (dico d , char* word , unsigned size){
  assert(strlen(word)==size);
  int i = 0 ;
  if (d == NULL) return false ;

  tree t = *( d + get_index(*word) ) ;
  if( t == NULL ) {printf("mot non présent\n"); return false ; }

  else {
    // la premiere lettre est présente
    if(size == 1){
      t->end_of_word  = 0 ;
      for(i=0 ; i<NB_KEYS ; i++){
        if (t->children[i] != NULL) { return true ;}
      }

      *(( d + get_index(*word) )) = NULL;

    }

    else if (size != 1){
      remove_rec(t->children,word+1 , size-1 ) ;
      for(i=0 ; i<NB_KEYS ; i++){
        if (t->children[i] != NULL) { return true ;}
      }
      *(( d + get_index(*word) )) = NULL;
    }
  }
  return true ;
}



unsigned int nb_words(dico d ){
  if(d == NULL) return 0 ;

  int n = 0 ;

  int i ;
  tree t ;
  for(i=0 ; i<NB_KEYS ; i++){
    t  = d[i] ;
    if(t !=NULL) {
      if(t->end_of_word) {n ++ ;}

      n = n + nb_words(t->children) ;
    }
  }

  return n ;
}


bool end_of_tree(tree t ){
  int i ;
  for(i=0 ;  i<NB_KEYS ; i++){
    if( t->children[i] != NULL )
    {return false ; }
  }

  return true ;
}


void print_tree(tree t   , char**word , int j ){
  //dans word on sauvegarde le début du mot
  if (t==NULL) { return ; }
  int i = 0 ;
  char c ;
  if ( t != NULL){
      c  = t->first ;
      if (end_of_tree(t)){
      *(*word+j) = c;
      printf("%s ; ", *word ) ;
  }
    if(!end_of_tree(t)){
      *(*word+j) = c;
        j++ ;
        //si on est à la fin du mot , on l'affiche
        if(t->end_of_word){
          printf("%s ; ", *word ) ;
        }
    }

    for(i=0 ; i<NB_KEYS ;i++)
    {
      print_tree (t->children[i] , word, j);
    }

    for(i=0 ; i<NB_KEYS ;i++) {  *(*word+j) = 0 ;}

}

}

void print_dico(dico d ){
  int i = 0 ;
  int n = 0 ;
  n = NB_LETTRESMAX ;
  char *word = malloc(n*sizeof(char)) ;

  if(d == NULL) {return ; }
  else {
    for(i=0 ; i<NB_KEYS ;i++){
      // on va afficher chaque arbre
        tree t  ;
        t = d[i];
        print_tree(t, &word, 0 ) ;
    }
  free(word);
}


}
