package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import set����class.dayRecord;
import set����class.exRecord;
import set����class.exercise;
import set����class.exlistClass;
import set����class.wc_exRecord;
import set����class.wc_set;

public class exRecordpage extends JDialog{
	private JPanel defaultpanel;
	private JTextField weight_textField;
	private JTextField today_textField;
	private JPanel set_list_panel; 
	private dayRecord dayrecord;
	private exRecord exrecord;
	private ArrayList<wcset_panel> wcpanel_list; 
	private ArrayList<exercise> exlist;
	
	public exRecordpage(exRecord other_exr, dayRecord pre_dayRecord) {
		setTitle("exRecordpage	");
		setSize(500,400);
		GridBagLayout gb = new GridBagLayout();
		gb.rowHeights = new int[] {50, 50,50,50,50,50,50};
		gb.columnWidths = new int[] {100,100,50,50,50};
		setLayout(gb);
		dayrecord = new dayRecord();
		GridBagConstraints gbc_default = new GridBagConstraints();
		
		/* �޾ƿ� ������� exRecord ���� ä��� */
		// other_exr�� name �� setgoal ������ ���� 
		exlistClass elc = new exlistClass("ALL_WORKOUT");
		exlist = elc.get_exlist();
		if (other_exr.getEx().getcalmethod().equals("")) {				//ù��° �Է�
				exrecord = new exRecord(other_exr.getEx().getname(),other_exr.getSet_goal());
				setEx_byname();  //� �̸����� � ���� setting
				if(exrecord.getEx().getcalmethod().equals("���� * Ƚ��")) {
					exrecord = new wc_exRecord(exrecord);
				}
		}else if(other_exr instanceof wc_exRecord) {					// �ι�° ���� �� ���� * Ƚ���� �
			exrecord = (wc_exRecord)other_exr;
			wc_exRecord tmp_wce = (wc_exRecord)other_exr; //exRecode Ŭ������ ����ȯ(wc_exRecord �� exRecord�� �ڽ� Ŭ�����̱� ����)
			// wcpanel_list ������ֱ�
			if(tmp_wce.getCount_set()==0) {
				wcpanel_list = new ArrayList<>();
			}else {
				wcpanel_list = new ArrayList<>();
				for(wc_set wcs : tmp_wce.getWc_set_ary()) {
					wcset_panel wcp = new wcset_panel(wcs);
					wcpanel_list.add(wcp);
				}
				System.out.println(wcpanel_list.size());
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
		
				
		/* ��Ʈ ����Ʈ �г� */
		set_list_panel = new JPanel();
		set_list_panel.setBackground(Color.WHITE);
		gb = new GridBagLayout();
		gb.rowHeights = new int[]{50, 50, 50, 50, 50};
		gb.columnWidths = new int[] {100,100,50,75,75};
		set_list_panel.setLayout(gb);
		
		gbc_default = new GridBagConstraints();
		gbc_default.fill = GridBagConstraints.BOTH;
		gbc_default.gridx = 0;
		gbc_default.gridy = 1;
		gbc_default.gridheight = 5;
		gbc_default.gridwidth = 5;
		JScrollPane sp = new JScrollPane(set_list_panel);
		add(sp, gbc_default);
		
		if(!other_exr.getEx().getcalmethod().equals("")) {
			repaint_wclist_panel();
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
					tmp_wce.add_wcset(wcs);
					wc_set pwcs = new wc_set();
					exrecord = tmp_wce;
					dayRecordpage.dayrecord.set_exr(exrecord);
					// wc_set �г� �߰�
					wcset_panel wcp = new wcset_panel(wcs);
					if(wcpanel_list == null)
						wcpanel_list = new ArrayList<>();
					wcpanel_list.add(wcp);
					repaint_wclist_panel();
				}
				
			}
		};
		addset_button.addActionListener(addset_listener);
		gbc_default =new GridBagConstraints();		
		gbc_default.anchor = GridBagConstraints.EAST;
		gbc_default.gridx = 2;
		gbc_default.gridwidth = 2;
		add(addset_button, gbc_default);
		
		//���� ��ư
		JButton savedR_button = new JButton("����");
		ActionListener savedR_listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dayRecordpage.dayrecord.set_exr(exrecord);
				//other_exr.shallow_copy(exrecord);
				dispose();
				
			}
		};
		savedR_button.addActionListener(savedR_listener);
		gbc_default.anchor = GridBagConstraints.CENTER;
		gbc_default.ipadx = 20;
		gbc_default.gridx = 4;
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
			
			set_list_panel.removeAll();
			for(wcset_panel exp : wcpanel_list) {
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
		public wcset_panel(wc_set wcs) {
			GridBagLayout gbl = new GridBagLayout();
			gbl.columnWidths = new int[] {100,100,50,50,50};
			gbl.rowHeights = new int[] {50,50};
			
			this.setLayout(gbl);
			this.setBackground(Color.YELLOW);

			
			
			int count;
			if(wcpanel_list == null || wcpanel_list.size()==0)
				count =1;
			else {
				count = wcpanel_list.size()+1;
			}
			
			// �� ��° ��Ʈ���� ��Ÿ���� �� 
			JLabel set_lable = new JLabel(Integer.toString(count)+"��Ʈ");
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.BOTH;
			gbc.gridx = 0;
			gbc.gridy = 0;
			this.add(set_lable,gbc);
			
			// ��ǥ ���� �ʵ�
			JTextField gweight_textfield = new JTextField();
			gweight_textfield.setText("��ǥ����"+Integer.toString(wcs.getWeight()));
			gbc = new GridBagConstraints();
			gbc.fill=GridBagConstraints.HORIZONTAL;
			gbc.gridx = 1;
			gbc.gridy = 0;
			this.add(gweight_textfield,gbc);
			
			// ���� ���� �ʵ�
			JTextField pweigth_textfield = new JTextField();
			pweigth_textfield.setText("������ ����");
			gbc = new GridBagConstraints();
			gbc.fill=GridBagConstraints.HORIZONTAL;
			gbc.gridx = 1;
			gbc.gridy = 1;
			this.add(pweigth_textfield,gbc);
			
			// ��ǥ �� ���� ����
			JButton update_btn = new JButton("����");
			ActionListener updateBtn_listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//exRecordpage exrp = new exRecordpage(other_exr);
					//exrp.setVisible(true);
				}
			};
			update_btn.addActionListener(updateBtn_listener);
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridx= 4;
			gbc.gridy= 1;
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
			gbc.gridx= 4;
			gbc.gridy= 0;
			gbc.insets= new Insets(0, 0, 0, 0);
			this.add(delete_btn,gbc);
			
			
			// ��ǥ Ƚ��
			JLabel gcount_label = new JLabel("��ǥȽ��:"+wcs.getCount());
			gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.BOTH;
			gbc.gridx = 2;
			gbc.gridy = 0;
			this.add(gcount_label,gbc);
			
			// ���� Ƚ��
			JLabel pcount_label = new JLabel("����Ƚ��:" + wcs.getCount());
			gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.BOTH;
			gbc.gridx = 2;
			gbc.gridy = 1;
			this.add(pcount_label,gbc);
			
			JLabel resttime_label = new JLabel("�޽Ľð�");
			gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.BOTH;
			gbc.gridx = 3;
			gbc.gridy = 0;
			this.add(resttime_label,gbc);
			
			// ��ǥ-> ���� load ��ư // ���� default �� 0���� ����. load ������ ��ǥ �� ������
			JButton load_btn = new JButton("Load");
			ActionListener loadBtn_listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//wcs�� wc_record ã�Ƽ� method ����
					
					if(exrecord instanceof wc_exRecord) {
						try {
							((wc_exRecord) exrecord).set_pwc();
						} catch (CloneNotSupportedException e2) {
							System.out.println("clone error");
						}
					}
					
					
				}
			};
			load_btn.addActionListener(loadBtn_listener);
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridx= 3;
			gbc.gridy= 1;
			gbc.insets = new Insets(0, 0, 0, 5);
			this.add(load_btn,gbc);
		}
		private int getindex() {
			return wcpanel_list.indexOf(this);
		}
	}
	
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
