package teamproject;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;


import javax.swing.Icon;
import javax.swing.JButton;

public class RoundBtn extends JButton{
	public RoundBtn() {
		super();
		setBorderPainted(false); // 테두리 x
	}
	
	public RoundBtn(String inputText) {
		super(inputText);
		setBorderPainted(false);
	}
	
	public RoundBtn(Icon inputicon) {
		super(inputicon);
		setBorderPainted(false);
	}
	
	
	public RoundBtn(String inputText, Icon inputicon) {
		super(inputText, inputicon);
		setBorderPainted(false);
	}
	
	protected void paintComponent(Graphics g) {
		Color backcolor=new Color(0,35,20); //배경색 결정
        Color textcolor=new Color(100,100,240); //글자색 결정
        int width = getWidth(); 
        int height = getHeight(); 
        Graphics2D g2 = (Graphics2D) g; //graphics 2D (진보한 그래픽 클래스)로 형변환
        if (getModel().isArmed()) {
        	g2.setColor(backcolor.darker()); 
        } //사용자가 버튼을 눌렀을 때 어둡게 표시
        else {
        	g2.setColor(backcolor);//그 이외의 경우 원래 색 표시
        }
        g2.fillRoundRect(0, 0, width, height, 10, 12); 
        FontMetrics fontMetrics = g2.getFontMetrics(); 
         Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), g2).getBounds(); 
        int textX = (width - stringBounds.width) / 2; 
        int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent(); 
        g2.setColor(textcolor); //글자 색 설정
        g2.setFont(getFont()); //폰트 가져오기
        g2.drawString(getText(), textX, textY); //글자 그리기
        g2.dispose(); 
        super.paintComponent(g); 
        }
	}




