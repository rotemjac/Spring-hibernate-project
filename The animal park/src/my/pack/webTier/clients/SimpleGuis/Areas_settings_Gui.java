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

public class Areas_settings_Gui extends JPanel 	{
	
	public JButton launchButton;
	public JPanel panel_first_row;
	public JPanel panel_second_row;
	public JPanel panel_third_row;
	public JPanel panel_fourth_row;
	public JPanel panel_fifth_row;
	public JPanel panel_six_row;
	
	public JComboBox first_area_cb;
	public JComboBox second_area_cb;
	public JComboBox third_area_cb;
	public JComboBox fourth_area_cb;
	public JComboBox fifth_area_cb;
	public JComboBox six_area_cb;
	
	public Areas_settings_Gui() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		///////First row panel/////////////
		 panel_first_row = new JPanel();
		add(panel_first_row);
		panel_first_row.setLayout(new BoxLayout(panel_first_row, BoxLayout.X_AXIS));
		
		JLabel first_area_lbl = new JLabel("worker responsible in area 1:");
		panel_first_row.add(first_area_lbl);
		
		 first_area_cb = new JComboBox();
		panel_first_row.add(first_area_cb);
		
		first_area_cb.setMaximumSize(new Dimension(160, 30));
		///////////////////////////////////
		
		
		///////Second row panel/////////////
		 panel_second_row = new JPanel();
		add(panel_second_row);
		panel_second_row.setLayout(new BoxLayout(panel_second_row, BoxLayout.X_AXIS));
		
		JLabel second_area_lbl = new JLabel("worker responsible in area 2:");
		panel_second_row.add(second_area_lbl);
		
		second_area_cb = new JComboBox();
		panel_second_row.add(second_area_cb);
		second_area_cb.setMaximumSize(new Dimension(160, 30));
		/////////////////////////////////
		
		///////Third row panel/////////////
		 panel_third_row = new JPanel();
		add(panel_third_row);
		panel_third_row.setLayout(new BoxLayout(panel_third_row, BoxLayout.X_AXIS));
		
		JLabel third_area_lbl = new JLabel("worker responsible in area 3:");
		panel_third_row.add(third_area_lbl);
		
		 third_area_cb = new JComboBox();
		panel_third_row.add(third_area_cb);
		third_area_cb.setMaximumSize(new Dimension(160, 30));
		/////////////////////////////////
		
		///////Forth row panel/////////////
		 panel_fourth_row = new JPanel();
		add(panel_fourth_row);
		panel_fourth_row.setLayout(new BoxLayout(panel_fourth_row, BoxLayout.X_AXIS));
		
		JLabel fourth_area_lbl = new JLabel("worker responsible in area 4:");
		panel_fourth_row.add(fourth_area_lbl);
		
		 fourth_area_cb = new JComboBox();
		panel_fourth_row.add(fourth_area_cb);
		fourth_area_cb.setMaximumSize(new Dimension(160, 30));
		/////////////////////////////////
		
		///////Fifth row panel/////////////
		 panel_fifth_row = new JPanel();
		add(panel_fifth_row);
		panel_fifth_row.setLayout(new BoxLayout(panel_fifth_row, BoxLayout.X_AXIS));
		
		JLabel fifth_area_lbl = new JLabel("worker responsible in area 5:");
		panel_fifth_row.add(fifth_area_lbl);
		
		 fifth_area_cb = new JComboBox();
		panel_fifth_row.add(fifth_area_cb);
		fifth_area_cb.setMaximumSize(new Dimension(160, 30));
		/////////////////////////////////
		
		///////Six row panel/////////////
		 panel_six_row = new JPanel();
		add(panel_six_row);
		panel_six_row.setLayout(new BoxLayout(panel_six_row, BoxLayout.X_AXIS));
		
		JLabel six_area_lbl = new JLabel("worker responsible in area 6:");
		panel_six_row.add(six_area_lbl);
		
		 six_area_cb = new JComboBox();
		panel_six_row.add(six_area_cb);
		six_area_cb.setMaximumSize(new Dimension(160, 30));
		/////////////////////////////////
		
		launchButton = new JButton("Send");
		add(launchButton);
		
		/////////////////////////////////

	}



}
