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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.awt.event.ActionEvent;

import Login.User;
import Login.login;
import Main.dayRecordpage;
import set����class.dayRecord;
import set����class.exRecord;
import set����class.exlistClass;
import ��.CalendarDemo;
public class login {

	private JFrame frame;
	private JTextField loginField;
	private JPasswordField passwordField;
	private JButton regiBtn;
	private JButton loginBtn;
	/**
	 * Launch the application.
	 */
	
	ArrayList<User> userinfoList = new ArrayList<User>(); //class �迭����Ʈ ����
	
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
	 * @throws FileNotFoundException 
	 */
	private void initialize() {
		//ȸ�� ���� �ҷ��� �迭����Ʈ�� �����ϱ�.
		

		frame = new JFrame("Health History");
		frame.setBounds(100, 100, 700, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		//from https://kr.freepik.com/free-vector/modern-login-page-template-with-blur-background_18141219.htm#&position=2&from_view=detail#&position=2&from_view=detail
		imgPanel loginP = new imgPanel(new ImageIcon("C:\\github\\javateamproject\\Health_history\\image\\login.jpg").getImage());
		frame.setSize(loginP.getDim());
		frame.setPreferredSize(loginP.getDim());
		frame.getContentPane().add(loginP);
		
		loginField = new JTextField();
		loginField.setFont(new Font("�޸ո���T", Font.PLAIN, 18));
		loginField.setBounds(263, 309, 230, 43);
		loginP.add(loginField);
		loginField.setColumns(10);
		loginField.setBorder(null);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(263, 374, 230, 36);
		passwordField.setBorder(null);
		loginP.add(passwordField);
		
		loginBtn = new JButton("LOG IN");
		loginBtn.setFont(new Font("�޸ո���T", Font.PLAIN, 18));
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ȸ�� ������ �ߵ� �� �ߵ� ���� ������ �����;� �Ѵ�.
				userinfoList = bringUserInfo();   //Ŭ���� �迭 �������� ����
				System.out.println(userinfoList.size() + "in login.java");
				ArrayList<String> idnow = new ArrayList<String>();
				ArrayList<String> pwnow = new ArrayList<String>();
				for(int i = 0; i < userinfoList.size(); i++) {
					idnow.add(userinfoList.get(i).getID());
					pwnow.add(userinfoList.get(i).getPW());
				}

				
				for(int i = 0; i < idnow.size(); i++) {
					if(idnow.get(i).equals(loginField.getText()) &&  pwnow.get(i).equals(getPasswordInfo())) {
						JOptionPane.showMessageDialog(null, "login success!!");
						ArrayList<dayRecord> dR_ary = new ArrayList<>();
//						dayRecordpage frame2 = new dayRecordpage(dR_ary);
//						frame2.setVisible(true);
//						frame.dispose(); //�α��� â ����
						break;
					}
					else {
						JOptionPane.showMessageDialog(null, "login failed.");
						loginField.setText("");
						passwordField.setText("");
					}
				}
//				if(!(rightid.equals(loginField.getText()) && 
//						rightpwd.equals(passwordField.getText()))) {
//					JOptionPane.showMessageDialog(null, "login failed.");
//				}else {
//					JOptionPane.showMessageDialog(null, "login success!!");
//					ArrayList<dayRecord> dR_ary = new ArrayList<>();
//					dayRecordpage frame2 = new dayRecordpage(dR_ary);
//					frame2.setVisible(true);
//					frame.setVisible(false);

			}
		});
		loginBtn.setBackground(new Color(0, 0, 139));
		loginBtn.setForeground(new Color(255, 255, 255));
		loginBtn.setBounds(202, 467, 297, 52);
		loginP.add(loginBtn);
		
		regiBtn = new JButton("REGISTER");
		regiBtn.setFont(new Font("�޸ո���T", Font.PLAIN, 18));
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
	
	
	public ArrayList<User> getUserinfoList() {
		return userinfoList;
	}

	public void setUserinfoList(ArrayList<User> userinfoList) {
		this.userinfoList = userinfoList;
	}

	public ArrayList<User> bringUserInfo(){
		ArrayList<User> userinfoList = new ArrayList<User>(); //class �迭����Ʈ ����
		File whatsin = new File("user\\");
		String[] filenames = whatsin.list();
		
		if(filenames != null) {
			for (int i = 0; i < filenames.length; i++) {
				User AA = new User();
				try {
					ObjectInputStream inputStream =
							new ObjectInputStream (new FileInputStream("user\\" + filenames[i]));
					AA = (User)inputStream.readObject(); //Ŭ���� ���� ��������
					System.out.println(AA.getGender()); 
					userinfoList.add(AA); //add to ArrayList<user>
					inputStream.close();
				}catch(FileNotFoundException e) {
					System.err.println("can't find file");
					System.exit(0);
				}catch(ClassNotFoundException e) {
					System.err.println("problem occuered");
					System.exit(0);		
				}catch(IOException e) {
					System.err.println("problem occuered");
					System.exit(0);
				}
			}
		}
		return userinfoList;
	}
	
	private String getPasswordInfo() {
		String str = new String(passwordField.getPassword());
		return str;
	}
}
