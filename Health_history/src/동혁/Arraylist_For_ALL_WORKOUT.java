package health;
import java.io.File;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
public class Arraylist_For_ALL_WORKOUT {
	static ArrayList<work> worklist = new ArrayList<work>();
	
		public static void txttoArraylist(String filePath) {
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
		
		public ArrayList<work> getArrlist() {
			return worklist;
		}
		
		public void clearArrlist() {
			worklist.clear();
		}
		
}
