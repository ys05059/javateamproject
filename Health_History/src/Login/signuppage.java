package Login;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.JButton;

public class signuppage extends JFrame {
	private JTextField textField;
	private JPasswordField passwordField;
	
	private ActionListener signup;
	
	public signuppage(ArrayList<User> user_ary) {
		User new_user = new User();
			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(61, 62, 106, 59);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(159, 69, 186, 46);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\uD68C\uC6D0\uAC00\uC785");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 30));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(131, 10, 148, 59);
		panel.add(lblNewLabel_1);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBounds(61, 112, 106, 59);
		panel.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setBounds(159, 119, 186, 46);
		panel.add(passwordField);
		
		JButton btnNewButton = new JButton("가입하기");
		btnNewButton.setBounds(184, 188, 95, 23);
		panel.add(btnNewButton);
		signup = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				new_user.setID(textField.getText());
				new_user.setPW(passwordField.getText());
				user_ary.add(new_user);
				signup_complete sf  = new signup_complete();
				dispose();
			}
		};
		btnNewButton.addActionListener(signup);
		
		
	}
}
class signup_complete extends JFrame implements ActionListener{
	
	public signup_complete(){
		super("");
		setSize(200,100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton bt = new JButton("가입이 완료 되었습니다");
		add(bt);
		bt.addActionListener(this);
		setLocation(200, 0);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();
	}
}
