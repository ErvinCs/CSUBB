#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <time.h>
#include <sys/wait.h>
#include <unistd.h>

#define N 5
#define VS 1000
#define UB 2000
#define LB 1

//NOT WORKING

pthread_mutex_t m1 = PTHREAD_MUTEX_INITIALIZER;
int v[VS];
int generated = 0;
int counter = 0;
int printer = 0;

int generate()
{
    srand(time(NULL));
    generated++;
    counter++;
    return rand() % UB + LB;
}



void* function(void* p)
{
    while(generated < VS){
    pthread_mutex_lock(&m1);
    //Sorted insertion
    v[counter] = generate();
    pthread_mutex_unlock(&m1);
    }
    return p;
}

int main()
{
    pthread_t th[N];
    
    int i;
    for(i = 0; i < N; i++)
        pthread_create(&th[i], NULL, function, NULL);

    while(generated < VS)
    {
        if(counter == 10)
        {
            pthread_mutex_lock(&m1);
            counter = 0;
            for(i = printer; i < printer + counter; i++)
            {
                printf("v[%d]=%d\n", i, v[i]);
            }
            pthread_mutex_unlock(&m1);
        }
    }
    

    for(i = 0; i < N; i++)
        pthread_join(th[i], NULL);

    pthread_mutex_destroy(&m1);

    return 0;
}
