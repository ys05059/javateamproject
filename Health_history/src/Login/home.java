package Login;

import java.awt.EventQueue;
import java.util.ArrayList;

import Main.dayRecordpage;
import Main.exRecordpage;
import set����class.dayRecord;
import set����class.exRecord;
import ��.CalendarDemo;

public class home {
	
	public static void main(String[] args) {
		/*
		 * ȸ������ ���Ͽ��� �޾ƿ��� & �������� ���Ͽ� �����ϱ�
		 */
		
		/*ArrayList<User> user_ary = new ArrayList<>();
		loginpage frame = new loginpage(user_ary);
		frame.setVisible(true);
		*/
		ArrayList<dayRecord> dR_ary = new ArrayList<>();
		dayRecordpage frame = new dayRecordpage(dR_ary);
		frame.setVisible(true);
		
		
		//CalendarDemo cd= new CalendarDemo();
		//cd.setVisible(true);
		/*
		 * ����� ���Ͽ��� �޾ƿͼ� exercise_ary�� ����
		 */
	}
}
