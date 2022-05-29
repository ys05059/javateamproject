package Login;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;


public class loginpage extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	private ActionListener signup_listener;
	
	public loginpage(final ArrayList<User> user_ary) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(null);
		
		// ������ ��
		JLabel lblNewLabel = new JLabel("Health History");
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(114, 29, 197, 37);
		panel.add(lblNewLabel);
		
		// ID ��
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(107, 74, 108, 45);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_1);
		
		// ID �Է¹ޱ�
		textField = new JTextField();
		textField.setBounds(205, 86, 106, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		// PW ��
		JLabel lblNewLabel_1_1 = new JLabel("PassWord");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(107, 117, 108, 45);
		panel.add(lblNewLabel_1_1);
		
		//PW �Է¹ޱ�
		passwordField = new JPasswordField();
		passwordField.setBounds(205, 129, 106, 21);
		panel.add(passwordField);
		
		
		// �α��� ��ư
		JButton login_Button = new JButton("�α���");
		login_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ��ϵ� ȸ������ üũ
				for(User u : user_ary) {
					System.out.println("�����:"+ u.getID());
					if(u.getID().equals(textField.getText()) && u.getPW().equals(passwordField.getText())) {
						SubFrame1 sb = new SubFrame1();
						sb.setVisible(true);
						dispose();
					}
				}
				
			}
		});	
		login_Button.setBounds(120, 172, 95, 23);
		panel.add(login_Button);
		
		// ȸ������ ��ư
		JButton signup_Button = new JButton("ȸ������");
		signup_listener = new ActionListener(){
			// ȸ�� ���� â ����
			@Override
			public void actionPerformed(ActionEvent e) {
				signuppage sh = new signuppage(user_ary);
				sh.setVisible(true);
			}
		};
		signup_Button.addActionListener(signup_listener);
		
		signup_Button.setBounds(227, 172, 95, 23);
		panel.add(signup_Button);
	}
}
class SubFrame1 extends JFrame implements ActionListener{
	public SubFrame1(){
		super("");
		setSize(200,100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton bt = new JButton("�α��� �Ϸ�");
		add(bt);
		bt.addActionListener(this);
		setLocation(200, 0);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();
		/*
		 * �� â�� ���鼭 ���� ������ �ҷ�����
		 */
	}
}
