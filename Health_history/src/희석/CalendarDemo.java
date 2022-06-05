package ��;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

import set����class.dayRecord;
import set����class.exRecord;
import set����class.exercise;
import set����class.exlistClass;

import java.time.LocalDate;
import java.util.ArrayList;
import ����.search_for_ALL_WORKOUT;
import javax.swing.SwingConstants;

public class CalendarDemo extends JFrame{

	public static final int WIDTH = 1200;
	public static final int HEIGHT = 800;
	public static final String[] DAYS_OF_NAME = {"","��", "��", "ȭ", "��", "��", "��" ,"��"};
	public static final String[] dongibuyeo = {"���� ���� �־ 2�� �� ��. -�Ƴ�� �������װ�- " , "��� �� ���� �� ���� �� �ƴϰ� ������ ���̴�. -���� ������-", 
			"�ڽ��� ���� �����ϴ� ���� �� �̻��� ������ ���� -������� �����-", "�����մϴ�. ��鸮�� �װ��� �����Դϴ�. -�Ƴ�� �������װ�-", 
			"���� ����� ������ ���� ������ ����, ������ ������ - ��� ���̽� ��Ÿ��-" } ;
	
	
	public JPanel backPanel;
	final ImageIcon calendarP = new ImageIcon("image\\calendarback.jpg"); 
	//��� �ֱ� ����
	final ImageIcon calendar_day = new ImageIcon("image\\calendar_day.jpg");
	
	public static CalendarFunc cfunc = new CalendarFunc();
	
	public JPanel Y_M = new JPanel(){
		public void paintComponent(Graphics g) {
			g.drawImage(calendarP.getImage(), 0, 0, null);
			setOpaque(false);
			super.paintComponent(g);
		}
	};
	public JLabel ym = new JLabel("0000��0��");
//	ImageIcon Beforeicon = new ImageIcon("icon\\before.png");
	public JButton beforeBtn = new JButton("Before");
//	ImageIcon Aftericon = new ImageIcon("icon\\after.png");
	public JButton afterBtn = new JButton("After");	
	
	public static JButton[] daysBtn = new JButton[42];
	public static JPanel days_num_panel;
	public static JPanel[] one_day_panel = new JPanel[42];
	public static JPanel[] showExInCal = new JPanel[42];
	private JLabel DONGIbueyeo;
	private JButton logoutBtn;
	
	
	private SouthMenuPanel menu;
	
	public dayRecordpage drp;
	public ArrayList<dayRecord> curr_dR_ary;
	
	private LocalDate select_date;
	
	private UserRecord UR;  //serialize �� User����Ŭ���� �ҷ�����
	
	private String whatID;
	
	public CalendarDemo(ArrayList<dayRecord> dR_ary, final String ID){
		super("Calendar");
		
		whatID = ID; //id �޾ƿ���
		
		//calender ���� ���� , id�� �� dat ������ �ִ� �� Ȯ���ϰ�� �ִٸ� deserialize �Ѵ�.
		
		
		
		
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
				int A = JOptionPane.showConfirmDialog(null, "�����Ͻðڽ��ϱ�?", "����", JOptionPane.YES_NO_OPTION);
				if(A == 1 || A == -1) { //no or x ���� �� -> ���� ���ϰ� ������
					System.exit(0);
				}
				else if(A == 0) { //yes ������ -> ����
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
	
		moveHandler moveAct = new moveHandler(); //�޷� �ѱ�� ��ư actionhandler
		beforeBtn.addActionListener(moveAct);
		afterBtn.addActionListener(moveAct);
		
		curr_dR_ary = dR_ary;  //�A�� ���� ������, �ΰ��� �ּҰ��� ��������
		
		logoutBtn = new JButton("�α׾ƿ�");
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
		cal.setLayout(new BorderLayout(10, 20));
		
		
		
		ym.setText(cfunc.getYandM());
		Y_M.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		Y_M.add(logoutBtn);
		Y_M.add(beforeBtn);
		Y_M.add(ym);
		Y_M.add(afterBtn);
		
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
	public static void paintExcPane(ArrayList<dayRecord> dR_ary) { // ���� �޾ƿ��� dayRecordpage�� �޼ҵ���๮ �߰��ؾ���
		LocalDate d;
		GridBagConstraints gbc =new GridBagConstraints();		
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
			if(cfunc.getMonth().equals(String.valueOf(d.getMonthValue()))) { // Ķ���� month�� ���� month �� dayRecord �ޱ�
				int i = d.getDayOfMonth()+CalendarFunc.fday-1;
				daysBtn[i].setBackground(Color.yellow);				
				ArrayList<exRecord> exs = x.getExr_ary();
				
				for(exRecord e : exs) {
					exercise exercise = e.getEx();
					
					String type = exercise.getcategory(); 
					String exname = exercise.getname();
					JLabel typel = new JLabel(type);
					JLabel exnamel = new JLabel(exname);
					
					JPanel oneex = new JPanel();
					oneex.setBackground(new Color(127, 197, 249));
					oneex.setLayout(new GridLayout(1, 2));
					
					oneex.add(typel);
					oneex.add(exnamel);
					
					showExInCal[i].add(oneex, gbc);
					showExInCal[i].revalidate();										
					showExInCal[i].repaint();
					
					gbc.gridy+=1;
				}
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
			//YandM += day +"��";
			
			select_date = LocalDate.of(Integer.parseInt(cfunc.getYear()),Integer.parseInt(cfunc.getMonth()), 
					Integer.parseInt(day));

			// ������ ���� �� ���� �� ����
			int index =0; 
			boolean dr_exist= false;
			// ���α׷� ó�� ����
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
						System.err.println("today_date�� ���� dayRecord�� �ֽ��ϴ�");
				}
				// �ش� ��¥�� �̹� ��ϵ� ������ ����
				if(dr_exist == true) {
					drp = new dayRecordpage(curr_dR_ary,curr_dR_ary.get(index));
					//drp.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					//drp.setModal(true);
					drp.setVisible(true);
				}
				// �ش� ��¥�� ó�� ���� 
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
			int A = JOptionPane.showConfirmDialog(null, "�����Ͻðڽ��ϱ�?", "����", JOptionPane.YES_NO_OPTION);
			if(A == 1 || A == -1) { //no or x ���� �� -> ���� ���ϰ� ������
				disposeCalendar();
				login AA = new login();
				AA.turnonLoginPage();
			}
			else if(A == 0) { //yes ������ -> ����
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

}
