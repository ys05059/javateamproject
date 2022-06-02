package Login;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Color;
import javax.swing.JPasswordField;

public class signup extends JFrame {

	private JFrame frame;
	private JTextField idInputFIeld;
	private JPasswordField passwordField;
	private JPasswordField passwordField2;
	private JTextField nicknameFIeld;
	
	
	
	private JComboBox<String> genderCombo;
	
	private final String[] gender = {"³²ÀÚ", "¿©ÀÚ"};

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					signup window = new signup();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public signup() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setVisible(true);
		setBounds(100, 100, 900, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		imgPanel signupPanel = new imgPanel(new ImageIcon("C:\\github\\javateamproject\\Health_history\\image\\flowwater.png").getImage());
		signupPanel.setBounds(0, 0, 986, 663);
		setSize(signupPanel.getDim());
		setPreferredSize(signupPanel.getDim());
		getContentPane().add(signupPanel);
		
		JLabel registerlabel = new JLabel("\uD68C\uC6D0 \uAC00\uC785");
		registerlabel.setFont(new Font("ÈÞ¸Õ¸ÅÁ÷Ã¼", Font.PLAIN, 25));
		registerlabel.setBounds(31, 10, 110, 56);
		signupPanel.add(registerlabel);
		
		ImageIcon IDicon = new ImageIcon("C:\\github\\javateamproject\\Health_history\\icon\\id.png");
		JLabel IDIcon = new JLabel(IDicon);
		IDIcon.setBounds(31, 76, 50, 50);
	
		signupPanel.add(IDIcon);
		
		JLabel IDlabel = new JLabel("ID \uC785\uB825");
		IDlabel.setFont(new Font("ÈÞ¸Õ¿¢½ºÆ÷", Font.BOLD, 20));
		IDlabel.setBounds(93, 77, 73, 49);
		signupPanel.add(IDlabel);
		
		idInputFIeld = new JTextField();
		idInputFIeld.setFont(new Font("ÈÞ¸Õ¿¢½ºÆ÷", Font.ITALIC, 16));
		idInputFIeld.setBounds(179, 82, 191, 35);
		signupPanel.add(idInputFIeld);
		idInputFIeld.setColumns(10);
		
		JButton isIDSame = new JButton("\uC911\uBCF5\uD655\uC778");
		isIDSame.setBackground(new Color(135, 206, 235));
		isIDSame.setFont(new Font("ÈÞ¸Õ¿¢½ºÆ÷", Font.BOLD | Font.ITALIC, 16));
		isIDSame.setBounds(382, 78, 98, 43);
		signupPanel.add(isIDSame);
		
		ImageIcon PWicon = new ImageIcon("C:\\github\\javateamproject\\Health_history\\icon\\PW.png");
		JLabel PWIcon = new JLabel(PWicon);
		PWIcon.setBounds(31, 148, 50, 50);
		signupPanel.add(PWIcon);
		
		JLabel PWlabel = new JLabel("PW \uC785\uB825");
		PWlabel.setFont(new Font("ÈÞ¸Õ¿¢½ºÆ÷", Font.BOLD, 20));
		PWlabel.setBounds(93, 149, 83, 49);
		signupPanel.add(PWlabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(179, 155, 191, 35);
		signupPanel.add(passwordField);
		
		JLabel PWlabel2 = new JLabel("PW \uC7AC\uC785\uB825");
		PWlabel2.setFont(new Font("ÈÞ¸Õ¿¢½ºÆ÷", Font.BOLD, 20));
		PWlabel2.setBounds(73, 221, 103, 49);
		signupPanel.add(PWlabel2);
		
		passwordField2 = new JPasswordField();
		passwordField2.setBounds(179, 225, 191, 35);
		signupPanel.add(passwordField2);
		
		JButton isPWSame = new JButton("\uC785\uB825\uD655\uC778");
		isPWSame.setFont(new Font("ÈÞ¸Õ¿¢½ºÆ÷", Font.BOLD | Font.ITALIC, 16));
		isPWSame.setBackground(new Color(135, 206, 235));
		isPWSame.setBounds(382, 221, 98, 43);
		signupPanel.add(isPWSame);
		
		ImageIcon NNicon = new ImageIcon("C:\\github\\javateamproject\\Health_history\\icon\\nickname.png");
		JLabel NicknameIcon = new JLabel(NNicon);
		NicknameIcon.setBounds(31, 296, 50, 50);
		signupPanel.add(NicknameIcon);
		
		JLabel nicknameLabel = new JLabel("\uB2C9\uB124\uC784");
		nicknameLabel.setFont(new Font("ÈÞ¸Õ¿¢½ºÆ÷", Font.BOLD, 20));
		nicknameLabel.setBounds(103, 303, 73, 49);
		signupPanel.add(nicknameLabel);
		
		nicknameFIeld = new JTextField();
		nicknameFIeld.setFont(new Font("ÈÞ¸Õ¿¢½ºÆ÷", Font.ITALIC, 16));
		nicknameFIeld.setColumns(10);
		nicknameFIeld.setBounds(179, 308, 191, 35);
		signupPanel.add(nicknameFIeld);
		
		ImageIcon genderIcon= new ImageIcon("C:\\github\\javateamproject\\Health_history\\icon\\gender.png");
		JLabel gendericon = new JLabel(genderIcon);
		gendericon.setBounds(31, 380, 50, 50);
		signupPanel.add(gendericon);
		
		JLabel genderLabel = new JLabel("\uC131\uBCC4");
		genderLabel.setFont(new Font("ÈÞ¸Õ¿¢½ºÆ÷", Font.BOLD, 20));
		genderLabel.setBounds(120, 381, 56, 49);
		signupPanel.add(genderLabel);
		
//		genderCombo = new JComboBox<String>(gender);
		
		
		
		
		getContentPane().setLayout(null);
	}
}
