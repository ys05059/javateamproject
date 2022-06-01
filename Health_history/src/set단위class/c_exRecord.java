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
	
	public void add_wcset(c_set cs) {
		c_set_ary.add(cs);
		super.setCount_set(c_set_ary.size());
	}
	
	/*
	public void add_wcset(String weight, String count, String resttime) {
		c_set_ary.add(new c_set(Integer.valueOf(count)));
		super.setCount_set(c_set_ary.size());
		//resttime은 나중에
	}*/
	
	public void del_cset(c_set tmp_cs) {
		int count =0;
		for (c_set cs : c_set_ary) {
			if(cs.equals(tmp_cs)) {
				c_set_ary.remove(count);
				break;
			}
			count++;
		}
		super.setCount_set(c_set_ary.size());
	}
	
	
	public ArrayList<c_set> getc_set_ary() {
		return new ArrayList<c_set>(c_set_ary);
	}

	public void setc_set_ary(ArrayList<c_set> wc_set_ary) {
		this.c_set_ary = new ArrayList<c_set>(wc_set_ary);
	}

}
