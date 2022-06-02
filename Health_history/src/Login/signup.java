package Login;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.Icon;

public class signup extends JFrame {

	private JFrame frame;
	private JTextField idInputFIeld;
	private JPasswordField passwordField;
	private JPasswordField passwordField2;
	private JTextField nicknameFIeld;
	
	
	
	private JComboBox<String> genderCombo;
	
	private final String[] gender = {"³²ÀÚ", "¿©ÀÚ"};
	private JTextField inputweightField;
	private JTextField heightField;
	private JTextField muscleField;
	private JTextField bodyField;

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
		registerlabel.setFont(new Font("ÈÞ¸Õ¸ÅÁ÷Ã¼", Font.PLAIN, 30));
		registerlabel.setBounds(31, 10, 231, 56);
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
		
		JRadioButton maleBtn = new JRadioButton("\uB0A8\uC131");
		maleBtn.setBackground(new Color(135, 206, 250));
		maleBtn.setSelected(true);
		maleBtn.setFont(new Font("ÈÞ¸Õ¿¢½ºÆ÷", Font.PLAIN, 18));
		maleBtn.setBounds(179, 392, 73, 23);
		
		
		JRadioButton femaleBtn = new JRadioButton("\uC5EC\uC131");
		femaleBtn.setBackground(new Color(135, 206, 250));
		femaleBtn.setFont(new Font("ÈÞ¸Õ¿¢½ºÆ÷", Font.PLAIN, 18));
		femaleBtn.setBounds(297, 392, 73, 23);
		signupPanel.add(femaleBtn);
		
		ButtonGroup malefemale = new ButtonGroup();
		malefemale.add(maleBtn);
		malefemale.add(femaleBtn);
		signupPanel.add(maleBtn);
		
		ImageIcon weightIcon= new ImageIcon("C:\\github\\javateamproject\\Health_history\\icon\\weight.png");
		JLabel weighticon = new JLabel(weightIcon);
		weighticon.setBounds(600, 76, 50, 50);
		signupPanel.add(weighticon);
		
		JLabel weightLabel = new JLabel("\uCCB4\uC911");
		weightLabel.setFont(new Font("ÈÞ¸Õ¿¢½ºÆ÷", Font.BOLD, 20));
		weightLabel.setBounds(676, 76, 50, 43);
		signupPanel.add(weightLabel);
		
		inputweightField = new JTextField();
		inputweightField.setHorizontalAlignment(SwingConstants.CENTER);
		inputweightField.setFont(new Font("ÈÞ¸Õ¿¢½ºÆ÷", Font.ITALIC, 16));
		inputweightField.setColumns(10);
		inputweightField.setBounds(738, 82, 98, 35);
		signupPanel.add(inputweightField);
		
		JLabel lblKg = new JLabel("KG");
		lblKg.setFont(new Font("ÈÞ¸Õ¿¢½ºÆ÷", Font.BOLD, 20));
		lblKg.setBounds(848, 76, 50, 43);
		signupPanel.add(lblKg);
		
		ImageIcon heightIcon= new ImageIcon("C:\\github\\javateamproject\\Health_history\\icon\\height.png");
		JLabel heighticon = new JLabel(heightIcon);
		heighticon.setBounds(600, 148, 50, 50);
		signupPanel.add(heighticon);
		
		JLabel heightLabel = new JLabel("\uC2E0\uC7A5");
		heightLabel.setFont(new Font("ÈÞ¸Õ¿¢½ºÆ÷", Font.BOLD, 20));
		heightLabel.setBounds(676, 148, 50, 43);
		signupPanel.add(heightLabel);
		
		heightField = new JTextField();
		heightField.setHorizontalAlignment(SwingConstants.CENTER);
		heightField.setFont(new Font("ÈÞ¸Õ¿¢½ºÆ÷", Font.ITALIC, 16));
		heightField.setColumns(10);
		heightField.setBounds(738, 154, 98, 35);
		signupPanel.add(heightField);
		
		JLabel CMlabel = new JLabel("cm");
		CMlabel.setFont(new Font("ÈÞ¸Õ¿¢½ºÆ÷", Font.BOLD, 20));
		CMlabel.setBounds(848, 148, 50, 43);
		signupPanel.add(CMlabel);
		
		ImageIcon muscleIcon= new ImageIcon("C:\\github\\javateamproject\\Health_history\\icon\\muscle.png");
		JLabel muscleicon = new JLabel(muscleIcon);
		muscleicon.setBounds(600, 221, 50, 50);
		signupPanel.add(muscleicon);
		
		JLabel muscleLabel = new JLabel("\uACE8\uADFC\uB7C9");
		muscleLabel.setFont(new Font("ÈÞ¸Õ¿¢½ºÆ÷", Font.BOLD, 20));
		muscleLabel.setBounds(666, 224, 62, 43);
		signupPanel.add(muscleLabel);
		
		muscleField = new JTextField();
		muscleField.setHorizontalAlignment(SwingConstants.CENTER);
		muscleField.setFont(new Font("ÈÞ¸Õ¿¢½ºÆ÷", Font.ITALIC, 16));
		muscleField.setColumns(10);
		muscleField.setBounds(738, 225, 98, 35);
		signupPanel.add(muscleField);
		
		JLabel KG_2 = new JLabel("KG");
		KG_2.setFont(new Font("ÈÞ¸Õ¿¢½ºÆ÷", Font.BOLD, 20));
		KG_2.setBounds(848, 221, 50, 43);
		signupPanel.add(KG_2);
		
		JLabel percent_1 = new JLabel("%");
		percent_1.setFont(new Font("ÈÞ¸Õ¿¢½ºÆ÷", Font.BOLD, 20));
		percent_1.setBounds(848, 300, 50, 43);
		signupPanel.add(percent_1);
		
		ImageIcon bodyIcon= new ImageIcon("C:\\github\\javateamproject\\Health_history\\icon\\body.png");
		JLabel bodyrateicon = new JLabel(bodyIcon);
		bodyrateicon.setBounds(600, 296, 50, 50);
		signupPanel.add(bodyrateicon);
		
		JLabel muscleLabel_1 = new JLabel("\uCCB4\uC9C0\uBC29\uB960");
		muscleLabel_1.setFont(new Font("ÈÞ¸Õ¿¢½ºÆ÷", Font.BOLD, 18));
		muscleLabel_1.setBounds(662, 303, 76, 43);
		signupPanel.add(muscleLabel_1);
		
		bodyField = new JTextField();
		bodyField.setHorizontalAlignment(SwingConstants.CENTER);
		bodyField.setFont(new Font("ÈÞ¸Õ¿¢½ºÆ÷", Font.ITALIC, 16));
		bodyField.setColumns(10);
		bodyField.setBounds(753, 305, 83, 35);
		signupPanel.add(bodyField);
		
		JButton registerBtn = new JButton("\uD68C\uC6D0\uAC00\uC785");
		registerBtn.setFont(new Font("ÈÞ¸Õ¿¢½ºÆ÷", Font.BOLD | Font.ITALIC, 16));
		registerBtn.setBackground(new Color(135, 206, 235));
		registerBtn.setBounds(408, 610, 184, 43);
		
		
		registerBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
				
			}
			
		});
		
		signupPanel.add(registerBtn);
		
		
		
		
		
		getContentPane().setLayout(null);
	}
}
