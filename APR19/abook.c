#include	<stdio.h>

#define		ON			1
#define		OFF			0

typedef struct _ADDRESS{
	int		flag;
	char	name[40];
	char	addr[100];
	char	tel[20];
	char	mail[50];
}ADDRESS;

int menu(){
	int  rtc;
	printf("1:LIST 2:EDIT 3:ADD 4:DEL  9:EXIT\n");
	printf("SELECT NUMBER > ");
	scanf("%d",&rtc);

	return rtc;
}

void print(ADDRESS *p){
	printf("%s : %s : %s : %s\n",p->name,p->addr,p->tel,p->mail);
}

void listup(ADDRESS *p,int count){
	int  i;

	if(count == 0){
		printf("--- NO DATA ---\n");
	}

	for(i=0;i<count;i++){
		if((p+i)->flag == ON){
			printf("[%d] ",(i+1));
			print(p+i);
		}
	}
}

void save(ADDRESS *p,int count){
	int i;
	FILE  *fp;				/* ファイルポインター */
	int n = 0;				/* 保存したデータを数えるための変数 */

	fp = fopen("abook.data","wb");
	if(fp != NULL){
		for(i=0;i<count;i++){
			if((p+i)->flag == ON){
				/* データをファイルへ出力する */
				fwrite(p+i,sizeof(ADDRESS),1,fp);
				n++;
			}
		}

		fclose(fp);		/* ファイルポインターを閉じる */
		printf("ADDRESS DATAs %d were saved.\n",n);
	}
}

int load(ADDRESS *p){
	FILE  *fp;
	ADDRESS  data;
	int   rtc;
	int  loop = ON;
	int  count = 0;

	fp = fopen("abook.data","rb");
	if(fp != NULL){
		printf("FILE IS OPENED.");
		while(loop == ON){
			rtc = fread(&data,sizeof(ADDRESS),1,fp);
			if(rtc == 1){
				if(data.flag == ON){
					print(&data);

					(p+count)->flag = ON;
					sprintf((p+count)->name,"%s",data.name);
					sprintf((p+count)->addr,"%s",data.addr);
					sprintf((p+count)->tel,"%s",data.tel);
					sprintf((p+count)->mail,"%s",data.mail);

					count++;
				}
			}
			else{
				loop = OFF;
			}
		}
		fclose(fp);
	}
	else{
		printf("--- NO DATA ---");
	}

	return count;
}

void edit(ADDRESS *p){
	printf("NAME : ");
	scanf("%s",p->name);

	printf("ADDR : ");
	scanf("%s",p->addr);

	printf("TEL : ");
	scanf("%s",p->tel);

	printf("MAIL : ");
	scanf("%s",p->mail);
}

void add(ADDRESS *p){
	p->flag = ON;
	edit(p);
}

void del(ADDRESS *p){
	int index;

	printf("DELETE : ");
	scanf("%d",&index);

	if(index <= 10){
		(p+index-1)->flag = OFF;
	}
}

void init(ADDRESS *p){
	int  i;
	for(i=0;i<10;i++){
		(p+i)->flag = OFF;
	}
}

int main(){
	ADDRESS array[10];
	int	 count = 0;
	int  loop = ON;
	int  rtc;
	int  index;

	init(array);
	count = load(array);

	/* 番兵のアルゴリズム */
	while(loop == ON){
		rtc = menu();
		switch(rtc){
		case 1:
			listup(array,count);
			break;
		case 2:
			printf("INPUT NUMBER > ");
			scanf("%d",&index);
			if(index <= count){
				edit(array+(index-1));
			}
			break;
		case 3:
			if(count < 10){
				add(&array[count]);
				count++;
			}
			break;
		case 4:
			del(array);
			break;
		case 9:
			loop = OFF;
			break;
		default:
			break;
		}
	}

	save(array,10);

	return 0;
}
