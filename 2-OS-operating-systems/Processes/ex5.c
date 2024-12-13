#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main()
{
    int pid;
    int i = 1;

    printf("\n");

    //1
    fork();
    //2
    if(pid = fork())
    {
        //4 processes, 1 here
        //Without the next 2 lines i'll have 5 processes in the end
        if(pid = fork())    
            fork();         
        //With the next 2 i will have 13 processes in the end
        fork();
        //9 total, 4 here
    }

    printf("parent=%d; child=%d\n", getppid(), getpid());
    //parent pid = child pid
    //child pid = 0


    return 0;
}
