package µ¿Çõ;

import java.io.File;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
public class worklist {
	static ArrayList<work> worklist = new ArrayList<work>();
	
		public static void set_worklist(String filePath) {
			try {
				if(worklist.size() == 0) {
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
				}	
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		//hi
		
		public ArrayList<work> get_worklist() {
			return worklist;
		}
		
		public ArrayList<String> getworktoStringlist(String cate) {
			
			ArrayList<String> A = new ArrayList<>();
			for(int i = 0; i < worklist.size(); i++) {
				if(worklist.get(i).getcategory().equals(cate)) {
					A.add(worklist.get(i).getworkout());
				}
				else {
					continue;
				}
			}
			return A;
		}
		
		public ArrayList<String> getcatetoStringlist() {
			ArrayList<String> A = new ArrayList<>();
			ArrayList<String> Ares = new ArrayList<>();
			for(int i = 0; i < worklist.size(); i++) {
				A.add(worklist.get(i).getcategory());
			}
			String[] Arr = A.toArray(new String[A.size()]);
			
			for(String item : Arr) {
				if(Ares.contains(item) == false) {
					Ares.add(item);
				}
			}
			return Ares;
		}
		
		
		public void clear_worklist() {
			worklist.clear();
		}
		
}
