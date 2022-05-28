
package 동혁;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import set단위class.exlistClass;
import set단위class.exercise;

public class search_for_ALL_WORKOUT extends exlistClass{

	
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
				A.set_worklist(file.getAbsolutePath());
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
				
				for(int i = 0; i < A.get_exlist().size(); i++) {
					if (name.equals(A.get_exlist().get(i).getname())) {
						res = A.get_exlist().get(i).getname() + "/" + A.get_exlist().get(i).getcategory() + "/"
								+ A.get_exlist().get(i).getcalmethod();
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
		A.clear_exlist();
		return res;
	}
	
	public String searchcate(String name){
		String res = "";
		search_for_ALL_WORKOUT B = new search_for_ALL_WORKOUT();
		try {
//			Arraylist_For_ALL_WORKOUT B = new Arraylist_For_ALL_WORKOUT(); // Arraylist_For_ALL_WORKOUT
			ArrayList<exercise> worklist2 = new ArrayList<exercise>();
			File file = new File("ALL_WORKOUT");
			if (file.exists() && file.isFile()) {
				B.set_worklist(file.getAbsolutePath());
//				for ( work w : B.getArrlist()) {
//					worklist2.add(w.clone());
//				}
				for(int i = 0; i < B.get_exlist().size(); i++) {
					if (name.equals(B.get_exlist().get(i).getcategory())) {
						res =  res + B.get_exlist().get(i).getname() + "$";
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
		B.clear_exlist();
		return res;
	}
}
