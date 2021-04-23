#include	<stdio.h>

int slen(char *s){
	int count = 0;
	while(*(s+count) != 0x00){
		count++;
	}
	return count;
}

int scompare(char *s1,char *s2){
	int  rtc = 0;
	int  len1;
	int  len2;
	int  i;

	len1 = slen(s1);
	len2 = slen(s2);

	if(len1 == len2){
		for(i=0;i<len1;i++){
			if(*(s1+i) != *(s2+i)){
				rtc = -1;
				break;
			}
		}
	}
	else{
		rtc = -1;
	}

	return rtc;
}

int main(){
	char	s1[128];
	char	s2[128];
	int		rtc;

	printf("s1 : ");
	scanf("%s",s1);

	printf("s2 : ");
	scanf("%s",s2);

	rtc = scompare(s1,s2);
	if(rtc == 0){
		printf("%s : %s --- 同じ内容です。\n",s1,s2);
	}
	else{
		printf("%s : %s --- 異なる内容です。\n",s1,s2);
	}

	return 0;
}
