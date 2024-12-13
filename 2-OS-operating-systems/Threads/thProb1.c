#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>
#include <time.h>
#include <sys/wait.h>

#define N 100
#define MAX_BRIDGE 10

pthread_mutex_t m1 = PTHREAD_MUTEX_INITIALIZER;

int semaphore = 0;  //0 or 1
int onBridge = 0;   //cars on the bridge at at any given moment

struct car
{
    int id;     //integer
    int dir;    //0 or 1
};

void* traficCon(void* p)
{
    while(1)
    {
        sleep(5);
        if (semaphore == 0)
            semaphore = 1;
        else
            semaphore = 0;
        //printf("Semaphore=%d\n", semaphore);
    }
}

int randomTime()
{
    srand(time(NULL));
    int t = rand() % 500 + 500;
    return t;
}

void* function(void* p)
{
    struct car* c = (struct car*)p;
    //printf("Semaphore=%d, C->DIR=%d\n", semaphore, c->dir);
    int cond = 1;
    while(cond){
    if (semaphore == 1 && c->dir == 1)
    {
        pthread_mutex_lock(&m1);
        //printf("in: %d\n", semaphore);
        int idc = c->id;
        int dirc = c->dir;
        int t = randomTime();
        //printf("Sleep=%d\n", t);
        usleep(t);
        onBridge--;
        printf("Car <%d> has passed, coming from direction <%d>\n", idc, dirc);
        cond = 0;
        pthread_mutex_unlock(&m1);
    }
    else if (semaphore == 0 && c->dir == 0)
    {
        pthread_mutex_lock(&m1);
        //printf("in: %d\n", semaphore);
        int idc = c->id;
        int dirc = c->dir;
        int t = randomTime();
        //printf("Sleep=%d\n", t);
        usleep(t);
        onBridge--;
        printf("Car <%d> has passed, coming from direction <%d>\n", idc, dirc);
        cond = 0;
        pthread_mutex_unlock(&m1);
    }
    }
    return p;
}



int main()
{
    pthread_t controller;
    pthread_create(&controller, NULL, traficCon, NULL);

    pthread_t th[N];
    struct car cars[N];
    int i;

    for(i = 0; i < N; i++)
    {
        srand(time(NULL));
        cars[i].id = i;
        cars[i].dir = rand() % 2;
    }

    for(i = 0; i < N; i++)
    {
        while (onBridge > 10)
            wait(NULL);
        onBridge++;
        int carid = cars[i].id;
        printf("Launched car.<%d>.\n", carid);
        pthread_create(&th[i], NULL, function, &cars[i]);    
    }

    for(i = 0; i < N; i++)
        pthread_join(th[i], NULL);


    pthread_cancel(controller);
    pthread_mutex_destroy(&m1);

    return 0;
}
