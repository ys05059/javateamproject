package Login;

import java.awt.EventQueue;


import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import Login.User;
import Login.login;
import Login.signuppage;
import Main.dayRecordpage;
import set´ÜÀ§class.dayRecord;
import set´ÜÀ§class.exRecord;
import set´ÜÀ§class.exlistClass;
import Èñ¼®.CalendarDemo;
public class login {

	private JFrame frame;
	private JTextField loginField;
	private JPasswordField passwordField;
	private JButton regiBtn;
	private JButton loginBtn;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
						if ("Nimbus".equals(info.getName())) {
							UIManager.setLookAndFeel(info.getClassName());
							break;
						}
					}
					login window = new login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Health History");
		frame.setBounds(100, 100, 700, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		//from https://kr.freepik.com/free-vector/modern-login-page-template-with-blur-background_18141219.htm#&position=2&from_view=detail#&position=2&from_view=detail
		imgPanel loginP = new imgPanel(new ImageIcon("C:\\github\\javateamproject\\Health_history\\image\\login.jpg").getImage());
		frame.setSize(loginP.getDim());
		frame.setPreferredSize(loginP.getDim());
		frame.getContentPane().add(loginP);
		
		loginField = new JTextField();
		loginField.setFont(new Font("ÈÞ¸Õ¸ðÀ½T", Font.PLAIN, 18));
		loginField.setBounds(263, 309, 230, 43);
		loginP.add(loginField);
		loginField.setColumns(10);
		loginField.setBorder(null);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(263, 374, 230, 36);
		passwordField.setBorder(null);
		loginP.add(passwordField);
		
		loginBtn = new JButton("LOG IN");
		loginBtn.setFont(new Font("ÈÞ¸Õ¸ðÀ½T", Font.PLAIN, 18));
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String rightid = "shin";
				String rightpwd = "1234";
				
				if(!(rightid.equals(loginField.getText()) && 
						rightpwd.equals(passwordField.getText()))) {
					JOptionPane.showMessageDialog(null, "login failed.");
				}else {
					JOptionPane.showMessageDialog(null, "login success!!");
					ArrayList<dayRecord> dR_ary = new ArrayList<>();
					dayRecordpage frame2 = new dayRecordpage(dR_ary);
					frame2.setVisible(true);
					frame.setVisible(false);
					
					
				}
			}
		});
		loginBtn.setBackground(new Color(0, 0, 139));
		loginBtn.setForeground(new Color(255, 255, 255));
		loginBtn.setBounds(202, 467, 297, 52);
		loginP.add(loginBtn);
		
		regiBtn = new JButton("REGISTER");
		regiBtn.setFont(new Font("ÈÞ¸Õ¸ðÀ½T", Font.PLAIN, 18));
		regiBtn.setForeground(new Color(255, 255, 255));
		regiBtn.setBackground(new Color(0, 0, 139));
		regiBtn.setBounds(202, 421, 297, 43);
		regiBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				signup A =new signup();
				
				
			}
			
		});
		loginP.add(regiBtn);
		
		frame.pack();
		
	}
}
