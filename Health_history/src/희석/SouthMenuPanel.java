package 희석;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import set단위class.dayRecord;
import java.awt.Color;
import java.awt.SystemColor;

public class SouthMenuPanel extends JPanel{
	
	final ImageIcon calendarP = new ImageIcon("image\\calendarback.jpg"); 
	private JButton gotoStatistics = new JButton("통계");
	private JButton gotoCurri = new JButton("커리큘럼");
	
	private String ID;
	
	public ArrayList<dayRecord> curr_dR_ary;

	public SouthMenuPanel(ArrayList<dayRecord> dR_ary, String nowID) {
		ID = nowID;
		setLayout(new FlowLayout());
		gotoStatistics.setBackground(SystemColor.activeCaption);
		gotoStatistics.addActionListener(new gotoStatisticsHandler());

//		gotoCalendar.addActionListener(new gotoCalendarHandler());
//		gotoCurri.setBackground(SystemColor.activeCaption);
//		gotoCurri.addActionListener(new gotoCurriHandler());


		add(gotoStatistics);
		//add(gotoCurri);
		curr_dR_ary = dR_ary;

	}
	
	private class gotoStatisticsHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			boolean isavailable = false;
			for(int i = 0; i < curr_dR_ary.size(); i++) {
				if(curr_dR_ary.get(i).getExr_ary().size() == 0) { //exrecord 가 남아있지 않으면
					isavailable = false; //false 리턴
				}else { //하나라도 기록이 남아있다면
					isavailable = true; //true 리턴하고
					break; //즉시 break
				}
			}
			
			if(isavailable == false) {
				JOptionPane.showMessageDialog(null, "입력된 운동이 없습니다.","경고", JOptionPane.ERROR_MESSAGE);
			}else {
				SelectStatisticsWayDemo sswd = new SelectStatisticsWayDemo(curr_dR_ary, ID);
				sswd.setVisible(true);
			}

		}
	}

	public void paintComponent(Graphics g) {
		
		g.drawImage(calendarP.getImage(), 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
		
	}

}
