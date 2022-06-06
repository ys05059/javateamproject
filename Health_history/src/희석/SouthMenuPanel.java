package í¬ì;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import setë¨ìclass.dayRecord;
import java.awt.Color;
import java.awt.SystemColor;

public class SouthMenuPanel extends JPanel{
	
	final ImageIcon calendarP = new ImageIcon("image\\calendarback.jpg"); 

  private String ID;
	private JButton gotoStatistics = new JButton("Åë°è");

	public ArrayList<dayRecord> curr_dR_ary;

	public SouthMenuPanel(ArrayList<dayRecord> dR_ary, String nowID) {
		ID = nowID;
		setLayout(new FlowLayout());
		gotoStatistics.setBackground(SystemColor.activeCaption);
		gotoStatistics.addActionListener(new gotoStatisticsHandler());


		add(gotoStatistics);
		curr_dR_ary = dR_ary;

	}
	private class gotoStatisticsHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			boolean isavailable = false;
			for(int i = 0; i < curr_dR_ary.size(); i++) {
				if(curr_dR_ary.get(i).getExr_ary().size() == 0) { //exrecord ê° ë¨ììì§ ìì¼ë©´
					isavailable = false; //false ë¦¬í´
				}else { //íëë¼ë ê¸°ë¡ì´ ë¨ììë¤ë©´
					isavailable = true; //true ë¦¬í´íê³ 
					break; //ì¦ì break
				}
			}
			
			if(isavailable == false) {
				JOptionPane.showMessageDialog(null, "ìë ¥ë ì´ëì´ ììµëë¤.","ê²½ê³ ", JOptionPane.ERROR_MESSAGE);
			}else {
				SelectStatisticsWayDemo sswd = new SelectStatisticsWayDemo(curr_dR_ary, ID);
				sswd.setVisible(true);
			}

		}
	}
	public void paintComponent(Graphics g) {
		
		g.drawImage(calendarP.getImage(), 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
		
	}

}
