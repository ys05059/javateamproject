package 희석;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Main.dayRecordpage;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import set단위class.dayRecord;
import set단위class.exRecord;

public class CalendarDemo extends JFrame{

	public static final int WIDTH = 1200;
	public static final int HEIGHT = 800;
	public static final String[] DAYS_OF_NAME = {"","일", "월", "화", "수", "목", "금" , "토"};
	
	public static CalendarFunc cfunc = new CalendarFunc();

	public static JPanel Y_M = new JPanel();
	public static JLabel ym = new JLabel("0000년 0월");
	public static JButton beforeBtn = new JButton("Before");
	public static JButton afterBtn = new JButton("After");
	public static JButton[] daysBtn = new JButton[42];
	public static JPanel[] one_day_panel = new JPanel[42];
	
	public CalendarDemo(){
		super("Calendar");
		this.setSize(WIDTH, HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		moveHandler moveAct = new moveHandler();
		beforeBtn.addActionListener(moveAct);
		afterBtn.addActionListener(moveAct);
		
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
			
			JPanel showExInCal = new JPanel();
			showExInCal.setLayout(new GridLayout(0,1));
			
			one_day_panel[i].add(showExInCal, BorderLayout.CENTER);
			
			days_num_panel.add(one_day_panel[i]);
		}
		cfunc.setButtons(daysBtn);
		cfunc.calSet();
		
		Days.add(days_num_panel, BorderLayout.CENTER);
		
		cal.add(Days, BorderLayout.CENTER);
		
		this.add(cal, BorderLayout.CENTER);
		
	}
	public void getExc() { // 운동목록 받아오기
		
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
			String YandM = cfunc.getYandM();
			YandM += day +"일";

			//dayRecordpage frame = new dayRecordpage();
			//frame.today_textField.setText(YandM);
			//frame.setVisible(true);
			
			// dayRecordpage을 어레이리스트? 로 전역으로 하나 만들고
			// 거기에 frame 삽입, 날짜 보내기.
			// frame 변경사항 있으면  one_day_panel[i].add(운동, borderlayout.center?
			// exRecord 운동값들은 어디에?
			
		}
	}

}
