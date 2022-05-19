package Main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Login.loginpage;
import set단위class.dayRecord;

import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;

public class dayRecordpage extends JFrame {

	private JPanel defaultpanel;
	private JTextField textField;
	private JTextField textField_1;
	private dayRecord dayrecord;
	
	public dayRecordpage() {
		 setTitle("exRecordpage	");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 450, 300);
			defaultpanel = new JPanel();
			defaultpanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(defaultpanel);
			defaultpanel.setLayout(new BorderLayout(0, 0));
			
			JPanel panel = new JPanel();
			defaultpanel.add(panel, BorderLayout.NORTH);
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			JLabel lblNewLabel = new JLabel("\uB0A0\uC9DC");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(lblNewLabel);
			
			textField_1 = new JTextField();
			panel.add(textField_1);
			textField_1.setColumns(10);
			
			JPanel panel_1 = new JPanel();
			defaultpanel.add(panel_1, BorderLayout.SOUTH);
			panel_1.setLayout(new BorderLayout(0, 0));
			
			JButton addexr_button = new JButton("운동 추가");
			addexr_button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					addexRecordpage exr = new addexRecordpage();
					exr.setVisible(true);
				}
			});
			
			panel_1.add(addexr_button, BorderLayout.EAST);
			
			JPanel panel_2 = new JPanel();
			panel_1.add(panel_2, BorderLayout.WEST);
			
			JLabel today_weight_label = new JLabel("today weight");
			panel_2.add(today_weight_label);
			
			textField = new JTextField();
			panel_2.add(textField);
			textField.setColumns(10);
			
			JPanel ex_select_panel = new JPanel();
			defaultpanel.add(ex_select_panel, BorderLayout.CENTER);
			
			JList list = new JList();
			ex_select_panel.add(list);
	}

}
