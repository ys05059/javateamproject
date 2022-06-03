package 희석;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import set단위class.dayRecord;

public class SouthMenuPanel extends JPanel{
	
	final ImageIcon calendarP = new ImageIcon("image\\calendarback.jpg"); 
	private JButton gotoStatistics = new JButton("통계");
	private JButton gotoCalendar = new JButton("일정");
	private JButton gotoCurri = new JButton("커리큘럼");
	
	public ArrayList<dayRecord> curr_dR_ary;

	public SouthMenuPanel(ArrayList<dayRecord> dR_ary) {
		setLayout(new FlowLayout());
		gotoStatistics.addActionListener(new gotoStatisticsHandler());
		gotoCalendar.addActionListener(new gotoCalendarHandler());
		gotoCurri.addActionListener(new gotoCurriHandler());

		add(gotoStatistics);
		add(gotoCalendar);
		add(gotoCurri);
		curr_dR_ary = dR_ary;

	}
	
	private class gotoStatisticsHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			DayStatisticsDemo dsd = new DayStatisticsDemo(curr_dR_ary);
			//PeriodStatisticsDemo wsd = new PeriodStatisticsDemo(curr_dR_ary);
		}
	}

	private class gotoCalendarHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
		}
	}

	private class gotoCurriHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
		}
	}
	
	
	public void paintComponent(Graphics g) {
		
		g.drawImage(calendarP.getImage(), 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
		
	}

}
