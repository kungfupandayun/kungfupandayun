#include <string.h>
#include <stdbool.h>
#include <stdlib.h>
#include <stdio.h>
#include <assert.h>

#define NB_KEYS 26
#define NB_LETTRESMAX 27


typedef struct _node {
    char first;
    bool end_of_word;
    struct _node ** children;
}node;

typedef node * tree;
typedef node ** dico;

struct iterator_info {
    tree t;
    int index_word;
};

struct _iterator {
    char * word;
    struct iterator_info ** stack;
    int index_stack;
};

typedef struct iterator_info * pile;
typedef struct _iterator iterator;

unsigned get_index(char c);
char get_char(unsigned index);

/** construction / destruction */
tree creer_node(char c,bool eow);
dico create_dico();
void destroy_dico(dico * d);


/** mesures */
unsigned nb_children(tree t);
unsigned nb_nodes(dico d);
unsigned height(dico d);

/** routine d'impression : en prefixe */
void print_prefix(dico d);

/** egalite structurelle */
bool equals(dico d1, dico d2);


/** algorithmes itératifs **/
bool contains_iter(dico d, char * word, unsigned size);
bool add_iter(dico d, char * word, unsigned size);
bool remove_iter(dico d, char * word, unsigned size);

/** compter le nb de mot et les imprimer dans une ligne**/
unsigned nb_words(dico d);
void print_dico(dico d);


bool contains_rec(dico d, char* word , unsigned size);
bool add_rec(dico d   , char*word , unsigned size);
bool remove_rec (dico d , char* word , unsigned size);
unsigned int nb_words(dico d );


void print_dico(dico d );



iterator * start_iterator(dico d);
char * next (iterator * it);
bool has_next(iterator * it);
void destroy_iterator(iterator ** it);
