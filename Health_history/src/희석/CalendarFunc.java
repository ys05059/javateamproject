package 희석;

import java.time.*;
import java.util.*;

import javax.swing.JButton;

public class CalendarFunc {
	public static final String[] DAY_OF_WEEK= {"","일", "월", "화", "수", "목", "금" ,"토"};

	private int year, month;
	private JButton[] buttons;
	private Calendar sDay = Calendar.getInstance();
	public static int fday;
	public CalendarFunc() {
		this.year = sDay.get(Calendar.YEAR);
		this.month = sDay.get(Calendar.MONTH);
	}
	
	public void setButtons(JButton[] buttons) {
		this.buttons = buttons;
	}
	public String getYear() {
		return Integer.toString(year);
	}
	public String getMonth() {
		return Integer.toString(month + 1);
	}
	public String getYandM() { 
		return getYear() + "년 " + getMonth() + "월";
	}
	
	public void calSet() {
		sDay.set(year, month,1);
		
		fday = sDay.get(Calendar.DAY_OF_WEEK);
		fday-=1;
		
		int i;
		
		for(int j=0;j<fday;j++)
			buttons[j].setText(" ");
		
		for(i=0;i<sDay.getActualMaximum(sDay.DATE); i++)
			buttons[fday + i].setText(String.valueOf(i+1));
		
		for(int j=i+fday;j<buttons.length;j++) 
			buttons[j].setText(" ");
		
	}
	
	public void Init(int move) {
		for(int i=0;i<buttons.length;i++) {
			buttons[i].setText("");
		}
		this.month += move;
		if(month < 0) {
			year-=1;
			month = 11;
		}else if(month>=12) {
			year+=1;
			month = 0;
		}
		calSet();
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setMonth(int month) {
		this.month = month;
	}
}