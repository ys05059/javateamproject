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
	
	public exlistClass(String filePath) { //file명 받아서 해당 파일 찾은 후 exlist(arraylist형) 으로 변환하는 생성자
		exlist= new ArrayList<exercise>();
		try {
			File file = new File(filePath);
			if (file.exists() && file.isFile()) { //파일이 존재하고 파일 형태일 경우 arraylist에 넣는다
				FileReader fr = new FileReader(file.getAbsolutePath());
				BufferedReader bufreader = new BufferedReader(fr);
	
				while(true) {
					String txt = bufreader.readLine(); //한 줄씩 읽어오기
				
					if(txt == null) {
						break;
					}
					String[] exarr = txt.split("/");
					if(exarr.length == 3) {
						exlist.add(new exercise(exarr[0], exarr[1], exarr[2]));
					}
					else {
						System.out.println("파일 내 잘못된 입력이 존재합니다.");
					}
				}
			}
			else {
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<String> getcatetoStringlist() { //카테고리만 뽑아와 배열리스트로 만들기
		ArrayList<String> A = new ArrayList<>(); //먼저 모든 배열리스트에서 카테고리에 해당하는 부분을 가져오고
		ArrayList<String> Ares = new ArrayList<>();//중복되는 부분을 삭제한 배열리스트를 재생성할 것임.
		for(int i = 0; i < exlist.size(); i++) {
			A.add(exlist.get(i).getcategory());
		}
		String[] Arr = A.toArray(new String[A.size()]);
		
		for(String item : Arr) {
			if(Ares.contains(item) == false) { //포함하지 않으면
				Ares.add(item);
			}
		}
		return Ares;
	}
	
	
	public ArrayList<String> getworktoStringlist(String cate) {
		
		ArrayList<String> A = new ArrayList<>();
		for(int i = 0; i < exlist.size(); i++) {
			if(exlist.get(i).getcategory().equals(cate)) {
				A.add(exlist.get(i).getname());
			}
			else {
				continue;
			}
		}
		return A;
	}
	
	
	public ArrayList<exercise> get_exlist() {
		ArrayList<exercise> A = new ArrayList<>();
		
		for (exercise i : exlist) {
			A.add(i);
		}
		return A;
		// deep copy 해줘야할 듯 - 일단 deepcopy 했고 
		//후에 오류나면 다시 처리하겠습니다(동혁)
	}
	
	public void clear_exlist() {
		exlist.clear();
	}
	
}
