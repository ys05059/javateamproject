package set단위class;

import java.io.Serializable;
import java.util.ArrayList;

public class exRecord implements Serializable{
	private exercise ex;
	private int set_goal;								// 목표 세트 수
	private int count_set;								// 들어있는 세트 수(수행한 세트 수)
		
	public exRecord() {
		ex = new exercise();
		set_goal =0;
		count_set =0;
	}
	
	public exRecord(String ex_name , int setgoal) {
		ex = new exercise(ex_name);
		set_goal =setgoal;
		count_set =0;
	}
	
	public exRecord(exRecord other_exr) {
		ex = other_exr.getEx();
		set_goal = other_exr.set_goal;
		count_set = other_exr.count_set;
	}
	
	public void shallow_copy(exRecord other_exr) {
		ex = other_exr.ex;
		set_goal = other_exr.set_goal;
		count_set = other_exr.count_set;
	}
	
	public int cal_count_set() {
		System.out.println("exRecord의 cal_count_set 불렸습니다. 수정하세요");
		return count_set;
	}
	public int getCount_set() {
	 return count_set;
	}

	public void setCount_set(int count_set) {
		this.count_set = count_set;
	}

	public exercise getEx() {
		return new exercise(ex);
	}

	public void setEx(exercise ex) {
		this.ex = new exercise(ex);
	}
	

	public int getSet_goal() {
		return set_goal;
	}

	public void setSet_goal(int set_goal) {
		this.set_goal = set_goal;
	}
}
