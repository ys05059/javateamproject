package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Main.addexRecordpage.input_check_dialog;
import set단위class.exRecord;

public class addsetpage extends JDialog {
	private JPanel contentPane;
	public addsetpage(exRecord exr) {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton addBtn = new JButton("추가");
		addBtn.setBounds(162, 188, 95, 23);
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				if(!exname_field.getText().equals("") && !setgoal_field.getText().equals("")) {											// 일단 정수 입력하지 않았을 때의 예외처리는 하지 않음
					addexRecordpage.this.dispose();
				}else {
					input_check_dialog icd = new input_check_dialog();
					icd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					icd.setModal(true);
					icd.setVisible(true);
				}*/
			}
		});
		contentPane.add(addBtn);
	}

}
