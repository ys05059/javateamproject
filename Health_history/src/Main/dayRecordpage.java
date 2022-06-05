package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import Login.imgPanel;
import set단위class.dayRecord;
import set단위class.exRecord;
import set단위class.wc_exRecord;
import 희석.CalendarDemo;

public class dayRecordpage extends JFrame {

	private JTextField weight_textField;
	private JPanel ex_list_panel; 
	public dayRecord dayrecord;
	private ArrayList<expanel> expanel_list; 
	imgPanel dayRecordP = new imgPanel(new ImageIcon("image\\batang1.jpg").getImage());
	final ImageIcon dayRecordP2 = new ImageIcon("image\\batang1.jpg"); 
	boolean exist; // 처음 입력하는건지, 있던거 덮어쓰는지

	public dayRecordpage(final ArrayList<dayRecord> dR_ary,dayRecord dr) {//이부분 final로 안하니 오류 떠서 final 추가했습니다(동혁)

		setTitle("dayRecordpage	");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500,400);
		getContentPane().setBackground(new Color(203, 254, 255));
		

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
		
		JLabel todaydate_Label = new JLabel();
		todaydate_Label.setText(dayrecord.getToday_date().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		gbc_default = new GridBagConstraints();
		gbc_default.fill = GridBagConstraints.HORIZONTAL;
		gbc_default.gridx = 1;
		gbc_default.gridy = 0;
		gbc_default.anchor = GridBagConstraints.WEST;
		add(todaydate_Label,gbc_default);
		
		
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
		ex_list_panel = new JPanel(){
			public void paintComponent(Graphics g) {
				g.drawImage(dayRecordP2.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		ex_list_panel.setSize(400,320);
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
				exrp.setVisible(true); // 운동 추가 창이 열린다.
				// 추가 안하고 창을 닫을 시 null error 가 뜨는데 추후에 해결하면 좋을듯합니다(동혁)
				exRecord tmp_ex = new exRecord(exrp.get_exname(),exrp.get_setgoal());
				
				// 받아온 운동 정보 저장
				dayrecord.add_exr(tmp_ex);
				//dayrecord에 저장한 이후 panel에 표현할 정보들도 저장한다. 이 작업은 dayrecord와 별개인 듯(동혁)
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
				 try { // 몸무게 입력 오류처리
					 Double.parseDouble(weight_textField.getText());
					 if(Double.valueOf(weight_textField.getText())<0) {
						 JOptionPane.showMessageDialog(null, "몸무게를 다시 입력하세요","경고", JOptionPane.ERROR_MESSAGE);
						 weight_textField.setText("");
						 return;
					 }
					 dayrecord.setToday_weight(Double.valueOf(weight_textField.getText()));
				 }catch(NumberFormatException e1) {
					 JOptionPane.showMessageDialog(null, "몸무게를 다시 입력하세요","경고", JOptionPane.ERROR_MESSAGE);
					 weight_textField.setText("");
					 return;
	            }
				
				// dayRecord를 dR_ary에 추가 -> 없으면 add / 있으면 set
				if(exist == false)
					dR_ary.add(dayrecord);
				else {
					int index=0;
					for(dayRecord tmp : dR_ary) {
						if(dayrecord.getToday_date().equals(tmp.getToday_date()))
							break;
						else 
							index++;
					}
					dR_ary.set(index, dayrecord);
				}
				CalendarDemo.paintExcPane(dR_ary);
				// 달력 페이지로 돌아감
				dispose();
			}
		};
		savedR_button.addActionListener(savedR_listener);
		gbc_default = new GridBagConstraints();
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
		}else {
			ex_list_panel.removeAll();
		}
		ex_list_panel.revalidate();															// 운동 선택 패널 초기화
		ex_list_panel.repaint();
	}
	
	class expanel extends JPanel{ 
		private JLabel ex_name;
		private JLabel setnum_label;
		private exRecord exr;
		private GridBagConstraints gbc;
		
		public expanel(exRecord other_exr) {
			exr = other_exr;
			TitledBorder oneTb = new TitledBorder(new LineBorder(Color.black));
			setBorder(oneTb);
			GridBagLayout gbl = new GridBagLayout();
			gbl.columnWidths = new int[] {100,100,50,50,50};
			gbl.rowHeights = new int[] {50};
			
			this.setLayout(gbl);
			this.setBackground(new Color(175,237,100));
			
			ex_name = new JLabel(other_exr.getEx().getname());
			set_gbc(0, 0, GridBagConstraints.BOTH);
			this.add(ex_name,gbc);
			
		
			JLabel set_label = new JLabel("현재/목표");
			set_gbc(1, 0, GridBagConstraints.BOTH);
			this.add(set_label,gbc);
			
			setnum_label = new JLabel("("+Integer.toString(other_exr.getCount_set())+"/"+Integer.toString(other_exr.getSet_goal())+")");
			set_gbc(2, 0, GridBagConstraints.BOTH);
			this.add(setnum_label,gbc);
			
			JButton update_btn = new JButton("수정");
			ActionListener updateBtn_listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					exRecordpage exrp;
					exRecord tmp_exr = dayrecord.getExr_ary().get(getindex());	
					if(tmp_exr instanceof wc_exRecord) {
						exrp = new exRecordpage((wc_exRecord)tmp_exr,dayrecord);
					}else {
						exrp = new exRecordpage(tmp_exr,dayrecord);
					}
					exrp.setVisible(true);
					// 창 닫혔을 때
					// 운동 패널 리스트 수정
					exrp.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosed(WindowEvent windowevent) {
							exRecord tmp_exr = dayrecord.getExr_ary().get(getindex());									// 수정된 exRecord 받아오기
							ex_name.setText(tmp_exr.getEx().getname());
							setnum_label.setText("("+Integer.toString(tmp_exr.getCount_set())+"/"+Integer.toString(tmp_exr.getSet_goal())+")");
							repaint_exlist_panel();
						}
					});
				}
			};
			update_btn.addActionListener(updateBtn_listener);
			set_gbc(3, 0, GridBagConstraints.HORIZONTAL);
			gbc.insets = new Insets(0, 0, 0, 5);
			this.add(update_btn,gbc);
			
			// 삭제 버튼
			JButton delete_btn = new JButton("삭제");																		// 패널에서의 삭제와 더불어 dayrecord에서도 삭제하기를  구현해야한다.
			ActionListener delBtn_listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (getindex() >=0) {
						System.out.println(getindex());
						dayrecord.delete_exr(exr);
						expanel_list.remove(getindex());
						dayrecord.printallexr_ary();
					}else {
						System.err.println("dayRecordpage: 운동삭제 오류");
					}
					repaint_exlist_panel();																				//받아온 운동 정보에 대한 ex_list_panel 업데이트
				}
			};
			delete_btn.addActionListener(delBtn_listener);
			set_gbc(4, 0, GridBagConstraints.HORIZONTAL);
			gbc.insets= new Insets(0, 0, 0, 0);
			this.add(delete_btn,gbc);
		}
		private int getindex() {
			return expanel_list.indexOf(this); //panel의 index위치를 리턴해준다
		}
		private void set_gbc(int x, int y, int fill) {
			gbc = new GridBagConstraints();
			gbc.gridx = x;
			gbc.gridy = y;
			gbc.fill = fill;
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
