#include	<stdio.h>

int slength(char *s){
	int  len = 0;
	while(*(s+len) != 0x00){
		len++;
	}
	return len;
}

int main(){
	char  str[128];
	int   len;

	printf("INPUT STRING : ");
	scanf("%s",str);

	/* 文字列の長さを調べる. */
	len = slength( str );
	printf("[%s]LENGTH : %d\n",str,len);

	return 0;
}
