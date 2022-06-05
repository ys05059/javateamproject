package 희석;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import Login.imgPanel;
import Main.UserRecord;
import Main.dayRecordpage;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import set단위class.Set;
import set단위class.dayRecord;
import set단위class.exRecord;
import set단위class.exercise;
import set단위class.exlistClass;

import java.time.LocalDate;
import java.util.ArrayList;
import 동혁.search_for_ALL_WORKOUT;
import javax.swing.SwingConstants;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class CalendarDemo extends JFrame{

	public static final int WIDTH = 1200;
	public static final int HEIGHT = 800;
	public static final String[] DAYS_OF_NAME = {"","일", "월", "화", "수", "목", "금" ,"토"};
	public static final String[] dongibuyeo = {"무슨 일이 있어도 2개 더 해. -아놀드 슈워제네거- " , "운동할 때 오는 건 힘든 게 아니고 통증일 뿐이다. -가수 김종국-", 
			"자신의 몸에 만족하는 순간 더 이상의 발전은 없다 -보디빌더 강경원-", "간단합니다. 흔들리면 그것은 지방입니다. -아놀드 슈워제네거-", 
			"몸을 만들고 싶으면 말로 떠들지 말고, 몸으로 떠들어라 - 배우 제이슨 스타뎀-" } ;
	
	
	public JPanel backPanel;
	final ImageIcon calendarP = new ImageIcon("image\\calendarback.jpg"); 
	//배경 넣기 위해
	final ImageIcon calendar_day = new ImageIcon("image\\calendar_day.jpg");
	
	public static CalendarFunc cfunc = new CalendarFunc();
	
	public JPanel Y_M = new JPanel(){
		public void paintComponent(Graphics g) {
			g.drawImage(calendarP.getImage(), 0, 0, null);
			setOpaque(false);
			super.paintComponent(g);
		}
	};
	
	public JPanel btnsP = new JPanel(){
		public void paintComponent(Graphics g) {
			g.drawImage(calendarP.getImage(), 0, 0, null);
			setOpaque(false);
			super.paintComponent(g);
		}
	};
	public JLabel ym = new JLabel("0000년0월");
//	ImageIcon Beforeicon = new ImageIcon("icon\\before.png");
	public JButton beforeBtn = new JButton("Before");
//	ImageIcon Aftericon = new ImageIcon("icon\\after.png");
	public JButton afterBtn = new JButton("After");	
	
	public static JButton[] daysBtn = new JButton[42];
	public static JPanel days_num_panel;
	public static JPanel[] one_day_panel = new JPanel[42];
	public static JPanel[] showExInCal = new JPanel[42];
	private JLabel DONGIbueyeo;
	private JLabel howtomove;
	
	
	private SouthMenuPanel menu;
	
	public dayRecordpage drp;
	public ArrayList<dayRecord> curr_dR_ary;
	
	private LocalDate select_date;
	
	private UserRecord UR;  //serialize 할 User운동기록클래스 불러오기
	
	
	public CalendarDemo(ArrayList<dayRecord> dR_ary, final String ID){
		super("Calendar");
		String whatID = ID; //id 받아오기
		
		//calender 시작 전에 , id로 된 dat 파일이 있는 지 확인하고ㅡ 있다면 deserialize 한다.
		this.setSize(WIDTH, HEIGHT);
		getContentPane().setLayout(new BorderLayout());
		this.setVisible(true);
		this.addWindowListener(new WindowListener(){

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				int A = JOptionPane.showConfirmDialog(null, "저장하시겠습니까?", "종료", JOptionPane.YES_NO_OPTION);
				if(A == 1 || A == -1) { //no or x 누를 때 -> 저장 안하고 나가기
					System.exit(0);
				}
				else if(A == 0) { //yes 누르면 -> 저장
					UR = new UserRecord(ID, curr_dR_ary);
					try {
						ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("userworkinfo//" + ID + ".dat"));
						outputStream.writeObject(UR);
						outputStream.close();
						System.exit(0);
						
					}catch(IOException e1) {
						System.err.println("Error writing to file.");
						System.exit(0);
					}
					
				}
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
				
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	
		moveHandler moveAct = new moveHandler(); //달력 넘기는 버튼 actionhandler
		beforeBtn.addActionListener(moveAct);
		afterBtn.addActionListener(moveAct);
		
		curr_dR_ary = dR_ary;  //A은 복사 실행중, 두개가 주소값이 같아진다
		
		howtomove = new JLabel("날짜 버튼을 눌러 운동을 입력하세요!");
		
		
		int k = (int)(Math.random() * 5);
		DONGIbueyeo = new JLabel(dongibuyeo[k] );
		
		
		JPanel cal = new JPanel(){
			public void paintComponent(Graphics g) {
				g.drawImage(calendarP.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		cal.setLayout(new BorderLayout(10, 20));
		
		
		
		ym.setText(cfunc.getYandM());
		Y_M.setLayout(new GridLayout(3,1));
		
		btnsP.setLayout(new FlowLayout());
		Y_M.add(howtomove);
		btnsP.add(beforeBtn);
		btnsP.add(ym);
		btnsP.add(afterBtn);
		Y_M.add(btnsP);
		cal.add(Y_M, BorderLayout.NORTH);
		DONGIbueyeo.setHorizontalAlignment(SwingConstants.RIGHT);
		DONGIbueyeo.setVerticalAlignment(SwingConstants.BOTTOM);
		
		Y_M.add(DONGIbueyeo);
		
		JPanel Days = new JPanel(){
			public void paintComponent(Graphics g) {
				g.drawImage(calendarP.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		Days.setLayout(new BorderLayout(5, 10));
				
		JPanel days_name_panel = new JPanel(){
			public void paintComponent(Graphics g) {
				g.drawImage(calendarP.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		days_name_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 155, 0));
		JLabel[] days_name_label = new JLabel[8];
		
		for(int i=1;i<=7;i++) {
			days_name_label[i] = new JLabel(DAYS_OF_NAME[i]);
			days_name_panel.add(days_name_label[i]);
		}
		
		Days.add(days_name_panel, BorderLayout.NORTH);
		
		days_num_panel = new JPanel(){
			public void paintComponent(Graphics g) {
				g.drawImage(calendarP.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		days_num_panel.setLayout(new GridLayout(6, 7, 10, 10));
		
		for(int i=0;i<daysBtn.length;i++) {
			one_day_panel[i] = new JPanel();
//			{
//				public void paintComponent(Graphics g) {
//					g.drawImage(calendar_day.getImage(), 0, 0, null);
//					setOpaque(false);
//					super.paintComponent(g);
//				}
//			};
			one_day_panel[i].setLayout(new BorderLayout());
			daysBtn[i] = new JButton();
			gotoaddexRec gotoaddexAct = new gotoaddexRec();
			daysBtn[i].addActionListener(gotoaddexAct);
			showExInCal[i] = new JPanel();
			showExInCal[i].setLayout(new GridBagLayout());
			if(i%7==0) daysBtn[i].setForeground(Color.RED);
			if(i%7==6) daysBtn[i].setForeground(Color.BLUE);
			
			one_day_panel[i].add(daysBtn[i], BorderLayout.NORTH);
			one_day_panel[i].add(showExInCal[i], BorderLayout.CENTER);

			days_num_panel.add(one_day_panel[i]);
		}
		cfunc.setButtons(daysBtn);
		cfunc.calSet();
		
		Days.add(days_num_panel, BorderLayout.CENTER);
		
		cal.add(Days, BorderLayout.CENTER);
		
		getContentPane().add(cal, BorderLayout.CENTER);
		
		
		menu = new SouthMenuPanel(curr_dR_ary);
		
		getContentPane().add(menu, BorderLayout.SOUTH);
		paintExcPane(curr_dR_ary);
	}
	public static void paintExcPane(ArrayList<dayRecord> dR_ary) { // 운동목록 받아오기 dayRecordpage에 메소드실행문 추가해야함
		LocalDate d;
		GridBagConstraints gbc =new GridBagConstraints();	
		HashMap<String, int[]> catemap = null;
		
		for(int i=0;i<daysBtn.length;i++) {
			daysBtn[i].setBackground(new Color(164, 230, 244));
			showExInCal[i].setBackground(new Color(127, 197, 249));
			showExInCal[i].removeAll();
			showExInCal[i].revalidate();										
			showExInCal[i].repaint();									
		}
		
		if(dR_ary.size()==0)
			return;

		for(dayRecord x : dR_ary) {
			d = x.getToday_date();
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridwidth = 2;			
			if(cfunc.getMonth().equals(String.valueOf(d.getMonthValue()))) { // 캘린더 month와 같은 month 의 dayRecord 받기
				int i = d.getDayOfMonth()+CalendarFunc.fday-1;
				daysBtn[i].setBackground(Color.yellow);				
				ArrayList<exRecord> exs = x.getExr_ary();
				catemap = new HashMap<String, int[]>();
				for(exRecord e : exs) {
					exercise ex = e.getEx();
					if(!catemap.containsKey(ex.getcategory())) { // 카테고리 맵에 해당 운동의 카테고리가 없으면 카테고리 추가
						int []cnts = new int[3]; // 카테고리 운동 수, 수행 세트 수, 목표 세트 수
						catemap.put(ex.getcategory(), cnts);
					}
					catemap.get(ex.getcategory())[0]+=1;
					catemap.get(ex.getcategory())[1]+=e.getCount_set();
					catemap.get(ex.getcategory())[2]+=e.getSet_goal();
					System.out.println(ex.getcategory());
				}
				List<Entry<String, int[]>> entryList = new ArrayList<Entry<String,int[]>>(catemap.entrySet());
				Collections.sort(entryList, new Comparator<Entry<String, int[]>>(){
					public int compare(Entry<String, int[]> a, Entry<String, int[]> b) {
						if(a.getValue()[0] <b.getValue()[0])
							return 1;
						else if(a.getValue()[0] > b.getValue()[0])
							return -1;
						else {
							return a.getKey().compareTo(b.getKey());
						}
					}
				});
				int idx=0;
				for(Entry<String, int[]> entry : entryList) {
					System.out.println(entry.getKey() + entry.getValue()[0]);
					if(idx==3)
						break;
					idx++;
					JPanel oneex = new JPanel();
					oneex.setBackground(new Color(127, 197, 249));
					oneex.setLayout(new GridLayout(1, 3));
					
					JLabel first = new JLabel(entry.getKey() + " " + String.valueOf(entry.getValue()[0]) + "개");
					first.setForeground(Color.BLACK);
					first.setHorizontalAlignment(JLabel.LEFT);
					first.setFont(new Font("Serif", Font.PLAIN ,11));
					
					JLabel second = new JLabel(String.valueOf(entry.getValue()[1]) + "/" + String.valueOf(entry.getValue()[2]));
					second.setForeground(Color.BLACK);
					second.setHorizontalAlignment(JLabel.RIGHT);
					second.setFont(new Font("Serif", Font.PLAIN ,11));
					
					oneex.add(first);
					oneex.add(second);
					
					showExInCal[i].add(oneex, gbc);
					gbc.gridy+=1;
				}
				showExInCal[i].revalidate();										
				showExInCal[i].repaint();
			}
		}
	}
	private class moveHandler implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			String btnStr = e.getActionCommand();
			
			int move = 0;
				
			if(btnStr.equals("Before")) {
				move = -1;
			}
			else if(btnStr.equals("After")) {
				move = 1;
			}
			cfunc.Init(move);
			ym.setText(cfunc.getYandM());
			paintExcPane(curr_dR_ary);
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
