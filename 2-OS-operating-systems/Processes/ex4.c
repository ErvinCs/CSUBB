#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>



int main()
{
    int limit = 5;
    printf("pid=%d, ppid=%d\n", getpid(), getppid());

    int pid = getpid();
    int i = 0;
    while (pid % 2 == 0 && i < limit)
    {
        printf("%d. parent=%d; child=%d\n", i, getppid(), getpid());
        fork();
        pid = getpid();
        i += 1;
    }

    return 0;
}
