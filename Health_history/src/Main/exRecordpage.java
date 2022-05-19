package Main;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class exRecordpage extends JFrame{
	private JPanel defaultpanel;
	
	public exRecordpage() {
		setTitle("exRecordpage	");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		defaultpanel = new JPanel();
		defaultpanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(defaultpanel);
		defaultpanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		defaultpanel.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("\uBC1B\uC544\uC628 \uC6B4\uB3D9\uBA85 ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel, BorderLayout.WEST);
		
		JLabel lblNewLabel_1 = new JLabel("\uC138\uD2B8 (1/3)");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblNewLabel_1, BorderLayout.EAST);
		
		JPanel panel_1 = new JPanel();
		defaultpanel.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton = new JButton("\uC138\uD2B8 \uCD94\uAC00");
		panel_1.add(btnNewButton, BorderLayout.WEST);
		
		JButton btnNewButton_1 = new JButton("\uC138\uD2B8 \uC0AD\uC81C");
		panel_1.add(btnNewButton_1, BorderLayout.EAST);
	}
	
}
