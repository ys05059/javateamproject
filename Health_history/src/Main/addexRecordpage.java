package Main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import set����class.exlistClass;

// dayRecordpage�� ����Ǿ� �ִ� ��߰� ������

public class addexRecordpage extends JDialog {
	private JPanel contentPane;
	private JTextField exname_field;
	private JTextField setgoal_field;
	private JComboBox<String> catecombo; //ī�װ� �� �� �ִ� �ڽ�
	private JComboBox<String> excombo;//ī�װ��� �ԷµǸ� �׿� �ش��ϴ� � �� �� �ִ� �ڽ�
	private exlistClass allwork; //ALL_WORKOUT���� arraylist�̾ƿ��� Ŭ����
	public boolean exit =  false; 		// ������������ üũ�ϴ� ����
	
	private final ImageIcon addRecordB = new ImageIcon("image\\batang1.jpg");
	
	String nowcate = "";
	String nowwork = "";
	ArrayList<String> cate = new ArrayList<>();
	ArrayList<String> work = new ArrayList<>();
	
	public addexRecordpage() {
		setTitle("add_exRecordpage");
		allwork = new exlistClass("ALL_WORKOUT"); 
		cate = allwork.getcatetoStringlist();
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setBackground(new Color(203, 254, 255));
		contentPane.setLayout(null);
		
		JLabel catename_Label = new JLabel("Category");
		catename_Label.setBounds(92, 40, 87, 15);
		contentPane.add(catename_Label);
		
		catecombo = new JComboBox<String>(cate.toArray(new String[cate.size()]));
		//jcombobox���� arratlist�� �� �� �����Ƿ�, list�� ��ȯ�ؼ� ������
		catecombo.setBounds(193, 38, 106, 21);
		catecombo.setSelectedIndex(0);
		JComboHandler combohd = new JComboHandler();
		//������ � �ൿ�� �� ���� ���� 
		catecombo.addActionListener(combohd);
		contentPane.add(catecombo);
		
		
		JLabel exname_Label = new JLabel("\uC6B4\uB3D9\uBA85");
		exname_Label.setBounds(110, 90, 52, 15);
		contentPane.add(exname_Label);
		
		excombo = new JComboBox<String>(work.toArray(new String[work.size()]));
		excombo.setBounds(193, 87, 106, 21);
		work = allwork.getworktoStringlist("����");
		excombo.setModel(new DefaultComboBoxModel(work.toArray(new String[work.size()])));
		JComboHandler2 combohd2 = new JComboHandler2();
		excombo.addActionListener(combohd2);
		contentPane.add(excombo);
		
		JLabel setgoal_Label = new JLabel("\uBAA9\uD45C \uC138\uD2B8\uC218");
		setgoal_Label.setBounds(92, 141, 87, 15);
		contentPane.add(setgoal_Label);
		
		setgoal_field = new JTextField();
		setgoal_field.setBounds(193, 138, 106, 21);
		contentPane.add(setgoal_field);
		
		JButton addBtn = new JButton("�߰�");
		addBtn.setBounds(162, 188, 95, 23);
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(exRecordpage.check_int_format(setgoal_field.getText())) {
					exit = true;
					addexRecordpage.this.dispose();
				}else {
					JOptionPane.showMessageDialog(null, "��ǥ ��Ʈ���� �ٽ� �Է��ϼ���");
					setgoal_field.setText("");
				}
			}
		});
		contentPane.add(addBtn);
	}

	public String get_exname() {
		return excombo.getSelectedItem().toString();
	}
	
	public String get_excate() {
		return catecombo.getSelectedItem().toString();
	}

	public int get_setgoal() {
		return Integer.valueOf(setgoal_field.getText());
	}
	
	
	private class JComboHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			nowcate = "";
			nowcate = catecombo.getSelectedItem().toString();
			work = allwork.getworktoStringlist(nowcate);
			excombo.setModel(new DefaultComboBoxModel(work.toArray(new String[work.size()])));
			//ī�װ� �Է¿� ���� exercise combobox�� �ε��� �޶����� �ϱ�

		}
		
	}
	
	private class JComboHandler2 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			nowwork = excombo.getSelectedItem().toString();
		}
		
	}
}
