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
import set����class.dayRecord;
import ��.CalendarDemo;
public class login {

	private JFrame frame;
	private JTextField loginField; //�α��� �Է� ������ �ʵ�
	private JPasswordField passwordField; //��й�ȣ �Է� ������ �ʵ�
	private JButton regiBtn; //���� ��ư
	private JButton loginBtn; //�α��� ��ư
	private ArrayList<dayRecord> curr_dR_ary;

	
	ArrayList<User> userinfoList = new ArrayList<User>(); //class �迭����Ʈ ����
	
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) { //JDK�� ����� lookandfeel ���
						if ("Nimbus".equals(info.getName())) {
							UIManager.setLookAndFeel(info.getClassName());
							break;
						}
						else { //Nimbus lookandfeel�� ���� ���
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
		//ȸ�� ���� �ҷ��� �迭����Ʈ�� �����ϱ�.

		frame = new JFrame("Health History");
		frame.setBounds(100, 100, 700, 700);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		//from https://kr.freepik.com/free-vector/modern-login-page-template-with-blur-background_18141219.htm#&position=2&from_view=detail#&position=2&from_view=detail
		imgPanel loginP = new imgPanel(new ImageIcon("image\\login.jpg").getImage()); //imgPanel Ŭ������ ������ ���
		frame.setSize(loginP.getDim()); // frame�� size �� ���� ũ�⿡ �����
		frame.setPreferredSize(loginP.getDim()); //setSize�� �ϸ� ������ �� �� �� �����Ƿ� setPreferredsize�� ����
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
				boolean loginok = false;
				//user ���Ͽ��� ��� ������ ID �� PW �� ������
				
				userinfoList = bringUserInfo();   //Ŭ���� �迭 �������� ����
				//�� �� ���ĺ��ʹ� ��� User�� ������ userinfoList�� �ִ�. (bringUserInfo�� ���������ν�)
				//������ ���� �������� ���̹Ƿ�, �迭����Ʈ�� �� ID �� PW�� ���� �޾ƿ´�.
				ArrayList<String> idnow = new ArrayList<String>();
				ArrayList<String> pwnow = new ArrayList<String>();
				for(int i = 0; i < userinfoList.size(); i++) {
					idnow.add(userinfoList.get(i).getID());
					pwnow.add(userinfoList.get(i).getPW());
				}

				for(int i = 0; i < idnow.size(); i++) { //���� ������ ���Ͽ� 
					if(idnow.get(i).equals(loginField.getText()) &&  pwnow.get(i).equals(getPasswordInfo())) {
						//�α��� ���� â�� ����
						JOptionPane.showMessageDialog(null, "login success!!", "�����մϴ�", JOptionPane.INFORMATION_MESSAGE);
						//�α��� ������ Ȯ�εǸ� ���̵�� ����� userworkinfo ������ �ִ��� Ȯ���ϰ� 
						//�ִٸ� deserialize�ؿ´�. ���� dayRecord�� ������ ���������ν� ���� ���� �������� �������� �� �� �ִ�.
						//���ٸ� deserialize �Ұ����ϹǷ� �ʱ� ȭ���� ����.
						File f = new File("userworkinfo//" + idnow.get(i) +".dat");
						if(f.exists()) {
							UserRecord AA = new UserRecord(); //UserRecord ��ü ����
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
						//calendar ����
						CalendarDemo A = new CalendarDemo(curr_dR_ary, idnow.get(i));
						A.setVisible(true);
						frame.setVisible(false); //�α��� â ����
						loginok = true;
						break;
					}
					
				}
				if(loginok == false) { //�α����� �� �� ���
					//�α��� �ȵǾ��ٰ� â�� ��� ��, field�� "" �� �ʱ�ȭ���ش�.
					JOptionPane.showMessageDialog(null, "login failed..", "���", JOptionPane.WARNING_MESSAGE);
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
		regiBtn.setFont(new Font("�޸ո���T", Font.PLAIN, 18));
		regiBtn.setForeground(new Color(255, 255, 255));
		regiBtn.setBackground(new Color(0, 0, 139));
		regiBtn.setBounds(202, 421, 297, 43);
		regiBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				signup A =new signup(); //ȸ������ â�� ����
			}
			
		});
		loginP.add(regiBtn);
		
		frame.pack();
		
	}
	
	//UserinfoList�� getter setter
	public ArrayList<User> getUserinfoList() {
		return userinfoList;
	}

	public void setUserinfoList(ArrayList<User> userinfoList) {
		this.userinfoList = userinfoList;
	}

	public ArrayList<User> bringUserInfo(){ //user�������� ���ϵ��� deserialize�ϴ� method
		ArrayList<User> userinfoList = new ArrayList<User>(); //User class �迭����Ʈ ����
		File whatsin = new File("user\\");
		String[] filenames = whatsin.list(); //���� �ȿ� ���ϵ��� listȭ�ϱ�.
		
		if(filenames != null) { //���� �� ������ ������ ���, 
			for (int i = 0; i < filenames.length; i++) { //���� �� ������ ���� �� ȸ�� ������ ���̹Ƿ�
				User AA = new User(); //User Ŭ���� ��ü ����
				try {
					//���� �̸��� user�� ID �� ������ �������Ƿ�,
					//����ڰ� �Է��� ID �� ����Ͽ� Ŭ������ deserialize�Ѵ�.
					ObjectInputStream inputStream =
							new ObjectInputStream (new FileInputStream("user\\" + filenames[i]));
					AA = (User)inputStream.readObject(); //Ŭ���� ���� ��������
					userinfoList.add(AA); //add to ArrayList<user>
					inputStream.close();
				}catch(FileNotFoundException e) { //������ ã�� �� ���� ��
					System.err.println("can't find file");
					System.exit(0);
				}catch(ClassNotFoundException e) {//Ŭ������ ã�� �� ���� ��
					System.err.println("problem occuered");
					System.exit(0);		
				}catch(IOException e) {//I/0������ �߻��� ���
					System.err.println("problem occuered");
					System.exit(0);
				}
			}
		}
		return userinfoList;
	}
	
	private String getPasswordInfo() { //password�� �Ϲ� text�� �޸� getText()�� �޾ƿ��� ���ϹǷ�
		//���� Ŭ������ ����� password�� �������� method�� �����ߴ�.
		String str = new String(passwordField.getPassword());
		return str;
	}
	
	// �α׾ƿ� �� �ٽ� loginâ�� ���� ���� method
	public void turnonLoginPage() {
		frame.setVisible(true);
	}
}
