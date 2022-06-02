package Login;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class imgPanel extends JPanel {
	private Image img;
	public imgPanel(Image image) {
		img = image;
		setSize(new Dimension(img.getWidth(null), img.getHeight(null)));
		setPreferredSize(new Dimension(img.getWidth(null), img.getHeight(null)));
		setLayout(null);
	}
	public Dimension getDim() {
		return new Dimension(img.getWidth(null), img.getHeight(null));
	}
	public void paintComponent(Graphics g) { 
		g.drawImage(img, 0, 0, null); //0,0부터 끝까지 그려주기
	}
	
	
}


