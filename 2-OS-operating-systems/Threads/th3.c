#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <pthread.h>

#define S 255

pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t mainMutex = PTHREAD_MUTEX_INITIALIZER;
char* s;

void* printContent(void* p)
{
    printf("Thread no: %ld\n", pthread_self());

    pthread_mutex_lock(&mutex);

    char* s = (char*) p;
    int i = 0;
    int len = strlen(s+i);
    printf("printContent=%s\nlen=%d\n", s, len);
    while(strlen(s+i) > 0)
    {
        printf("%c", s[i]);
        i++;
    }    
    printf("\n");

    pthread_mutex_unlock(&mutex);

    return p;
}

int main(int argc, char *argv[])
{
    pthread_t threads[argc];
 
    time_t t1 = time(NULL);

    int i;
    for(i = 1; i < argc; i++)
    {
        //s = (char*)malloc(sizeof(char)*S);
        //strcpy(s, argv[i]);
        //printf("argv[%d]=%s\n", i, s);
        pthread_create(&threads[i], NULL, printContent, argv[i]);

    }

    for(i = 1; i < argc; i++)
        pthread_join(threads[i], NULL);

    time_t t2 = time(NULL);
    double x = difftime(t2, t1);
    printf("TotalExecutionTime=%.0f\n", x);
        
    pthread_mutex_destroy(&mutex);

    return 0;
}
