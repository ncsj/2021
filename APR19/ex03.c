#include	<stdio.h>

void scopy(char *s1,char *s2){
	int  i = 0;
	while(*(s1+i) != 0x00){
		*(s2+i) = *(s1+i);
		i++;
	}
	*(s2+i) = 0x00;
}

int main(){
	char  s1[128];
	char  s2[128];

	printf("S1 : ");
	scanf("%s",s1);

	/* s1の内容をs2へコピーする。 */
	scopy(s1,s2);

	printf("S2 : %s\n",s2);

	return 0;
}
