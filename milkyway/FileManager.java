import  java.io.*;
import  java.awt.*;
import  java.util.ArrayList;

/********************
  クラス：FileManager
  ファイルの読み込み、保存を行うクラスです。

  @Author Haruka Tokito
  *******************/

public class FileManager{
	String file = null;

	// StringをArrayListにする
	ArrayList <String> array = new ArrayList <String> ();

	/**
	  本クラスのコンンストラクター
	  ファイルの定義を行なっている
	  @param String file
	  */
	public FileManager(String file){
		this.file = file;
	}


	/**
	  ファイルを読み込むためのメソッド
	  */
	public String [] load(){
		String [] lines = null;
		try{
			FileInputStream fin = new FileInputStream(file);	//指定したファイルを読み込む
			InputStreamReader is = new InputStreamReader(fin);	//読み込んだファイルの中身を取り出す 
			BufferedReader reader = new BufferedReader(is);		//取り出したファイルを一行ずつ読み込む

			while(true){
				try{
					String line = reader.readLine();
					if(line == null){
						break;
					}
						array.add(line);
				}
				catch(EOFException e){
					break;
				}
			}
			lines = new String [array.size()];

			for(int i=0;i<lines.length;i++){
				lines[i] = array.get(i);		// 情報を一つずつ取り出す
			}
			reader.close();
			is.close();
			fin.close();
		}
		catch(FileNotFoundException e){
			System.out.println(e.toString());
		}
		catch(IOException e){
			System.out.println(e.toString());
		}
		return lines;
	}



	/**
	  ArrayListにStringを追加するためのメソッド
	  @param String name
	  */

	public void addData(String name){
		if(name != null){
			array.add(name);
		}
	}


	
	/**
	  ArrayListの要素を削除するメソッド
	  @param int index
	  */
	public void removeData(int index){
		if(array.get(index) != null){
				array.remove(index);
				System.out.println("ok");
			}	
	}



	/**
      保存をするためにFileOutputStream,PrintStreamを使って書き出す

	  @param String file
	  */
	public void save(String file){
		if(file != null && array != null){
			try{
				FileOutputStream fout = new FileOutputStream(file);
				PrintStream ps = new PrintStream(fout);

				for(int i=0;i<array.size();i++){
					ps.print(array.get(i));		// 書き込み
					ps.print("\n");				//　改行
				}
				ps.close();
				fout.close();
			}
			catch(Exception e){
				System.out.println(e.toString());
			}
		}
	}


	public static void main(String args[]){
		FileManager manager = new FileManager(args[0]);

		String [] lines = manager.load();
		for(String line : lines){
			System.out.println(line);
		}
	}
}
