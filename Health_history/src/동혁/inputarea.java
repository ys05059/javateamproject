package health;

import java.util.Scanner;


public class inputarea {
	private String name;
	private String cate;
	
	public inputarea(){
		this.name = "";
		this.cate = "";
	}
	
	
	public String inputwork() {
		String name1 = "";
		Scanner sc = new Scanner(System.in);
		System.out.println("운동명 입력");
		name1 = sc.nextLine();
		setInput1(name1);
		return getInput1();
		
	}
	public String inputcate() {
		String cate1 = "";
		Scanner sc = new Scanner(System.in);
		System.out.println("카테고리명 입력");
		cate1 = sc.nextLine();
		setInput2(cate1);
		return getInput2();
		
	}
	
	
	public void setInput1(String here) {
		this.name = here;
	}
	public void setInput2(String here) {
		this.cate = here;
	}
	public String getInput1() {
		return this.name;
	}
	public String getInput2() {
		return this.cate;
	}
	
	


	
}
