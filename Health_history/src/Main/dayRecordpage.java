package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

import Main.exRecordpage.cset_panel;
import set단위class.c_set;
import set단위class.dayRecord;
import set단위class.exRecord;
import set단위class.wc_exRecord;

public class dayRecordpage extends JDialog {

	private JPanel defaultpanel;
	private JTextField weight_textField;
	private JTextField today_textField;
	private JPanel ex_list_panel; 
	static dayRecord dayrecord;
	private ArrayList<expanel> expanel_list; 
	boolean exist; // 처음 입력하는건지, 있던거 덮어쓰는지
	
	public dayRecordpage(final ArrayList<dayRecord> dR_ary,dayRecord dr) {//이부분 final로 안하니 오류 떠서 final 추가했습니다(동혁)
		
		setTitle("dayRecordpage	");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,400);
		GridBagLayout gb = new GridBagLayout();
		gb.rowHeights = new int[] {50, 50,50,50,50,50,50};
		gb.columnWidths = new int[] {100,100,50,50,50};
		setLayout(gb);
		
		// 기존 정보 있는지에 따라 분기
		dayrecord = dr;
		if(dr.getExr_ary().size()>0) {
			exist = true;
			expanel_list = new ArrayList<>();	
			for (exRecord exr :dr.getExr_ary()) {
				expanel tmp_exp = new expanel(exr);
				expanel_list.add(tmp_exp);
			}
		}else {
			exist = false;
			expanel_list = new ArrayList<>();
			
		}
		// 만약 dr의 ex_ary가 차있다면 expanel_list에 기존내용 추가해줘야함
		
		GridBagConstraints gbc_default = new GridBagConstraints();
		
		/* 날짜 입력 패널 */
		JLabel today_Label = new JLabel("오늘의 날짜");
		today_Label.setHorizontalAlignment(SwingConstants.CENTER);
		gbc_default.anchor = GridBagConstraints.WEST;
		gbc_default.gridx = 0;
		gbc_default.gridy = 0;
		add(today_Label,gbc_default);
		
		today_textField = new JTextField();
		today_textField.setText(dayrecord.getToday_date().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
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
		if(dayrecord.getToday_weight()>0) {
			weight_textField.setText(Double.toString(dayrecord.getToday_weight()));
		}
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
		if(!expanel_list.isEmpty()) {
			repaint_exlist_panel();
		}
		
		// 운동추가 버튼 클릭
		JButton addexr_button = new JButton("운동 추가");
		ActionListener addex_listener= new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 추가할 운동 정보 받아오기
				addexRecordpage exrp = new addexRecordpage();
				exrp.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				exrp.setModal(true);
				exrp.setVisible(true);
				exRecord tmp_ex = new exRecord(exrp.get_exname(),exrp.get_setgoal());
				
				// 받아온 운동 정보 저장
				dayrecord.add_exr(tmp_ex);
				expanel tmp_exp = new expanel(tmp_ex);
				if (expanel_list == null)
					expanel_list = new ArrayList<>();	
				expanel_list.add(tmp_exp);
				
				//받아온 운동 정보에 대한 ex_list_panel 업데이트
				repaint_exlist_panel();
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
		ActionListener savedR_listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 적어도 date와 몸무게는 있어야함 없으면 에러창
				if(today_textField.getText().equals("") ) {
					savedR_check_dialog icd = new savedR_check_dialog();
					icd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					icd.setModal(true);
					icd.setVisible(true);
				}
				//몸무게 저장
				dayrecord.setToday_weight(Double.valueOf(weight_textField.getText()));				
				
				// dayRecord를 dR_ary에 추가
				// 없으면 추가 있으면 다시 세팅
				if(exist == false)
					dR_ary.add(dayrecord);
				else {
					int index=0;
					for(dayRecord tmp : dR_ary) {
						if(dr.getToday_date().equals(tmp.getToday_date()))
							break;
						else 
							index++;
					}
					dR_ary.set(index, dayrecord);
				}
				// 달력 페이지로 돌아감
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
		if(expanel_list!= null && !expanel_list.isEmpty()) {  												// 운동 1개라도 있을 경우
			GridBagConstraints gbc = new GridBagConstraints();									// exRecord 한 개에 대한 gbc
			gbc.fill = GridBagConstraints.BOTH;
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridwidth = 5;
			int count = 0;
			
			ex_list_panel.removeAll();
			for(expanel exp : expanel_list) {
				gbc.gridy = count++;
				ex_list_panel.add(exp,gbc);
			}
		}else
			ex_list_panel.removeAll();
		
		ex_list_panel.revalidate();															// 운동 선택 패널 초기화
		ex_list_panel.repaint();
	}
	/*
	private void update_exlist() {
		for(expanel ep : expanel_list) {
			ep.update_expanel(null););
		}
	}*/
	
	class expanel extends JPanel{
		private JLabel ex_name;
		private JLabel setnum_label;
		
		public expanel(exRecord other_exr) {
			
			GridBagLayout gbl = new GridBagLayout();
			gbl.columnWidths = new int[] {100,100,50,50,50};
			gbl.rowHeights = new int[] {50};
			
			this.setLayout(gbl);
			this.setBackground(Color.YELLOW);
			
			ex_name = new JLabel(other_exr.getEx().getname());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.BOTH;
			gbc.gridx = 0;
			gbc.gridy = 0;
			this.add(ex_name,gbc);
			
		
			JLabel set_label = new JLabel("현재/목표");
			gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.BOTH;
			gbc.gridx = 1;
			gbc.gridy = 0;
			this.add(set_label,gbc);
			
			setnum_label = new JLabel("("+Integer.toString(other_exr.getCount_set())+"/"+Integer.toString(other_exr.getSet_goal())+")");
			gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.BOTH;
			gbc.gridx = 2;
			gbc.gridy = 0;
			this.add(setnum_label,gbc);
			
			JButton update_btn = new JButton("수정");
			ActionListener updateBtn_listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					exRecordpage exrp;
					exRecord exr = dayrecord.getExr_ary().get(getindex());	
					if(exr instanceof wc_exRecord) {
						exrp = new exRecordpage((wc_exRecord)exr,dayrecord);
					}else {
						exrp = new exRecordpage(exr,dayrecord);
					}
					exrp.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					exrp.setModal(true);
					exrp.setVisible(true);
					
					// 창 닫혔을 때
					// 운동 패널 리스트 수정
					exr = dayrecord.getExr_ary().get(getindex());									// 수정된 exRecord 받아오기
					ex_name.setText(exr.getEx().getname());
					setnum_label.setText("("+Integer.toString(exr.getCount_set())+"/"+Integer.toString(exr.getSet_goal())+")");
					repaint_exlist_panel();
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
						dayrecord.delete_exr(other_exr);
						expanel_list.remove(getindex());
					}else {
						System.err.println("dayRecordpage: 운동삭제 오류");
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
	
	
	
	public void set_date(LocalDate date) {
		dayrecord.setToday_date(date);
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
