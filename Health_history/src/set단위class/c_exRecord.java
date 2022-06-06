package set단위class;

import java.util.ArrayList;

public class c_exRecord extends exRecord{
	// clone 신경써줘야함
	private ArrayList<c_set> c_set_ary;
	
	public c_exRecord() {
		super();
		c_set_ary = new ArrayList<>();
	}
	
	public c_exRecord(exRecord exr) {
		super(exr);
		c_set_ary = new ArrayList<>();
	}
	
	public c_exRecord (c_exRecord other_ce) {
		super(other_ce);
		c_set_ary = other_ce.c_set_ary;
	}
	
	public void first_add_wcset(c_set cs) {
		c_set_ary.add(cs);
	}
	
	public void add_wcset(c_set cs) {
		c_set_ary.add(cs);
		super.setSet_goal(super.getSet_goal()+1);
	}
	
	public void del_cset(c_set tmp_cs) {
		int count =0;
		for (c_set cs : c_set_ary) {
			if(cs.equals(tmp_cs)) {
				c_set_ary.remove(count);
				break;
			}
			count++;
		}
		super.setSet_goal(super.getSet_goal()-1);
	}

	@Override
	public int cal_count_set() {
		int count =0;
		for(c_set cs : c_set_ary) {
			if(cs.getPerform_check() == true)
				count++;
		}
		return count;
	}
	
	public ArrayList<c_set> getc_set_ary() {
		return new ArrayList<c_set>(c_set_ary);
	}

	public void setc_set_ary(ArrayList<c_set> wc_set_ary) {
		this.c_set_ary = new ArrayList<c_set>(wc_set_ary);
	}

}
