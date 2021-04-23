#include	<stdio.h>

int order(int *p,int v,int count){
	int n = 1;
	int i;

	for(i=0;i<count;i++){
		if(*(p+i) < v){
			n++;
		}
	}

	return n;
}

int center(int *p){
	int count = 11;
	int n;
	int i;
	int v;
	int o;

	n = (count / 2) + (count % 2);
	for(i=0;i<count;i++){
		o = order(p,*(p+i),count);
		if(o == n){
			v = *(p+i);
		}
	}

	return v;
}

int main(){
	int array[] = {0,1,2,3,4,7,8,9,10,5,6};
	int n;
	int i;

	for(i=0;i<11;i++){
		n = order(array,array[i],11);
		printf("%d : %d\n",array[i],n);
	}

	n = center(array);
	printf("CENTER : %d\n",n);

	return 0;
}
