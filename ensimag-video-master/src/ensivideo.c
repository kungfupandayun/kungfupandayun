#include <stdio.h>
#include <unistd.h>
#include <assert.h>
#include <SDL2/SDL.h>
#include <pthread.h>

#include "stream_common.h"
#include "oggstream.h"


int main(int argc, char *argv[]) {
    int res;

    if (argc != 2) {
	fprintf(stderr, "Usage: %s FILE", argv[0]);
	exit(EXIT_FAILURE);
    }
    assert(argc == 2);


    // Initialisation de la SDL
    res = SDL_Init(SDL_INIT_VIDEO|SDL_INIT_AUDIO|SDL_INIT_EVENTS);
    atexit(SDL_Quit);
    assert(res == 0);

    // start the two stream readers
    pthread_t tid1 , tid2;
    void* status;
    if(pthread_create(&tid1,NULL,theoraStreamReader,(void*)argv[1]) == -1) {
      perror("pthread_create");
    }

    if(pthread_create(&tid2,NULL,vorbisStreamReader,(void*)argv[1]) == -1) {
      perror("pthread_create");
    }
    printf("on a créé les 2 threads\n");
    // wait audio thread
    if (pthread_join(tid2,&status)) {
      perror("pthread_join");
    }


      printf("on a créé les 2 threads\n");
    // 1 seconde de garde pour le son,
    sleep(1);

    // tuer les deux threads videos si ils sont bloqués
    pthread_cancel(tid1);
    pthread_cancel(tid2);
    // attendre les 2 threads videos

    /*mon code*/
    // if (pthread_join(tid1,&status)) {
    //   perror("pthread_join");
    // }
    // if (pthread_join(tid2, &status)) {
    //   perror("pthread_join");
    // }
    // printf("on attend les 2 threads\n");

    exit(EXIT_SUCCESS);
}
