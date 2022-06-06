package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import set단위class.c_exRecord;
import set단위class.c_set;
import set단위class.dayRecord;
import set단위class.exRecord;
import set단위class.exercise;
import set단위class.exlistClass;
import set단위class.t_exRecord;
import set단위class.t_set;
import set단위class.wc_exRecord;
import set단위class.wc_set;

public class exRecordpage extends JFrame{
	private JPanel set_list_panel; 
	private exRecord exrecord;
	private ArrayList<wcset_panel> wcpanel_list; 
	private ArrayList<cset_panel> cpanel_list; 
	private ArrayList<tset_panel> tpanel_list; 
	private ArrayList<exercise> exlist;
	private dayRecord dayrecord;
	private String[] label_ary;
	JLabel gp_label;
	
	public exRecordpage(exRecord other_exr, dayRecord pre_dayRecord) {
		dayrecord = pre_dayRecord;
		setTitle("exRecordpage	");
		setBackground(new Color(175,237,100));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(470,500);
		GridBagLayout gb = new GridBagLayout();
		gb.columnWidths = new int[] {10,75,70,65,80,60,75,10};
		gb.rowHeights = new int[] {50,50,50,50,50,50,50,50};
		setLayout(gb);
		GridBagConstraints gbc_default = new GridBagConstraints();
		/* 받아온 운동명으로 exRecord 정보 채우기 */		// other_exr는 name 과 setgoal 정보만 있음 
		exlistClass elc = new exlistClass("ALL_WORKOUT");
		exlist = elc.get_exlist();
		setting_exRecord(other_exr);

		/* 받아온 운동명 출력 패널 */
		JLabel today_Label = new JLabel(exrecord.getEx().getname());
		gbc_default.fill = GridBagConstraints.BOTH;
		gbc_default.gridx = 3;
		gbc_default.gridy = 0;
		gbc_default.weightx =2;
		add(today_Label,gbc_default);
		
		set_labelary();
		JLabel weight_label = new JLabel(label_ary[0]);
		gbc_default.anchor = GridBagConstraints.WEST;
		gbc_default.fill = GridBagConstraints.HORIZONTAL;
		gbc_default.gridx = 2;
		gbc_default.gridy = 1;
		add(weight_label,gbc_default);
		
		JLabel count_label = new JLabel(label_ary[1]);
		gbc_default.anchor = GridBagConstraints.WEST;
		gbc_default.fill = GridBagConstraints.HORIZONTAL;
		gbc_default.gridx = 3;
		gbc_default.gridy = 1;
		add(count_label,gbc_default);
		
		JLabel resttime_label = new JLabel(label_ary[2]);
		gbc_default.anchor = GridBagConstraints.WEST;
		gbc_default.fill = GridBagConstraints.BOTH;
		gbc_default.gridx = 4;
		gbc_default.gridy = 1;
		add(resttime_label,gbc_default);
		
		gp_label = new JLabel();
		gp_label.setText("("+Integer.toString(exrecord.getCount_set())+"/"+Integer.toString(exrecord.getSet_goal())+")");
		gp_label.setHorizontalAlignment(SwingConstants.RIGHT);
		gbc_default.fill = GridBagConstraints.BOTH;
		gbc_default.gridx = 6;
		gbc_default.gridy = 1;
		add(gp_label,gbc_default);
		
		
		/* 세트 리스트 패널 */
		set_list_panel = new JPanel();
		set_list_panel.setBackground(Color.WHITE);
		gb = new GridBagLayout();
		gb.columnWidths = new int[]{};
		gb.rowHeights = new int[]{50, 50, 50, 50, 50};
		set_list_panel.setLayout(gb);
		
		gbc_default = new GridBagConstraints();
		gbc_default.fill = GridBagConstraints.BOTH;
		gbc_default.gridx = 1;
		gbc_default.gridy = 2;
		gbc_default.gridheight = 5;
		gbc_default.gridwidth = 6;
		JScrollPane sp = new JScrollPane(set_list_panel);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(sp, gbc_default);
		
		// 처음 세트 리스트 세팅하기
		if(exrecord instanceof wc_exRecord) {
			repaint_wclist_panel();
		}else if(exrecord instanceof c_exRecord) {
			repaint_clist_panel();
		}else if(exrecord instanceof t_exRecord) {
			repaint_tlist_panel();
		}
		
		// 세트추가 버튼 클릭
		JButton addset_button = new JButton("세트 추가");
		ActionListener addset_listener= new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// 추가할 세트 정보 받아오기
				if(exrecord instanceof wc_exRecord) { // 이렇게 하니 운동을 하나 추가하면 세트 추가 버튼 눌러도
					//창이 안뜬다
					dayrecord.printallexr_ary();
					add_wcsetpage asp = new add_wcsetpage(new exRecord(exrecord));
					asp.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					asp.setModal(true);
					asp.setVisible(true);
					// 기존 정보(이름,setgoal) 넣어주기
					wc_set wcs = new wc_set(Integer.valueOf(asp.get_weight()),Integer.valueOf(asp.get_count()));
					wcs.setRest_time(asp.get_resttime());
					((wc_exRecord)exrecord).add_wcset(wcs);
					dayrecord.set_exr(exrecord);
					// wc_set 패널 추가
					wcset_panel wcp = new wcset_panel(wcs);
					wcpanel_list.add(wcp);
					repaint_wclist_panel();
				}
				else if(exrecord instanceof c_exRecord) { //Ƚ���� ��
					add_csetpage asp = new add_csetpage(new exRecord(exrecord));
					asp.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					asp.setModal(true);
					asp.setVisible(true);
					// exrecord에 wc_exRecord 저장하기
					c_set cs = new c_set(Integer.valueOf(asp.get_count()));
					cs.setRest_time(asp.get_resttime());
					((c_exRecord)exrecord).add_wcset(cs);
					dayrecord.set_exr(exrecord);
					// wc_set 패널 추가
					cset_panel cp = new cset_panel(cs);
					cpanel_list.add(cp);
					repaint_clist_panel();
				}
				else if (exrecord instanceof t_exRecord) {
					add_tsetpage asp = new add_tsetpage(new exRecord(exrecord));
					asp.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					asp.setModal(true);
					asp.setVisible(true);
					// exrecord에 t_exRecord 저장하기
					t_set wcs = new t_set(asp.get_goaltime());
					wcs.setRest_time(asp.get_resttime());
					((t_exRecord)exrecord).add_tset(wcs);	
					dayrecord.set_exr(exrecord);
					// wc_set 패널 추가
					 tset_panel tp = new tset_panel(wcs);
					tpanel_list.add(tp);
					repaint_tlist_panel();
				}
				set_gplabel();
			}
		};
		addset_button.addActionListener(addset_listener);
		gbc_default =new GridBagConstraints();		
		gbc_default.anchor = GridBagConstraints.EAST;
		gbc_default.gridx = 2;
		gbc_default.gridy =7;
		gbc_default.gridwidth = 2;
		add(addset_button, gbc_default);
		
		//저장 버튼
		JButton savedR_button = new JButton("저장");
		ActionListener savedR_listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exrecord.setCount_set(exrecord.cal_count_set());
				dayrecord.set_exr(exrecord);
				dispose();
				
			}
		};
		savedR_button.addActionListener(savedR_listener);
		gbc_default.anchor = GridBagConstraints.CENTER;
		gbc_default.ipadx = 20;
		gbc_default.gridx = 4;
		gbc_default.gridy= 7;
		gbc_default.gridwidth = 2;
		add(savedR_button, gbc_default);
	}
	
	// wcpanel_list를 바탕으로 wclist_panel repaint하기
	private void repaint_wclist_panel(){
		if(wcpanel_list != null && !wcpanel_list.isEmpty()) {  												// 운동 1개라도 있을 경우
			GridBagConstraints gbc = new GridBagConstraints();									// exRecord 한 개에 대한 gbc
			gbc.fill = GridBagConstraints.BOTH;
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridwidth = 5;
			int count = 0;
			
			set_list_panel.removeAll();
			for(wcset_panel exp : wcpanel_list) {
				exp.set_setlabel(count+1);
				gbc.gridy = count++;
				set_list_panel.add(exp,gbc);
			}
		}else
			set_list_panel.removeAll();
		
		set_list_panel.revalidate();															// 운동 선택 패널 초기화
		set_list_panel.repaint();
	}
	private void repaint_clist_panel(){
		if(cpanel_list != null && !cpanel_list.isEmpty()) {  												// 운동 1개라도 있을 경우
			GridBagConstraints gbc = new GridBagConstraints();									// exRecord 한 개에 대한 gbc
			gbc.fill = GridBagConstraints.BOTH;
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridwidth = 5;
			int count = 0;
			
			set_list_panel.removeAll();
			for(cset_panel exp : cpanel_list) {
				exp.set_setlabel(count+1);
				gbc.gridy = count++;
				set_list_panel.add(exp,gbc);
			}
		}else
			set_list_panel.removeAll();
		
		set_list_panel.revalidate();															// 운동 선택 패널 초기화
		set_list_panel.repaint();
	}
	private void repaint_tlist_panel(){
		if(tpanel_list != null && !tpanel_list.isEmpty()) {  												// 운동 1개라도 있을 경우
			GridBagConstraints gbc = new GridBagConstraints();									// exRecord 한 개에 대한 gbc
			gbc.fill = GridBagConstraints.BOTH;
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridwidth = 5;
			int count = 0;
			
			set_list_panel.removeAll();
			for(tset_panel exp : tpanel_list) {
				exp.set_setlabel(count+1);
				gbc.gridy = count++;
				set_list_panel.add(exp,gbc);
			}
		}else
			set_list_panel.removeAll();
		
		set_list_panel.revalidate();															// 운동 선택 패널 초기화
		set_list_panel.repaint();
	}
	private void setting_exRecord(exRecord other_exr) {
		if (other_exr.getEx().getcalmethod().equals("")) {											//첫번째 접근
			exrecord = new exRecord(other_exr.getEx().getname(),other_exr.getSet_goal());
			setEx_byname();  																		//운동 이름으로 운동 정보 setting
			// 입력받은 목표 세트수로 초기설정 및 패널 생성
			if(exrecord.getEx().getcalmethod().equals("무게 * 횟수")) {
				exrecord = new wc_exRecord(exrecord);
				wcpanel_list = new ArrayList<>();
				for(int i =0 ; i < other_exr.getSet_goal() ;i++) {
					wc_set tmp_ws = new wc_set(10,3,LocalTime.of(0, 2,0));							// default 값 : 10 ,3 , 02:00
					((wc_exRecord)exrecord).first_add_wcset(tmp_ws);
					wcpanel_list.add(new wcset_panel(tmp_ws));
				}
			}else if (exrecord.getEx().getcalmethod().equals("횟수")) {
				exrecord = new c_exRecord(exrecord);
				cpanel_list = new ArrayList<>();
				for(int i =0 ; i < other_exr.getSet_goal() ;i++) {
					c_set tmp_cs = new c_set(3,LocalTime.of(0, 2,0));								// default 값 : 3 , 02:00
					((c_exRecord)exrecord).first_add_wcset(tmp_cs);
					cpanel_list.add(new cset_panel(tmp_cs));
				}
			}else if (exrecord.getEx().getcalmethod().equals("시간")) {
				exrecord = new t_exRecord(exrecord);
				tpanel_list = new ArrayList<>();
				for(int i =0 ; i < other_exr.getSet_goal() ;i++) {
					t_set tmp_cs = new t_set(LocalTime.of(0, 3,0),LocalTime.of(0, 2,0));			// default 값 : 03:00 , 02:00
					((t_exRecord)exrecord).first_add_tset(tmp_cs);
					tpanel_list.add(new tset_panel(tmp_cs));
				}
			}
		}else if(other_exr instanceof wc_exRecord) {												// 재접근 - 무게 * 횟수
			exrecord = other_exr;
			// wcpanel_list 만들어주기
			if(exrecord.getSet_goal()==0) {
				wcpanel_list = new ArrayList<>();
			}else {
				wcpanel_list = new ArrayList<>();
				for(wc_set wcs : ((wc_exRecord)exrecord).getWc_set_ary()) {
					wcpanel_list.add(new wcset_panel(wcs));
				}
			}
		}else if(other_exr instanceof c_exRecord) {													// 재접근 - 횟수
			exrecord = other_exr;
			// wcpanel_list 만들어주기
			if(exrecord.getSet_goal()==0) {
				cpanel_list = new ArrayList<>();
			}else {
				cpanel_list = new ArrayList<>();
				for(c_set cs : ((c_exRecord)exrecord).getc_set_ary()) {
					cpanel_list.add(new cset_panel(cs));
				}
			}
		}else if(other_exr instanceof t_exRecord) {													// 재접근 - 시간
			exrecord = other_exr;
			// wcpanel_list 만들어주기
			if(exrecord.getSet_goal()==0) {
				tpanel_list = new ArrayList<>();
			}else {
				tpanel_list = new ArrayList<>();
				for(t_set ts :((t_exRecord)exrecord).gett_set_ary()) {
					tpanel_list.add(new tset_panel(ts));
				}
			}
		}
		else {
			System.err.println("exrecord 새로 받아오기 실패");
		}
	}
	private void set_labelary() {
		label_ary = new String[3];
		if(exrecord instanceof wc_exRecord) {
			label_ary[0] = "무게";
			label_ary[1] = "횟수";
			label_ary[2] = "휴식시간";
		}else if(exrecord instanceof c_exRecord){
			label_ary[0] = "횟수";
			label_ary[1] = "휴식시간";
			label_ary[2] = "";
		}else if(exrecord instanceof t_exRecord){
			label_ary[0] = "목표시간";
			label_ary[1] = "수행시간";
			label_ary[2] = "휴식시간";
		}
	}
	private void set_gplabel() {
		exrecord.setCount_set(exrecord.cal_count_set());
		gp_label.setText("("+Integer.toString(exrecord.getCount_set())+"/"+Integer.toString(exrecord.getSet_goal())+")");
	}
	// 무게 * 횟수 세트 패널
	class wcset_panel extends JPanel{
		JLabel set_lable;
		JPanel summaryPanel;
		JPanel updatePanel;
		JButton check_btn;
		JButton open_Btn;
		JButton close_Btn;
		JTextField gweight_textfield;
		JTextField gcount_textfield;
		JTextField resttime_textfield;
		JTextField pweight_textfield;
		JTextField pcount_textfield;
		JLabel weight_sum_label;
		JLabel count_sum_label;
		JLabel resttime_sum_label;
		wc_set wcs;
		GridBagConstraints gbc;
		
		public wcset_panel(wc_set other_wcs) {
			wcs = other_wcs;
			
			this.setBackground(Color.YELLOW);
			GridBagLayout gbl = new GridBagLayout();
			gbl.columnWidths = new int[] {};
			gbl.rowHeights = new int[] {50};
			this.setLayout(gbl);
			
			/*기본 출력 패널*/
			summaryPanel = new JPanel();
			gbl = new GridBagLayout();
			gbl.columnWidths = new int[] {75,70,70,80,60,60};
			gbl.rowHeights = new int[] {50};
			summaryPanel.setLayout(gbl);
			
			set_lable = new JLabel(get_setnum()+"세트");
			set_gbc(0, 0,GridBagConstraints.BOTH);
			set_lable.setHorizontalAlignment(JLabel.LEFT);
			summaryPanel.add(set_lable,gbc);
			
			weight_sum_label = new JLabel("("+Integer.toString(other_wcs.getP_weight())+"/"+Integer.toString(other_wcs.getWeight())+")");
			set_gbc(1, 0,GridBagConstraints.BOTH);
			summaryPanel.add(weight_sum_label,gbc);

			count_sum_label = new JLabel("("+Integer.toString(other_wcs.getP_count())+"/"+Integer.toString(other_wcs.getCount())+")");
			set_gbc(2, 0,GridBagConstraints.BOTH);
			summaryPanel.add(count_sum_label,gbc);
			
			resttime_sum_label = new JLabel(other_wcs.getRest_time().format(DateTimeFormatter.ofPattern("mm:ss")));
			set_gbc(3, 0,GridBagConstraints.BOTH);
			summaryPanel.add(resttime_sum_label,gbc);
			
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
					((wc_exRecord)exrecord).del_wcset(wcs);										//exrecord에서 삭제된 내용 날려주기
					repaint_wclist_panel();														//받아온 운동 정보에 대한 ex_list_panel 업데이트
				}
			};
			delete_btn.addActionListener(delBtn_listener);
			set_gbc(4, 0,GridBagConstraints.HORIZONTAL);
			summaryPanel.add(delete_btn,gbc);
			
			open_Btn = new JButton("열기");
			ActionListener loadBtn_listener2 = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					set_gbc(0, 1, GridBagConstraints.BOTH);
					wcset_panel.this.add(updatePanel,gbc);										// update패널 보여주기
					wcset_panel.this.summaryPanel.remove(check_btn);							// 닫힘 버튼으로 바꾸기
					wcset_panel.this.check_btn = wcset_panel.this.close_Btn;
					set_gbc(5, 0, GridBagConstraints.HORIZONTAL);
					wcset_panel.this.summaryPanel.add(check_btn,gbc);
					
					wcpanel_list.set(wcset_panel.this.getindex(), wcset_panel.this);			// 패널리스트에 패널 새로고침 후 repaint
					repaint_wclist_panel();
				}
				
			};
			open_Btn.addActionListener(loadBtn_listener2);
			
			close_Btn= new JButton("닫기");
			ActionListener closeBtn_listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					wcset_panel.this.remove(updatePanel);
					wcset_panel.this.summaryPanel.remove(check_btn);
					wcset_panel.this.check_btn = wcset_panel.this.open_Btn;
					set_gbc(5, 0, GridBagConstraints.HORIZONTAL);
					wcset_panel.this.summaryPanel.add(check_btn,gbc);
					wcpanel_list.set(wcset_panel.this.getindex(), wcset_panel.this);
					repaint_wclist_panel();
				}
			};
			close_Btn.addActionListener(closeBtn_listener);
			
			check_btn = open_Btn;
			set_gbc(5, 0,GridBagConstraints.HORIZONTAL);
			summaryPanel.add(check_btn,gbc);
			
			set_gbc(0, 0, GridBagConstraints.BOTH);
			this.add(summaryPanel,gbc);
			
			/* 열기버튼 클릭시 나오는 패널 */
			updatePanel = new JPanel();
			GridBagLayout gbl2 = new GridBagLayout();
			gbl2.columnWidths = new int[]  {70,70,70,80,60,60};
			gbl2.rowHeights = new int[] {50,50};
			
			updatePanel.setLayout(gbl2);

			// 몇 번째 세트인지 나타내는 라벨 
			JLabel set_lable = new JLabel("목표");
			set_gbc(0, 0,GridBagConstraints.BOTH);
			set_lable.setHorizontalAlignment(JLabel.LEFT);
			updatePanel.add(set_lable,gbc);
			
			// 목표 무게 필드
			gweight_textfield = new JTextField();
			gweight_textfield.setText(Integer.toString(other_wcs.getWeight()));
			set_gbc(1, 0,GridBagConstraints.HORIZONTAL);
			updatePanel.add(gweight_textfield,gbc);
			
			// 목표 횟수
			gcount_textfield = new JTextField();
			gcount_textfield.setText(Integer.toString(other_wcs.getCount()));
			set_gbc(2, 0,GridBagConstraints.HORIZONTAL);
			updatePanel.add(gcount_textfield,gbc);
			
			// 휴식시간
			resttime_textfield = new JTextField();
			resttime_textfield.setText(other_wcs.getRest_time().format(DateTimeFormatter.ofPattern("mm:ss")));
			set_gbc(3, 0,GridBagConstraints.HORIZONTAL);
			updatePanel.add(resttime_textfield,gbc);
			
			// 수행 라벨
			JLabel performed_label = new JLabel("수행");
			set_gbc(0, 1,GridBagConstraints.BOTH);
			updatePanel.add(performed_label,gbc);
			
			// 수행 무게 필드
			pweight_textfield = new JTextField();
			pweight_textfield.setText(Integer.toString(other_wcs.getP_weight()));
			set_gbc(1, 1,GridBagConstraints.HORIZONTAL);
			updatePanel.add(pweight_textfield,gbc);
			
			// 수행 횟수
			pcount_textfield = new JTextField();
			pcount_textfield.setText(Integer.toString(other_wcs.getP_count()));
			set_gbc(2, 1,GridBagConstraints.HORIZONTAL);
			updatePanel.add(pcount_textfield,gbc);
			
			// 목표-> 수행 load 버튼 // 수행 default 는 0으로 설정. load 누르면 목표 값 가져옴
			JButton load_btn = new JButton("Load");
			ActionListener loadBtn_listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					wcs.performed_update();
					pweight_textfield.setText(Integer.toString(wcs.getP_weight()));
					pcount_textfield.setText(Integer.toString(wcs.getP_count()));
					weight_sum_label.setText("("+Integer.toString(wcs.getP_weight())+"/"+Integer.toString(wcs.getWeight())+")");
					count_sum_label.setText("("+Integer.toString(wcs.getP_count())+"/"+Integer.toString(wcs.getCount())+")");
					set_gplabel();
				}
			};
			load_btn.addActionListener(loadBtn_listener);
			set_gbc(3, 1,GridBagConstraints.HORIZONTAL);
			updatePanel.add(load_btn,gbc);
			
			JButton reset_Btn = new JButton("reset");
			ActionListener resetBtn_listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					wcs.performed_reset();
					pweight_textfield.setText(Integer.toString(wcs.getP_weight()));
					pcount_textfield.setText(Integer.toString(wcs.getP_count()));
					weight_sum_label.setText("("+Integer.toString(wcs.getP_weight())+"/"+Integer.toString(wcs.getWeight())+")");
					count_sum_label.setText("("+Integer.toString(wcs.getP_count())+"/"+Integer.toString(wcs.getCount())+")");
					set_gplabel();
				}
			};
			reset_Btn.addActionListener(resetBtn_listener);
			set_gbc(4, 1,GridBagConstraints.HORIZONTAL);
			updatePanel.add(reset_Btn,gbc);
			
			// 목표 및 수행 저장
			JButton update_btn = new JButton("저장");
			ActionListener updateBtn_listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if(!check_int_format(gweight_textfield.getText())) {
						JOptionPane.showMessageDialog(null, "목표 무게는 정수만 입력가능합니다","경고", JOptionPane.ERROR_MESSAGE);
						gweight_textfield.setText("");
						return;
					}
					if(!check_int_format(gcount_textfield.getText())) {
						JOptionPane.showMessageDialog(null, "목표 횟수는 정수만 입력가능합니다","경고", JOptionPane.ERROR_MESSAGE);
						gcount_textfield.setText("");
						return;
					}
					if(!check_int_format(pcount_textfield.getText())) {
						JOptionPane.showMessageDialog(null, "수행 무게는 정수만 입력가능합니다","경고", JOptionPane.ERROR_MESSAGE);
						pcount_textfield.setText("");
						return;
					}
					if(!check_int_format(pweight_textfield.getText())) {
						JOptionPane.showMessageDialog(null, "수행 횟수는 정수만 입력가능합니다","경고", JOptionPane.ERROR_MESSAGE);
						pweight_textfield.setText("");
						return;
					}
					if(!check_time_format(resttime_textfield.getText())){
						JOptionPane.showMessageDialog(null, "휴식시간을 다시 입력하세요","경고", JOptionPane.ERROR_MESSAGE);
						resttime_textfield.setText("00:00");
						return;
					}
					
					wcs.setWeight(Integer.valueOf(gweight_textfield.getText()));
					wcs.setCount(Integer.valueOf(gcount_textfield.getText()));
					wcs.setP_count(Integer.valueOf(pcount_textfield.getText()));
					wcs.setP_weight(Integer.valueOf(pweight_textfield.getText()));
					wcs.setRest_time(resttime_textfield.getText());
					
					weight_sum_label.setText("("+Integer.toString(wcs.getP_weight())+"/"+Integer.toString(wcs.getWeight())+")");
					count_sum_label.setText("("+Integer.toString(wcs.getP_count())+"/"+Integer.toString(wcs.getCount())+")");
					resttime_sum_label.setText(wcs.getRest_time().format(DateTimeFormatter.ofPattern("mm:ss")));
					set_gplabel();
				}
			};
			update_btn.addActionListener(updateBtn_listener);
			set_gbc(5, 1,GridBagConstraints.HORIZONTAL);
			updatePanel.add(update_btn,gbc);
		}
		public void set_setlabel(int setnum) {
			set_lable.setText(setnum + "세트");
		}
		private int getindex() {
			return wcpanel_list.indexOf(this);
		}
		
		private int get_setnum() {
			if(wcpanel_list == null || wcpanel_list.size()==0)
				return 1;
			else 
				return wcpanel_list.size()+1;
		}
		private void set_gbc(int x, int y, int fill) {
			gbc = new GridBagConstraints();
			gbc.gridx = x;
			gbc.gridy = y;
			gbc.fill = fill;
		}
	}
	class cset_panel extends JPanel{
		JPanel summaryPanel;
		JPanel updatePanel;
		JButton check_btn;
		JButton open_Btn;
		JButton close_Btn;		
		JLabel set_lable;
		JTextField gcount_textfield;
		JTextField resttime_textfield;
		JTextField pcount_textfield;
		JLabel count_sum_label;
		JLabel resttime_sum_label;
		c_set cs;
		GridBagConstraints gbc;
		public cset_panel(c_set other_cs) {
			cs = other_cs;
			GridBagLayout gbl = new GridBagLayout();
			gbl.columnWidths = new int[] {};
			gbl.rowHeights = new int[] {50};
			
			this.setLayout(gbl);
			this.setBackground(Color.YELLOW);
			
			/*기본 출력 패널*/
			summaryPanel = new JPanel();
			gbl = new GridBagLayout();
			gbl.columnWidths = new int[] {75,70,70,80,60,60};
			gbl.rowHeights = new int[] {50};
			summaryPanel.setLayout(gbl);
			
			set_lable = new JLabel(get_setnum()+"세트");
			set_gbc(0, 0,GridBagConstraints.BOTH);
			set_lable.setHorizontalAlignment(JLabel.LEFT);
			summaryPanel.add(set_lable,gbc);
			
			count_sum_label= new JLabel("("+Integer.toString(other_cs.getP_count())+"/"+Integer.toString(other_cs.getCount())+")");
			set_gbc(1, 0,GridBagConstraints.BOTH);
			summaryPanel.add(count_sum_label,gbc);
			
			resttime_sum_label = new JLabel(other_cs.getRest_time().format(DateTimeFormatter.ofPattern("mm:ss")));
			set_gbc(2, 0,GridBagConstraints.BOTH);
			summaryPanel.add(resttime_sum_label,gbc);
			
			// 삭제 버튼
			JButton delete_btn = new JButton("삭제");
			ActionListener delBtn_listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (getindex() >=0) {
						System.out.println(getindex());
						cpanel_list.remove(getindex());
					}else {
						System.out.println("잘못됨");
					}
					((c_exRecord)exrecord).del_cset(cs);										//exrecord에서 삭제된 내용 날려주기
					repaint_clist_panel();														//받아온 운동 정보에 대한 ex_list_panel 업데이트
				}
			};
			delete_btn.addActionListener(delBtn_listener);
			set_gbc(4, 0,GridBagConstraints.HORIZONTAL);
			summaryPanel.add(delete_btn,gbc);
			
			open_Btn = new JButton("열기");
			ActionListener loadBtn_listener2 = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					set_gbc(0, 1, GridBagConstraints.BOTH);
					cset_panel.this.add(updatePanel,gbc);										// update패널 보여주기
					cset_panel.this.summaryPanel.remove(check_btn);							// 닫힘 버튼으로 바꾸기
					cset_panel.this.check_btn = cset_panel.this.close_Btn;
					set_gbc(5, 0, GridBagConstraints.HORIZONTAL);
					cset_panel.this.summaryPanel.add(check_btn,gbc);
					
					cpanel_list.set(cset_panel.this.getindex(), cset_panel.this);			// 패널리스트에 패널 새로고침 후 repaint
					repaint_clist_panel();
				}
				
			};
			open_Btn.addActionListener(loadBtn_listener2);
			
			close_Btn= new JButton("닫기");
			ActionListener closeBtn_listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cset_panel.this.remove(updatePanel);
					cset_panel.this.summaryPanel.remove(check_btn);
					cset_panel.this.check_btn = cset_panel.this.open_Btn;
					set_gbc(5, 0, GridBagConstraints.HORIZONTAL);
					cset_panel.this.summaryPanel.add(check_btn,gbc);
					cpanel_list.set(cset_panel.this.getindex(), cset_panel.this);
					repaint_clist_panel();
				}
			};
			close_Btn.addActionListener(closeBtn_listener);
			
			check_btn = open_Btn;
			set_gbc(5, 0,GridBagConstraints.HORIZONTAL);
			summaryPanel.add(check_btn,gbc);
			
			set_gbc(0, 0, GridBagConstraints.BOTH);
			this.add(summaryPanel,gbc);
			
			/* 열기버튼 클릭시 나오는 패널 */
			updatePanel = new JPanel();
			GridBagLayout gbl2 = new GridBagLayout();
			gbl2.columnWidths = new int[] {70,70,70,80,60,60};
			gbl2.rowHeights = new int[] {50,50};
			updatePanel.setLayout(gbl2);
			
			JLabel goal_label = new JLabel("목표");
			set_gbc(1, 0,GridBagConstraints.BOTH);
			updatePanel.add(goal_label,gbc);
			
			// 목표 횟수
			gcount_textfield = new JTextField();
			gcount_textfield.setText(Integer.toString(cs.getCount()));
			set_gbc(2, 0,GridBagConstraints.HORIZONTAL);
			updatePanel.add(gcount_textfield,gbc);
			
			// 휴식시간
			resttime_textfield = new JTextField();
			resttime_textfield.setText(cs.getRest_time().format(DateTimeFormatter.ofPattern("mm:ss")));
			set_gbc(3, 0,GridBagConstraints.HORIZONTAL);
			updatePanel.add(resttime_textfield,gbc);
			
			// 수행 라벨
			JLabel performed_label = new JLabel("수행");
			set_gbc(1, 1,GridBagConstraints.BOTH);
			updatePanel.add(performed_label,gbc);
			
			// 수행 횟수
			pcount_textfield = new JTextField();
			pcount_textfield.setText(Integer.toString(cs.getP_count()));
			set_gbc(2, 1,GridBagConstraints.HORIZONTAL);
			updatePanel.add(pcount_textfield,gbc);
			
			// 목표-> 수행 load 버튼 // 수행 default 는 0으로 설정. load 누르면 목표 값 가져옴
			JButton load_btn = new JButton("Load");
			ActionListener loadBtn_listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cs.performed_update();
					pcount_textfield.setText(Integer.toString(cs.getP_count()));
					count_sum_label.setText("("+Integer.toString(cs.getP_count())+"/"+Integer.toString(cs.getCount())+")");
					set_gplabel();
				}
			};
			load_btn.addActionListener(loadBtn_listener);
			set_gbc(3, 1,GridBagConstraints.HORIZONTAL);
			updatePanel.add(load_btn,gbc);
			
			JButton reset_Btn = new JButton("reset");
			ActionListener resetBtn_listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cs.performed_reset();
					pcount_textfield.setText(Integer.toString(cs.getP_count()));
					count_sum_label.setText("("+Integer.toString(cs.getP_count())+"/"+Integer.toString(cs.getCount())+")");
					set_gplabel();
				}
			};
			reset_Btn.addActionListener(resetBtn_listener);
			set_gbc(4, 1,GridBagConstraints.HORIZONTAL);
			updatePanel.add(reset_Btn,gbc);
			
			// 목표 및 수행 저장
			JButton update_btn = new JButton("저장");
			ActionListener updateBtn_listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if(!check_int_format(gcount_textfield.getText())) {
						JOptionPane.showMessageDialog(null, "목표 횟수는 정수만 입력가능합니다","경고", JOptionPane.ERROR_MESSAGE);
						gcount_textfield.setText("");
						return;
					}
					if(!check_int_format(pcount_textfield.getText())) {
						JOptionPane.showMessageDialog(null, "수행 무게는 정수만 입력가능합니다","경고", JOptionPane.ERROR_MESSAGE);
						pcount_textfield.setText("");
						return;
					}
					if(!check_time_format(resttime_textfield.getText())){
						JOptionPane.showMessageDialog(null, "휴식시간을 다시 입력하세요","경고", JOptionPane.ERROR_MESSAGE);
						resttime_textfield.setText("00:00");
						return;
					}
					
					cs.setCount(Integer.valueOf(gcount_textfield.getText()));
					cs.setP_count(Integer.valueOf(pcount_textfield.getText()));
					cs.setRest_time(resttime_textfield.getText());
					count_sum_label.setText("("+Integer.toString(cs.getP_count())+"/"+Integer.toString(cs.getCount())+")");
					resttime_sum_label.setText(cs.getRest_time().format(DateTimeFormatter.ofPattern("mm:ss")));
					set_gplabel();
				}
			};
			update_btn.addActionListener(updateBtn_listener);
			set_gbc(5, 1,GridBagConstraints.HORIZONTAL);
			updatePanel.add(update_btn,gbc);
			
		}
		public void set_setlabel(int setnum) {
			set_lable.setText(setnum + "세트");
		}
		private int getindex() {
			return cpanel_list.indexOf(this);
		}
		
		private int get_setnum() {
			if(cpanel_list == null || cpanel_list.size()==0)
				return 1;
			else 
				return cpanel_list.size()+1;
		}
		private void set_gbc(int x, int y, int fill) {
			gbc = new GridBagConstraints();
			gbc.gridx = x;
			gbc.gridy = y;
			gbc.fill = fill;
		}
	}
	class tset_panel extends JPanel{
		JPanel summaryPanel;
		JPanel updatePanel;
		JButton check_btn;
		JButton open_Btn;
		JButton close_Btn;		
		JLabel set_lable;
		JTextField resttime_textfield;
		JTextField goaltime_textfield;
		JTextField ptime_textfield;
		JLabel ptime_label;
		JLabel goal_label1;
		JLabel resttime_label;
		t_set ts;
		GridBagConstraints gbc;
		public tset_panel(t_set other_ts) {
			ts = other_ts;
			GridBagLayout gbl = new GridBagLayout();
			gbl.columnWidths = new int[] {};
			gbl.rowHeights = new int[] {50};
			this.setLayout(gbl);
			this.setBackground(Color.YELLOW);
			
			/*기본 출력 패널*/
			summaryPanel = new JPanel();
			gbl = new GridBagLayout();
			gbl.columnWidths = new int[]{75,70,70,80,60,60};
			gbl.rowHeights = new int[] {50};
			summaryPanel.setLayout(gbl);
			
			set_lable = new JLabel(get_setnum()+"세트");
			set_gbc(0, 0,GridBagConstraints.BOTH);
			set_lable.setHorizontalAlignment(JLabel.LEFT);
			summaryPanel.add(set_lable,gbc);
			
			goal_label1 = new JLabel(other_ts.getG_time().format(DateTimeFormatter.ofPattern("mm:ss")));;
			set_gbc(1, 0,GridBagConstraints.BOTH);
			summaryPanel.add(goal_label1,gbc);
			
			ptime_label = new JLabel(other_ts.getP_time().format(DateTimeFormatter.ofPattern("mm:ss")));;
			set_gbc(2, 0,GridBagConstraints.BOTH);
			summaryPanel.add(ptime_label,gbc);
			
			resttime_label = new JLabel(other_ts.getRest_time().format(DateTimeFormatter.ofPattern("mm:ss")));
			set_gbc(3, 0,GridBagConstraints.BOTH);
			summaryPanel.add(resttime_label,gbc);
			
			// 삭제 버튼
			JButton delete_btn = new JButton("삭제");
			ActionListener delBtn_listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (getindex() >=0) {
						System.out.println(getindex());
						tpanel_list.remove(getindex());
					}else {
						System.out.println("잘못됨");
					}
					((t_exRecord)exrecord).del_tset(ts);										//exrecord에서 삭제된 내용 날려주기
					repaint_tlist_panel();														//받아온 운동 정보에 대한 ex_list_panel 업데이트
				}
			};
			delete_btn.addActionListener(delBtn_listener);
			set_gbc(4, 0,GridBagConstraints.HORIZONTAL);
			summaryPanel.add(delete_btn,gbc);
			
			open_Btn = new JButton("열기");
			ActionListener loadBtn_listener2 = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					set_gbc(0, 1, GridBagConstraints.BOTH);
					tset_panel.this.add(updatePanel,gbc);										// update패널 보여주기
					tset_panel.this.summaryPanel.remove(check_btn);							// 닫힘 버튼으로 바꾸기
					tset_panel.this.check_btn = tset_panel.this.close_Btn;
					set_gbc(5, 0, GridBagConstraints.HORIZONTAL);
					tset_panel.this.summaryPanel.add(check_btn,gbc);
					
					tpanel_list.set(tset_panel.this.getindex(), tset_panel.this);			// 패널리스트에 패널 새로고침 후 repaint
					repaint_tlist_panel();
				}
				
			};
			open_Btn.addActionListener(loadBtn_listener2);
			
			close_Btn= new JButton("닫기");
			ActionListener closeBtn_listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tset_panel.this.remove(updatePanel);
					tset_panel.this.summaryPanel.remove(check_btn);
					tset_panel.this.check_btn = tset_panel.this.open_Btn;
					set_gbc(5, 0, GridBagConstraints.HORIZONTAL);
					tset_panel.this.summaryPanel.add(check_btn,gbc);
					tpanel_list.set(tset_panel.this.getindex(), tset_panel.this);
					repaint_tlist_panel();
				}
			};
			close_Btn.addActionListener(closeBtn_listener);
			
			check_btn = open_Btn;
			set_gbc(5, 0,GridBagConstraints.HORIZONTAL);
			summaryPanel.add(check_btn,gbc);
			
			set_gbc(0, 0, GridBagConstraints.BOTH);
			this.add(summaryPanel,gbc);
			
			/* 열기버튼 클릭시 나오는 패널 */
			updatePanel = new JPanel();
			GridBagLayout gbl2 = new GridBagLayout();
			gbl2.columnWidths = new int[]{70,70,70,80,60,60};
			gbl2.rowHeights = new int[] {50,50};
			updatePanel.setLayout(gbl2);
			
			JLabel goal_label = new JLabel("목표");
			set_gbc(1, 0,GridBagConstraints.BOTH);
			updatePanel.add(goal_label,gbc);
			
			// 목표 시간
			goaltime_textfield = new JTextField();
			goaltime_textfield.setText(ts.getG_time().format(DateTimeFormatter.ofPattern("mm:ss")));
			set_gbc(2, 0,GridBagConstraints.HORIZONTAL);
			updatePanel.add(goaltime_textfield,gbc);
			
			// 휴식시간
			resttime_textfield = new JTextField();
			resttime_textfield.setText(ts.getRest_time().format(DateTimeFormatter.ofPattern("mm:ss")));
			set_gbc(3, 0,GridBagConstraints.HORIZONTAL);
			updatePanel.add(resttime_textfield,gbc);
			
			// 수행 라벨
			JLabel performed_label = new JLabel("수행");
			set_gbc(1, 1,GridBagConstraints.BOTH);
			updatePanel.add(performed_label,gbc);
			
			// 수행 횟수
			ptime_textfield = new JTextField();
			ptime_textfield.setText(ts.getP_time().format(DateTimeFormatter.ofPattern("mm:ss")));
			set_gbc(2, 1,GridBagConstraints.HORIZONTAL);
			updatePanel.add(ptime_textfield,gbc);
			
			// 목표-> 수행 load 버튼 // 수행 default 는 0으로 설정. load 누르면 목표 값 가져옴
			JButton load_btn = new JButton("Load");
			ActionListener loadBtn_listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ts.performed_update();
					ptime_label.setText(ts.getP_time().format(DateTimeFormatter.ofPattern("mm:ss")));
					ptime_textfield.setText(ts.getP_time().format(DateTimeFormatter.ofPattern("mm:ss")));
					set_gplabel();
				}
			};
			load_btn.addActionListener(loadBtn_listener);
			set_gbc(3, 1,GridBagConstraints.HORIZONTAL);
			updatePanel.add(load_btn,gbc);
			
			JButton reset_Btn = new JButton("reset");
			ActionListener resetBtn_listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ts.performed_reset();
					ptime_textfield.setText(ts.getP_time().format(DateTimeFormatter.ofPattern("mm:ss")));
					ptime_label.setText(ts.getP_time().format(DateTimeFormatter.ofPattern("mm:ss")));
					set_gplabel();
				}
			};
			reset_Btn.addActionListener(resetBtn_listener);
			set_gbc(4, 1,GridBagConstraints.HORIZONTAL);
			updatePanel.add(reset_Btn,gbc);
			
			// 목표 및 수행 저장
			JButton update_btn = new JButton("저장");
			ActionListener updateBtn_listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(!check_time_format(goaltime_textfield.getText())){
						JOptionPane.showMessageDialog(null, "목표시간을 다시 입력하세요","경고", JOptionPane.ERROR_MESSAGE);
						goaltime_textfield.setText("00:00");
						return;
					}
					if(!check_time_format(ptime_textfield.getText())){
						JOptionPane.showMessageDialog(null, "수행시간을 다시 입력하세요","경고", JOptionPane.ERROR_MESSAGE);
						ptime_textfield.setText("00:00");
						return;
					}
					if(!check_time_format(resttime_textfield.getText())){
						JOptionPane.showMessageDialog(null, "휴식시간을 다시 입력하세요","경고", JOptionPane.ERROR_MESSAGE);
						resttime_textfield.setText("00:00");
						return;
					}
					
					ts.setG_time(goaltime_textfield.getText());
					ts.setP_time(ptime_textfield.getText());
					ts.setRest_time(resttime_textfield.getText());
					goal_label1.setText(ts.getG_time().format(DateTimeFormatter.ofPattern("mm:ss")));
					ptime_label.setText(ts.getP_time().format(DateTimeFormatter.ofPattern("mm:ss")));
					resttime_label.setText(ts.getRest_time().format(DateTimeFormatter.ofPattern("mm:ss")));
					set_gplabel();
				}
			};
			update_btn.addActionListener(updateBtn_listener);
			set_gbc(5, 1,GridBagConstraints.HORIZONTAL);
			updatePanel.add(update_btn,gbc);
			
		}
		public void set_setlabel(int setnum) {
			set_lable.setText(setnum + "세트");
		}
		private int getindex() {
			return tpanel_list.indexOf(this);
		}
		
		private int get_setnum() {
			if(tpanel_list == null || tpanel_list.size()==0)
				return 1;
			else 
				return tpanel_list.size()+1;
		}
		private void set_gbc(int x, int y, int fill) {
			gbc = new GridBagConstraints();
			gbc.gridx = x;
			gbc.gridy = y;
			gbc.fill = fill;
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
	
	public static boolean check_int_format(String input) {
		if(input.equals(""))
			return false;
		return input.chars().allMatch(Character::isDigit) && Integer.parseInt(input)>=0;
	}
	
	public static boolean check_time_format(String input) {
		String s1,s2;
		if(input.contains(":")) {
			s1 = input.split(":")[0];
			s2 = input.split(":")[1];
			if(s1.length()==2 &&s2.length()==2 && check_int_format(s1) && check_int_format(s2)&&Integer.parseInt(s1)<60 && Integer.parseInt(s2)<60) {
					return true;
			}
		}
		return  false;
	}
	/*
	public static String check_blank(String input, String type) {
		if(input.equals("")&&type.equals("int")) {
			return "0";
		}else
			return input;
	}*/
	
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
