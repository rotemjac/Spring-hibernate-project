package my.pack.webTier.clients.SimpleGuis;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Setting_specific_feature extends JPanel 	{
	

	public JComboBox choose_worker_cb;
	public JLabel first_area_lbl;
	public JTextField textField;
	public JButton btnSetFeature;
	private JPanel panel1;
	private JPanel panel2;

	
	public Setting_specific_feature() {
		
		///////First row panel/////////////
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		panel1 = new JPanel();
		add(panel1);
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
		
		first_area_lbl = new JLabel("Some default lable");
		panel1.add(first_area_lbl);
		
		choose_worker_cb = new JComboBox();
		panel1.add(choose_worker_cb);
		
		 choose_worker_cb.setMaximumSize(new Dimension(110, 30));
		 
		 panel2 = new JPanel();
		 add(panel2);
		 panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
		 
		 textField = new JTextField();
		 panel2.add(textField);
		 textField.setColumns(10);
		 textField.setMaximumSize(new Dimension(110, 20));
		 
		 btnSetFeature = new JButton("Set feature");
		 panel2.add(btnSetFeature);
		///////////////////////////////////
		


	}



}
