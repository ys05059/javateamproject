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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.awt.event.ActionEvent;

import Login.login;
import Main.UserRecord;
import set단위class.dayRecord;
import 희석.CalendarDemo;
public class login {

	private JFrame frame;
	private JTextField loginField; //로그인 입력 가능한 필드
	private JPasswordField passwordField; //비밀번호 입력 가능한 필드
	private JButton regiBtn; //가입 버튼
	private JButton loginBtn; //로그인 버튼
	private ArrayList<dayRecord> curr_dR_ary;

	
	ArrayList<User> userinfoList = new ArrayList<User>(); //class 배열리스트 설정
	
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) { //JDK에 내장된 lookandfeel 사용
						if ("Nimbus".equals(info.getName())) {
							UIManager.setLookAndFeel(info.getClassName());
							break;
						}
						else { //Nimbus lookandfeel이 없을 경우
							UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
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
		imgPanel loginP = new imgPanel(new ImageIcon("image\\login.jpg").getImage()); //imgPanel 클래스의 생성자 사용
		frame.setSize(loginP.getDim()); // frame의 size 를 사진 크기에 맞춘다
		frame.setPreferredSize(loginP.getDim()); //setSize만 하면 적용이 안 될 수 있으므로 setPreferredsize도 적용
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
				//user 파일에서 모든 유저의 ID 와 PW 를 가져와
				
				userinfoList = bringUserInfo();   //클래스 배열 얕은복사 수행
				//이 줄 이후부터는 모든 User의 정보가 userinfoList에 있다. (bringUserInfo를 실행함으로써)
				//유저의 수는 유동적일 것이므로, 배열리스트를 써 ID 와 PW를 각각 받아온다.
				ArrayList<String> idnow = new ArrayList<String>();
				ArrayList<String> pwnow = new ArrayList<String>();
				for(int i = 0; i < userinfoList.size(); i++) {
					idnow.add(userinfoList.get(i).getID());
					pwnow.add(userinfoList.get(i).getPW());
				}

				for(int i = 0; i < idnow.size(); i++) { //유저 정보를 비교하여 
					if(idnow.get(i).equals(loginField.getText()) &&  pwnow.get(i).equals(getPasswordInfo())) {
						//로그인 성공 창을 띄운다
						JOptionPane.showMessageDialog(null, "login success!!", "축하합니다", JOptionPane.INFORMATION_MESSAGE);
						//로그인 정보가 확인되면 아이디로 저장된 userworkinfo 파일이 있는지 확인하고 
						//있다면 deserialize해온다. 이후 dayRecord에 정보를 전달함으로써 이전 저장 지점부터 보여지게 할 수 있다.
						//없다면 deserialize 불가능하므로 초기 화면을 띄운다.
						File f = new File("userworkinfo//" + idnow.get(i) +".dat");
						if(f.exists()) {
							UserRecord AA = new UserRecord(); //UserRecord 객체 생성
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
						//calendar 띄우기
						CalendarDemo A = new CalendarDemo(curr_dR_ary, idnow.get(i));
						A.setVisible(true);
						frame.setVisible(false); //로그인 창 끄기
						loginok = true;
						break;
					}
					
				}
				if(loginok == false) { //로그인이 안 된 경우
					//로그인 안되었다고 창을 띄운 후, field를 "" 로 초기화해준다.
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
				signup A =new signup(); //회원가입 창을 띄운다
			}
			
		});
		loginP.add(regiBtn);
		
		frame.pack();
		
	}
	
	//UserinfoList의 getter setter
	public ArrayList<User> getUserinfoList() {
		return userinfoList;
	}

	public void setUserinfoList(ArrayList<User> userinfoList) {
		this.userinfoList = userinfoList;
	}

	public ArrayList<User> bringUserInfo(){ //user폴더에서 파일들을 deserialize하는 method
		ArrayList<User> userinfoList = new ArrayList<User>(); //User class 배열리스트 설정
		File whatsin = new File("user\\");
		String[] filenames = whatsin.list(); //폴더 안에 파일들을 list화하기.
		
		if(filenames != null) { //폴더 안 파일이 존재할 경우, 
			for (int i = 0; i < filenames.length; i++) { //폴더 안 파일의 수가 곧 회원 정보의 수이므로
				User AA = new User(); //User 클래스 객체 생성
				try {
					//파일 이름을 user의 ID 로 설정해 놓았으므로,
					//사용자가 입력한 ID 에 기반하여 클래스를 deserialize한다.
					ObjectInputStream inputStream =
							new ObjectInputStream (new FileInputStream("user\\" + filenames[i]));
					AA = (User)inputStream.readObject(); //클래스 정보 가져오기
					userinfoList.add(AA); //add to ArrayList<user>
					inputStream.close();
				}catch(FileNotFoundException e) { //파일을 찾을 수 없을 때
					System.err.println("can't find file");
					System.exit(0);
				}catch(ClassNotFoundException e) {//클래스를 찾을 수 없을 때
					System.err.println("problem occuered");
					System.exit(0);		
				}catch(IOException e) {//I/0오류가 발생할 경우
					System.err.println("problem occuered");
					System.exit(0);
				}
			}
		}
		return userinfoList;
	}
	
	private String getPasswordInfo() { //password는 일반 text와 달리 getText()로 받아오지 못하므로
		//따로 클래스를 만들어 password를 가져오는 method를 구현했다.
		String str = new String(passwordField.getPassword());
		return str;
	}
	
	// 로그아웃 시 다시 login창을 띄우기 위한 method
	public void turnonLoginPage() {
		frame.setVisible(true);
	}
}
