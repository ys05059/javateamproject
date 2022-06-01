package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Main.dayRecordpage.expanel;
import Main.dayRecordpage.savedR_check_dialog;
import set����class.c_exRecord;
import set����class.c_set;
import set����class.dayRecord;
import set����class.exRecord;
import set����class.exercise;
import set����class.exlistClass;
import set����class.t_exRecord;
import set����class.t_set;
import set����class.wc_exRecord;
import set����class.wc_set;

public class exRecordpage extends JDialog{
	private JPanel set_list_panel; 
	private exRecord exrecord;
	private ArrayList<wcset_panel> wcpanel_list; 
	private ArrayList<cset_panel> cpanel_list; 
	private ArrayList<exercise> exlist;
	
	public exRecordpage(exRecord other_exr, dayRecord pre_dayRecord) {
		setTitle("exRecordpage	");
		setSize(600,500);
		GridBagLayout gb = new GridBagLayout();
		gb.rowHeights = new int[] {50, 50,50,50,50,50,50};
		gb.columnWidths = new int[]  {100,50,50,50,75,75};
		setLayout(gb);
		GridBagConstraints gbc_default = new GridBagConstraints();
		
		/* �޾ƿ� ������� exRecord ���� ä��� */
		// other_exr�� name �� setgoal ������ ����
		exlistClass elc = new exlistClass("ALL_WORKOUT");
		exlist = elc.get_exlist();
		if (other_exr.getEx().getcalmethod().equals("")) {				//ù��° �Է�
				exrecord = new exRecord(other_exr.getEx().getname(),other_exr.getSet_goal());
				setEx_byname();
				if(exrecord.getEx().getcalmethod().equals("���� * Ƚ��")) {
					exrecord = new wc_exRecord(exrecord);
				}else if (exrecord.getEx().getcalmethod().equals("Ƚ��")) {
					exrecord = new c_exRecord(exrecord);
				}
		}else if(other_exr instanceof wc_exRecord) {					// �ι�° ���� �� ���� * Ƚ���� �
			exrecord = (wc_exRecord)other_exr;
			wc_exRecord tmp_wce = (wc_exRecord)other_exr;
			// wcpanel_list ������ֱ�
			if(tmp_wce.getCount_set()==0) {
				wcpanel_list = new ArrayList<>();
			}else {
				wcpanel_list = new ArrayList<>();
				for(wc_set wcs : tmp_wce.getWc_set_ary()) {
					wcset_panel wcp = new wcset_panel(wcs);
					wcpanel_list.add(wcp);
				}
			}
		}else if(other_exr instanceof c_exRecord) {
			exrecord = (c_exRecord)other_exr;
			c_exRecord tmp_ce = (c_exRecord)other_exr;
			// wcpanel_list ������ֱ�
			if(tmp_ce.getCount_set()==0) {
				cpanel_list = new ArrayList<>();
			}else {
				cpanel_list = new ArrayList<>();
				for(c_set cs : tmp_ce.getc_set_ary()) {
					cset_panel cp = new cset_panel(cs);
					cpanel_list.add(cp);
				}
			}
		}else {
			System.err.println("exrecord ���� �޾ƿ��� ����");
		}
		
		/* �޾ƿ� ��� ��� �г� */
		JLabel today_Label = new JLabel(exrecord.getEx().getname());
		today_Label.setHorizontalAlignment(SwingConstants.CENTER);
		gbc_default.anchor = GridBagConstraints.WEST;
		gbc_default.gridx = 0;
		gbc_default.gridy = 0;
		add(today_Label,gbc_default);
		
		JLabel weight_label = new JLabel("����");
		weight_label.setHorizontalAlignment(SwingConstants.CENTER);
		gbc_default.anchor = GridBagConstraints.WEST;
		gbc_default.gridx = 2;
		gbc_default.gridy = 0;
		add(weight_label,gbc_default);
		
		JLabel count_label = new JLabel("Ƚ��");
		count_label.setHorizontalAlignment(SwingConstants.CENTER);
		gbc_default.anchor = GridBagConstraints.WEST;
		gbc_default.gridx = 3;
		gbc_default.gridy = 0;
		add(count_label,gbc_default);
		
		JLabel resttime_label = new JLabel("�޽Ľð�");
		resttime_label.setHorizontalAlignment(SwingConstants.CENTER);
		gbc_default.anchor = GridBagConstraints.WEST;
		gbc_default.gridx = 4;
		gbc_default.gridy = 0;
		add(resttime_label,gbc_default);
		
		/* ��Ʈ ����Ʈ �г� */
		set_list_panel = new JPanel();
		set_list_panel.setBackground(Color.WHITE);
		gb = new GridBagLayout();
		gb.rowHeights = new int[]{50, 50, 50, 50, 50};
		gb.columnWidths = new int[]  {100,50,50,50,50,50};
		set_list_panel.setLayout(gb);
		
		gbc_default = new GridBagConstraints();
		gbc_default.fill = GridBagConstraints.BOTH;
		gbc_default.gridx = 0;
		gbc_default.gridy = 1;
		gbc_default.gridheight = 5;
		gbc_default.gridwidth = 5;
		JScrollPane sp = new JScrollPane(set_list_panel);
		add(sp, gbc_default);
		
		// ó�� ��Ʈ ����Ʈ �����ϱ�
		if(other_exr instanceof wc_exRecord) {
			repaint_wclist_panel();
		}else if(other_exr instanceof c_exRecord) {
			repaint_clist_panel();
		}
		
		
		
		// ��Ʈ�߰� ��ư Ŭ��
		JButton addset_button = new JButton("��Ʈ �߰�");
		ActionListener addset_listener= new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �߰��� ��Ʈ ���� �޾ƿ���
				if(exrecord instanceof wc_exRecord) {
					add_wcsetpage asp = new add_wcsetpage(new exRecord(exrecord));
					asp.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					asp.setModal(true);
					asp.setVisible(true);
					// exrecord�� wc_exRecord �����ϱ�
					wc_exRecord tmp_wce = (wc_exRecord)exrecord;								// ���� ����(�̸�,setgoal) �־��ֱ�
					wc_set wcs = new wc_set(Integer.valueOf(asp.get_weight()),Integer.valueOf(asp.get_count()));
					wcs.setRest_time(asp.get_resttime());
					tmp_wce.add_wcset(wcs);
					exrecord = tmp_wce;
					dayRecordpage.dayrecord.set_exr(exrecord);
					// wc_set �г� �߰�
					wcset_panel wcp = new wcset_panel(wcs);
					if(wcpanel_list == null)
						wcpanel_list = new ArrayList<>();
					wcpanel_list.add(wcp);
					repaint_wclist_panel();
				}
				else if(exrecord instanceof c_exRecord) {
					add_csetpage asp = new add_csetpage(new exRecord(exrecord));
					asp.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					asp.setModal(true);
					asp.setVisible(true);
					// exrecord�� wc_exRecord �����ϱ�
					c_exRecord tmp_ce = (c_exRecord)exrecord;								// ���� ����(�̸�,setgoal) �־��ֱ�
					c_set wcs = new c_set(Integer.valueOf(asp.get_count()));
					wcs.setRest_time(asp.get_resttime());
					tmp_ce.add_wcset(wcs);
					exrecord = tmp_ce;
					dayRecordpage.dayrecord.set_exr(exrecord);
					// wc_set �г� �߰�
					cset_panel wcp = new cset_panel(wcs);
					if(cpanel_list == null)
						cpanel_list = new ArrayList<>();
					cpanel_list.add(wcp);
					repaint_clist_panel();
				}/*
				else if (exrecord instanceof t_exRecord) {
					add_tsetpage asp = new add_tsetpage(new exRecord(exrecord));
					asp.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					asp.setModal(true);
					asp.setVisible(true);
					// exrecord�� wc_exRecord �����ϱ�
					t_exRecord tmp_ce = (t_exRecord)exrecord;								// ���� ����(�̸�,setgoal) �־��ֱ�
					t_set wcs = new t_set(asp.get_goaltime());
					wcs.setRest_time(asp.get_resttime());
					tmp_ce.add_tset(wcs);
					exrecord = tmp_ce;
					dayRecordpage.dayrecord.set_exr(exrecord);
					// wc_set �г� �߰�
					 wcp = new cset_panel(wcs);
					if(cpanel_list == null)
						cpanel_list = new ArrayList<>();
					cpanel_list.add(wcp);
					repaint_clist_panel();
				}*/
				
			
				
			}
		};
		addset_button.addActionListener(addset_listener);
		gbc_default =new GridBagConstraints();		
		gbc_default.anchor = GridBagConstraints.EAST;
		gbc_default.gridx = 1;
		gbc_default.gridwidth = 2;
		add(addset_button, gbc_default);
		
		//���� ��ư
		JButton savedR_button = new JButton("����");
		ActionListener savedR_listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dayRecordpage.dayrecord.set_exr(exrecord);
				dispose();
				
			}
		};
		savedR_button.addActionListener(savedR_listener);
		gbc_default.anchor = GridBagConstraints.CENTER;
		gbc_default.ipadx = 20;
		gbc_default.gridx = 3;
		gbc_default.gridwidth = 2;
		add(savedR_button, gbc_default);
	}
	
	// wcpanel_list�� �������� wclist_panel repaint�ϱ�
	private void repaint_wclist_panel(){
		if(wcpanel_list != null || !wcpanel_list.isEmpty()) {  												// � 1���� ���� ���
			GridBagConstraints gbc = new GridBagConstraints();									// exRecord �� ���� ���� gbc
			gbc.fill = GridBagConstraints.BOTH;
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridwidth = 5;
			int count = 0;
			
			for(wcset_panel exp : wcpanel_list) {
				exp.set_setlabel(count+1);
				gbc.gridy = count++;
				set_list_panel.add(exp,gbc);
			}
		}else
			set_list_panel.removeAll();
		
		set_list_panel.revalidate();															// � ���� �г� �ʱ�ȭ
		set_list_panel.repaint();
	}
	private void repaint_clist_panel(){
		if(cpanel_list != null || !cpanel_list.isEmpty()) {  												// � 1���� ���� ���
			GridBagConstraints gbc = new GridBagConstraints();									// exRecord �� ���� ���� gbc
			gbc.fill = GridBagConstraints.BOTH;
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridwidth = 5;
			int count = 0;
			
			set_list_panel.removeAll();
			for(cset_panel exp : cpanel_list) {
				exp.set_setlabel(count+1);
				gbc.gridy = count++;
				set_list_panel.add(exp,gbc);
			}
		}else
			set_list_panel.removeAll();
		
		set_list_panel.revalidate();															// � ���� �г� �ʱ�ȭ
		set_list_panel.repaint();
	}
	
	// ���� * Ƚ�� ��Ʈ �г�
	class wcset_panel extends JPanel{
		JLabel set_lable;
		GridBagConstraints gbc;
		public wcset_panel(wc_set wcs) {
			GridBagLayout gbl = new GridBagLayout();
			gbl.columnWidths = new int[] {100,50,50,50,50,50};
			gbl.rowHeights = new int[] {50,50};
			
			this.setLayout(gbl);
			this.setBackground(Color.YELLOW);
			
			// �� ��° ��Ʈ���� ��Ÿ���� �� 
			set_lable = new JLabel(get_setnum()+"��Ʈ");
			gbc = new GridBagConstraints();
			set_gbc(0, 0,GridBagConstraints.BOTH);
			this.add(set_lable,gbc);
			
			JLabel goal_label = new JLabel("��ǥ");
			gbc = new GridBagConstraints();
			set_gbc(1, 0,GridBagConstraints.BOTH);
			this.add(goal_label,gbc);
			
			
			// ��ǥ ���� �ʵ�
			JTextField gweight_textfield = new JTextField();
			gweight_textfield.setText(Integer.toString(wcs.getWeight()));
			gbc = new GridBagConstraints();
			set_gbc(2, 0,GridBagConstraints.HORIZONTAL);
			this.add(gweight_textfield,gbc);
			
			// ��ǥ Ƚ��
			JTextField gcount_textfield = new JTextField();
			gcount_textfield.setText(Integer.toString(wcs.getCount()));
			gbc = new GridBagConstraints();
			set_gbc(3, 0,GridBagConstraints.HORIZONTAL);
			this.add(gcount_textfield,gbc);
			
			// �޽Ľð�
			JTextField resttime_textfield = new JTextField();
			resttime_textfield.setText(wcs.getRest_time().format(DateTimeFormatter.ofPattern("mm:ss")));
			gbc = new GridBagConstraints();
			set_gbc(4, 0,GridBagConstraints.HORIZONTAL);
			this.add(resttime_textfield,gbc);
			
			// ���� ��
			JLabel performed_label = new JLabel("����");
			gbc = new GridBagConstraints();
			set_gbc(1, 1,GridBagConstraints.BOTH);
			this.add(performed_label,gbc);
			
			// ���� ���� �ʵ�
			JTextField pweight_textfield = new JTextField();
			pweight_textfield.setText(Integer.toString(wcs.getP_weight()));
			gbc = new GridBagConstraints();
			set_gbc(2, 1,GridBagConstraints.HORIZONTAL);
			this.add(pweight_textfield,gbc);
			
			// ���� Ƚ��
			JTextField pcount_textfield = new JTextField();
			pcount_textfield.setText(Integer.toString(wcs.getP_count()));
			gbc = new GridBagConstraints();
			set_gbc(3, 1,GridBagConstraints.HORIZONTAL);
			this.add(pcount_textfield,gbc);
			
			// ��ǥ �� ���� ����
			JButton update_btn = new JButton("����");
			ActionListener updateBtn_listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					wcs.setWeight(Integer.valueOf(gweight_textfield.getText()));
					wcs.setCount(Integer.valueOf(gcount_textfield.getText()));
					wcs.setP_count(Integer.valueOf(pweight_textfield.getText()));
					wcs.setP_weight(Integer.valueOf(pcount_textfield.getText()));
					wcs.setRest_time(resttime_textfield.getText());
				}
			};
			update_btn.addActionListener(updateBtn_listener);
			set_gbc(5, 1,GridBagConstraints.HORIZONTAL);
			gbc.insets = new Insets(0, 0, 0, 5);
			this.add(update_btn,gbc);
			
			// ���� ��ư
			JButton delete_btn = new JButton("����");
			ActionListener delBtn_listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (getindex() >=0) {
						System.out.println(getindex());
						wcpanel_list.remove(getindex());
					}else {
						System.out.println("�߸���");
					}
					//�޾ƿ� � ������ ���� ex_list_panel ������Ʈ
					
					repaint_wclist_panel();
				}
			};
			delete_btn.addActionListener(delBtn_listener);
			set_gbc(5, 0,GridBagConstraints.HORIZONTAL);
			gbc.insets= new Insets(0, 0, 0, 5);
			this.add(delete_btn,gbc);
			
			
			// ��ǥ-> ���� load ��ư // ���� default �� 0���� ����. load ������ ��ǥ �� ������
			JButton load_btn = new JButton("Load");
			ActionListener loadBtn_listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					wcs.performed_update();
					pweight_textfield.setText(Integer.toString(wcs.getP_weight()));
					pcount_textfield.setText(Integer.toString(wcs.getP_count()));
				}
			};
			load_btn.addActionListener(loadBtn_listener);
			set_gbc(4, 1,GridBagConstraints.HORIZONTAL);
			gbc.insets = new Insets(0, 0, 0, 5);
			this.add(load_btn,gbc);
		}
		public void set_setlabel(int setnum) {
			set_lable.setText(setnum + "��Ʈ");
		}
		private int getindex() {
			return wcpanel_list.indexOf(this);
		}
		
		private int get_setnum() {
			if(wcpanel_list == null || wcpanel_list.size()==0)
				return 1;
			else 
				return wcpanel_list.size()+1;
		}
		private void set_gbc(int x, int y, int fill) {
			gbc.gridx = x;
			gbc.gridy = y;
			gbc.fill = fill;
		}
	}
	
	
	class cset_panel extends JPanel{
		JLabel set_lable;
		GridBagConstraints gbc;
		public cset_panel(c_set cs) {
			GridBagLayout gbl = new GridBagLayout();
			gbl.columnWidths = new int[] {100,50,50,50,50,50};
			gbl.rowHeights = new int[] {50,50};
			
			this.setLayout(gbl);
			this.setBackground(Color.YELLOW);
			
			// �� ��° ��Ʈ���� ��Ÿ���� �� 
			set_lable = new JLabel(get_setnum()+"��Ʈ");
			gbc = new GridBagConstraints();
			set_gbc(0, 0,GridBagConstraints.BOTH);
			this.add(set_lable,gbc);
			
			JLabel goal_label = new JLabel("��ǥ");
			gbc = new GridBagConstraints();
			set_gbc(1, 0,GridBagConstraints.BOTH);
			this.add(goal_label,gbc);
			
			
			// ��ǥ Ƚ��
			JTextField gcount_textfield = new JTextField();
			gcount_textfield.setText(Integer.toString(cs.getCount()));
			gbc = new GridBagConstraints();
			set_gbc(3, 0,GridBagConstraints.HORIZONTAL);
			this.add(gcount_textfield,gbc);
			
			// �޽Ľð�
			JTextField resttime_textfield = new JTextField();
			resttime_textfield.setText(cs.getRest_time().format(DateTimeFormatter.ofPattern("mm:ss")));
			gbc = new GridBagConstraints();
			set_gbc(4, 0,GridBagConstraints.HORIZONTAL);
			this.add(resttime_textfield,gbc);
			
			// ���� ��
			JLabel performed_label = new JLabel("����");
			gbc = new GridBagConstraints();
			set_gbc(1, 1,GridBagConstraints.BOTH);
			this.add(performed_label,gbc);
			
			// ���� Ƚ��
			JTextField pcount_textfield = new JTextField();
			pcount_textfield.setText(Integer.toString(cs.getP_count()));
			gbc = new GridBagConstraints();
			set_gbc(3, 1,GridBagConstraints.HORIZONTAL);
			this.add(pcount_textfield,gbc);
			
			// ��ǥ �� ���� ����
			JButton update_btn = new JButton("����");
			ActionListener updateBtn_listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cs.setCount(Integer.valueOf(gcount_textfield.getText()));
					cs.setP_count(Integer.valueOf(pcount_textfield.getText()));
					cs.setRest_time(resttime_textfield.getText());
				}
			};
			update_btn.addActionListener(updateBtn_listener);
			set_gbc(5, 1,GridBagConstraints.HORIZONTAL);
			gbc.insets = new Insets(0, 0, 0, 5);
			this.add(update_btn,gbc);
			
			// ���� ��ư
			JButton delete_btn = new JButton("����");
			ActionListener delBtn_listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (getindex() >=0) {
						System.out.println(getindex());
						wcpanel_list.remove(getindex());
					}else {
						System.out.println("�߸���");
					}
					//�޾ƿ� � ������ ���� ex_list_panel ������Ʈ
					
					repaint_wclist_panel();
				}
			};
			delete_btn.addActionListener(delBtn_listener);
			set_gbc(5, 0,GridBagConstraints.HORIZONTAL);
			gbc.insets= new Insets(0, 0, 0, 5);
			this.add(delete_btn,gbc);
			
			
			// ��ǥ-> ���� load ��ư // ���� default �� 0���� ����. load ������ ��ǥ �� ������
			JButton load_btn = new JButton("Load");
			ActionListener loadBtn_listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cs.performed_update();
					pcount_textfield.setText(Integer.toString(cs.getP_count()));
				}
			};
			load_btn.addActionListener(loadBtn_listener);
			set_gbc(4, 1,GridBagConstraints.HORIZONTAL);
			gbc.insets = new Insets(0, 0, 0, 5);
			this.add(load_btn,gbc);
		}
		public void set_setlabel(int setnum) {
			set_lable.setText(setnum + "��Ʈ");
		}
		private int getindex() {
			return wcpanel_list.indexOf(this);
		}
		
		private int get_setnum() {
			if(wcpanel_list == null || wcpanel_list.size()==0)
				return 1;
			else 
				return wcpanel_list.size()+1;
		}
		private void set_gbc(int x, int y, int fill) {
			gbc.gridx = x;
			gbc.gridy = y;
			gbc.fill = fill;
		}
	}
	/*
	class tset_panel extends JPanel{
		JLabel set_lable;
		GridBagConstraints gbc;
		public tset_panel(t_set ts) {
			GridBagLayout gbl = new GridBagLayout();
			gbl.columnWidths = new int[] {100,50,50,50,50,50};
			gbl.rowHeights = new int[] {50,50};
			
			this.setLayout(gbl);
			this.setBackground(Color.YELLOW);
			
			// �� ��° ��Ʈ���� ��Ÿ���� �� 
			set_lable = new JLabel(get_setnum()+"��Ʈ");
			gbc = new GridBagConstraints();
			set_gbc(0, 0,GridBagConstraints.BOTH);
			this.add(set_lable,gbc);
			
			JLabel goal_label = new JLabel("��ǥ");
			gbc = new GridBagConstraints();
			set_gbc(1, 0,GridBagConstraints.BOTH);
			this.add(goal_label,gbc);
			
			
			// ��ǥ �ð�
			JTextField goaltime_textfield = new JTextField();
			goaltime_textfield.setText(Integer.toString(ts.getCount()));
			gbc = new GridBagConstraints();
			set_gbc(3, 0,GridBagConstraints.HORIZONTAL);
			this.add(goaltime_textfield,gbc);
			
			// �޽Ľð�
			JTextField resttime_textfield = new JTextField();
			resttime_textfield.setText(ts.getRest_time().format(DateTimeFormatter.ofPattern("mm:ss")));
			gbc = new GridBagConstraints();
			set_gbc(4, 0,GridBagConstraints.HORIZONTAL);
			this.add(resttime_textfield,gbc);
			
			// ���� ��
			JLabel performed_label = new JLabel("����");
			gbc = new GridBagConstraints();
			set_gbc(1, 1,GridBagConstraints.BOTH);
			this.add(performed_label,gbc);
			
			// ���� Ƚ��
			JTextField pcount_textfield = new JTextField();
			pcount_textfield.setText(Integer.toString(ts.getP_count()));
			gbc = new GridBagConstraints();
			set_gbc(3, 1,GridBagConstraints.HORIZONTAL);
			this.add(pcount_textfield,gbc);
			
			// ��ǥ �� ���� ����
			JButton update_btn = new JButton("����");
			ActionListener updateBtn_listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ts.setCount(Integer.valueOf(goaltime_textfield.getText()));
					ts.setP_count(Integer.valueOf(pcount_textfield.getText()));
					ts.setRest_time(resttime_textfield.getText());
				}
			};
			update_btn.addActionListener(updateBtn_listener);
			set_gbc(5, 1,GridBagConstraints.HORIZONTAL);
			gbc.insets = new Insets(0, 0, 0, 5);
			this.add(update_btn,gbc);
			
			// ���� ��ư
			JButton delete_btn = new JButton("����");
			ActionListener delBtn_listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (getindex() >=0) {
						System.out.println(getindex());
						wcpanel_list.remove(getindex());
					}else {
						System.out.println("�߸���");
					}
					//�޾ƿ� � ������ ���� ex_list_panel ������Ʈ
					
					repaint_wclist_panel();
				}
			};
			delete_btn.addActionListener(delBtn_listener);
			set_gbc(5, 0,GridBagConstraints.HORIZONTAL);
			gbc.insets= new Insets(0, 0, 0, 5);
			this.add(delete_btn,gbc);
			
			
			// ��ǥ-> ���� load ��ư // ���� default �� 0���� ����. load ������ ��ǥ �� ������
			JButton load_btn = new JButton("Load");
			ActionListener loadBtn_listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ts.performed_update();
					pcount_textfield.setText(Integer.toString(ts.getP_count()));
				}
			};
			load_btn.addActionListener(loadBtn_listener);
			set_gbc(4, 1,GridBagConstraints.HORIZONTAL);
			gbc.insets = new Insets(0, 0, 0, 5);
			this.add(load_btn,gbc);
		}
		public void set_setlabel(int setnum) {
			set_lable.setText(setnum + "��Ʈ");
		}
		private int getindex() {
			return wcpanel_list.indexOf(this);
		}
		
		private int get_setnum() {
			if(wcpanel_list == null || wcpanel_list.size()==0)
				return 1;
			else 
				return wcpanel_list.size()+1;
		}
		private void set_gbc(int x, int y, int fill) {
			gbc.gridx = x;
			gbc.gridy = y;
			gbc.fill = fill;
		}
	}
	*/
	// � �̸����� ����� set�ϱ�
	private void setEx_byname() {
		for(int i = 0; i < exlist.size(); i++) {
			if (exrecord.getEx().getname().equals(exlist.get(i).getname())) { // �̸����� �˻��ϱ� -> ã���� ��  
				exrecord.setEx(exlist.get(i));
				break;
			}
			else {
				continue;
			}
		}
	}
	
	class savedR_check_dialog extends JDialog{
		public savedR_check_dialog(){
			setSize(200,100);
			JLabel label = new JLabel("�Է��� Ȯ���ϼ���");
			label.setHorizontalAlignment(JLabel.CENTER);
			add(label,BorderLayout.CENTER);
			JButton bt = new JButton("Ȯ��");
			bt.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					savedR_check_dialog.this.dispose();
				}
			});
			add(bt,BorderLayout.SOUTH);
			setLocation(200, 200);
		}
		
	}
}
