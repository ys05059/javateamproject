package Main;

import java.awt.EventQueue;
import java.util.ArrayList;

import Login.User;
import Login.loginpage;
import set단위class.dayRecord;
import set단위class.exRecord;
import set단위class.exlistClass;
import 희석.CalendarDemo;

public class home {
	
	public static void main(String[] args) {
		/*
		 * 회원정보 파일에서 받아오기 & 마지막에 파일에 저장하기
		 */
		
		//ArrayList<User> user_ary = new ArrayList<>();
		//loginpage frame = new loginpage(user_ary);
		//frame.setVisible(true);
		
		ArrayList<dayRecord> dR_ary = new ArrayList<>();
		dayRecordpage frame2 = new dayRecordpage(dR_ary);
		frame2.setVisible(true);
		
		
		
		
		//CalendarDemo cd= new CalendarDemo();
		//cd.setVisible(true);
		/*
		 * 운동정보 파일에서 받아와서 exercise_ary에 저장
		 */
	}
}
