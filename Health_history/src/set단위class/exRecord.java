package set단위class;

import java.util.ArrayList;

public class exRecord {
	private exercise ex;
	private ArrayList<Set> set_ary;
	private int set_goal;								// 목표 세트 수

	public exRecord() {
		ex = new exercise();
		set_ary = new ArrayList<>();
		set_goal =0;
	}
	
	public exRecord(String ex_name, int setgoal) {
		ex = new exercise(ex_name);
		set_ary = new ArrayList<>();
		set_goal =setgoal;
	}
	
	public exercise getEx() {
		return new exercise(ex);
	}

	public void setEx(exercise ex) {
		this.ex = new exercise(ex);
	}
	
	public ArrayList<Set> getSet_ary() {
		return new ArrayList<Set>(set_ary);
	}
	
	public void addset(Set s) {
		set_ary.add(s);
	}

	public int getSet_goal() {
		return set_goal;
	}

	public void setSet_goal(int set_goal) {
		this.set_goal = set_goal;
	}
}
