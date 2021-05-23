#include "fonction_base.h"


iterator * empile_stack(iterator * it,pile p){
  //on verifie si le stack est vide
    //si vide, on ajoute la pile deirectement sur la partage_tete
    //sinon; on decale un pas => stack[i+1]= stack[i] commence par la derniere pile
    assert(it!=NULL);
    assert(p!=NULL);
    int i;
    if(it->index_stack>0){
      for(i=(it->index_stack-1);i>=0;i--){
        it->stack[i+1]=it->stack[i];
      }
    }
      it->stack[0]=p;
      it->index_stack++;
  return it;
}


pile  depile_stack(iterator * it){
  // cette fonction dépile stacke et renvoie le haut de la pile
  assert(it!=NULL);
  pile tete= it->stack[0];
  int j;
  for(j = 0 ; j < it->index_stack-1 ; j++){
    it->stack[j] = it->stack[j+1] ;
  }
  it->index_stack--;
  return tete;
}


iterator * start_iterator(dico d){
  //allocation d'un iterator,  un tableau de pile et un tableau de char
    //initialiser par empiler tous les têtes du mot (de a à z)
  if(d==NULL) return NULL;
  iterator *it=malloc(sizeof(iterator));
  it->stack=malloc(nb_nodes(d)*sizeof(pile));
  it->word=malloc(height(d)*sizeof(char));
  it->index_stack=0;

  int i;
  for(i=0;i<NB_KEYS;i++){
    tree t=d[i];
    if(t!=NULL ){
      //si la lettre existe, on empile dans le stack
      pile p=malloc(sizeof(pile));
      p->t=t;
      p->index_word=0;
      empile_stack(it,p);
    }
  }
  return it;
}




char * next (iterator * it){
  // start with  the first stack
  assert(it!=NULL);
  pile tete;
  int i,eow;
  dico d;
  do{
    //on depile de la premiere de stack
      //on enleve un assert si tete.t est null
     tete=depile_stack(it);
     assert(tete->t!=NULL);
    //Insertion de la lettre recuperée dans la chaine de char
    //Scruptination et recuperation de l'enfant de char recuperée
    it->word[tete->index_word]= (tete->t)->first;
    d=(tete->t)->children;
    assert(d!=NULL);
      for(i=0;i<NB_KEYS;i++){
        if((d[i])!=NULL) {
          pile to_be_insert=malloc(sizeof(pile));
          to_be_insert->t=d[i];
          to_be_insert->index_word=(tete->index_word)+1;
          empile_stack(it,to_be_insert);
        }
      }
      eow=tete->t->end_of_word;
      free(tete);
    // si le mot recuperée est la fin du mot sort du programme
  }while(eow==0);

  return it->word ;
}



bool has_next(iterator * it){
    if(it->index_stack==0) {
      return false;
    }
    else return true;
}


void destroy_iterator(iterator ** it){
  if((*it)==NULL) return ;
  free((*it)->stack);
  free((*it)->word);
  free(*it);
}
