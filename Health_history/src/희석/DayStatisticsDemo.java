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

public class DayStatisticsDemo extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JComboBox<String> dateComboBox;
	private String[] datesString;
	private String currDate;
	
	private String statistics;

	public ArrayList<dayRecord> curr_dR_ary;

	public static DayStatisticsFunc dsfunc;
	
	public DayStatisticsDemo(ArrayList<dayRecord> dR_ary) {
		setTitle("DayStatisticsDemo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500,400);
		curr_dR_ary = dR_ary;
		
		datesString = mk_date_str(curr_dR_ary);
		dsfunc = new DayStatisticsFunc(curr_dR_ary);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel date_of_today_label = new JLabel("날짜");
		date_of_today_label.setBounds(44, 39, 50, 15);
		contentPane.add(date_of_today_label);

		dateComboBox = new JComboBox<String>(datesString);
		dateComboBox.setBounds(91,36,96,21);
		contentPane.add(dateComboBox);
		JComboHandler comboHandler = new JComboHandler();
		dateComboBox.addActionListener(comboHandler);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 79, 418, 257);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		this.setVisible(true);
	}

	private class JComboHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			currDate = dateComboBox.getSelectedItem().toString();
			
			statistics = dsfunc.makeTextArea();
			setTextArea(statistics);
		}
	}
	
	public void setTextArea(String s) {
		textArea.setText("");
		textArea.setText(s);
	}
	
	public String[] mk_date_str(ArrayList<dayRecord> curr_dR_ary) {
		LocalDate ld;
		String[] str = new String[curr_dR_ary.size()];
		int i=0;
		for(dayRecord dr : curr_dR_ary) {
			ld = dr.getToday_date();
			
			str[i++]=ld.toString();
		}
		return str;
	}
}