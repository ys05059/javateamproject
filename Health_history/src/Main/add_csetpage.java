package Main;

import java.awt.Color;
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

import set????class.exRecord;

public class add_csetpage extends JDialog {
	private JPanel contentPane;
	private JTextField count_textField;
	private JTextField resttime_textfield; 
	public boolean exit = false;
	public add_csetpage(exRecord exr) {
		setTitle("add_csetpage");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(203, 254, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel exname_Label = new JLabel(exr.getEx().getname());
		exname_Label.setHorizontalAlignment(SwingConstants.CENTER);
		exname_Label.setFont(new Font("????", Font.PLAIN, 20));
		exname_Label.setBounds(153, 32, 133, 21);
		contentPane.add(exname_Label);
		
		JLabel count_label = new JLabel("Ƚ??");
		count_label.setBounds(132, 120, 52, 15);
		contentPane.add(count_label);
		
		count_textField = new JTextField();
		count_textField.setBounds(218, 114, 106, 21);
		contentPane.add(count_textField);
		count_textField.setColumns(10);
		
		JLabel resttime_label = new JLabel("?޽? ?ð?");
		resttime_label.setBounds(132, 160, 52, 15);
		contentPane.add(resttime_label);
		
		resttime_textfield = new JTextField();
		resttime_textfield.setText("02:00");
		resttime_textfield.setBounds(218, 157, 106, 21);
		contentPane.add(resttime_textfield);
		resttime_textfield.setColumns(10);
		
		JButton addset_Btn = new JButton("??Ʈ ?߰?");
		addset_Btn.setBounds(171, 195, 95, 23);
		addset_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!exRecordpage.check_int_format(count_textField.getText())) {
					JOptionPane.showMessageDialog(null, "??ǥ Ƚ???? ?????? ?Է°????մϴ?","????", JOptionPane.ERROR_MESSAGE);
					count_textField.setText("");
					return;
				}
				if(!exRecordpage.check_time_format(resttime_textfield.getText())){
					JOptionPane.showMessageDialog(null, "?޽Ľð??? ?ٽ? ?Է??ϼ???","????", JOptionPane.ERROR_MESSAGE);
					resttime_textfield.setText("00:00");
					return;
				}
				exit = true;
				add_csetpage.this.dispose();
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
