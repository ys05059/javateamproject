package Main;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class addexRecordpage extends JFrame {
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	
	public addexRecordpage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uC6B4\uB3D9\uBA85");
		lblNewLabel.setBounds(110, 90, 52, 15);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(193, 87, 106, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\uBAA9\uD45C \uC138\uD2B8\uC218");
		lblNewLabel_1.setBounds(92, 141, 87, 15);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(193, 138, 106, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("\uC6B4\uB3D9 \uCD94\uAC00");
		btnNewButton.setBounds(162, 188, 95, 23);
		contentPane.add(btnNewButton);
	}
	
}
