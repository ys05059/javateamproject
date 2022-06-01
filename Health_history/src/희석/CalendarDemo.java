package 희석;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Main.dayRecordpage;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import set단위class.dayRecord;
import set단위class.exRecord;
import set단위class.exercise;

import java.time.LocalDate;
import java.util.ArrayList;

public class CalendarDemo extends JFrame{

	public static final int WIDTH = 1200;
	public static final int HEIGHT = 800;
	public static final String[] DAYS_OF_NAME = {"","일", "월", "화", "수", "목", "금" ,"토"};
	
	public static CalendarFunc cfunc = new CalendarFunc();

	public JPanel Y_M = new JPanel();
	public JLabel ym = new JLabel("0000년 0월");
	public JButton beforeBtn = new JButton("Before");
	public JButton afterBtn = new JButton("After");	
	
	public static JButton[] daysBtn = new JButton[42];
	public static JPanel[] one_day_panel = new JPanel[42];
	public static JPanel[] showExInCal = new JPanel[42];

	private SouthMenuPanel menu;
	
	public dayRecordpage drp;
	public ArrayList<dayRecord> curr_dR_ary;
	
	private LocalDate select_date;
	
	
	public CalendarDemo(ArrayList<dayRecord> dR_ary){
		super("Calendar");
		this.setSize(WIDTH, HEIGHT);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		moveHandler moveAct = new moveHandler();
		beforeBtn.addActionListener(moveAct);
		afterBtn.addActionListener(moveAct);
		
		curr_dR_ary = dR_ary;
		
		JPanel cal = new JPanel();
		cal.setLayout(new BorderLayout(10, 20));
		
		ym.setText(cfunc.getYandM());
		Y_M.setLayout(new FlowLayout());
		Y_M.add(beforeBtn);
		Y_M.add(ym);
		Y_M.add(afterBtn);
		
		cal.add(Y_M, BorderLayout.NORTH);
		
		JPanel Days = new JPanel();
		Days.setLayout(new BorderLayout(5, 10));
				
		JPanel days_name_panel = new JPanel();
		days_name_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 155, 0));
		JLabel[] days_name_label = new JLabel[8];
		
		for(int i=1;i<=7;i++) {
			days_name_label[i] = new JLabel(DAYS_OF_NAME[i]);
			days_name_panel.add(days_name_label[i]);
		}
		
		Days.add(days_name_panel, BorderLayout.NORTH);
		
		JPanel days_num_panel = new JPanel();
		days_num_panel.setLayout(new GridLayout(6, 7, 10, 10));
		
		for(int i=0;i<daysBtn.length;i++) {
			one_day_panel[i] = new JPanel();
			one_day_panel[i].setLayout(new BorderLayout());
			daysBtn[i] = new JButton();
			gotoaddexRec gotoaddexAct = new gotoaddexRec();
			daysBtn[i].addActionListener(gotoaddexAct);
			
			if(i%7==0) daysBtn[i].setForeground(Color.RED);
			if(i%7==6) daysBtn[i].setForeground(Color.BLUE);
			
			one_day_panel[i].add(daysBtn[i], BorderLayout.NORTH);
			
			days_num_panel.add(one_day_panel[i]);
		}
		cfunc.setButtons(daysBtn);
		cfunc.calSet();
		
		Days.add(days_num_panel, BorderLayout.CENTER);
		
		cal.add(Days, BorderLayout.CENTER);
		
		this.add(cal, BorderLayout.CENTER);
		
		
		menu = new SouthMenuPanel(curr_dR_ary);
		
		this.add(menu, BorderLayout.SOUTH);
		
	}
	
	public static void paintExcPane(ArrayList<dayRecord> dR_ary) { // 운동목록 받아오기 dayRecordpage에 메소드실행문 추가해야함
		
		LocalDate d;
		GridBagConstraints gbc =new GridBagConstraints();		
		
		if(dR_ary.size()==0)
			return;
		
		for(dayRecord x : dR_ary) {
			d = x.getToday_date();
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridwidth = 2;
			
			if(cfunc.getMonth().equals(String.valueOf(d.getMonthValue()))) { // 캘린더 month와 같은 month 의 dayRecord 받기
				int i = d.getDayOfMonth()-1;
				daysBtn[i].setBackground(Color.yellow);

				showExInCal[i] = new JPanel();
				showExInCal[i].setBackground(Color.WHITE);
				GridBagLayout gb = new GridBagLayout();

				gb.rowHeights = new int[]{20, 20, 20, 20, 20};
				gb.columnWidths = new int[] {50,50,20,35,35};
				
				showExInCal[i].setLayout(gb);
								
				ArrayList<exRecord> exs = x.getExr_ary();
				
				
				for(exRecord e : exs) {
					exercise exercise = e.getEx();
					
////					//String type = exercise.getType();
////					//String exname = exercise.getName();
////					
////					JLabel typel = new JLabel(type);
////					JLabel exnamel = new JLabel(exname);
////					
//					JPanel oneex = new JPanel();
//					oneex.setLayout(new GridLayout(1, 2));
//					
//					oneex.add(typel);
//					oneex.add(exnamel);
//					
//					showExInCal[i].add(oneex, gbc);
//					
//					gbc.gridy+=1;
//					one_day_panel[i].add(showExInCal[i], BorderLayout.CENTER);	// 왜 안보일까 흑흑
				}
			}
		}
	}
	
	private class moveHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String btnStr = e.getActionCommand();
			int move = 0;
				
			if(btnStr.equals("Before"))
				move = -1;
			else if(btnStr.equals("After"))
				move = 1;
			cfunc.Init(move);
			ym.setText(cfunc.getYandM());
		}
	}
	
	private class gotoaddexRec implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String day = e.getActionCommand().toString();
			
			//String YandM = cfunc.getYandM();
			//YandM += day +"일";
			
			select_date = LocalDate.of(Integer.parseInt(cfunc.getYear()),Integer.parseInt(cfunc.getMonth()), 
					Integer.parseInt(day));
			
			// 운동기록이 있을 때 없을 때 구분
			int index =0; 
			boolean dr_exist= false;
			// 프로그램 처음 시작
			if (curr_dR_ary.isEmpty()) {
				drp = new dayRecordpage(curr_dR_ary,new dayRecord(select_date));
				//drp.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				//drp.setModal(true);
				drp.setVisible(true);
			}
			else {
				for(dayRecord dr : curr_dR_ary) {
					if(dr.getToday_date()!= null) {
						if(dr.getToday_date().equals(select_date)) {
							dr_exist=true;
							break;
						}else
							index++;
					}else
						System.err.println("today_date가 없는 dayRecord가 있습니다");
				}
				// 해당 날짜에 이미 기록된 내용이 있음
				if(dr_exist == true) {
					drp = new dayRecordpage(curr_dR_ary,curr_dR_ary.get(index));
					//drp.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					//drp.setModal(true);
					drp.setVisible(true);
				}
				// 해당 날짜에 처음 생성 
				if(dr_exist == false) {
					drp = new dayRecordpage(curr_dR_ary,new dayRecord(select_date));
					//drp.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					//drp.setModal(true);
					drp.setVisible(true);
				}
			}
		}
	}
}