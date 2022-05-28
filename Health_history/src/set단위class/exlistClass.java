package set단위class;

import java.io.File;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
public class exlistClass {
	private ArrayList<exercise> exlist;
	
	public exlistClass() {
		 exlist= new ArrayList<exercise>();
	}
	
	public exlistClass(String filePath) {
		exlist= new ArrayList<exercise>();
		try {
			File file = new File(filePath);
			if (file.exists() && file.isFile()) {
				FileReader fr = new FileReader(file.getAbsolutePath());
				BufferedReader bufreader = new BufferedReader(fr);
	
				while(true) {
					String txt = bufreader.readLine();
				
					if(txt == null) {
						break;
					}
					String[] exarr = txt.split("/");
					exlist.add(new exercise(exarr[0], exarr[1], exarr[2]));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/*
	public static void set_worklist(String filePath) {
		try {
			File file = new File(filePath);
			FileReader fr = new FileReader(file.getAbsolutePath());
			BufferedReader bufreader = new BufferedReader(fr);

			while(true) {
				String txt = bufreader.readLine();
			
				if(txt == null) {
					break;
				}
				String[] workarr = txt.split("/");
				worklist.add(new work(workarr[0], workarr[1], workarr[2]));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}*/
	
	public ArrayList<exercise> get_exlist() {
		return exlist;
		// deep copy 해줘야할 듯
	}
	
	public void clear_exlist() {
		exlist.clear();
	}
	
}
