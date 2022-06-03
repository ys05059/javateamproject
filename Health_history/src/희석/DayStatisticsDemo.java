package 희석;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;

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
import java.util.StringTokenizer;

import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class DayStatisticsDemo extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JComboBox<String> dateComboBox;
	private JTextArea dateTextArea;
	//private String[] datesString;
	private String currDate;
	private LocalDate today;
	
	private String statistics;

	public ArrayList<dayRecord> curr_dR_ary;

	public static DayStatisticsFunc dsfunc;
	
	public DayStatisticsDemo(ArrayList<dayRecord> dR_ary, LocalDate dayld) {
		setTitle("DayStatisticsDemo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500,400);
		curr_dR_ary = dR_ary;
		today = dayld;
		currDate=today.toString();
		//datesString = mk_date_str(curr_dR_ary);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel date_of_today_label = new JLabel("날짜");
		date_of_today_label.setBounds(44, 39, 50, 15);
		contentPane.add(date_of_today_label);

		dateTextArea = new JTextArea(currDate);
		dateTextArea.setBounds(91,36,96,21);
		contentPane.add(dateTextArea);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 79, 418, 257);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		dsfunc = new DayStatisticsFunc(curr_dR_ary, today);

		statistics = dsfunc.makeTextArea();
		setTextArea(statistics);		
		this.setVisible(true);
	}

	public void setTextArea(String s) {
		textArea.setText("");
		textArea.setText(s);
	}
}