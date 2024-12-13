#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#define N 100
int main()
{
    int a=7, b=3, s;
    char* string = (char*)malloc(sizeof(char)*N);
    int p2c[2], c2p[2];
    //Reading fd is on 0; writing fd is on 1
    pipe(p2c);
    pipe(c2p);
    
    int n = fork();
    if (n < 0)
    {
        perror("Could not create child.\n");
        exit(1);
    }
    
    
    if (n == 0)
    {   //child
        int x[2], sum;

        //Close the pipes that the child does not use
        close(p2c[1]);
        close(c2p[0]);

        //Read from the parent pipe; blocking
        read(p2c[0], &x, 2*sizeof(int));   
        sum = x[0] + x[1];
        strcpy(string, "This is a string.");
        //Write to the parent pipe
        write(c2p[1], &sum, sizeof(int));    
        write(c2p[1], string, sizeof(char)*100);

        //Close pipes when they are no longer used
        close(c2p[1]);
        close(p2c[0]);

        exit(0);
    }
    //I can either use else-branches or exit on the child's branch

    write(p2c[1], &a, sizeof(int));
    write(p2c[1], &b, sizeof(int));
    read(c2p[0], &s, sizeof(int));
    read(c2p[0], string, sizeof(char)*N);
    wait(0);    //Otherwise the child might become a zombie; dunno if it should be here or above write tho

    //Close the remaining pipes
    close(p2c[1]);
    close(c2p[0]);
    //These were closed in the child, but not in the parent
    close(c2p[1]);
    close(p2c[0]);

    //Good practice: check what a pipe returns    
    //Check man read 2
    //Check the number of items read; it won't always read everything; read until it does 
    //Reading continously after a fail (ex: read n values)
    /*
    int n;
    while (n < noOfExpectedValues)
        n += read(p[0], (char*)((size_t)buffer + n), size-n)
    */
    //Treat all exception cases
    //Check man write

    printf("sum=%d\n", s);
    printf("string=%s\n", string); 
    free(string);

    return 0;
}
