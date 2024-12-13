#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main()
{
    int i, j, k;

    print("\n");
    
    i = fork();

    if ((j = fork()))
    {
        k = fork();
    }

    printf("i=%d, j=%d, k=%d\n", i, j, k);

    return 0;
}
