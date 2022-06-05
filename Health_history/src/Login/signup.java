package Login;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.Icon;

import Login.User;
import Login.login;
import javax.swing.DefaultComboBoxModel;

public class signup extends JFrame implements ActionListener{

	private JFrame frame;
	private JTextField idInputFIeld;
	private JPasswordField passwordField;
	private JPasswordField passwordField2;
	private String gender;
	private JTextField nicknameFIeld;
	private JTextField inputweightField;
	private JTextField heightField;
	private JTextField muscleField;
	private JTextField bodyField;
	private JButton isIDSame;
	private JButton isPWSame;
	private JComboBox genderCombo;
	
	private User userinfo;
	private login log;
	
	
	ArrayList<User> userinfoList_signup = new ArrayList<User>(); //class 배열리스트 설정
	boolean ispushjungbokBtn = false; //중복버튼 눌렀는지 체크
	boolean ispushinputcheckBtn = false; //입력확인버튼 눌렀는지 쳌
	
	public signup() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
//		before signup, we should bring all user info .
		File whatsin = new File("user\\");
		String[] filenames = whatsin.list();
		
		if(filenames != null) {
			for (int i = 0; i < filenames.length; i++) {
				User AA = new User();
				try {
					ObjectInputStream inputStream =
							new ObjectInputStream (new FileInputStream("user\\" + filenames[i]));
					AA = (User)inputStream.readObject(); //클래스 정보 가져오기
					userinfoList_signup.add(AA); //add to ArrayList<user>
					inputStream.close();
				}catch(FileNotFoundException e) {
					System.err.println("can't find file");
					System.exit(0);
				}catch(ClassNotFoundException e) {
					System.err.println("prlblem occuered");
					System.exit(0);		
				}catch(IOException e) {
					System.err.println("problem occuered");
					System.exit(0);
				}
			}
			
			
		}
		
		
		setVisible(true);
		setBounds(100, 100, 900, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		imgPanel signupPanel = new imgPanel(new ImageIcon("image\\flowwater.png").getImage());
		signupPanel.setBounds(0, 0, 986, 663);
		setSize(signupPanel.getDim());
		setPreferredSize(signupPanel.getDim());
		getContentPane().add(signupPanel);
		
		JLabel registerlabel = new JLabel("\uD68C\uC6D0 \uAC00\uC785");
		registerlabel.setFont(new Font("휴먼매직체", Font.PLAIN, 30));
		registerlabel.setBounds(31, 10, 231, 56);
		signupPanel.add(registerlabel);
		
		ImageIcon IDicon = new ImageIcon("icon\\id.png");
		JLabel IDIcon = new JLabel(IDicon);
		IDIcon.setBounds(31, 76, 50, 50);
	
		signupPanel.add(IDIcon);
		
		JLabel IDlabel = new JLabel("ID \uC785\uB825");
		IDlabel.setFont(new Font("휴먼엑스포", Font.BOLD, 20));
		IDlabel.setBounds(93, 77, 73, 49);
		signupPanel.add(IDlabel);
		
		idInputFIeld = new JTextField();
		idInputFIeld.setFont(new Font("휴먼엑스포", Font.ITALIC, 16));
		idInputFIeld.setBounds(179, 82, 191, 35);
		
		signupPanel.add(idInputFIeld);
		idInputFIeld.setColumns(10);
		
		isIDSame = new JButton("\uC911\uBCF5\uD655\uC778");
		isIDSame.setBackground(new Color(135, 206, 235));
		isIDSame.setFont(new Font("휴먼엑스포", Font.BOLD | Font.ITALIC, 16));
		isIDSame.setBounds(382, 78, 98, 43);
		isIDSame.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean avail = true;
				ArrayList<String> idnow = new ArrayList<String>();
				for(int i = 0; i < userinfoList_signup.size(); i++) {
					idnow.add(userinfoList_signup.get(i).getID());
				}
				for(int i = 0; i < idnow.size(); i++) {
					if(idnow.get(i).equals(idInputFIeld.getText())) {
						JOptionPane.showMessageDialog(null, "이미 존재하는 아이디입니다.");
						idInputFIeld.setText("");
						avail = false;
						ispushjungbokBtn = false;
						break;
					}
				}
				if(avail == true) {
					JOptionPane.showMessageDialog(null, "사용 가능한 아이디입니다.");
					ispushjungbokBtn = true;
				}
				
				
				
			}
			
		});
		signupPanel.add(isIDSame);
		
		ImageIcon PWicon = new ImageIcon("icon\\PW.png");
		JLabel PWIcon = new JLabel(PWicon);
		PWIcon.setBounds(31, 148, 50, 50);
		signupPanel.add(PWIcon);
		
		JLabel PWlabel = new JLabel("PW \uC785\uB825");
		PWlabel.setFont(new Font("휴먼엑스포", Font.BOLD, 20));
		PWlabel.setBounds(93, 149, 83, 49);
		signupPanel.add(PWlabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(179, 155, 191, 35);
		signupPanel.add(passwordField);
		
		JLabel PWlabel2 = new JLabel("PW \uC7AC\uC785\uB825");
		PWlabel2.setFont(new Font("휴먼엑스포", Font.BOLD, 20));
		PWlabel2.setBounds(73, 221, 103, 49);
		signupPanel.add(PWlabel2);
		
		passwordField2 = new JPasswordField();
		passwordField2.setBounds(179, 225, 191, 35);
		signupPanel.add(passwordField2);
		
		isPWSame = new JButton("\uC785\uB825\uD655\uC778");
		isPWSame.setFont(new Font("휴먼엑스포", Font.BOLD | Font.ITALIC, 16));
		isPWSame.setBackground(new Color(135, 206, 235));
		isPWSame.setBounds(382, 221, 98, 43);
		isPWSame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(getPasswordInfo().equals(getPasswordInfo2())) {
					JOptionPane.showMessageDialog(null, "동일한 비밀번호입니다.");
					ispushinputcheckBtn = true;
				}
				else {
					JOptionPane.showMessageDialog(null, "두 입력이 서로 다릅니다. 다시 입력하세요");
					ispushinputcheckBtn = false;
					passwordField.setText("");
					passwordField2.setText("");
				}
				
			}
			
		});
		signupPanel.add(isPWSame);
		
		
		ImageIcon NNicon = new ImageIcon("icon\\nickname.png");
		JLabel NicknameIcon = new JLabel(NNicon);
		NicknameIcon.setBounds(31, 296, 50, 50);
		signupPanel.add(NicknameIcon);
		
		JLabel nicknameLabel = new JLabel("\uB2C9\uB124\uC784");
		nicknameLabel.setFont(new Font("휴먼엑스포", Font.BOLD, 20));
		nicknameLabel.setBounds(103, 303, 73, 49);
		signupPanel.add(nicknameLabel);
		
		nicknameFIeld = new JTextField();
		nicknameFIeld.setFont(new Font("휴먼엑스포", Font.ITALIC, 16));
		nicknameFIeld.setColumns(10);
		nicknameFIeld.setBounds(179, 308, 191, 35);
		signupPanel.add(nicknameFIeld);
		
		ImageIcon genderIcon= new ImageIcon("icon\\gender.png");
		JLabel gendericon = new JLabel(genderIcon);
		gendericon.setBounds(31, 380, 50, 50);
		signupPanel.add(gendericon);
		
		JLabel genderLabel = new JLabel("\uC131\uBCC4");
		genderLabel.setFont(new Font("휴먼엑스포", Font.BOLD, 20));
		genderLabel.setBounds(120, 381, 56, 49);
		signupPanel.add(genderLabel);
		
		genderCombo = new JComboBox();
		genderCombo.addItem("남성");
		genderCombo.addItem("여성");
		genderCombo.setFont(new Font("휴먼엑스포", Font.BOLD, 16));
		genderCombo.setBounds(179, 390, 73, 35);
		signupPanel.add(genderCombo);
		
		
		ImageIcon weightIcon= new ImageIcon("icon\\weight.png");
		JLabel weighticon = new JLabel(weightIcon);
		weighticon.setBounds(600, 76, 50, 50);
		signupPanel.add(weighticon);
		
		JLabel weightLabel = new JLabel("\uCCB4\uC911");
		weightLabel.setFont(new Font("휴먼엑스포", Font.BOLD, 20));
		weightLabel.setBounds(676, 76, 50, 43);
		signupPanel.add(weightLabel);
		
		inputweightField = new JTextField();
		inputweightField.setHorizontalAlignment(SwingConstants.CENTER);
		inputweightField.setFont(new Font("휴먼엑스포", Font.ITALIC, 16));
		inputweightField.setColumns(10);
		inputweightField.setBounds(738, 82, 98, 35);
		signupPanel.add(inputweightField);
		
		JLabel lblKg = new JLabel("KG");
		lblKg.setFont(new Font("휴먼엑스포", Font.BOLD, 20));
		lblKg.setBounds(848, 76, 50, 43);
		signupPanel.add(lblKg);
		
		ImageIcon heightIcon= new ImageIcon("icon\\height.png");
		JLabel heighticon = new JLabel(heightIcon);
		heighticon.setBounds(600, 148, 50, 50);
		signupPanel.add(heighticon);
		
		JLabel heightLabel = new JLabel("\uC2E0\uC7A5");
		heightLabel.setFont(new Font("휴먼엑스포", Font.BOLD, 20));
		heightLabel.setBounds(676, 148, 50, 43);
		signupPanel.add(heightLabel);
		
		heightField = new JTextField();
		heightField.setHorizontalAlignment(SwingConstants.CENTER);
		heightField.setFont(new Font("휴먼엑스포", Font.ITALIC, 16));
		heightField.setColumns(10);
		heightField.setBounds(738, 154, 98, 35);
		signupPanel.add(heightField);
		
		JLabel CMlabel = new JLabel("cm");
		CMlabel.setFont(new Font("휴먼엑스포", Font.BOLD, 20));
		CMlabel.setBounds(848, 148, 50, 43);
		signupPanel.add(CMlabel);
		
		ImageIcon muscleIcon= new ImageIcon("icon\\muscle.png");
		JLabel muscleicon = new JLabel(muscleIcon);
		muscleicon.setBounds(600, 221, 50, 50);
		signupPanel.add(muscleicon);
		
		JLabel muscleLabel = new JLabel("\uACE8\uADFC\uB7C9");
		muscleLabel.setFont(new Font("휴먼엑스포", Font.BOLD, 20));
		muscleLabel.setBounds(666, 224, 62, 43);
		signupPanel.add(muscleLabel);
		
		muscleField = new JTextField();
		muscleField.setHorizontalAlignment(SwingConstants.CENTER);
		muscleField.setFont(new Font("휴먼엑스포", Font.ITALIC, 16));
		muscleField.setColumns(10);
		muscleField.setBounds(738, 225, 98, 35);
		signupPanel.add(muscleField);
		
		JLabel KG_2 = new JLabel("KG");
		KG_2.setFont(new Font("휴먼엑스포", Font.BOLD, 20));
		KG_2.setBounds(848, 221, 50, 43);
		signupPanel.add(KG_2);
		
		JLabel percent_1 = new JLabel("%");
		percent_1.setFont(new Font("휴먼엑스포", Font.BOLD, 20));
		percent_1.setBounds(848, 300, 50, 43);
		signupPanel.add(percent_1);
		
		ImageIcon bodyIcon= new ImageIcon("icon\\body.png");
		JLabel bodyrateicon = new JLabel(bodyIcon);
		bodyrateicon.setBounds(600, 296, 50, 50);
		signupPanel.add(bodyrateicon);
		
		JLabel muscleLabel_1 = new JLabel("\uCCB4\uC9C0\uBC29\uB960");
		muscleLabel_1.setFont(new Font("휴먼엑스포", Font.BOLD, 18));
		muscleLabel_1.setBounds(662, 303, 76, 43);
		signupPanel.add(muscleLabel_1);
		
		bodyField = new JTextField();
		bodyField.setHorizontalAlignment(SwingConstants.CENTER);
		bodyField.setFont(new Font("휴먼엑스포", Font.ITALIC, 16));
		bodyField.setColumns(10);
		bodyField.setBounds(753, 305, 83, 35);
		signupPanel.add(bodyField);
		
		JButton registerBtn = new JButton("\uD68C\uC6D0\uAC00\uC785");
		registerBtn.setFont(new Font("휴먼엑스포", Font.BOLD | Font.ITALIC, 16));
		registerBtn.setBackground(new Color(135, 206, 235));
		registerBtn.setBounds(408, 610, 184, 43);
		
		
		registerBtn.addActionListener(new ActionListener(){
			//모두 기입을 했다면, 가입 처리와 동시에 유저 이름으로 파일을 만들고, 유저의 정보를 byte화해서 저장한다.
			@Override
			public void actionPerformed(ActionEvent e) {
				if(ispushjungbokBtn == true && ispushinputcheckBtn == true) {
					log = new login();
					
					userinfo = new User(idInputFIeld.getText(), getPasswordInfo2(), genderCombo.getSelectedItem().toString(),
							nicknameFIeld.getText(), 
							Float.valueOf(inputweightField.getText()), Float.valueOf(heightField.getText()), 
							Float.valueOf(muscleField.getText()) , Float.valueOf(bodyField.getText()));
					userinfoList_signup.add(userinfo); //유저 정보 업데이트  = signup의 user class arraylist 에 add

					try {

						ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream
								("user\\" + idInputFIeld.getText() +".dat"));
						outputStream.writeObject(userinfo);
						outputStream.close();
					}catch(IOException e1) {
						System.err.println("error occuered when writing to file");
						System.exit(0);
					}
					
					
					log.setUserinfoList(userinfoList_signup);
					ispushjungbokBtn = false;
					ispushinputcheckBtn = false;
							
					dispose();
					

				}
				else if(ispushjungbokBtn == false && ispushinputcheckBtn == false) {
					JOptionPane.showMessageDialog(null, "중복확인버튼과 비번입력확인버튼을 눌러야 합니다.");
				}
				else if(ispushjungbokBtn == true && ispushinputcheckBtn == false) {
					JOptionPane.showMessageDialog(null, "비번입력확인버튼을 눌러야 합니다.");
				}
				else if(ispushjungbokBtn == false && ispushinputcheckBtn == true) {
					JOptionPane.showMessageDialog(null, "중복확인버튼을 눌러야 합니다.");
				}				
				
			}
			
		});
		
		signupPanel.add(registerBtn);
		

		getContentPane().setLayout(null);
	}
	
	private String getPasswordInfo() {
		String str = new String(passwordField.getPassword());
		return str;
	}
	
	private String getPasswordInfo2() {
		String str = new String(passwordField2.getPassword());
		return str;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals("남성")) {
			gender = "남성";
		}
		else{
			gender = "여성";
		}
	}
	
	public void makeDir(String username) { //create directory by username
		
		File Directory = new File(username);
		
		if (!Directory.exists()) {
			try {
				Directory.mkdir();
				
			}catch (Exception e) {
				e.getStackTrace();
			}
		}else {
			JOptionPane.showMessageDialog(null, "이미 가입되어 있는 회원정보입니다.");
		}
		
	}
	
	public ArrayList<User> getUserinfoList() {
		return userinfoList_signup;
	}

	public void setUserinfoList(ArrayList<User> userinfoList) {
		this.userinfoList_signup.addAll(userinfoList);
	}
}

