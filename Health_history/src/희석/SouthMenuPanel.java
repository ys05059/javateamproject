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
import java.awt.Color;
import java.awt.SystemColor;

public class SouthMenuPanel extends JPanel{
	
	final ImageIcon calendarP = new ImageIcon("image\\calendarback.jpg"); 
	private JButton gotoStatistics = new JButton("통계");

	public ArrayList<dayRecord> curr_dR_ary;

	public SouthMenuPanel(ArrayList<dayRecord> dR_ary) {
		setLayout(new FlowLayout());
		gotoStatistics.setBackground(SystemColor.activeCaption);
		gotoStatistics.addActionListener(new gotoStatisticsHandler());


		add(gotoStatistics);
		curr_dR_ary = dR_ary;

	}
	private class gotoStatisticsHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			SelectStatisticsWayDemo sswd = new SelectStatisticsWayDemo(curr_dR_ary);
			sswd.setVisible(true);
		}
	}
	public void paintComponent(Graphics g) {
		
		g.drawImage(calendarP.getImage(), 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
		
	}

}
