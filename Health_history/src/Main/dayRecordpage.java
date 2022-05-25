package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import set단위class.dayRecord;
import set단위class.exRecord;

public class dayRecordpage extends JFrame {

	private JPanel defaultpanel;
	private JTextField weight_textField;
	private JTextField today_textField;
	private JPanel ex_list_panel; 
	private dayRecord dayrecord;
	private ActionListener addex_listener;
	private ActionListener savedR_listener;
	
	public dayRecordpage(ArrayList<dayRecord> dR_ary) {
		setTitle("exRecordpage	");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,400);
		GridBagLayout gb = new GridBagLayout();
		gb.rowHeights = new int[] {50, 50,50,50,50,50,50};
		gb.columnWidths = new int[] {100,100,50,50,50};
		setLayout(gb);
		dayrecord = new dayRecord();
		GridBagConstraints gbc_default = new GridBagConstraints();
		
		/* 날짜 입력 패널 */
		JLabel today_Label = new JLabel("오늘의 날짜");
		today_Label.setHorizontalAlignment(SwingConstants.CENTER);
		gbc_default.anchor = GridBagConstraints.WEST;
		gbc_default.gridx = 0;
		gbc_default.gridy = 0;
		add(today_Label,gbc_default);
		
		today_textField = new JTextField();
		gbc_default.fill = GridBagConstraints.HORIZONTAL;
		gbc_default.gridx = 1;
		gbc_default.gridy = 0;
		gbc_default.anchor = GridBagConstraints.WEST;
		add(today_textField,gbc_default);
		
		
		// 몸무게 라벨
		JLabel today_weight_label = new JLabel("오늘의 몸무게");
		gbc_default = new GridBagConstraints();
		gbc_default.fill = GridBagConstraints.HORIZONTAL;
		gbc_default.gridx = 0;
		gbc_default.gridy = 6;
		add(today_weight_label,gbc_default);
		
		// 뭄무게 textField
		weight_textField = new JTextField();
		gbc_default = new GridBagConstraints();
		gbc_default.fill = GridBagConstraints.HORIZONTAL;
		gbc_default.gridx = 1;
		gbc_default.gridy = 6;
		add(weight_textField,gbc_default);
		
		/* 운동 리스트 패널 */
		ex_list_panel = new JPanel();
		ex_list_panel.setBackground(Color.WHITE);
		gb = new GridBagLayout();
		gb.rowHeights = new int[]{50, 50, 50, 50, 50};
		gb.columnWidths = new int[] {100,100,50,75,75};
		ex_list_panel.setLayout(gb);
		
		gbc_default = new GridBagConstraints();
		gbc_default.fill = GridBagConstraints.BOTH;
		gbc_default.gridx = 0;
		gbc_default.gridy = 1;
		gbc_default.gridheight = 5;
		gbc_default.gridwidth = 5;
		JScrollPane sp = new JScrollPane(ex_list_panel);
		add(sp, gbc_default);
		
		// 운동추가 버튼 클릭
		JButton addexr_button = new JButton("운동 추가");
		addex_listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addexRecordpage exr = new addexRecordpage();
				exr.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				exr.setModal(true);
				exr.setVisible(true);
				dayrecord.add_exr(new exRecord(exr.get_exname(),exr.get_setgoal()));
				
				if(!dayrecord.getExr_ary().isEmpty()) {  												// 운동 1개라도 있을 경우
					GridBagConstraints gbc = new GridBagConstraints();									// exRecord 한 개에 대한 gbc
					gbc.fill = GridBagConstraints.BOTH;
					gbc.gridx = 0;
					gbc.gridy = 0;
					gbc.gridwidth = 5;
					int count = 0;
					for(exRecord tmp_exr : dayrecord.getExr_ary()) {
						expanel ex= new expanel(tmp_exr);
						gbc.gridy = count++;
						ex_list_panel.add(ex,gbc);
					}
				}
				ex_list_panel.revalidate();															// 운동 선택 패널 초기화
				ex_list_panel.repaint();
			}
		};
		addexr_button.addActionListener(addex_listener);
		gbc_default =new GridBagConstraints();		
		gbc_default.anchor = GridBagConstraints.EAST;
		gbc_default.gridx = 2;
		gbc_default.gridwidth = 2;
		add(addexr_button, gbc_default);
		
		//저장 버튼
		JButton savedR_button = new JButton("저장");
		savedR_listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 적어도 date와 몸무게는 있어야함 없으면 에러창
				if(today_textField.getText().equals("") || weight_textField.getText().equals("")) {
					savedR_check_dialog icd = new savedR_check_dialog();
					icd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					icd.setModal(true);
					icd.setVisible(true);
				}
				// dayRecord를 dR_ary에 추가
				dR_ary.add(dayrecord);
				
				// 달력 페이지로 돌아감
				
			}
		};
		savedR_button.addActionListener(savedR_listener);
		gbc_default.anchor = GridBagConstraints.CENTER;
		gbc_default.ipadx = 20;
		gbc_default.gridx = 4;
		gbc_default.gridwidth = 2;
		add(savedR_button, gbc_default);

		
		
	}
	
	class expanel extends JPanel{
		
		public expanel(exRecord other_exr) {
			GridBagLayout gbl = new GridBagLayout();
			gbl.columnWidths = new int[] {100,100,50,50,50};
			gbl.rowHeights = new int[] {50};
			
			this.setLayout(gbl);
			this.setBackground(Color.YELLOW);
			
			JLabel ex_name = new JLabel(other_exr.getEx().getName());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.BOTH;
			gbc.gridx = 0;
			gbc.gridy = 0;
			this.add(ex_name,gbc);
			
			JLabel set_label = new JLabel("세트 수");
			gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.BOTH;
			gbc.gridx = 1;
			gbc.gridy = 0;
			this.add(set_label,gbc);
			
			JLabel setnum_label = new JLabel(Integer.toString(other_exr.getSet_goal()));
			gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.BOTH;
			gbc.gridx = 2;
			gbc.gridy = 0;
			this.add(setnum_label,gbc);
			
			JButton btn1 = new JButton("수정");
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridx= 3;
			gbc.gridy= 0;
			gbc.insets = new Insets(0, 0, 0, 5);
			this.add(btn1,gbc);
			
			JButton btn2 = new JButton("삭제");
			gbc.gridx= 4;
			gbc.gridy= 0;
			gbc.insets= new Insets(0, 0, 0, 0);
			this.add(btn2,gbc);
		}
		
	}
	class savedR_check_dialog extends JDialog{
		public savedR_check_dialog(){
			setSize(200,100);
			JLabel label = new JLabel("입력을 확인하세요");
			label.setHorizontalAlignment(JLabel.CENTER);
			add(label,BorderLayout.CENTER);
			JButton bt = new JButton("확인");
			bt.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					savedR_check_dialog.this.dispose();
				}
			});
			add(bt,BorderLayout.SOUTH);
			setLocation(200, 200);
		}
		
	}
}
