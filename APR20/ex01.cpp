#include	<stdio.h>
class DATA{
	int  x;
	int  y;
public:
	void set(int x,int y){
		this->x = x;
		this->y = y;
	}
	void print(){
		printf("%d,%d\n",this->x,this->y);
	}
};
int main(){
	DATA d1;
	DATA d2;

	d1.set(100,100);
	d2.set(200,200);
	
	d1.x = 0;

	d1.print();
	d2.print();
	return 0;
}
