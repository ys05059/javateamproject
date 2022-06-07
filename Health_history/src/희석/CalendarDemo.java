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
import java.awt.Insets;
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
import Login.login;
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
	
	public static CalendarFunc cfunc = new CalendarFunc();	// 캘린더 버튼의 숫자를 설정해주는 기능
	
	public JPanel Y_M = new JPanel(){	// 현재 년도, 달, 달 이동버튼 포함하는 패널
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
	public JPanel btnsP2 = new JPanel(){ // adding logout btn and myeongun
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
	public static JPanel days_num_panel;	// 버튼이랑 해당 날짜 패널 들어가는 패널
	public static JPanel[] one_day_panel = new JPanel[42]; // 해당 날짜 패널
	public static JPanel[] showExInCal = new JPanel[42]; // 해당 날짜의 운동 정보 제공하는 패널
	private JLabel DONGIbueyeo;
	private JButton logoutBtn;
	private SouthMenuPanel menu;
	public dayRecordpage drp;
	public ArrayList<dayRecord> curr_dR_ary;
	
	private LocalDate select_date; // 버튼으로 선택한 날짜 받아서 저장하는 변수
	private UserRecord UR;  //serialize �� User����Ŭ���� �ҷ����
	private String whatID;

	
	public CalendarDemo(ArrayList<dayRecord> dR_ary, final String ID){
		super("Calendar");
		whatID = ID; //id �޾ƿ��
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
	
		moveHandler moveAct = new moveHandler(); //달력 넘기는 버튼 actionHandler
		beforeBtn.addActionListener(moveAct);
		afterBtn.addActionListener(moveAct);
		
		curr_dR_ary = dR_ary;  //A은 복사 실행중, 두개가 주소값이 같아진다 따로 객체 값 변경하는 부분은 없음

		logoutBtn = new JButton("로그아웃");
		logoutBtn.setBackground(SystemColor.PINK);
		logoutBtn.addActionListener(new logoutAct());
		
		int k = (int)(Math.random() * 5);
		DONGIbueyeo = new JLabel(dongibuyeo[k] );
		
		
		JPanel cal = new JPanel(){
			public void paintComponent(Graphics g) {
				g.drawImage(calendarP.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		cal.setLayout(new BorderLayout(10, 20)); // 현재 프레임 전체를 감싸는 패널
		
		ym.setText(cfunc.getYandM());
		Y_M.setLayout(new GridLayout(2,1));
		
		btnsP.setLayout(new FlowLayout());
		btnsP.add(beforeBtn);
		btnsP.add(ym);
		btnsP.add(afterBtn);
		Y_M.add(btnsP);
		
		btnsP2.setLayout(new FlowLayout());
		btnsP2.add(DONGIbueyeo);
		btnsP2.add(logoutBtn);
		Y_M.add(btnsP2);

		DONGIbueyeo.setHorizontalAlignment(SwingConstants.RIGHT);
		DONGIbueyeo.setVerticalAlignment(SwingConstants.BOTTOM);
		
		
		cal.add(Y_M, BorderLayout.NORTH);
		
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
		/*
		 * days_num_panel 안에 one_day_panel 42개 있고,
		 * one_day_panel에 daysBtn, showExInCal패널이 있고
		 * showExInCal패널에서 운동정보 나옴
		 */
		
		for(int i=0;i<daysBtn.length;i++) {
			one_day_panel[i] = new JPanel();
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
		cfunc.setButtons(daysBtn); // daysBtn 배열 주소 넘겨줘서 CalendarFunc의 buttons배열에 할당
		cfunc.calSet();//CalendarFunc의 buttons배열에 daysBtn주소 할당되어 있으니 CalendarFunc에서 버튼에 숫자 할당
		
		Days.add(days_num_panel, BorderLayout.CENTER);
		
		cal.add(Days, BorderLayout.CENTER);
		
		getContentPane().add(cal, BorderLayout.CENTER);
		
		
		menu = new SouthMenuPanel(curr_dR_ary, whatID);
		
		getContentPane().add(menu, BorderLayout.SOUTH);
		
		paintExcPane(curr_dR_ary);
	}
	
	/*
	 * CalendarFunc에서 날짜까지만 할당하고 
	 * dayRecord 주소 받아와서 운동 정보들 showExInCal에 개시
	 */
	public static void paintExcPane(ArrayList<dayRecord> dR_ary) {
		LocalDate d;
		GridBagConstraints gbc =new GridBagConstraints();
		HashMap<String, int[]> catemap = null;
		
		for(int i=0;i<daysBtn.length;i++) {
			daysBtn[i].setBackground(new Color(164, 230, 244));
			showExInCal[i].setBackground(new Color(127, 197, 249));
			showExInCal[i].removeAll();
			showExInCal[i].revalidate();										
			showExInCal[i].repaint();									
		}// 달 이동시 우선 전체 패널들 초기화
		
		if(dR_ary.size()==0)
			return; // 운동 아무것도 없으면 초기화만 시키고 메소드 종료

		for(dayRecord x : dR_ary) {
			d = x.getToday_date();
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridwidth = 1;
			if(cfunc.getMonth().equals(String.valueOf(d.getMonthValue()))) { // 캘린더 month와 같은 month 의 dayRecord 받기
				int i = d.getDayOfMonth()+CalendarFunc.fday-1;
				daysBtn[i].setBackground(Color.yellow);
				ArrayList<exRecord> exs = x.getExr_ary();
				if(exs.size() == 0) {
					daysBtn[i].setBackground(new Color(164, 230, 244)); //운동 다 삭제되면 색깔 바꾸기
				}
				catemap = new HashMap<String, int[]>(); // {운동 카테고리 명 : { 수행 갯수 : 목표 갯수}}
				for(exRecord e : exs) {
					exercise ex = e.getEx();
					if(!catemap.containsKey(ex.getcategory())) { // 카테고리 맵에 해당 운동의 카테고리가 없으면 카테고리 추가
						int []cnts = new int[3]; // 카테고리 운동 수, 수행 세트 수, 목표 세트 수
						catemap.put(ex.getcategory(), cnts);
					}
					catemap.get(ex.getcategory())[0]+=1;	// 카테고리 갯수
					catemap.get(ex.getcategory())[1]+=e.getCount_set(); // 수행 갯수
					catemap.get(ex.getcategory())[2]+=e.getSet_goal(); // 목표 갯수
				}
				List<Entry<String, int[]>> entryList = new ArrayList<Entry<String,int[]>>(catemap.entrySet());
				Collections.sort(entryList, new Comparator<Entry<String, int[]>>(){ // 카테고리수로 정렬 후 사전순 정렬
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
				GridBagConstraints sgbc =new GridBagConstraints();
				sgbc.gridy=0;
				sgbc.fill = GridBagConstraints.BOTH;
				sgbc.weightx = 3;
				for(Entry<String, int[]> entry : entryList) {
					sgbc.gridx=0;
					if(idx==3)
						break;
					idx++;
					JPanel oneex = new JPanel();
					oneex.setBackground(new Color(127, 197, 249));
					oneex.setLayout(new GridBagLayout());
					
					JLabel first = new JLabel(entry.getKey()+"");
					first.setForeground(Color.BLACK);
					first.setHorizontalAlignment(JLabel.CENTER);
					first.setFont(new Font("Serif", Font.PLAIN ,11));
					
					JLabel second = new JLabel(String.valueOf(entry.getValue()[0] + "개"));
					second.setForeground(Color.BLACK);
					second.setHorizontalAlignment(JLabel.CENTER);
					second.setFont(new Font("Serif", Font.PLAIN ,11));
					
					JLabel third = new JLabel( String.valueOf(entry.getValue()[1]) + "/" + String.valueOf(entry.getValue()[2]));
					third.setForeground(Color.BLACK);
					third.setHorizontalAlignment(JLabel.CENTER);
					third.setFont(new Font("Serif", Font.PLAIN ,11));
					
					oneex.add(first, sgbc);
					sgbc.gridx+=1;
					sgbc.insets = new Insets(0,20,0,20);
					oneex.add(second, sgbc);
					sgbc.gridx+=1;
					sgbc.insets = new Insets(0,0,0,0);
					oneex.add(third, sgbc);
					
					showExInCal[i].add(oneex, gbc);
					gbc.gridy+=1;
				}
				showExInCal[i].revalidate();										
				showExInCal[i].repaint();
			}
		}
	}
	
	private class moveHandler implements ActionListener { // 달 이동 액션핸들러
		
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
	
	private class gotoaddexRec implements ActionListener{ // dayRecordpage로 이동
		
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
	
	private class	logoutAct implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int A = JOptionPane.showConfirmDialog(null, "저장하시겠습니까?", "종료", JOptionPane.YES_NO_OPTION);
			if(A == 1 || A == -1) { //no or x 누를 때 -> 저장 안하고 나가기
				disposeCalendar();
				login AA = new login();
				AA.turnonLoginPage();
			}
			else if(A == 0) { //yes 누르면 -> 저장
				UR = new UserRecord(whatID, curr_dR_ary);
				try {
					ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("userworkinfo//" + whatID + ".dat"));
					outputStream.writeObject(UR);
					outputStream.close();
					disposeCalendar();
					login AA = new login();
					AA.turnonLoginPage();
					
				}catch(IOException e1) {
					System.err.println("Error writing to file.");
					System.exit(0);
				}
				
			}
//			login A = new login();
//			A.turnonLoginPage();
		
		}
	}
	
	private void disposeCalendar() {
		this.dispose();
	}
	
	public String getID() {
		return whatID;
	}

}
