#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/stat.h>
#define N 100

/*
Scrieti un program C care creeaza un fiu careia ii trimite prin pipe un nume de fisier.
R1
Citit de la tastatura de parinte si fiul ii trimite inapoi dimensiunea acelui fisier.
*/


int main()
{
    int p2c[2], c2p[2], size;
    pipe(p2c);
    pipe(c2p);
    
    char* name = (char*)malloc(sizeof(char)*N);
    printf("FileName=");
    scanf("%s", name);

    if(access(name, F_OK ) < 0)
    {
        perror("Error");
        exit(1);
    }

    pid_t n = fork();
    if (n < 0)
    {
        perror("Could not create child.\n");
        exit(1);
    }

    if (n == 0)
    {
        close(p2c[1]);
        close(c2p[0]);
        
        struct stat st;
        stat(name, &st);
        int size = (int)st.st_size;
        write(c2p[1], &size, sizeof(int));
        
        close(c2p[1]);
        close(p2c[0]);

        exit(0);
    }

    read(c2p[0], &size, sizeof(int));
    printf("FileSize=%d bytes\n", size);
    
    close(p2c[1]);
    close(c2p[0]);
    close(c2p[1]);
    close(p2c[0]);

    free(name);
    
    return 0;
}
