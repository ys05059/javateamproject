package set����class;

import java.io.File;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
public class exlistClass {
	private ArrayList<exercise> exlist;
	
	public exlistClass() {
		 exlist= new ArrayList<exercise>();
	}
	
	public exlistClass(String filePath) { //file�� �޾Ƽ� �ش� ���� ã�� �� exlist(arraylist��) ���� ��ȯ�ϴ� ������
		exlist= new ArrayList<exercise>();
		try {
			File file = new File(filePath);
			if (file.exists() && file.isFile()) { //������ �����ϰ� ���� ������ ��� arraylist�� �ִ´�
				FileReader fr = new FileReader(file.getAbsolutePath());
				BufferedReader bufreader = new BufferedReader(fr);
	
				while(true) {
					String txt = bufreader.readLine(); //�� �پ� �о����
				
					if(txt == null) {
						break;
					}
					String[] exarr = txt.split("/");
					if(exarr.length == 3) {
						exlist.add(new exercise(exarr[0], exarr[1], exarr[2]));
					}
					else {
						System.out.println("���� �� �߸��� �Է��� �����մϴ�.");
					}
				}
			}
			else {
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<String> getcatetoStringlist() { //ī�װ��� �̾ƿ� �迭����Ʈ�� �����
		ArrayList<String> A = new ArrayList<>(); //���� ��� �迭����Ʈ���� ī�װ��� �ش��ϴ� �κ��� ��������
		ArrayList<String> Ares = new ArrayList<>();//�ߺ��Ǵ� �κ��� ������ �迭����Ʈ�� ������� ����.
		for(int i = 0; i < exlist.size(); i++) {
			A.add(exlist.get(i).getcategory());
		}
		String[] Arr = A.toArray(new String[A.size()]);
		
		for(String item : Arr) {
			if(Ares.contains(item) == false) { //�������� ������
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
		// deep copy ������� �� - �ϴ� deepcopy �߰� 
		//�Ŀ� �������� �ٽ� ó���ϰڽ��ϴ�(����)
	}
	
	public void clear_exlist() {
		exlist.clear();
	}
	
}
