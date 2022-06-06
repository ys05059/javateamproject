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
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class SelectStatisticsWayDemo extends JFrame {

	private JPanel contentPane;

	private String dayStr;
	private String startStr;
	private String endStr;
	private JCheckBox dayChkBox;
	private JCheckBox periodChkBox;
	
	private JComboBox dayYComBox;
	private JComboBox dayMComBox;
	private JComboBox dayDComBox;
	
	private JComboBox<String> startYComBox;
	private JComboBox startMComBox;
	private JComboBox startDComBox;
	private JComboBox endYComBox;
	private JComboBox endMComBox;
	private JComboBox endDComBox;
	
	private LocalDate dayld;
	private LocalDate startld;
	private LocalDate endld;
	private ArrayList<dayRecord> curr_dR_ary;
	
	private String[] yearStrAry = {"2018", "2019", "2020", "2021", "2022"};
	private String[] monthStrAry = {"1","2","3","4","5","6","7","8","9","10","11","12"};
	private String[] dayStrAry;
	
	public SelectStatisticsWayDemo(ArrayList<dayRecord> dR_ary) {
		setTitle("SelectStatisticsWayDemo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 497, 291);
		curr_dR_ary = dR_ary;
		dayStr="";
		startStr="";
		endStr="";
		int default_month =dR_ary.get(0).getToday_date().getMonthValue()-1;
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		dayChkBox = new JCheckBox("하루통계");
		dayChkBox.setBounds(27, 24, 78, 23);
		contentPane.add(dayChkBox);
		
		periodChkBox = new JCheckBox("기간통계");
		periodChkBox.setBounds(27, 149, 78, 23);
		contentPane.add(periodChkBox);

		dayStrAry = makedayStrAry();
		
		JLabel symbol = new JLabel("~");
		symbol.setHorizontalAlignment(SwingConstants.CENTER);
		symbol.setBounds(245, 153, 32, 15);
		contentPane.add(symbol);

		dayYComBox = new JComboBox(yearStrAry);
		dayYComBox.setBounds(136, 24, 76, 23);
		dayYComBox.setSelectedIndex(4);
		contentPane.add(dayYComBox);
		
		dayMComBox = new JComboBox(monthStrAry);
		dayMComBox.setBounds(224, 24, 73, 23);
		dayMComBox.setSelectedIndex(default_month);
		contentPane.add(dayMComBox);
		
		dayDComBox = new JComboBox(dayStrAry);
		dayDComBox.setBounds(309, 24, 78, 23);
		contentPane.add(dayDComBox);
		
		startYComBox = new JComboBox(yearStrAry);
		startYComBox.setBounds(136, 120, 76, 23);
		startYComBox.setSelectedIndex(4);
		contentPane.add(startYComBox);
		
		startMComBox = new JComboBox(monthStrAry);
		startMComBox.setBounds(224, 120, 73, 23);
		startMComBox.setSelectedIndex(default_month);
		contentPane.add(startMComBox);
		
		startDComBox = new JComboBox(dayStrAry);
		startDComBox.setBounds(309, 120, 78, 23);
		contentPane.add(startDComBox);
		
		endYComBox = new JComboBox(yearStrAry);
		endYComBox.setBounds(136, 178, 76, 23);
		endYComBox.setSelectedIndex(4);
		contentPane.add(endYComBox);
		
		endMComBox = new JComboBox(monthStrAry);
		endMComBox.setBounds(224, 178, 73, 23);
		endMComBox.setSelectedIndex(default_month);
		contentPane.add(endMComBox);
		
		endDComBox = new JComboBox(dayStrAry);
		endDComBox.setBounds(309, 178, 78, 23);
		contentPane.add(endDComBox);
		

		JButton gotoStatisticBtn = new JButton("통계");
		gotoStatisticBtn.setBounds(416, 103, 55, 23);
		contentPane.add(gotoStatisticBtn);
		
		gotoStatisticBtn.addActionListener(new gotoStatisticsHandler());
	}
	
	public String[] makedayStrAry() {
		String[] str = new String[31];
		for(int i=0;i<31;i++) {
			str[i] = String.valueOf(i+1);
		}
		return str;
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
				JOptionPane.showMessageDialog(null, "둘 중 하나만 선택하세요","경고", JOptionPane.ERROR_MESSAGE);
			}
			else if(!day&&!period) {
				JOptionPane.showMessageDialog(null, "둘 중 하나를 선택해주세요","경고", JOptionPane.ERROR_MESSAGE);
			}
			else {
				if(day) {
					dayStr="";
					dayStr+=dayYComBox.getSelectedItem()+"-"+dayMComBox.getSelectedItem()+"-"
							+dayDComBox.getSelectedItem();
					dayld=makeLocalDate(dayStr);
					
					DayStatisticsDemo dsd = new DayStatisticsDemo(curr_dR_ary, dayld);
				}
				else {
					startStr="";
					startStr=startYComBox.getSelectedItem()+"-"+startMComBox.getSelectedItem()+"-"+
							startDComBox.getSelectedItem();
					endStr="";
					endStr=endYComBox.getSelectedItem()+"-"+endMComBox.getSelectedItem()+"-"+
							endDComBox.getSelectedItem();
					
					switch (PeriodStatisticsFunc.chkDateSeq(startStr, endStr)) {
						case 0:
							JOptionPane.showMessageDialog(null, "같은 날짜입니다","경고", JOptionPane.ERROR_MESSAGE);
							endStr="";
							startStr="";
							break;
						case -1:
							JOptionPane.showMessageDialog(null, "기간을 확인해주세요","경고", JOptionPane.ERROR_MESSAGE);
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
