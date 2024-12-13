#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>

int main(int argc, char* argv)
{
    int res;
    res = fork();
    
    if (res == -1)
    {
        perror("Child process creation failed!\n");
        exit(1);
    }
    else if (res == 0)
    {
        printf("Child process PID= %d", getpid());   
    }
    else
    {
        printf("Parent process PID=%d; Child process PID=%d.\n", getpid(), res);
    }

    return 0;
}
