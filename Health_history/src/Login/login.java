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
import Main.UserRecord;
import Main.dayRecordpage;
import set단위class.dayRecord;
import set단위class.exRecord;
import set단위class.exlistClass;
import 희석.CalendarDemo;
public class login {

	private JFrame frame;
	private JTextField loginField;
	private JPasswordField passwordField;
	private JButton regiBtn;
	private JButton loginBtn;
	private ArrayList<dayRecord> curr_dR_ary;
	/**
	 * Launch the application.
	 */
	
	ArrayList<User> userinfoList = new ArrayList<User>(); //class 배열리스트 설정
	
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
		//회원 정보 불러와 배열리스트에 저장하기.

		frame = new JFrame("Health History");
		frame.setBounds(100, 100, 700, 700);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		//from https://kr.freepik.com/free-vector/modern-login-page-template-with-blur-background_18141219.htm#&position=2&from_view=detail#&position=2&from_view=detail
		imgPanel loginP = new imgPanel(new ImageIcon("image\\login.jpg").getImage());
		frame.setSize(loginP.getDim());
		frame.setPreferredSize(loginP.getDim());
		frame.getContentPane().add(loginP);
		
		loginField = new JTextField();
		loginField.setFont(new Font("휴먼모음T", Font.PLAIN, 18));
		loginField.setBounds(263, 309, 230, 43);
		loginP.add(loginField);
		loginField.setColumns(10);
		loginField.setBorder(null);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(263, 374, 230, 36);
		passwordField.setBorder(null);
		loginP.add(passwordField);
		
		loginBtn = new JButton("LOG IN");
		loginBtn.setFont(new Font("휴먼모음T", Font.PLAIN, 18));
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean loginok = false;
				//회원 가입을 했든 안 했든 유저 정보는 가져와야 한다.
				userinfoList = bringUserInfo();   //클래스 배열 얕은복사 수행
				
				ArrayList<String> idnow = new ArrayList<String>();
				ArrayList<String> pwnow = new ArrayList<String>();
				for(int i = 0; i < userinfoList.size(); i++) {
					idnow.add(userinfoList.get(i).getID());
					pwnow.add(userinfoList.get(i).getPW());
				}

				
				for(int i = 0; i < idnow.size(); i++) {
					if(idnow.get(i).equals(loginField.getText()) &&  pwnow.get(i).equals(getPasswordInfo())) {
						JOptionPane.showMessageDialog(null, "login success!!", "축하합니다", JOptionPane.INFORMATION_MESSAGE);
						//로그인 정보가 확인되면 아이디로 저장된 파일이 있는지 확인하고 있다면 deserialize
						File f = new File("userworkinfo//" + idnow.get(i) +".dat");
						if(f.exists()) {
							UserRecord AA = new UserRecord();
							try {
								ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("userworkinfo//" +idnow.get(i) +".dat"));
								AA = (UserRecord)inputStream.readObject();
								inputStream.close();
							}catch(FileNotFoundException e1) {
								System.err.println("Can't find file.");
								System.exit(0);
							}catch(ClassNotFoundException e1) {
								System.err.println("error1");
								System.exit(0);
							}catch(IOException e1) {
								e1.printStackTrace();
								System.exit(0);
							}
							curr_dR_ary = AA.getUserWorkInfo();
							
						}else {
							curr_dR_ary = new ArrayList<dayRecord>();
							System.out.println("nothing");
						}
						
						CalendarDemo A = new CalendarDemo(curr_dR_ary, idnow.get(i));
						A.setVisible(true);
						frame.dispose(); //로그인 창 종료
						loginok = true;
						break;
					}
					
				}
				if(loginok == false) {
					JOptionPane.showMessageDialog(null, "login failed..", "경고", JOptionPane.WARNING_MESSAGE);
					loginField.setText("");
					passwordField.setText("");
				}

			}
		});
		loginBtn.setBackground(new Color(0, 0, 139));
		loginBtn.setForeground(new Color(255, 255, 255));
		loginBtn.setBounds(202, 467, 297, 52);
		loginP.add(loginBtn);
		
		regiBtn = new JButton("REGISTER");
		regiBtn.setFont(new Font("휴먼모음T", Font.PLAIN, 18));
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
		ArrayList<User> userinfoList = new ArrayList<User>(); //class 배열리스트 설정
		File whatsin = new File("user\\");
		String[] filenames = whatsin.list();
		
		if(filenames != null) {
			for (int i = 0; i < filenames.length; i++) {
				User AA = new User();
				try {
					ObjectInputStream inputStream =
							new ObjectInputStream (new FileInputStream("user\\" + filenames[i]));
					AA = (User)inputStream.readObject(); //클래스 정보 가져오기
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
