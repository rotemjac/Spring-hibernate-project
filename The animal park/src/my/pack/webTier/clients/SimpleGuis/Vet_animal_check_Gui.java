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

public class Vet_animal_check_Gui extends JPanel 
									
									
{
	
	public JButton launchButton;
	public JPanel panel_choose_the_vet;
	public JPanel panel_animals_table;
	
	public JComboBox chosen_vet_cb;
	
	public Vet_animal_check_Gui() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		///////First row panel/////////////
		 panel_choose_the_vet = new JPanel();
		add(panel_choose_the_vet);
		panel_choose_the_vet.setLayout(new BoxLayout(panel_choose_the_vet, BoxLayout.X_AXIS));
		
		JLabel first_area_lbl = new JLabel(" The Vet:  ");
		panel_choose_the_vet.add(first_area_lbl);
		
		 chosen_vet_cb = new JComboBox();
		panel_choose_the_vet.add(chosen_vet_cb);
		
		chosen_vet_cb.setMaximumSize(new Dimension(160, 30));
		/////////////////////////////////
		
		launchButton = new JButton("Send");
		add(launchButton);
		///////////////////////////////////
		
		
		///////Second row panel/////////////
		 panel_animals_table = new JPanel();
		 add(panel_animals_table);
		 panel_animals_table.setLayout(new BoxLayout(panel_animals_table, BoxLayout.X_AXIS));
		
		/////////////////////////////////

	}



}
