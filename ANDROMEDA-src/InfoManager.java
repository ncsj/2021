import	java.util.ArrayList;
import	java.io.FileInputStream;
import	java.io.ObjectInputStream;
import	java.io.FileOutputStream;
import	java.io.ObjectOutputStream;
import	java.io.EOFException;

/**
  このクラスは地図データマネージャーの機能を持つをクラス
  ArrayListからデータの読み込みや保存を行う

  @author	Takuya Nakaigwa
  @since	1.0
**/
public class InfoManager{
	ArrayList <InfoData> info_list = new ArrayList <InfoData> ();

	public void add(InfoData data){
		info_list.add(data);
	}

/**
	地図データから情報を取得するメソッド
  */
	public InfoData get(int index){
		InfoData data = null;
		if(index < info_list.size()){
			data = info_list.get(index);
		}
		return data;
	}

/**
  ファイルに保存した地図データを読み込むメソッド
  */
	public InfoData [] load(String file){
		InfoData [] datas = null;

		try{
			FileInputStream   fin = new FileInputStream(file);
			ObjectInputStream is  = new ObjectInputStream(fin);

			boolean flag = true;
			while(flag){
				try{
					Object o = is.readObject();
					info_list.add((InfoData)o);
				}
				catch(EOFException ee){
					System.out.println("end Of File");
					flag = false;
				}
				catch(Exception ee){
					System.out.println(ee.toString());
				}
			}

			is.close();
			fin.close();

			datas = new InfoData [info_list.size()];
			for(int i=0;i<datas.length;i++){
				datas[i] = info_list.get(i);
			}
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
		return datas;
	}

/**
	取得した地図データをファイルに保存するメソッド
  */
	public void save(String file){
		try{
			FileOutputStream fout = new FileOutputStream(file);
			ObjectOutputStream os = new ObjectOutputStream(fout);

			for(InfoData data : info_list){
				os.writeObject(data);
			}

			os.close();
			fout.close();
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
	}
}
