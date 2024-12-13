#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#define N 6
//Rand B
/*
Sa se scrie un program care creeaza 5 threaduri 
care primesc fiecare ca parametrul numarul de ordine cu care este creat. Threadurile afiseaza prin rotatie cate un cuvant dintre cele primite de program 
din linia de comanda, pana cand sunt afisate toate (pot fi oricate!). 
Mesajul cu care este afisat un cuvant trebuie sa fie de forma "Threadul <X>: <cuvant>".
*/
pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;
char* word;
int pos = 0;

void* printWord(void* p)
{
    pthread_mutex_lock(&mutex);
    word = (char*) p;
    printf("Thread<%d>: %s\n", pos, word);
    int i = 0;
    /*while ((i + N) < globalArgs)
    {
        word = (char*)(p + i + N);
        printf("Thread<%d>: %s\n", pos, word);
        i += 1;
    }*/
    pos += 1;
    pthread_mutex_unlock(&mutex);
    return p;
}

int main(int argc, char* argv[])
{
    pthread_t threads[N];

    int i;
    for(i = 1; i <= N & i < argc; i++)
        pthread_create(&threads[i], NULL, printWord, argv[i]);

    for(i = 1; i <= N & i < argc; i++)
        pthread_join(threads[i], NULL);

    pthread_mutex_destroy(&mutex);
    
    return 0;
}
