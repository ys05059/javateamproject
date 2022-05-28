package Main;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Main.dayRecordpage.expanel;
import Main.dayRecordpage.savedR_check_dialog;
import set단위class.dayRecord;
import set단위class.exRecord;
import set단위class.exercise;
import set단위class.exlistClass;

public class exRecordpage extends JDialog{
	private JPanel defaultpanel;
	private JTextField weight_textField;
	private JTextField today_textField;
	private JPanel set_list_panel; 
	private dayRecord dayrecord;
	private exRecord exrecord;
	private ArrayList<expanel> expanel_list; 
	private ArrayList<exercise> exlist;
	
	public exRecordpage(exRecord other_exr, dayRecord pre_dayRecord) {
		setTitle("exRecordpage	");
		setSize(500,400);
		GridBagLayout gb = new GridBagLayout();
		gb.rowHeights = new int[] {50, 50,50,50,50,50,50};
		gb.columnWidths = new int[] {100,100,50,50,50};
		setLayout(gb);
		dayrecord = new dayRecord();
		GridBagConstraints gbc_default = new GridBagConstraints();
		
		/* 받아온 운동명으로 exRecord 정보 채우기 */
		// other_exr는 name 과 setgoal 정보만 있음
		exlistClass elc = new exlistClass("ALL_WORKOUT");
		exlist = elc.get_exlist();
		exrecord = new exRecord(other_exr.getEx().getname(),other_exr.getSet_goal());
		setEx_byname();
		exrecord.setSet_goal(5);
		
		
		/* 받아온 운동명 출력 패널 */
		JLabel today_Label = new JLabel(exrecord.getEx().getname());
		today_Label.setHorizontalAlignment(SwingConstants.CENTER);
		gbc_default.anchor = GridBagConstraints.WEST;
		gbc_default.gridx = 0;
		gbc_default.gridy = 0;
		add(today_Label,gbc_default);
		
				
		/* 세트 리스트 패널 */
		set_list_panel = new JPanel();
		set_list_panel.setBackground(Color.WHITE);
		gb = new GridBagLayout();
		gb.rowHeights = new int[]{50, 50, 50, 50, 50};
		gb.columnWidths = new int[] {100,100,50,75,75};
		set_list_panel.setLayout(gb);
		
		gbc_default = new GridBagConstraints();
		gbc_default.fill = GridBagConstraints.BOTH;
		gbc_default.gridx = 0;
		gbc_default.gridy = 1;
		gbc_default.gridheight = 5;
		gbc_default.gridwidth = 5;
		JScrollPane sp = new JScrollPane(set_list_panel);
		add(sp, gbc_default);
		
		// 세트추가 버튼 클릭
		JButton addset_button = new JButton("세트 추가");
		ActionListener addset_listener= new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 추가할 세트 정보 받아오기
				addsetpage asp = new addsetpage(new exRecord(exrecord));
				asp.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				asp.setModal(true);
				asp.setVisible(true);
				
				/*exRecord tmp_ex = new exRecord(exrp.get_exname(),exrp.get_setgoal());
				
				// 받아온 운동 정보 저장
				dayrecord.add_exr(tmp_ex);
				expanel tmp_exp = new expanel(tmp_ex);
				if (expanel_list == null)
					expanel_list = new ArrayList<>();
				expanel_list.add(tmp_exp);*/
				
				//받아온 운동 정보에 대한 ex_list_panel 업데이트
				repaint_exlist_panel();
			}
		};
		addset_button.addActionListener(addset_listener);
		gbc_default =new GridBagConstraints();		
		gbc_default.anchor = GridBagConstraints.EAST;
		gbc_default.gridx = 2;
		gbc_default.gridwidth = 2;
		add(addset_button, gbc_default);
		
		//저장 버튼
		JButton savedR_button = new JButton("저장");
		ActionListener savedR_listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				other_exr.shallow_copy(exrecord);
				dispose();
				
			}
		};
		savedR_button.addActionListener(savedR_listener);
		gbc_default.anchor = GridBagConstraints.CENTER;
		gbc_default.ipadx = 20;
		gbc_default.gridx = 4;
		gbc_default.gridwidth = 2;
		add(savedR_button, gbc_default);
	}
	
	private void repaint_exlist_panel(){
		if(!expanel_list.isEmpty()) {  												// 운동 1개라도 있을 경우
			GridBagConstraints gbc = new GridBagConstraints();									// exRecord 한 개에 대한 gbc
			gbc.fill = GridBagConstraints.BOTH;
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridwidth = 5;
			int count = 0;
			
			set_list_panel.removeAll();
			for(expanel exp : expanel_list) {
				gbc.gridy = count++;
				set_list_panel.add(exp,gbc);
			}
		}else
			set_list_panel.removeAll();
		
		set_list_panel.revalidate();															// 운동 선택 패널 초기화
		set_list_panel.repaint();
	}
	
	
	class expanel extends JPanel{
		public expanel(exRecord other_exr) {
			GridBagLayout gbl = new GridBagLayout();
			gbl.columnWidths = new int[] {100,100,50,50,50};
			gbl.rowHeights = new int[] {50};
			
			this.setLayout(gbl);
			this.setBackground(Color.YELLOW);
			
			JLabel ex_name = new JLabel(other_exr.getEx().getname());
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
			
			JButton update_btn = new JButton("수정");
			ActionListener updateBtn_listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//exRecordpage exrp = new exRecordpage(other_exr);
					//exrp.setVisible(true);
				}
			};
			update_btn.addActionListener(updateBtn_listener);
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridx= 3;
			gbc.gridy= 0;
			gbc.insets = new Insets(0, 0, 0, 5);
			
			
			this.add(update_btn,gbc);
			
			// 삭제 버튼
			JButton delete_btn = new JButton("삭제");
			ActionListener delBtn_listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (getindex() >=0) {
						System.out.println(getindex());
						expanel_list.remove(getindex());
					}else {
						System.out.println("잘못됨");
					}
					//받아온 운동 정보에 대한 ex_list_panel 업데이트
					
					repaint_exlist_panel();
				}
			};
			delete_btn.addActionListener(delBtn_listener);
			gbc.gridx= 4;
			gbc.gridy= 0;
			gbc.insets= new Insets(0, 0, 0, 0);
			this.add(delete_btn,gbc);
		}
		private int getindex() {
			return expanel_list.indexOf(this);
		}
	}
	
	// 운동 이름으로 운동정보 set하기
	private void setEx_byname() {
		for(int i = 0; i < exlist.size(); i++) {
			if (exrecord.getEx().getname().equals(exlist.get(i).getname())) { // 이름으로 검색하기 -> 찾았을 때  
				exrecord.setEx(exlist.get(i));
				break;
			}
			else {
				continue;
			}
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
