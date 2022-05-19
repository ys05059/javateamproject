package set단위class;

import java.time.LocalDate;
import java.util.ArrayList;

public class dayRecord {
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
	
	// exRecord 한 개 추가
	public void add_exr(exRecord other_exr) {
		exr_ary.add(other_exr);
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
	
	
	
}
