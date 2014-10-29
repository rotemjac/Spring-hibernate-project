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

public class Adding_new_worker_Gui extends JPanel 	{
	
	private String position;
	
	public JPanel panel_title;
	public JLabel lbl_title;
	
	public JPanel panel_first_name;
	public JPanel panel_last_name;
	public JPanel panel_id;
	public JPanel panel_gender;
	public JPanel panel_age;
	public JPanel panel_cell_num;
	public JPanel panel_salary;
	public JPanel panel_adrr_city;
	public JPanel panel_adrr_street;
	public JPanel panel_adrr_house;
	
	public JLabel lbl_first_name;
	public JLabel lbl_last_name;
	public JLabel lbl_id;
	public JLabel lbl_gender;
	public JLabel lbl_age;
	public JLabel lbl_cell_num;
	public JLabel lbl_salary;
	public JLabel lbl_adrr_city;
	public JLabel lbl_adrr_street;
	public JLabel lbl_adrr_house;
	
	public JTextField tf_first_name;
	public JTextField tf_last_name;
	public JTextField tf_id;
	public JTextField tf_gender;
	public JTextField tf_age;
	public JTextField tf_cell_num;
	public JTextField tf_salary;
	public JTextField tf_adrr_city;
	public JTextField tf_adrr_street;
	public JTextField tf_adrr_house;
	
	public JButton launchButton;

	
	public Adding_new_worker_Gui() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		///////Panel title /////////////
		panel_title= new JPanel();
		add(panel_title);
		panel_title.setLayout(new BoxLayout(panel_title, BoxLayout.X_AXIS));
		lbl_title= new JLabel(" Please fill the new worker details :  ");
		panel_title.add(lbl_title);
		
		///////Panel first name /////////////
		panel_first_name = new JPanel();
		add(panel_first_name);
		panel_first_name.setLayout(new BoxLayout(panel_first_name, BoxLayout.X_AXIS));
		
		lbl_first_name = new JLabel(" First name: ");
		panel_first_name.add(lbl_first_name);
		
		tf_first_name = new JTextField();
		tf_first_name.setMaximumSize(new Dimension(130, 20));
		panel_first_name.add(tf_first_name);
		tf_first_name.setColumns(10);
		
		///////Panel last name /////////////
		panel_last_name = new JPanel();
		add(panel_last_name);
		panel_last_name.setLayout(new BoxLayout(panel_last_name, BoxLayout.X_AXIS));
		
		lbl_last_name = new JLabel(" Last name: ");
		panel_last_name.add(lbl_last_name);
		
		tf_last_name = new JTextField();
		tf_last_name.setMaximumSize(new Dimension(130, 20));
		panel_last_name.add(tf_last_name);
		tf_last_name.setColumns(10);
		
		///////Panel id /////////////
		panel_id = new JPanel();
		add(panel_id);
		panel_id.setLayout(new BoxLayout(panel_id, BoxLayout.X_AXIS));
		
		lbl_id = new JLabel("          Id:  ");
		panel_id.add(lbl_id);
		
		tf_id = new JTextField();
		tf_id.setMaximumSize(new Dimension(130, 20));
		panel_id.add(tf_id);
		
		///////Panel gender /////////////
		panel_gender = new JPanel();
		add(panel_gender);
		panel_gender.setLayout(new BoxLayout(panel_gender, BoxLayout.X_AXIS));
		
		lbl_gender = new JLabel("    Gender: ");
		panel_gender.add(lbl_gender);
		
		tf_gender = new JTextField();
		tf_gender.setMaximumSize(new Dimension(130, 20));
		panel_gender.add(tf_gender);
		
		///////Panel age /////////////
		panel_age = new JPanel();
		add(panel_age);
		panel_age.setLayout(new BoxLayout(panel_age, BoxLayout.X_AXIS));
		
		lbl_age = new JLabel("       Age: ");
		panel_age.add(lbl_age);
		
		tf_age = new JTextField();
		tf_age.setMaximumSize(new Dimension(130, 20));
		panel_age.add(tf_age);
		
		///////Panel cell_num /////////////
		panel_cell_num = new JPanel();
		add(panel_cell_num);
		panel_cell_num.setLayout(new BoxLayout(panel_cell_num, BoxLayout.X_AXIS));
		
		lbl_cell_num = new JLabel(" Cell Number: ");
		panel_cell_num.add(lbl_cell_num);
		
		tf_cell_num = new JTextField();
		tf_cell_num.setMaximumSize(new Dimension(130, 20));
		panel_cell_num.add(tf_cell_num);
		
		///////Panel salary /////////////
		panel_salary = new JPanel();
		add(panel_salary);
		panel_salary.setLayout(new BoxLayout(panel_salary, BoxLayout.X_AXIS));
		
		lbl_salary = new JLabel("     Salary: ");
		panel_salary.add(lbl_salary);
		
		tf_salary = new JTextField();
		tf_salary.setMaximumSize(new Dimension(130, 20));
		panel_salary.add(tf_salary);
		
		///////Panel adrr_city /////////////
		panel_adrr_city = new JPanel();
		add(panel_adrr_city);
		panel_adrr_city.setLayout(new BoxLayout(panel_adrr_city, BoxLayout.X_AXIS));
		
		lbl_adrr_city = new JLabel("     City: ");
		panel_adrr_city.add(lbl_adrr_city);
		
		tf_adrr_city = new JTextField();
		tf_adrr_city.setMaximumSize(new Dimension(130, 20));
		panel_adrr_city.add(tf_adrr_city);
		
		///////Panel adrr_street /////////////
		panel_adrr_street = new JPanel();
		add(panel_adrr_street);
		panel_adrr_street.setLayout(new BoxLayout(panel_adrr_street, BoxLayout.X_AXIS));
		
		lbl_adrr_street = new JLabel("     Street:  ");
		panel_adrr_street.add(lbl_adrr_street);
		
		tf_adrr_street = new JTextField();
		tf_adrr_street.setMaximumSize(new Dimension(130, 20));
		panel_adrr_street.add(tf_adrr_street);
		
		///////Panel adrr_street /////////////
		panel_adrr_house = new JPanel();
		add(panel_adrr_house);
		panel_adrr_house.setLayout(new BoxLayout(panel_adrr_house, BoxLayout.X_AXIS));
		
		lbl_adrr_house = new JLabel("    House: ");
		panel_adrr_house.add(lbl_adrr_house);
		
		tf_adrr_house = new JTextField();
		tf_adrr_house.setMaximumSize(new Dimension(130, 20));
		panel_adrr_house.add(tf_adrr_house);
		
		///////////////////////////////////
		launchButton = new JButton("Send");
		add(launchButton);
		
		/////////////////////////////////

	}


	public String getPosition() {
		return position;
	}

	
	public void setPosition(String position) {
		this.position = position;
	}


	

}
