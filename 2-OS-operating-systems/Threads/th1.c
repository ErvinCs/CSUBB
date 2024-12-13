#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

void printMessage(void *p)
{
    char* message = (char*)p;
    printf("%s\n", message);
}

int main()
{
    pthread_t thread1, thread2;
    
    const char* message1 = "Thread1 - Message";
    const char* message2 = "Thread2 - Message";

    int iret1, iret2;

    iret1 = pthread_create(&thread1, NULL, printMessage, (void*)message1);
    if (iret1)
    {
        fprintf(stderr, "Error - pthread_create() return code: %d\n", iret1);
        exit(EXIT_FAILURE);
    }

    iret2 = pthread_create(&thread2, NULL, printMessage, (void*)message2);
    if (iret2)
    {
        fprintf(stderr, "Error - pthread_create() return code: %d\n", iret2);
        exit(EXIT_FAILURE);
    }

    wait();

    pthread_join( thread1, NULL);
	pthread_join( thread2, NULL);
	exit(EXIT_SUCCESS);

    pthread_mutex_destroy(&mutex);

    return 0;
}
