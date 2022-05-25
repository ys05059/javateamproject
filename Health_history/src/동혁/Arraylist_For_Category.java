package health;


import java.io.File;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
public class Arraylist_For_Category {
	static ArrayList<category> categorylist = new ArrayList<category>();
	
	
	public static void txttoArraylist2(String filePath) {
		try {
			File file = new File(filePath);
			FileReader fr = new FileReader(file.getAbsolutePath());
			BufferedReader bufreader = new BufferedReader(fr);

			while(true) {
				String txt = bufreader.readLine();
				
				if(txt == null) {
					break;
				}
				String[] catarr = txt.split("/");
				categorylist.add(new category(catarr[0], catarr[1],catarr[2]));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public ArrayList<category> getArrlist2(){
		return categorylist;
	}
}
