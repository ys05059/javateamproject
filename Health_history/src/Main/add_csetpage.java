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

public class add_csetpage extends JDialog {
	private JPanel contentPane;
	private JTextField count_textField;
	private JTextField resttime_textfield; 
	public add_csetpage(exRecord exr) {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel exname_Label = new JLabel(exr.getEx().getname());
		exname_Label.setHorizontalAlignment(SwingConstants.CENTER);
		exname_Label.setFont(new Font("굴림", Font.PLAIN, 20));
		exname_Label.setBounds(153, 32, 133, 21);
		contentPane.add(exname_Label);
		
		JLabel count_label = new JLabel("횟수");
		count_label.setBounds(132, 120, 52, 15);
		contentPane.add(count_label);
		
		count_textField = new JTextField();
		count_textField.setBounds(218, 114, 106, 21);
		contentPane.add(count_textField);
		count_textField.setColumns(10);
		
		JLabel resttime_label = new JLabel("휴식 시간");
		resttime_label.setBounds(132, 160, 52, 15);
		contentPane.add(resttime_label);
		
		resttime_textfield = new JTextField();
		resttime_textfield.setBounds(218, 157, 106, 21);
		contentPane.add(resttime_textfield);
		resttime_textfield.setColumns(10);
		
		JButton addset_Btn = new JButton("세트 추가");
		addset_Btn.setBounds(171, 195, 95, 23);
		addset_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!count_textField.getText().equals("") && !resttime_textfield.getText().equals("")) {										
					add_csetpage.this.dispose();
				}
				// 예외처리 해야함 // 시간은 mm:ss 형식 제한 필요
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
	public String get_count() {
		return count_textField.getText();
	}
	public String get_resttime() {
		return resttime_textfield.getText();
	}
}
