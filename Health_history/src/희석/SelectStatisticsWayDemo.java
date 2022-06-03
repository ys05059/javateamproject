package 희석;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import set단위class.dayRecord;

import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class SelectStatisticsWayDemo extends JFrame {

	private JPanel contentPane;
	private JTextField dayText;
	private JTextField startText;
	private JTextField endText;

	private String dayStr;
	private String startStr;
	private String endStr;
	private JCheckBox dayChkBox;
	private JCheckBox periodChkBox;
	
	private LocalDate dayld;
	private LocalDate startld;
	private LocalDate endld;
	private PeriodStatisticsFunc wfunc;
	private ArrayList<dayRecord> curr_dR_ary;
	
	public SelectStatisticsWayDemo(ArrayList<dayRecord> dR_ary) {
		setTitle("SelectStatisticsWayDemo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 562, 381);
		curr_dR_ary = dR_ary;
		dayStr="";
		startStr="";
		endStr="";
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		dayChkBox = new JCheckBox("하루통계");
		dayChkBox.setBounds(41, 112, 107, 23);
		contentPane.add(dayChkBox);
		
		periodChkBox = new JCheckBox("기간통계");
		periodChkBox.setBounds(41, 204, 107, 23);
		contentPane.add(periodChkBox);
		
		dayText = new JTextField();
		dayText.setBounds(220, 113, 96, 21);
		contentPane.add(dayText);
		dayText.setColumns(10);
		
		startText = new JTextField();
		startText.setBounds(150, 205, 96, 21);
		contentPane.add(startText);
		startText.setColumns(10);
		
		endText = new JTextField();
		endText.setBounds(297, 205, 96, 21);
		contentPane.add(endText);
		endText.setColumns(10);
		
		
		JLabel symbol = new JLabel("~");
		symbol.setHorizontalAlignment(SwingConstants.CENTER);
		symbol.setBounds(246, 208, 50, 15);
		contentPane.add(symbol);
		
		JButton gotoStatisticBtn = new JButton("통계");
		gotoStatisticBtn.setBounds(426, 161, 91, 23);
		contentPane.add(gotoStatisticBtn);
		gotoStatisticBtn.addActionListener(new gotoStatisticsHandler());
	}

	public LocalDate makeLocalDate(String todayStr) {
		System.out.println(todayStr);
		StringTokenizer dateTokens = new StringTokenizer(todayStr, "-" , false);
		
		int y = Integer.parseInt(dateTokens.nextToken());
		int m = Integer.parseInt(dateTokens.nextToken());
		int d = Integer.parseInt(dateTokens.nextToken());
				
		LocalDate day = LocalDate.of(y, m, d);
		
		return day;
	}
	private class gotoStatisticsHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			boolean day = dayChkBox.isSelected();
			boolean period = periodChkBox.isSelected();
			if(day&&period) {
				System.out.println("둘다선택됨");
			}
			else if(!day&&!period) {
				System.out.println("아무것도 선택 안됨");
			}
			else {
				if(day) {
					dayStr=dayText.getText();
					dayld=makeLocalDate(dayStr);
					DayStatisticsDemo dsd = new DayStatisticsDemo(curr_dR_ary, dayld);
				}
				else {
					startStr=startText.getText();
					endStr=endText.getText();
					
					switch (PeriodStatisticsFunc.chkDateSeq(startStr, endStr)) {
						case 0:
							System.out.println("Same day");
							endStr="";
							startStr="";
							break;
						case -1:
							System.out.println("end day precedes start day");
							endStr="";
							startStr="";
							break;
						case 1:
							boolean chk1 = false; // 입력한 날짜가 dayRecord의 날짜중에 있거나 
							boolean chk2 = true;
							int startdayChk;
							int enddayChk;
							startld=makeLocalDate(startStr);
							endld=makeLocalDate(endStr);
							for(dayRecord dr : curr_dR_ary) {
								startdayChk = PeriodStatisticsFunc.chkDateSeq(dr.getToday_date().toString() , startStr);
								enddayChk = PeriodStatisticsFunc.chkDateSeq(endStr, dr.getToday_date().toString());
								if(startdayChk == 1 || startdayChk == 0) {
									startld=dr.getToday_date();
									chk1=true;
								}
								if(enddayChk==1 || enddayChk==0) {
									endld=dr.getToday_date();
									chk2=true;
								}
								if(chk1&&chk2)
									break;
							}
							PeriodStatisticsDemo psd = new PeriodStatisticsDemo(curr_dR_ary, startld, endld);
					}
				}
			}
			
		}
	}
}
