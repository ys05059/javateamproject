package set단위class;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class dayRecord implements Serializable, Comparable<dayRecord>{
	private ArrayList<exRecord> exr_ary;
	private double today_weight;
	private LocalDate today_date;
	
	// default Constructor
	public dayRecord() {
		exr_ary = new ArrayList<exRecord>();
		today_weight = 0.0;
		today_date =LocalDate.of(2000, 1, 1);
	}
	
	// Constructor (weight, date)
	public dayRecord(double tw, LocalDate td) {
		exr_ary = new ArrayList<exRecord>();
		today_weight = tw;
		today_date = td;
	}
	
	public dayRecord(LocalDate date) {
		exr_ary = new ArrayList<exRecord>();
		today_weight = 0.0;
		today_date = date;
	}
	public dayRecord(dayRecord dr) {
		exr_ary = new ArrayList<exRecord>(dr.getExr_ary());
		today_weight = dr.getToday_weight();
		today_date = dr.getToday_date();
	}
	
	// exRecord 한 개 추가
	public void add_exr(exRecord other_exr) {
		exr_ary.add(other_exr);
	}
	
	public void set_exr(exRecord other_exr) {
		int index =0;
		for(exRecord exr : exr_ary) {
			if(exr.getEx().getname().equals(other_exr.getEx().getname())) {
					exr_ary.set(index,other_exr);
			}
			index++;
		}
	}
	
	public void delete_exr (exRecord other_exr) {
		int index =0;
		for(exRecord exr : exr_ary) {
			if(exr.getEx().getname().equals(other_exr.getEx().getname())) {
				exr_ary.remove(index);
				break;
			}
			index++;
		}
	}

	public ArrayList<exRecord> getExr_ary() {
		return new ArrayList<exRecord>(exr_ary);
	}

	public double getToday_weight() {
		return today_weight;
	}

	public void setToday_weight(double today_weight) {
		this.today_weight = today_weight;
	}

	public LocalDate getToday_date() {
		return today_date;
	}

	public void setToday_date(LocalDate today_date) {
		this.today_date = today_date;
	}
	
	public void printallexr_ary() { //배열리스트 안에 잘 들어가는지 확인
		if(exr_ary.size() > 0) {
			for(int i = 0; i < exr_ary.size(); i++) {
				System.out.println(exr_ary.get(i).getEx().getname());
				System.out.println(exr_ary.get(i).getEx().getcategory());
				System.out.println(exr_ary.get(i).getSet_goal());
				System.out.println(exr_ary.get(i).getCount_set());
			}
			System.out.println(today_date);
		}
	}
	
	public void remove_exr_ary(int here) { //index값 받아와 해당 배열리스트 정보 지우기
		if(exr_ary.size() > 0) {
			exr_ary.remove(here);
		}
	}
	
	public int get_total_countset() {
		int count =0;
		for(exRecord er : exr_ary) {
			count += er.getCount_set();
		}
		return count;
	}

	@Override 
	public int compareTo(dayRecord dr) {
		if(today_date.isBefore(dr.getToday_date()))
			return -1;
		else if(today_date.isAfter(dr.getToday_date()))
			return 1;
		else
			return 0;
		
		//return today_date.compareTo(dr.getToday_date());
	}
	
	
}
