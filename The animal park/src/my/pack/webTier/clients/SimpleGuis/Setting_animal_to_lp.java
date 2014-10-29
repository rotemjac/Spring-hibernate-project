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
import javax.swing.JCheckBox;

public class Setting_animal_to_lp extends JPanel 	{
	

	public JComboBox choose_animal_cb;
	public JLabel animal_lbl;
	public JButton btnSetFeature;
	public JComboBox choose_lp_cb;
	public JLabel lp_lbl;
	public JCheckBox CleanUpCheckBox;

	
	public Setting_animal_to_lp() {
		
		///////First row panel/////////////
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setPreferredSize(new Dimension(140, 140));
		animal_lbl = new JLabel("Animal Id: ");
		this.add(animal_lbl);
		
		choose_animal_cb = new JComboBox();
		 this.add(choose_animal_cb);
		
		 choose_animal_cb.setMaximumSize(new Dimension(120, 30));
		
		lp_lbl = new JLabel("LP Id: ");
		add(lp_lbl);
		
		choose_lp_cb = new JComboBox();
		choose_lp_cb.setMaximumSize(new Dimension(120, 30));
		add(choose_lp_cb);
		
		CleanUpCheckBox = new JCheckBox("Clean lp?");
		CleanUpCheckBox.setSelected(false);
		add(CleanUpCheckBox);
		
		btnSetFeature = new JButton("Set");
		add(btnSetFeature);
		///////////////////////////////////
		


	}



}
