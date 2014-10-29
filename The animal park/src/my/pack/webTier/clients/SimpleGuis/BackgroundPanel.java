package my.pack.webTier.clients.SimpleGuis;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel {

	private ImageIcon imageIcon;
	public BackgroundPanel() {
		this.imageIcon = new ImageIcon(getClass().getResource("animals.jpg"));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(imageIcon.getImage(), 0,0,imageIcon.getIconWidth(),imageIcon.getIconHeight(),this);
	}

	
	
	
}