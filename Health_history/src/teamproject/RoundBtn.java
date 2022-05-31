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
		setBorderPainted(false); // �׵θ� x
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
		Color backcolor=new Color(0,35,20); //���� ����
        Color textcolor=new Color(100,100,240); //���ڻ� ����
        int width = getWidth(); 
        int height = getHeight(); 
        Graphics2D g2 = (Graphics2D) g; //graphics 2D (������ �׷��� Ŭ����)�� ����ȯ
        if (getModel().isArmed()) {
        	g2.setColor(backcolor.darker()); 
        } //����ڰ� ��ư�� ������ �� ��Ӱ� ǥ��
        else {
        	g2.setColor(backcolor);//�� �̿��� ��� ���� �� ǥ��
        }
        g2.fillRoundRect(0, 0, width, height, 10, 12); 
        FontMetrics fontMetrics = g2.getFontMetrics(); 
         Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), g2).getBounds(); 
        int textX = (width - stringBounds.width) / 2; 
        int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent(); 
        g2.setColor(textcolor); //���� �� ����
        g2.setFont(getFont()); //��Ʈ ��������
        g2.drawString(getText(), textX, textY); //���� �׸���
        g2.dispose(); 
        super.paintComponent(g); 
        }
	}




