package Main;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


import set단위class.exRecord;

public class add_wcsetpage extends JDialog {
	private JPanel contentPane;
	private JTextField weight_textField;
	private JTextField count_textField;
	private JTextField resttime_textfield; 
	public add_wcsetpage(exRecord exr) {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel exname_Label = new JLabel(exr.getEx().getname());
		exname_Label.setHorizontalAlignment(SwingConstants.CENTER);
		exname_Label.setFont(new Font("����", Font.PLAIN, 20));
		exname_Label.setBounds(153, 32, 133, 21);
		contentPane.add(exname_Label);
		
		JLabel weight_Label = new JLabel("����");
		weight_Label.setBounds(132, 79, 52, 15);
		getContentPane().add(weight_Label);

		weight_textField = new JTextField();
		weight_textField.setBounds(218, 76, 106, 21);
		contentPane.add(weight_textField);
		weight_textField.setColumns(10);

		JLabel count_label = new JLabel("Ƚ��");
		count_label.setBounds(132, 120, 52, 15);
		contentPane.add(count_label);
		
		count_textField = new JTextField();
		count_textField.setBounds(218, 114, 106, 21);
		contentPane.add(count_textField);
		count_textField.setColumns(10);
		
		JLabel resttime_label = new JLabel("�޽� �ð�");
		resttime_label.setBounds(132, 160, 52, 15);
		contentPane.add(resttime_label);
		
		resttime_textfield = new JTextField();
		resttime_textfield.setBounds(218, 157, 106, 21);
		contentPane.add(resttime_textfield);
		resttime_textfield.setColumns(10);
		
		JButton addset_Btn = new JButton("��Ʈ �߰�");
		addset_Btn.setBounds(171, 195, 95, 23);
		addset_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!weight_textField.getText().equals("") && !count_textField.getText().equals("") && !resttime_textfield.getText().equals("")) {											// �ϴ� ���� �Է����� �ʾ��� ���� ����ó���� ���� ����
					add_wcsetpage.this.dispose();
				}
				// ����ó�� �ؾ���
				/*else {
					input_check_dialog icd = new input_check_dialog();
					icd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					icd.setModal(true);
					icd.setVisible(true);
				}*/
			}
		});
		contentPane.add(addset_Btn);
		
		
	}
	
	public String get_weight() {
		return weight_textField.getText();
	}
	public String get_count() {
		return count_textField.getText();
	}
	public String get_resttime() {
		return resttime_textfield.getText();
	}
}
