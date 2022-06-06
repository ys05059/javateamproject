package 희석;

import java.awt.Color;
import java.awt.Graphics;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import set단위class.dayRecord;

public class DayStatisticsDemo extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JTextField dateTextField;
	//private String[] datesString;
	private String currDate;
	private LocalDate today;
	
	private String statistics;
	private final ImageIcon batangG = new ImageIcon("image\\batang2.jpg"); 
	public ArrayList<dayRecord> curr_dR_ary;

	public static DayStatisticsFunc dsfunc;
	
	public DayStatisticsDemo(ArrayList<dayRecord> dR_ary, LocalDate dayld) {
		setTitle("DayStatisticsDemo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500,400);
		curr_dR_ary = dR_ary;
		today = dayld;
		currDate=today.toString();
		
		contentPane = new JPanel(){
			public void paintComponent(Graphics g) {
				g.drawImage(batangG.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.black);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel date_of_today_label = new JLabel("날짜");
		date_of_today_label.setBounds(44, 39, 50, 15);
		contentPane.add(date_of_today_label);

		dateTextField = new JTextField(currDate);
		dateTextField.setBounds(91,36,96,21);
		dateTextField.setEditable(false);
		contentPane.add(dateTextField);
		
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