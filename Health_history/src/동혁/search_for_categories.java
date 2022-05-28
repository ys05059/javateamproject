package health;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class search_for_categories {

	public static void main(String[] args) throws IOException{
		try {
			ArrayList<category> categorylist2 = new ArrayList<category>();
			String name = "";
			inputarea A = new inputarea();
			name = A.inputcate();
			
			Arraylist_For_Category B = new Arraylist_For_Category();
			File file = new File("cate_" + name);
			if (file.exists() && file.isFile()) {
				B.txttoArraylist2(file.getAbsolutePath());
				for ( category w : B.getArrlist2()) {
					categorylist2.add(w.clone());
				}
				
				for (int i = 0; i < categorylist2.size(); i++) {
					System.out.print((i+1) + ". " + categorylist2.get(i).getworkout());
					System.out.println(","+categorylist2.get(i).getcalmethod());

				}
				
				
			}
			else {
				System.out.println("그런 운동이 존재하지 않습니다.");
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}

