package Login;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class imgPanel extends JPanel { //image를 panel에 넣어 크기 조정하는 class
	private Image img; 
	public imgPanel(Image image) {
		img = image;
		setSize(new Dimension(img.getWidth(null), img.getHeight(null)));
		setPreferredSize(new Dimension(img.getWidth(null), img.getHeight(null))); 
		//이 줄을 사용하면 flowlayout에서도 컴포넌트의 크기를 조정할 수 있다.
		setLayout(null); //absolutelayout 설정
	}
	public Dimension getDim() { //img의 너비와 높이를 반환하는 생성자
		return new Dimension(img.getWidth(null), img.getHeight(null));
	}
	public void paintComponent(Graphics g) { 
		g.drawImage(img, 0, 0, null); //0,0부터 끝까지 그려주기
	}
	
}


