#include <stdio.h>

int compare(int term1, int term2);

int main()
{
	FILE* file;

	int term1 = -1;
	int term2 = -1;
	int max   = -1;
	int length;

	printf("Enter string length: ");
	scanf("%d", &length);

	printf("Enter number: ");
	scanf("%d", &term1);

	for (int i = 1; i < length; i++) 
	{
		printf("Enter number: ");
		scanf("%d", &term2);
		max = compare(term1, term2);
		term1 = term2;
	}

	printf("Maximum = %x\n", max);

	file = fopen("output.txt", "w");
	if (file == NULL)
	{
		printf("Could not open output.txt!");
		return 0;
	}
	fprintf(file, "Maximum = %x\n", max);
	fclose(file);

	return 0;
}