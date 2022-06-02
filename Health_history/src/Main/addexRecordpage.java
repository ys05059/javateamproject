package Main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import set단위class.exlistClass;

// dayRecordpage와 연결되어 있는 운동추가 페이지

public class addexRecordpage extends JDialog {
	private JPanel contentPane;
	private JTextField exname_field;
	private JTextField setgoal_field;
	private JComboBox<String> catecombo; //카테고리 고를 수 있는 박스
	private JComboBox<String> excombo;//카테고리가 입력되면 그에 해당하는 운동 고를 수 있는 박스
	private exlistClass allwork; //ALL_WORKOUT에서 arraylist뽑아오는 클래스
	String nowcate = "";
	String nowwork = "";
	ArrayList<String> cate = new ArrayList<>();
	ArrayList<String> work = new ArrayList<>();
	

	public addexRecordpage() {
		setTitle("add_exRecordpage");
		allwork = new exlistClass("ALL_WORKOUT"); 
		cate = allwork.getcatetoStringlist();
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel catename_Label = new JLabel("Category");
		catename_Label.setBounds(92, 40, 87, 15);
		contentPane.add(catename_Label);
		
		catecombo = new JComboBox<String>(cate.toArray(new String[cate.size()]));
		//jcombobox에는 arratlist가 들어갈 수 없으므로, list로 변환해서 저장함
		catecombo.setBounds(193, 38, 106, 21);
		JComboHandler combohd = new JComboHandler();
		//누르면 어떤 행동을 할 건지 정함 
		catecombo.addActionListener(combohd);
		contentPane.add(catecombo);
		
		
		JLabel exname_Label = new JLabel("\uC6B4\uB3D9\uBA85");
		exname_Label.setBounds(110, 90, 52, 15);
		contentPane.add(exname_Label);
		
		excombo = new JComboBox<String>(work.toArray(new String[work.size()]));
		excombo.setBounds(193, 87, 106, 21);
		JComboHandler2 combohd2 = new JComboHandler2();
		excombo.addActionListener(combohd2);
		contentPane.add(excombo);
		
		JLabel setgoal_Label = new JLabel("\uBAA9\uD45C \uC138\uD2B8\uC218");
		setgoal_Label.setBounds(92, 141, 87, 15);
		contentPane.add(setgoal_Label);
		
		setgoal_field = new JTextField();
		setgoal_field.setBounds(193, 138, 106, 21);
		contentPane.add(setgoal_field);
		
		JButton addBtn = new JButton("추가");
		addBtn.setBounds(162, 188, 95, 23);
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isnum = true;			
				
				if( !setgoal_field.getText().equals("")) { //숫자 입력 안할 시  예외처리
					for(int i = 0; i < setgoal_field.getText().length(); i++) {
						if(!Character.isDigit(setgoal_field.getText().charAt(i))){
							isnum = false; //하나라도 숫자 아닌 게 들어가 있다면 false return
						}
					}
					if(isnum == false) {
						JOptionPane.showMessageDialog(null, "세트수에는 숫자를 입력하십시오.");
						setgoal_field.setText("");
					}
					else {
						addexRecordpage.this.dispose();
					}

				}else {
					input_check_dialog icd = new input_check_dialog();
					icd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					icd.setModal(true);
					icd.setVisible(true);
				}
			}
		});
		contentPane.add(addBtn);
	}

	public String get_exname() {
		return excombo.getSelectedItem().toString();
	}
	
	public String get_excate() {
		return catecombo.getSelectedItem().toString();
	}

	public int get_setgoal() {
		return Integer.valueOf(setgoal_field.getText());
	}
	
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
	
	
	private class JComboHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			nowcate = "";
			nowcate = catecombo.getSelectedItem().toString();
			work = allwork.getworktoStringlist(nowcate);
			excombo.setModel(new DefaultComboBoxModel(work.toArray(new String[work.size()])));
			//카테고리 입력에 따라 exercise combobox의 인덱스 달라지게 하기

		}
		
	}
	
	private class JComboHandler2 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			nowwork = excombo.getSelectedItem().toString();
		}
		
	}
}
