package teamproject;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import Login.SubFrame1;
import Login.User;
import Main.addexRecordpage.input_check_dialog;

public class 도구 {

	
	
	// 버튼 추가 및 ActionListener 생성
	JButton login_Button = new JButton("로그인");
	login_Button.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			// 등록된 회원인지 체크
					SubFrame1 sb = new SubFrame1();
					sb.setVisible(true);
		}
	}
	
	// 간단한 오류창 띄우기
	input_check_dialog icd = new input_check_dialog();
	icd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	icd.setModal(true);
	icd.setVisible(true);

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
