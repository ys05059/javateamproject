package health;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class search_for_ALL_WORKOUT {

	public static void main(String[] args) throws IOException{
		try {
			String name = "";
			boolean ishere = false;
			ArrayList<work> worklist2 = new ArrayList<work>();
			inputarea A = new inputarea();
			name = A.inputwork();
			Arraylist_For_ALL_WORKOUT B = new Arraylist_For_ALL_WORKOUT();
			File file = new File("ALL_WORKOUT");
			if (file.exists() && file.isFile()) {
				B.txttoArraylist(file.getAbsolutePath());
				for ( work w : B.getArrlist()) {
					worklist2.add(w.clone());
				}
				for(int i = 0; i < worklist2.size(); i++) {
					if (name.equals(worklist2.get(i).getworkout())) {
						ishere = true;
						System.out.println("검색한 운동 : " + worklist2.get(i).getworkout());
						System.out.println("카테고리 : " + worklist2.get(i).getcategory());
						System.out.println("계산 방법 : " + worklist2.get(i).getcalmethod());
						break;
					}
					else {
						ishere = false;
					}
				}
				if (ishere == false) {
					System.out.println("그런 운동이 존재하지 않습니다.");
				}
				else {
					System.out.println();
				}
				
				
			}
			else {
				System.out.println("error. 운동 파일이 존재하지 않습니다.");
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
