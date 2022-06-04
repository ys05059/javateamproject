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
import Main.dayRecordpage;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import set����class.Set;
import set����class.dayRecord;
import set����class.exRecord;
import set����class.exercise;
import set����class.exlistClass;

import java.time.LocalDate;
import java.util.ArrayList;
import ����.search_for_ALL_WORKOUT;
import javax.swing.SwingConstants;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

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
	private JLabel howtomove;
	
	
	private SouthMenuPanel menu;
	
	public dayRecordpage drp;
	public ArrayList<dayRecord> curr_dR_ary;
	
	private LocalDate select_date;
	
	
	public CalendarDemo(ArrayList<dayRecord> dR_ary){
		super("Calendar");
		this.setSize(WIDTH, HEIGHT);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		this.setVisible(true);
	
		moveHandler moveAct = new moveHandler(); //�޷� �ѱ�� ��ư actionhandler
		beforeBtn.addActionListener(moveAct);
		afterBtn.addActionListener(moveAct);
		
		curr_dR_ary = dR_ary;
		
		howtomove = new JLabel("��¥ ��ư�� ���� ��� �Է��ϼ���!");
		
		
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
		
		Y_M.add(howtomove);
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
			if(cfunc.getMonth().equals(String.valueOf(d.getMonthValue()))) { // Ķ���� month�� ���� month �� dayRecord �ޱ�
				int i = d.getDayOfMonth()+CalendarFunc.fday-1;
				daysBtn[i].setBackground(Color.yellow);				
				ArrayList<exRecord> exs = x.getExr_ary();
				catemap = new HashMap<String, int[]>();
				for(exRecord e : exs) {
					exercise ex = e.getEx();
					if(!catemap.containsKey(ex.getcategory())) { // ī�װ� �ʿ� �ش� ��� ī�װ��� ������ ī�װ� �߰�
						int []cnts = new int[3]; // ī�װ� � ��, ���� ��Ʈ ��, ��ǥ ��Ʈ ��
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
					oneex.setLayout(new GridLayout(1, 2));
					
					JLabel first = new JLabel(entry.getKey() + " " + String.valueOf(entry.getValue()[0]) + "��");
					first.setForeground(Color.white);
					first.setHorizontalAlignment(JLabel.LEFT);
					JLabel second = new JLabel(String.valueOf(entry.getValue()[1]) + "/" + String.valueOf(entry.getValue()[2]));
					second.setForeground(Color.white);
					second.setHorizontalAlignment(JLabel.RIGHT);
					
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
	
}
