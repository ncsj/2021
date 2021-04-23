#include	<stdio.h>

typedef	struct _POINT{
	int  x;
	int  y;
}POINT;

void  set(POINT *p,int x,int y){
	p->x = x;
	p->y = y;
}

void  print(POINT *p){
	printf("%d,%d\n",p->x,p->y);
}

int main(){
	POINT  p1;
	POINT  p2;

	set(&p1,100,100);
	set(&p2,123,234);

	print(&p1);
	print(&p2);

	return 0;
}
