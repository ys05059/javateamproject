package Main;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import Login.User;
import Login.login;
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

		try {
//			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//				if ("Nimbus".equals(info.getName())) {
//					UIManager.setLookAndFeel(info.getClassName());
//					break;
//				}
//			}

			//liquid lookandfeel
//			UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
			
			//idw lookandfeel
//			UIManager.setLookAndFeel("net.infonode.gui.laf.InfoNodeLookAndFeel");
			
			//jtattoo lookandfeel
//			UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
//			UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
//			UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
//			UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
//			UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
			UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
//			com.jtattoo.plaf.bernstein.BernsteinLookAndFeel
//			com.jtattoo.plaf.fast.FastLookAndFeel
//			com.jtattoo.plaf.hifi.HiFiLookAndFeel
//			com.jtattoo.plaf.luna.LunaLookAndFeel
//			com.jtattoo.plaf.mcwin.McWinLookAndFeel
//			com.jtattoo.plaf.mint.MintLookAndFeel
//			com.jtattoo.plaf.noire.NoireLookAndFeel
//			com.jtattoo.plaf.smart.SmartLookAndFeel
//			com.jtattoo.plaf.texture.TextureLookAndFeel
//			com.jtattoo.plaf.custom.flx.FLXLookAndFeel
		

		}
		catch (Exception e) {
			//if not loaded look and feel,,, 
			
		}

		ArrayList<dayRecord> dR_ary = new ArrayList<>();
		//dayRecordpage frame2 = new dayRecordpage(dR_ary);
		//frame2.setVisible(true);

		
		
		
		
		//dayRecordpage frame2 = new dayRecordpage(dR_ary);
		//frame2.setVisible(true);
		
		CalendarDemo cd= new CalendarDemo(dR_ary);
		cd.setVisible(true);
		/*
		 * ����� ���Ͽ��� �޾ƿͼ� exercise_ary�� ����
		 */
	}
}
