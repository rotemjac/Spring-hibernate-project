package my.pack.advices.photos;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.*;


/** 

Window with photos
notice: you need to add the photos inside your package!
 
**/

public class Window_with_photos  extends JPanel  {

	private JLabel lable1;
	private ImageIcon image1;

	
	public Window_with_photos () {
		
		image1=new ImageIcon(getClass().getResource("animals.jpg"));
		lable1=new JLabel(image1);

		add(lable1);

		
		//----------Frame setup-----------//
		setLayout(new FlowLayout());
		
		//Set when the window closed, stop wasting system resource 
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//setTitle("Window with photos");
		this.setMinimumSize(new Dimension(100,100));
		
		//Important-without whis we will not see the window
		setVisible(true);
		
	}	

}
