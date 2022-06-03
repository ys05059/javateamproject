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
	private JTextArea startDayTextArea;
	private JTextArea endDayTextArea;
	
	private String startDatestr;
	private String endDatestr;
	private LocalDate sld;
	private LocalDate eld;
	public ArrayList<dayRecord> curr_dR_ary;

	public static PeriodStatisticsFunc pfunc;
	
	private String statistics; // 통계내용

	public PeriodStatisticsDemo(ArrayList<dayRecord> dR_ary, LocalDate startld, LocalDate endld) {
		setTitle("WeekStatisticsDemo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500,400);
		getContentPane().setLayout(null);
		curr_dR_ary = dR_ary;
		startDatestr=startld.toString();
		endDatestr=endld.toString();
		sld = startld;
		eld = endld;
		
		date_of_start_day_label = new JLabel("시작일");
		date_of_start_day_label.setBounds(41, 39, 50, 15);
		getContentPane().add(date_of_start_day_label);

		startDayTextArea = new JTextArea(startDatestr);
		startDayTextArea.setBounds(91,36,96,21);
		getContentPane().add(startDayTextArea);
		
		date_of_end_day_label = new JLabel("종료일");
		date_of_end_day_label.setBounds(277, 39, 50, 15);
		getContentPane().add(date_of_end_day_label);

		endDayTextArea = new JTextArea(endDatestr);
		endDayTextArea.setBounds(330,36,96,21);
		getContentPane().add(endDayTextArea);
	
		scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 69, 428, 258);
		getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		pfunc = new PeriodStatisticsFunc(curr_dR_ary, sld, eld);
		statistics = pfunc.makeTextArea();
		textArea.setText(statistics);
		setVisible(true);
	}
}
