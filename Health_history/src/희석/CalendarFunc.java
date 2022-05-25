package 희석;

import java.time.*;
import java.util.*;

import javax.swing.JButton;

public class CalendarFunc {
	public static final String[] DAY_OF_WEEK= {"","일","월","화","수","목","금"};
	
	private int year, month;
	private JButton[] buttons;
	private Calendar sDay = Calendar.getInstance();

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
	public String getYandM() { // 해당 년도, 몇월인지 반환( Label으로 사용 )
		return getYear() + "년 " + getMonth() + "월";
	}
	
	public void calSet() {
		int firstDay=sDay.get(Calendar.DAY_OF_WEEK);
		int endDay = sDay.getActualMaximum(Calendar.DATE);
		
		for(int i=1;i<firstDay;i++) {
			buttons[i].setText(" ");
		}
		for(int i=0;i<endDay;i++) {
			buttons[i].setText(Integer.toString(i+1));
		}
		for(int i=endDay;i<buttons.length;i++) {
			buttons[i].setText(" ");
		}
	}
	
	public void Init(int move) {
		for(int i=0;i<buttons.length;i++) {
			buttons[i].setText("");
		}
		this.month += move;
		if(month <= 0) {
			year-=1;
			month = 12;
		}else if(month>=13) {
			year+=1;
			month = 1;
		}
		calSet();
	}
}
