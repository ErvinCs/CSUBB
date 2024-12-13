#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

#define N 10

int counter = 0;
int sum = 0;
pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;

void* increment(void* p)
{
    printf("Thread no: %ld\n", pthread_self());
    pthread_mutex_lock(&mutex);
    counter++;
    pthread_mutex_unlock(&mutex);
    return p;
}

void* sumUp(void* p)
{
    printf("Thread no: %ld\n", pthread_self());
    pthread_mutex_lock(&mutex);
    int i;
    for(i = 0; i < 100; i++)
    {
        counter++;
        sum = sum + counter;
    }
        
    pthread_mutex_unlock(&mutex);
    return p;
}

int main()
{
    pthread_t threads[N];

    int i;
    for(i = 0; i < 10; i++)
        pthread_create(&threads[i], NULL, sumUp, NULL);
        
    for(i = 0; i < 10; i++)
        pthread_join(threads[i], NULL);

    printf("Incr=%d\n", counter);
    printf("Sum=%d\n", sum);

    pthread_mutex_destroy(&mutex);

    return 0;
}

