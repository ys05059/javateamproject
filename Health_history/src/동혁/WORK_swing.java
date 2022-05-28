package 동혁;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.DropMode;
import java.awt.Color;
import javax.swing.ScrollPaneConstants;

public class WORK_swing {
	
	private JFrame frame;
	private JTextField inputwork;
	private JPanel panel;
	private JPanel panel2;
	private JButton backbtn;
	private JTextArea resTextarea;
	private JLabel work_label;
	private JButton searchbtn;
	private JTextField inputcate;
	private JScrollPane jp;

	/**
	 * Launch the application.
	 */
	search_for_ALL_WORKOUT data = new search_for_ALL_WORKOUT();
	private JLabel reslabel;
	public static void main(String[] args) {
		try {
			WORK_swing window = new WORK_swing();
			window.frame.setVisible(true); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public WORK_swing() {
		workout();
	}

	private void workout()  {
		frame = new JFrame();
		frame.setBounds(100, 100, 510, 315);
		frame.setLocation(550, 280);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		panel = new JPanel();
		panel.setBounds(0, 0, 496, 278);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		work_label = new JLabel("\uC6B4\uB3D9\uBA85");
		work_label.setFont(new Font("HY엽서M", Font.PLAIN, 20));
		work_label.setHorizontalAlignment(SwingConstants.CENTER);
		work_label.setBounds(52, 48, 103, 41);
		panel.add(work_label);
		
		inputwork = new JTextField();
		inputwork.setFont(new Font("HY엽서M", Font.PLAIN, 18));
		inputwork.setBounds(12, 114, 205, 41);
		panel.add(inputwork);
		inputwork.setColumns(10);
		
		searchbtn = new JButton("\uC6B4\uB3D9 \uC774\uB984\uC73C\uB85C \uAC80\uC0C9");
		searchbtn.setFont(new Font("HY엽서M", Font.PLAIN, 18));
		searchbtn.setBounds(12, 198, 205, 57);
		searchbtnHandler sh = new searchbtnHandler();
		searchbtn.addActionListener(sh);
		panel.add(searchbtn);
		
		JLabel title = new JLabel("\uAC80\uC0C9 \uC138\uC158");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("HY엽서M", Font.PLAIN, 20));
		title.setBounds(171, 0, 147, 41);
		panel.add(title);
		
		JLabel cate_label = new JLabel("\uCE74\uD14C\uACE0\uB9AC\uBA85");
		cate_label.setHorizontalAlignment(SwingConstants.CENTER);
		cate_label.setFont(new Font("HY엽서M", Font.PLAIN, 20));
		cate_label.setBounds(323, 48, 103, 41);
		panel.add(cate_label);
		
		inputcate = new JTextField();
		inputcate.setFont(new Font("HY엽서M", Font.PLAIN, 18));
		inputcate.setColumns(10);
		inputcate.setBounds(279, 114, 205, 41);
		panel.add(inputcate);
		
		JButton searchbtn2 = new JButton("\uCE74\uD14C\uACE0\uB9AC\uB85C \uAC80\uC0C9");
		searchbtn2.setFont(new Font("HY엽서M", Font.PLAIN, 18));
		searchbtn2.setBounds(279, 198, 205, 57);
		searchbtnHandler2 sh2 = new searchbtnHandler2();
		searchbtn2.addActionListener(sh2);
		panel.add(searchbtn2);
		

		// #####################################
		
		panel2 = new JPanel();
		panel2.setBounds(0, 0, 496, 278);
		frame.getContentPane().add(panel2);
		panel2.setLayout(null);
		panel2.setVisible(false);
		
		backbtn = new JButton("back");
		backbtn.setFont(new Font("HY엽서M", Font.PLAIN, 17));
		backbtn.setBounds(375, 184, 109, 34);
		backbtnHandler bb = new backbtnHandler();
		backbtn.addActionListener(bb);
		panel2.add(backbtn);
		
		resTextarea = new JTextArea();
		resTextarea.setBackground(Color.WHITE);
		resTextarea.setFont(new Font("휴먼매직체", Font.PLAIN, 23));
		resTextarea.setBounds(37, 34, 303, 180);
		resTextarea.setSelectionStart(0);
		resTextarea.setSelectionEnd(0);
		resTextarea.setEditable(false);
		
		jp = new JScrollPane(resTextarea);
		jp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jp.setBounds(37, 34, 303, 180);
		
		Runnable topscroll = new Runnable() {

			@Override
			public void run() {
				jp.getVerticalScrollBar().setValue(0);
				
			}
			
		};
		SwingUtilities.invokeLater(topscroll);
		
		panel2.add(jp);
		
		reslabel = new JLabel("\uAC80\uC0C9 \uACB0\uACFC");
		reslabel.setHorizontalAlignment(SwingConstants.CENTER);
		reslabel.setFont(new Font("휴먼모음T", Font.PLAIN, 16));
		reslabel.setBounds(0, 0, 109, 28);
		panel2.add(reslabel);
		

	}
	
	private class searchbtnHandler implements ActionListener{
		String res = "";
		public void actionPerformed(ActionEvent e) {
			res = data.search(inputwork.getText());
			switch(res) {
			case "":
				JOptionPane.showMessageDialog(null, "그런 운동이 없습니다.");
				break;
			case "filenotfound":
				JOptionPane.showMessageDialog(null, "파일 읽기 오류. 관리자에게 문의하세요.");
				break;
			default:
				panel.setVisible(false);
				panel2.setVisible(true);
				String[] split = res.split("/");
				if(split.length == 3) {
					resTextarea.append("검색한 운동 : " + split[0] + "\n");
					resTextarea.append("\n");
					resTextarea.append("카테고리 : " + split[1] + "\n");
					resTextarea.append("\n");
					resTextarea.append("계산 방법 : " + split[2] + "\n");
					res = "";
					inputwork.setText("");
				}
				else {
					JOptionPane.showMessageDialog(null, "파일 읽기 오류. 관리자에게 문의하세요.");
					panel.setVisible(true);
					panel2.setVisible(false);
				}
				
			}
		
		} 
		
	}
	
	private class backbtnHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			resTextarea.setText("");
			panel.setVisible(true);
			panel2.setVisible(false);
			
		}
		
	}
	
	private class searchbtnHandler2 implements ActionListener{
		String res = "";

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			res = data.searchcate(inputcate.getText());
			switch(res) {
			case "":
				JOptionPane.showMessageDialog(null, "그런 운동이 없습니다.");
				break;
			case "filenotfound":
				JOptionPane.showMessageDialog(null, "파일 읽기 오류. 관리자에게 문의하세요.");
				break;
			default:
				panel.setVisible(false);
				panel2.setVisible(true);
				String[] firstsplit = res.split("\\$");
				resTextarea.append("검색한 카테고리 :  " + inputcate.getText() + "\n" );
				resTextarea.append("운동 LIST" + "\n");
				
				for(int i = 0; i < firstsplit.length; i++) {
					
					resTextarea.append("     " + firstsplit[i] + "\n");
					
				}
				res = "";	
				inputcate.setText("");
			}
			
		}
		
	}
}


