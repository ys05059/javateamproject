package teamproject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Login.SubFrame1;
import Login.User;

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
