package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import Main.exRecordpage.cset_panel;
import set����class.c_set;
import set����class.dayRecord;
import set����class.exRecord;
import set����class.wc_exRecord;

public class dayRecordpage extends JDialog {

	private JPanel defaultpanel;
	private JTextField weight_textField;
	private JTextField today_textField;
	private JPanel ex_list_panel; 
	static dayRecord dayrecord;
	private ArrayList<expanel> expanel_list; 
	boolean exist; // ó�� �Է��ϴ°���, �ִ��� �������
	
	public dayRecordpage(final ArrayList<dayRecord> dR_ary,dayRecord dr) {//�̺κ� final�� ���ϴ� ���� ���� final �߰��߽��ϴ�(����)
		
		setTitle("dayRecordpage	");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,400);
		GridBagLayout gb = new GridBagLayout();
		gb.rowHeights = new int[] {50, 50,50,50,50,50,50};
		gb.columnWidths = new int[] {100,100,50,50,50};
		setLayout(gb);
		
		// ���� ���� �ִ����� ���� �б�
		dayrecord = dr;
		if(dr.getExr_ary().size()>0) {
			exist = true;
			expanel_list = new ArrayList<>();	
			for (exRecord exr :dr.getExr_ary()) {
				expanel tmp_exp = new expanel(exr);
				expanel_list.add(tmp_exp);
			}
		}else {
			exist = false;
			expanel_list = new ArrayList<>();
			
		}
		// ���� dr�� ex_ary�� ���ִٸ� expanel_list�� �������� �߰��������
		
		GridBagConstraints gbc_default = new GridBagConstraints();
		
		/* ��¥ �Է� �г� */
		JLabel today_Label = new JLabel("������ ��¥");
		today_Label.setHorizontalAlignment(SwingConstants.CENTER);
		gbc_default.anchor = GridBagConstraints.WEST;
		gbc_default.gridx = 0;
		gbc_default.gridy = 0;
		add(today_Label,gbc_default);
		
		today_textField = new JTextField();
		today_textField.setText(dayrecord.getToday_date().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		gbc_default.fill = GridBagConstraints.HORIZONTAL;
		gbc_default.gridx = 1;
		gbc_default.gridy = 0;
		gbc_default.anchor = GridBagConstraints.WEST;
		add(today_textField,gbc_default);
		
		
		// ������ ��
		JLabel today_weight_label = new JLabel("������ ������");
		gbc_default = new GridBagConstraints();
		gbc_default.fill = GridBagConstraints.HORIZONTAL;
		gbc_default.gridx = 0;
		gbc_default.gridy = 6;
		add(today_weight_label,gbc_default);
		
		// ������ textField
		weight_textField = new JTextField();
		if(dayrecord.getToday_weight()>0) {
			weight_textField.setText(Double.toString(dayrecord.getToday_weight()));
		}
		gbc_default = new GridBagConstraints();
		gbc_default.fill = GridBagConstraints.HORIZONTAL;
		gbc_default.gridx = 1;
		gbc_default.gridy = 6;
		add(weight_textField,gbc_default);
		
		/* � ����Ʈ �г� */
		ex_list_panel = new JPanel();
		ex_list_panel.setBackground(Color.WHITE);
		gb = new GridBagLayout();
		gb.rowHeights = new int[]{50, 50, 50, 50, 50};
		gb.columnWidths = new int[] {100,100,50,75,75};
		ex_list_panel.setLayout(gb);
		
		gbc_default = new GridBagConstraints();
		gbc_default.fill = GridBagConstraints.BOTH;
		gbc_default.gridx = 0;
		gbc_default.gridy = 1;
		gbc_default.gridheight = 5;
		gbc_default.gridwidth = 5;
		JScrollPane sp = new JScrollPane(ex_list_panel);
		add(sp, gbc_default);
		if(!expanel_list.isEmpty()) {
			repaint_exlist_panel();
		}
		
		// ��߰� ��ư Ŭ��
		JButton addexr_button = new JButton("� �߰�");
		ActionListener addex_listener= new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �߰��� � ���� �޾ƿ���
				addexRecordpage exrp = new addexRecordpage();
				exrp.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				exrp.setModal(true);
				exrp.setVisible(true);
				exRecord tmp_ex = new exRecord(exrp.get_exname(),exrp.get_setgoal());
				
				// �޾ƿ� � ���� ����
				dayrecord.add_exr(tmp_ex);
				expanel tmp_exp = new expanel(tmp_ex);
				if (expanel_list == null)
					expanel_list = new ArrayList<>();	
				expanel_list.add(tmp_exp);
				
				//�޾ƿ� � ������ ���� ex_list_panel ������Ʈ
				repaint_exlist_panel();
			}
		};
		addexr_button.addActionListener(addex_listener);
		gbc_default =new GridBagConstraints();		
		gbc_default.anchor = GridBagConstraints.EAST;
		gbc_default.gridx = 2;
		gbc_default.gridwidth = 2;
		add(addexr_button, gbc_default);
		
		//���� ��ư
		JButton savedR_button = new JButton("����");
		ActionListener savedR_listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ��� date�� �����Դ� �־���� ������ ����â
				if(today_textField.getText().equals("") ) {
					savedR_check_dialog icd = new savedR_check_dialog();
					icd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					icd.setModal(true);
					icd.setVisible(true);
				}
				//������ ����
				dayrecord.setToday_weight(Double.valueOf(weight_textField.getText()));				
				
				// dayRecord�� dR_ary�� �߰�
				// ������ �߰� ������ �ٽ� ����
				if(exist == false)
					dR_ary.add(dayrecord);
				else {
					int index=0;
					for(dayRecord tmp : dR_ary) {
						if(dr.getToday_date().equals(tmp.getToday_date()))
							break;
						else 
							index++;
					}
					dR_ary.set(index, dayrecord);
				}
				// �޷� �������� ���ư�
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
	
	
	private void repaint_exlist_panel(){
		if(expanel_list!= null && !expanel_list.isEmpty()) {  												// � 1���� ���� ���
			GridBagConstraints gbc = new GridBagConstraints();									// exRecord �� ���� ���� gbc
			gbc.fill = GridBagConstraints.BOTH;
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridwidth = 5;
			int count = 0;
			
			ex_list_panel.removeAll();
			for(expanel exp : expanel_list) {
				gbc.gridy = count++;
				ex_list_panel.add(exp,gbc);
			}
		}else
			ex_list_panel.removeAll();
		
		ex_list_panel.revalidate();															// � ���� �г� �ʱ�ȭ
		ex_list_panel.repaint();
	}
	/*
	private void update_exlist() {
		for(expanel ep : expanel_list) {
			ep.update_expanel(null););
		}
	}*/
	
	class expanel extends JPanel{
		private JLabel ex_name;
		private JLabel setnum_label;
		
		public expanel(exRecord other_exr) {
			
			GridBagLayout gbl = new GridBagLayout();
			gbl.columnWidths = new int[] {100,100,50,50,50};
			gbl.rowHeights = new int[] {50};
			
			this.setLayout(gbl);
			this.setBackground(Color.YELLOW);
			
			ex_name = new JLabel(other_exr.getEx().getname());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.BOTH;
			gbc.gridx = 0;
			gbc.gridy = 0;
			this.add(ex_name,gbc);
			
		
			JLabel set_label = new JLabel("����/��ǥ");
			gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.BOTH;
			gbc.gridx = 1;
			gbc.gridy = 0;
			this.add(set_label,gbc);
			
			setnum_label = new JLabel("("+Integer.toString(other_exr.getCount_set())+"/"+Integer.toString(other_exr.getSet_goal())+")");
			gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.BOTH;
			gbc.gridx = 2;
			gbc.gridy = 0;
			this.add(setnum_label,gbc);
			
			JButton update_btn = new JButton("����");
			ActionListener updateBtn_listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					exRecordpage exrp;
					exRecord exr = dayrecord.getExr_ary().get(getindex());	
					if(exr instanceof wc_exRecord) {
						exrp = new exRecordpage((wc_exRecord)exr,dayrecord);
					}else {
						exrp = new exRecordpage(exr,dayrecord);
					}
					exrp.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					exrp.setModal(true);
					exrp.setVisible(true);
					
					// â ������ ��
					// � �г� ����Ʈ ����
					exr = dayrecord.getExr_ary().get(getindex());									// ������ exRecord �޾ƿ���
					ex_name.setText(exr.getEx().getname());
					setnum_label.setText("("+Integer.toString(exr.getCount_set())+"/"+Integer.toString(exr.getSet_goal())+")");
					repaint_exlist_panel();
				}
			};
			update_btn.addActionListener(updateBtn_listener);
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridx= 3;
			gbc.gridy= 0;
			gbc.insets = new Insets(0, 0, 0, 5);
			
			
			this.add(update_btn,gbc);
			
			// ���� ��ư
			JButton delete_btn = new JButton("����");
			ActionListener delBtn_listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (getindex() >=0) {
						System.out.println(getindex());
						dayrecord.delete_exr(other_exr);
						expanel_list.remove(getindex());
					}else {
						System.err.println("dayRecordpage: ����� ����");
					}
					//�޾ƿ� � ������ ���� ex_list_panel ������Ʈ
					repaint_exlist_panel();
				}
			};
			delete_btn.addActionListener(delBtn_listener);
			gbc.gridx= 4;
			gbc.gridy= 0;
			gbc.insets= new Insets(0, 0, 0, 0);
			this.add(delete_btn,gbc);
		}
		private int getindex() {
			return expanel_list.indexOf(this);
		}
	}
	
	
	
	public void set_date(LocalDate date) {
		dayrecord.setToday_date(date);
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
