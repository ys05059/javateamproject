package 희석;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import set단위class.dayRecord;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollBar;

public class PeriodStatisticsDemo extends JFrame {

	private JLabel date_of_start_day_label;
	private JLabel date_of_end_day_label;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	
	private String[] datesString;
	
	private JComboBox<String> startDateComboBox;
	private JComboBox<String> endDateComboBox;
	
	private String startDate;
	private String endDate;
	
	public ArrayList<dayRecord> curr_dR_ary;

	public static PeriodStatisticsFunc wfunc;
	
	private String statistics; // 통계내용

	public PeriodStatisticsDemo(ArrayList<dayRecord> dR_ary) {
		setTitle("WeekStatisticsDemo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500,400);
		getContentPane().setLayout(null);
		curr_dR_ary = dR_ary;
		datesString = mk_date_str(curr_dR_ary);
		
		date_of_start_day_label = new JLabel("시작일");
		date_of_start_day_label.setBounds(41, 39, 50, 15);
		getContentPane().add(date_of_start_day_label);

		startDateComboBox = new JComboBox<String>(datesString);
		startDateComboBox.setBounds(91,36,96,21);
		getContentPane().add(startDateComboBox);
		JComboStartHandler startComboHandler = new JComboStartHandler();
		startDateComboBox.addActionListener(startComboHandler);
		
		date_of_end_day_label = new JLabel("종료일");
		date_of_end_day_label.setBounds(277, 39, 50, 15);
		getContentPane().add(date_of_end_day_label);
		

		endDateComboBox = new JComboBox<String>(datesString);
		endDateComboBox.setBounds(330,36,96,21);
		getContentPane().add(endDateComboBox);
		JComboEndtHandler endComboHandler = new JComboEndtHandler();
		endDateComboBox.addActionListener(endComboHandler);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 69, 428, 258);
		getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		setVisible(true);
	}
	
	public String[] mk_date_str(ArrayList<dayRecord> dR_ary) {
		LocalDate ld;
		String[] str = new String[dR_ary.size()];
		int i=0;
		for(dayRecord dr : dR_ary) {
			ld = dr.getToday_date();
			str[i++]=ld.toString();
		}
		return str;
	}
	
	private class JComboStartHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			startDate = startDateComboBox.getSelectedItem().toString();
		}
	}
	private class JComboEndtHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			endDate = endDateComboBox.getSelectedItem().toString();
			switch (PeriodStatisticsFunc.chkDateSeq(startDate, endDate)) {
			case 0:
				System.out.println("Same day");
				endDate="";
				startDate="";
				break;
			case -1:
				System.out.println("end day precedes start day");
				endDate="";
				startDate="";
				break;
			case 1:
			//	statistics = wfunc.makeTextArea();
			//	textArea.setText(statistics);				
			}
		}
	}
	
}
