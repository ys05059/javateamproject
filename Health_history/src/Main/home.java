package Main;

import java.awt.EventQueue;
import java.util.ArrayList;

import Login.User;
import Login.loginpage;
import set����class.dayRecord;
import set����class.exRecord;
import set����class.exlistClass;
import ��.CalendarDemo;

public class home {
	
	public static void main(String[] args) {
		/*
		 * ȸ������ ���Ͽ��� �޾ƿ��� & �������� ���Ͽ� �����ϱ�
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
		 * ����� ���Ͽ��� �޾ƿͼ� exercise_ary�� ����
		 */
	}
}
