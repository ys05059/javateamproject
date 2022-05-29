package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import set����class.dayRecord;
import set����class.exRecord;

public class dayRecordpage extends JFrame {

	private JPanel defaultpanel;
	private JTextField weight_textField;
	private JTextField today_textField;
	private JPanel ex_list_panel; 
	private dayRecord dayrecord;
	private ArrayList<expanel> expanel_list; 
	
	public dayRecordpage(final ArrayList<dayRecord> dR_ary) {
		setTitle("exRecordpage	");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,400);
		GridBagLayout gb = new GridBagLayout();
		gb.rowHeights = new int[] {50, 50,50,50,50,50,50};
		gb.columnWidths = new int[] {100,100,50,50,50};
		setLayout(gb);
		dayrecord = new dayRecord();
		GridBagConstraints gbc_default = new GridBagConstraints();
		
		/* ��¥ �Է� �г� */
		JLabel today_Label = new JLabel("������ ��¥");
		today_Label.setHorizontalAlignment(SwingConstants.CENTER);
		gbc_default.anchor = GridBagConstraints.WEST;
		gbc_default.gridx = 0;
		gbc_default.gridy = 0;
		add(today_Label,gbc_default);
		
		today_textField = new JTextField();
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
				if(today_textField.getText().equals("") || weight_textField.getText().equals("")) {
					savedR_check_dialog icd = new savedR_check_dialog();
					icd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					icd.setModal(true);
					icd.setVisible(true);
				}
				// dayRecord�� dR_ary�� �߰�
				dR_ary.add(dayrecord);
				
				// �޷� �������� ���ư�
				
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
		if(!expanel_list.isEmpty()) {  												// � 1���� ���� ���
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
	
	class expanel extends JPanel{
		public expanel(final exRecord other_exr) {
			GridBagLayout gbl = new GridBagLayout();
			gbl.columnWidths = new int[] {100,100,50,50,50};
			gbl.rowHeights = new int[] {50};
			
			this.setLayout(gbl);
			this.setBackground(Color.YELLOW);
			
			JLabel ex_name = new JLabel(other_exr.getEx().getName());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.BOTH;
			gbc.gridx = 0;
			gbc.gridy = 0;
			this.add(ex_name,gbc);
			
			JLabel set_label = new JLabel("��Ʈ ��");
			gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.BOTH;
			gbc.gridx = 1;
			gbc.gridy = 0;
			this.add(set_label,gbc);
			
			JLabel setnum_label = new JLabel(Integer.toString(other_exr.getSet_goal()));
			gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.BOTH;
			gbc.gridx = 2;
			gbc.gridy = 0;
			this.add(setnum_label,gbc);
			
			JButton update_btn = new JButton("����");
			ActionListener updateBtn_listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					exRecordpage exrp = new exRecordpage(other_exr);
					exrp.setVisible(true);
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
						expanel_list.remove(getindex());
					}else {
						System.out.println("�߸���");
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
