package µ¿Çõ;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class search_for_ALL_WORKOUT extends worklist{

	
	search_for_ALL_WORKOUT(){
		super();
		
	}
	
//	File file = new File("ALL_WORKOUT");
	
	public String search(String name){
//		ArrayList<work> worklist2 = new ArrayList<work>();
		String res = "";
		search_for_ALL_WORKOUT A = new search_for_ALL_WORKOUT();
		try {
			File file = new File("ALL_WORKOUT");
//			Arraylist_For_ALL_WORKOUT B = new Arraylist_For_ALL_WORKOUT(); // Arraylist_For_ALL_WORKOUT
			if (file.exists() && file.isFile()) {
				A.set_worklist(file.getAbsolutePath());;
//				for ( work w : A.getArrlist()) {
//					worklist2.add(w.clone());
//				}
//				for(int i = 0; i < worklist2.size(); i++) {
//					if (name.equals(worklist2.get(i).getworkout())) {
//						res =  worklist2.get(i).getworkout() + "/" + worklist2.get(i).getcategory() + "/"
//								+ worklist2.get(i).getcalmethod();
//						break;
//					}
//					else {
//					}
//				}
				
				for(int i = 0; i < A.get_worklist().size(); i++) {
					if (name.equals(A.get_worklist().get(i).getworkout())) {
						res = A.get_worklist().get(i).getworkout() + "/" + A.get_worklist().get(i).getcategory() + "/"
								+ A.get_worklist().get(i).getcalmethod();
						break;
					}
					else {
						continue;
					}
				}
				
				
			}
			else {
				res = "filenotfound";
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
		A.clear_worklist();
		return res;
	}
	
	
	public String searchcate(String name){
		String res = "";
		search_for_ALL_WORKOUT B = new search_for_ALL_WORKOUT();
		try {
//			Arraylist_For_ALL_WORKOUT B = new Arraylist_For_ALL_WORKOUT(); // Arraylist_For_ALL_WORKOUT
			ArrayList<work> worklist2 = new ArrayList<work>();
			File file = new File("ALL_WORKOUT");
			if (file.exists() && file.isFile()) {
				B.set_worklist(file.getAbsolutePath());
//				for ( work w : B.getArrlist()) {
//					worklist2.add(w.clone());
//				}
				for(int i = 0; i < B.get_worklist().size(); i++) {
					if (name.equals(B.get_worklist().get(i).getcategory())) {
						res =  res + B.get_worklist().get(i).getworkout() + "$";
					}
					else {
					}
				}
			}
			else {
				res = "filenotfound";
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
		B.clear_worklist();
		return res;
	}
}
