package µ¿Çõ;

import java.io.File;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
public class worklist {
	static ArrayList<work> worklist = new ArrayList<work>();
	
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
		}
		
		//hi
		
		public ArrayList<work> get_worklist() {
			return worklist;
		}
		
		public void clear_worklist() {
			worklist.clear();
		}
		
}
