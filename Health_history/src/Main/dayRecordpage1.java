package Main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JList;

public class dayRecordpage1 extends JFrame {

	private JPanel defaultpanel;
	private JTextField textField;
	private JTextField textField_1;
	
	public dayRecordpage1() {
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
			
			
			
			JButton btnNewButton_1 = new JButton("운동 추가");
			panel_1.add(btnNewButton_1, BorderLayout.EAST);
			
			JPanel panel_2 = new JPanel();
			panel_1.add(panel_2, BorderLayout.WEST);
			
			JLabel lblNewLabel_2 = new JLabel("today weight");
			panel_2.add(lblNewLabel_2);
			
			textField = new JTextField();
			panel_2.add(textField);
			textField.setColumns(10);
			
			JPanel ex_select_panel = new JPanel();
			defaultpanel.add(ex_select_panel, BorderLayout.CENTER);
			
			JList list = new JList();
			ex_select_panel.add(list);
	}

}
