#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>
#include <time.h>
#include <sys/wait.h>

#define N 5

pthread_mutex_t m = PTHREAD_MUTEX_INITIALIZER;

int g;

int readVal()
{
    int number;
    printf("Enter number: ");
    scanf("%d", &number);
 
    return number; 
}

int randNum()
{
    srand(time(NULL));
    return rand() % 10;
}

void* f(void* p)
{
    int* i = (int*)p;
    //printf("Launched thread<%d>\n", *i);
    while(g > 0)
    {
        
        int x = randNum();
        int oldg = g;

        pthread_mutex_lock(&m);
        g = g - x;
        pthread_mutex_unlock(&m);

        //printf("pid=%d",getpid());
        printf("Thread<%d> decrementeaza <%d> cu <%d> si ramanae <%d>.\n", *i, oldg, x, g);
        
        //sleep(1);
    }

    return p;
}

int main(int argc, int argv[])
{
    //g = argv[1];
    g = readVal();

    pthread_t th[N];
    int i;
    for(i = 0; i < N; i++)
    {
        printf("i=%d\n", i);
        pthread_create(&th[i], NULL, f, &i);
    }

    for(i = 0; i < N; i++)
        pthread_join(th[i], NULL);

    
    pthread_mutex_destroy(&m);

    return 0;
}
