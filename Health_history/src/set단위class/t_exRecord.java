package set단위class;

import java.util.ArrayList;

public class t_exRecord extends exRecord{
	// clone 신경써줘야함
	private ArrayList<t_set> t_set_ary;
	
	public t_exRecord() {
		super();
		t_set_ary = new ArrayList<>();
	}
	
	public t_exRecord(exRecord exr) {
		super(exr);
		t_set_ary = new ArrayList<>();
	}
	
	public t_exRecord (t_exRecord other_te) {
		super(other_te);
		t_set_ary = other_te.t_set_ary;
	}
	
	public void add_tset(t_set ts) {
		t_set_ary.add(ts);
		super.setCount_set(t_set_ary.size());
	}
	
	
	public ArrayList<t_set> gett_set_ary() {
		return new ArrayList<t_set>(t_set_ary);
	}

	public void sett_set_ary(ArrayList<t_set> wc_set_ary) {
		this.t_set_ary = new ArrayList<t_set>(wc_set_ary);
	}

}
