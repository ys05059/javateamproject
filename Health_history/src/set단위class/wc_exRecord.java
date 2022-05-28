package set단위class;

import java.util.ArrayList;

public class wc_exRecord extends exRecord{
	// clone 신경써줘야함
	private ArrayList<wc_set> wc_set_ary;
	
	public wc_exRecord() {
		super();
		wc_set_ary = new ArrayList<>();
	}
	
	public wc_exRecord(exRecord exr) {
		super(exr);
		wc_set_ary = new ArrayList<>();
	}
	
	public void add_wcset(wc_set wcs) {
		wc_set_ary.add(wcs);
		super.setCount_set(wc_set_ary.size());
	}
	
	public void add_wcset(String weight, String count, String resttime) {
		wc_set_ary.add(new wc_set(Integer.valueOf(weight),Integer.valueOf(count)));
		super.setCount_set(wc_set_ary.size());
		//resttime은 나중에
	}
	
	public ArrayList<wc_set> getWc_set_ary() {
		return new ArrayList<wc_set>(wc_set_ary);
	}

	public void setWc_set_ary(ArrayList<wc_set> wc_set_ary) {
		this.wc_set_ary = new ArrayList<wc_set>(wc_set_ary);
	}
	
	

}
