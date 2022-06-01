package 희석;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import set단위class.dayRecord;

public class SouthMenuPanel extends JPanel{
	
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
			//DayStatisticsDemo dsd = new DayStatisticsDemo(curr_dR_ary);
			WeekStatisticsDemo wsd = new WeekStatisticsDemo(curr_dR_ary);
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
}
