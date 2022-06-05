package Main;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
		setTitle("add_wcsetpage");
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
		
		JLabel weight_Label = new JLabel("무게");
		weight_Label.setBounds(132, 79, 52, 15);
		getContentPane().add(weight_Label);

		weight_textField = new JTextField();
		weight_textField.setBounds(218, 76, 106, 21);
		contentPane.add(weight_textField);
		weight_textField.setColumns(10);

		JLabel count_label = new JLabel("횟수");
		count_label.setBounds(132, 120, 52, 15);
		contentPane.add(count_label);
		
		count_textField = new JTextField();
		count_textField.setBounds(218, 114, 106, 21);
		contentPane.add(count_textField);
		count_textField.setColumns(10);
		
		JLabel resttime_label = new JLabel("휴식 시간");
		resttime_label.setBounds(132, 160, 70, 15);
		contentPane.add(resttime_label);
		
		resttime_textfield = new JTextField();
		resttime_textfield.setText("02:00");
		resttime_textfield.setBounds(218, 157, 106, 21);
		contentPane.add(resttime_textfield);
		resttime_textfield.setColumns(10);
		
		JButton addset_Btn = new JButton("세트 추가");
		addset_Btn.setBounds(171, 195, 95, 23);
		addset_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!exRecordpage.check_int_format(weight_textField.getText())) {
					JOptionPane.showMessageDialog(null, "목표 무게는 정수만 입력가능합니다","경고", JOptionPane.ERROR_MESSAGE);
					weight_textField.setText("0");
					return;
				}
				if(!exRecordpage.check_int_format(count_textField.getText())) {
					JOptionPane.showMessageDialog(null, "목표 횟수는 정수만 입력가능합니다","경고", JOptionPane.ERROR_MESSAGE);
					count_textField.setText("0");
					return;
				}
				if(!exRecordpage.check_time_format(resttime_textfield.getText())){
					JOptionPane.showMessageDialog(null, "휴식시간을 다시 입력하세요","경고", JOptionPane.ERROR_MESSAGE);
					resttime_textfield.setText("00:00");
					return;
				}
				add_wcsetpage.this.dispose();
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
