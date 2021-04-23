#include	<stdio.h>

int check(char *s,char c){
	int count = 0;

	while(*s != 0x00){
		if(*s == c){
			count++;
		}
		s++;
	}

	return count;
}

int main(){
	char str[] = "This is a pen. The pen is black.";
	int count = 0;
	char  c;

	printf("INPUT c : ");
	scanf("%c",&c);

	printf("CHECK [%c] @ \"%s\"\n",c,str);

	count = check(str,c);
	printf("COUNT [%c] : %d\n",c,count);
	
	return 0;
}
