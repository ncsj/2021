#include	<stdio.h>

int max(int *p){
	int  r;
	int  i = 0;
	r = *p;
	while(*(p+i) != -1){
		if(r < *(p+i)){
			r = *(p+i);
		}
		i++;
	}
	return r;
}

int min(int *p){
	int r;
	int  i = 0;
	r = *p;
	while(*(p+i) != -1){
		if(r > *(p+i)){
			r = *(p+i);
		}
		i++;
	}
	return r;
}

int ave(int *p){
	int  r = 0;
	int  sum = 0;
	int  count = 0;
	while(*(p+count) != -1){
		sum = sum + *(p+count);
		count++;
	}
	r = sum / count;
	return r;
}

int main(){
	/* 以下の配列にて、-1は終端を意味する。 */
	int  array[] = {1,3,5,7,9,0,2,4,6,8,-1};
	int  n;

	n = max(array);				/* 最大値 */
	printf("MAX : %d\n",n);

	n = min(array);				/* 最小値 */
	printf("MIN : %d\n",n);

	n = ave(array);				/* 平均値 */
	printf("AVE : %d\n",n);

	return 0;
}
