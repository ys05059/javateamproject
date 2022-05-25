package Main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

// dayRecordpage와 연결되어 있는 운동추가 페이지

public class addexRecordpage extends JDialog {
	private JPanel contentPane;
	private JTextField exname_field;
	private JTextField setgoal_field;
	
	public addexRecordpage() {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel exname_Label = new JLabel("\uC6B4\uB3D9\uBA85");
		exname_Label.setBounds(110, 90, 52, 15);
		contentPane.add(exname_Label);
		
		exname_field = new JTextField();
		exname_field.setBounds(193, 87, 106, 21);
		contentPane.add(exname_field);
		exname_field.setColumns(10);
		
		JLabel setgoal_Label = new JLabel("\uBAA9\uD45C \uC138\uD2B8\uC218");
		setgoal_Label.setBounds(92, 141, 87, 15);
		contentPane.add(setgoal_Label);
		
		setgoal_field = new JTextField();
		setgoal_field.setBounds(193, 138, 106, 21);
		contentPane.add(setgoal_field);
		
		JButton addBtn = new JButton("추가");
		addBtn.setBounds(162, 188, 95, 23);
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!exname_field.getText().equals("") && !setgoal_field.getText().equals("")) {											// 일단 정수 입력하지 않았을 때의 예외처리는 하지 않음
					addexRecordpage.this.dispose();
				}else {
					input_check_dialog icd = new input_check_dialog();
					icd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					icd.setModal(true);
					icd.setVisible(true);
				}
			}
		});
		contentPane.add(addBtn);
	}

	public String get_exname() {
		return exname_field.getText();
	}

	public int get_setgoal() {
		return Integer.valueOf(setgoal_field.getText());
	}
	
	class input_check_dialog extends JDialog{
		public input_check_dialog(){
			setSize(200,100);
			JLabel label = new JLabel("입력을 확인하세요");
			label.setHorizontalAlignment(JLabel.CENTER);
			add(label,BorderLayout.CENTER);
			JButton bt = new JButton("확인");
			bt.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					input_check_dialog.this.dispose();
				}
			});
			add(bt,BorderLayout.SOUTH);
			setLocation(200, 200);
		}
		
	}
}
