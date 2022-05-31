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
import set단위class.wc_exRecord;
import set단위class.wc_set;

public class exRecordpage extends JDialog{
	private JPanel defaultpanel;
	private JTextField weight_textField;
	private JTextField today_textField;
	private JPanel set_list_panel; 
	private dayRecord dayrecord;
	private exRecord exrecord;
	private ArrayList<wcset_panel> wcpanel_list; 
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
		if (other_exr.getEx().getcalmethod().equals("")) {				//첫번째 입력
				exrecord = new exRecord(other_exr.getEx().getname(),other_exr.getSet_goal());
				setEx_byname();  //운동 이름으로 운동 정보 setting
				if(exrecord.getEx().getcalmethod().equals("무게 * 횟수")) {
					exrecord = new wc_exRecord(exrecord);
				}
		}else if(other_exr instanceof wc_exRecord) {					// 두번째 이후 중 무게 * 횟수인 운동
			exrecord = (wc_exRecord)other_exr;
			wc_exRecord tmp_wce = (wc_exRecord)other_exr; //exRecode 클래스로 형변환(wc_exRecord 가 exRecord의 자식 클래스이기 때문)
			// wcpanel_list 만들어주기
			if(tmp_wce.getCount_set()==0) {
				wcpanel_list = new ArrayList<>();
			}else {
				wcpanel_list = new ArrayList<>();
				for(wc_set wcs : tmp_wce.getWc_set_ary()) {
					wcset_panel wcp = new wcset_panel(wcs);
					wcpanel_list.add(wcp);
				}
				System.out.println(wcpanel_list.size());
			}
		}else {
			System.err.println("exrecord 새로 받아오기 실패");
		}

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
		
		if(!other_exr.getEx().getcalmethod().equals("")) {
			repaint_wclist_panel();
		}
		
		
		// 세트추가 버튼 클릭
		JButton addset_button = new JButton("세트 추가");
		ActionListener addset_listener= new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 추가할 세트 정보 받아오기
				if(exrecord instanceof wc_exRecord) {
					add_wcsetpage asp = new add_wcsetpage(new exRecord(exrecord));
					asp.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					asp.setModal(true);
					asp.setVisible(true);
					// exrecord에 wc_exRecord 저장하기
					wc_exRecord tmp_wce = (wc_exRecord)exrecord;								// 기존 정보(이름,setgoal) 넣어주기
					wc_set wcs = new wc_set(Integer.valueOf(asp.get_weight()),Integer.valueOf(asp.get_count()));
					tmp_wce.add_wcset(wcs);
					wc_set pwcs = new wc_set();
					exrecord = tmp_wce;
					dayRecordpage.dayrecord.set_exr(exrecord);
					// wc_set 패널 추가
					wcset_panel wcp = new wcset_panel(wcs);
					if(wcpanel_list == null)
						wcpanel_list = new ArrayList<>();
					wcpanel_list.add(wcp);
					repaint_wclist_panel();
				}
				
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
				dayRecordpage.dayrecord.set_exr(exrecord);
				//other_exr.shallow_copy(exrecord);
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
	
	// wcpanel_list를 바탕으로 wclist_panel repaint하기
	private void repaint_wclist_panel(){
		if(wcpanel_list != null || !wcpanel_list.isEmpty()) {  												// 운동 1개라도 있을 경우
			GridBagConstraints gbc = new GridBagConstraints();									// exRecord 한 개에 대한 gbc
			gbc.fill = GridBagConstraints.BOTH;
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridwidth = 5;
			int count = 0;
			
			set_list_panel.removeAll();
			for(wcset_panel exp : wcpanel_list) {
				gbc.gridy = count++;
				set_list_panel.add(exp,gbc);
			}
		}else
			set_list_panel.removeAll();
		
		set_list_panel.revalidate();															// 운동 선택 패널 초기화
		set_list_panel.repaint();
	}
	
	// 무게 * 횟수 세트 패널
	class wcset_panel extends JPanel{
		public wcset_panel(wc_set wcs) {
			GridBagLayout gbl = new GridBagLayout();
			gbl.columnWidths = new int[] {100,100,50,50,50};
			gbl.rowHeights = new int[] {50,50};
			
			this.setLayout(gbl);
			this.setBackground(Color.YELLOW);

			
			
			int count;
			if(wcpanel_list == null || wcpanel_list.size()==0)
				count =1;
			else {
				count = wcpanel_list.size()+1;
			}
			
			// 몇 번째 세트인지 나타내는 라벨 
			JLabel set_lable = new JLabel(Integer.toString(count)+"세트");
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.BOTH;
			gbc.gridx = 0;
			gbc.gridy = 0;
			this.add(set_lable,gbc);
			
			// 목표 무게 필드
			JTextField gweight_textfield = new JTextField();
			gweight_textfield.setText("목표무게"+Integer.toString(wcs.getWeight()));
			gbc = new GridBagConstraints();
			gbc.fill=GridBagConstraints.HORIZONTAL;
			gbc.gridx = 1;
			gbc.gridy = 0;
			this.add(gweight_textfield,gbc);
			
			// 수행 무게 필드
			JTextField pweigth_textfield = new JTextField();
			pweigth_textfield.setText("수행한 무게");
			gbc = new GridBagConstraints();
			gbc.fill=GridBagConstraints.HORIZONTAL;
			gbc.gridx = 1;
			gbc.gridy = 1;
			this.add(pweigth_textfield,gbc);
			
			// 목표 및 수행 저장
			JButton update_btn = new JButton("저장");
			ActionListener updateBtn_listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//exRecordpage exrp = new exRecordpage(other_exr);
					//exrp.setVisible(true);
				}
			};
			update_btn.addActionListener(updateBtn_listener);
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridx= 4;
			gbc.gridy= 1;
			gbc.insets = new Insets(0, 0, 0, 5);
			this.add(update_btn,gbc);
			
			// 삭제 버튼
			JButton delete_btn = new JButton("삭제");
			ActionListener delBtn_listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (getindex() >=0) {
						System.out.println(getindex());
						wcpanel_list.remove(getindex());
					}else {
						System.out.println("잘못됨");
					}
					//받아온 운동 정보에 대한 ex_list_panel 업데이트
					
					repaint_wclist_panel();
				}
			};
			delete_btn.addActionListener(delBtn_listener);
			gbc.gridx= 4;
			gbc.gridy= 0;
			gbc.insets= new Insets(0, 0, 0, 0);
			this.add(delete_btn,gbc);
			
			
			// 목표 횟수
			JLabel gcount_label = new JLabel("목표횟수:"+wcs.getCount());
			gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.BOTH;
			gbc.gridx = 2;
			gbc.gridy = 0;
			this.add(gcount_label,gbc);
			
			// 수행 횟수
			JLabel pcount_label = new JLabel("수행횟수:" + wcs.getCount());
			gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.BOTH;
			gbc.gridx = 2;
			gbc.gridy = 1;
			this.add(pcount_label,gbc);
			
			JLabel resttime_label = new JLabel("휴식시간");
			gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.BOTH;
			gbc.gridx = 3;
			gbc.gridy = 0;
			this.add(resttime_label,gbc);
			
			// 목표-> 수행 load 버튼 // 수행 default 는 0으로 설정. load 누르면 목표 값 가져옴
			JButton load_btn = new JButton("Load");
			ActionListener loadBtn_listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//wcs로 wc_record 찾아서 method 수행
					
					if(exrecord instanceof wc_exRecord) {
						try {
							((wc_exRecord) exrecord).set_pwc();
						} catch (CloneNotSupportedException e2) {
							System.out.println("clone error");
						}
					}
					
					
				}
			};
			load_btn.addActionListener(loadBtn_listener);
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridx= 3;
			gbc.gridy= 1;
			gbc.insets = new Insets(0, 0, 0, 5);
			this.add(load_btn,gbc);
		}
		private int getindex() {
			return wcpanel_list.indexOf(this);
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
