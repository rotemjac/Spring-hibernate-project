package my.pack.webTier.clients.Guis.subManagers;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.sun.org.apache.bcel.internal.generic.I2F;

import my.pack.dataAccessTier.domain.subfacilities.Animal;
import my.pack.dataAccessTier.domain.subfacilities.Camera;
import my.pack.dataAccessTier.domain.subfacilities.Fence;
import my.pack.dataAccessTier.domain.subfacilities.Gate;
import my.pack.dataAccessTier.domain.subfacilities.Land_Portion;
import my.pack.dataAccessTier.domain.superclasses.Worker;
import my.pack.webTier.clients.MainGuis.The_main_execution;
import my.pack.webTier.clients.SimpleGuis.Areas_settings_Gui;
import my.pack.webTier.clients.SimpleGuis.Setting_animal_to_lp;
import my.pack.webTier.controlers.Mock_Controller;

public class TL_caregiver_operations extends JPanel 

implements ActionListener,ItemListener {

	Mock_Controller mController;

	JLabel jl1,jl2;
	//JTable table;
	String[] title;
	Object[][] data;

	List<Worker> workers_list;
	List<Land_Portion> lp_list;

	List<Worker> caregivers_List;
	Areas_settings_Gui casg;
	Setting_animal_to_lp satl;
	char clean_lp_flag;
	int set_up_weapon_table_width,set_up_weapon_table_height;

	public TL_caregiver_operations() {
		mController = Mock_Controller.getInstance();
		clean_lp_flag='F';
		set_up_weapon_table_width=470;
		set_up_weapon_table_height=200;
	}

	public JPanel createContentPane(String operation)	{

		//In order to avoid many scrolpanels on the "this" panel
		if (this.getComponentCount()!=0) {this.remove(0);}


		if (operation=="Set caregivers to areas") { 
			if (this.getComponentCount()!=0) {this.remove(0);}
			caregivers_List=mController.
					getTl_caregiver_Service().GetYourStaff("TL_caregiver");

			casg=new Areas_settings_Gui();
			this.add(casg);
			casg.launchButton.addActionListener(this);

			String[] caregivers=new String[10];
			int i=0;

			/**  JComboBox operations creation	*/
			for (Worker caregiver:caregivers_List) {
				caregivers[i]=caregiver.getFirst_name()+" "+caregiver.getLast_name();		
				i++;
			}
			DefaultComboBoxModel model1 = new DefaultComboBoxModel( caregivers );
			DefaultComboBoxModel model2 = new DefaultComboBoxModel( caregivers );
			DefaultComboBoxModel model3 = new DefaultComboBoxModel( caregivers );
			DefaultComboBoxModel model4 = new DefaultComboBoxModel( caregivers );
			DefaultComboBoxModel model5 = new DefaultComboBoxModel( caregivers );
			DefaultComboBoxModel model6 = new DefaultComboBoxModel( caregivers );

			casg.first_area_cb.setModel(model1);
			casg.second_area_cb.setModel(model2);
			casg.third_area_cb.setModel(model3);
			casg.fourth_area_cb.setModel(model4);
			casg.fifth_area_cb.setModel(model5);
			casg.six_area_cb.setModel(model6);


		}

		else if (operation=="View/change animals in land portions") {
			if (this.getComponentCount()!=0) {this.remove(0);}
			satl=new Setting_animal_to_lp();
			this.add(satl);
			satl.btnSetFeature.addActionListener(this);
			satl.CleanUpCheckBox.addItemListener(this);
			this.add(create_table("View/change animals in land portions"));
			
			//---Setup for the models for the checkboxes---//
			
			//Lp ids setup
			String[] lp_ids=new String[lp_list.size()];

			int j=0;
			for(Land_Portion lp:lp_list) {
				lp_ids[j]=lp.getLand_portion_id();
				j++;
			}

			//Animals ids setup
			List<String> list_of_animals_ids=mController.
						getTl_caregiver_Service().GetOnlyAnimalsIds();
			
			String[] array_of_animals_ids=new String[list_of_animals_ids.size()];

			//Cast list to array for the model
			j=0;
			for(String id:list_of_animals_ids) {
				array_of_animals_ids[j]=id;
				j++;
			}

			//Setting the models
			DefaultComboBoxModel model_animals = new DefaultComboBoxModel(array_of_animals_ids);
			DefaultComboBoxModel model_lp = new DefaultComboBoxModel(lp_ids);

			satl.choose_animal_cb.setModel(model_animals);
			satl.choose_lp_cb.setModel(model_lp);
		}

		else {	

			//Adds table to panel
			this.add(create_table(operation));
		}

		//content panes must be opaque
		this.setOpaque(true); 
		return this;  
	}


	public JScrollPane create_table (String operation) {

		//The "View/change animals in land portions" method 
		//have 2 components. the other operation have 1.
		if (operation=="View/change animals in land portions") {
			if (this.getComponentCount()>1) {this.remove(0);}
		}

		else if (this.getComponentCount()!=0) {this.remove(0);}
		
		switch(operation) {

		case ("Get details of all caregivers"):

			workers_list=mController.getTl_caregiver_Service().GetYourStaff("TL_caregiver");

		// The data used as the titles for the table.
		title=new String[10];
		title[0]="Id";title[1]="First name";title[2]="Last name"; 
		title[3]="Gender";title[4]="Age";title[5]="Salary";
		title[6]="Cell number";title[7]="City";title[8]="Street";
		title[9]="House num";

		// The data used in the table, placed as a multi-dimensional array.
		data=new Object[workers_list.size()][10];

		int i=0;
		for (Worker worker:workers_list) {
			data[i][0]=worker.getId();
			data[i][1]=worker.getFirst_name();
			data[i][2]=worker.getLast_name();
			data[i][3]=worker.getGender();
			data[i][4]=worker.getAge();
			data[i][5]=worker.getSalary();
			data[i][6]=worker.getCell_number();
			data[i][7]=worker.getAddress().getCity();
			data[i][8]=worker.getAddress().getStreet();
			data[i][9]=worker.getAddress().getHouse_num();
			i++;
		}
		break;

		case ("View/change animals in land portions"): 
			lp_list=mController.getTl_caregiver_Service().GetAllLPWithAnimalsId();

		// The data used as the titles for the table.
		title=new String[3];
		title[0]="Id";title[1]="Animal 1";title[2]="Animal 2"; 

		// The data used in the table, placed as a multi-dimensional array.
		data=new Object[lp_list.size()][3];

		i=0;
		for (Land_Portion lp:lp_list) {

			data[i][0]=lp.getLand_portion_id();

			if (lp.getAnimals_id().size()!=0) {

				data[i][1]=lp.getAnimals_id().get(0);

				if (lp.getAnimals_id().size()>1) {
					data[i][2]=lp.getAnimals_id().get(1);
				}
			}

			else {
				data[i][1]="None";
				data[i][2]="None";
			}
			i++;
		}

		break;

		}//End of switch



		// Table instantiated using the two sets of data.
		JTable table=new JTable(data, title);
		// The table displayed in a Scrollpane.

		JScrollPane scrollPane = new JScrollPane (table,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		if (operation!="View/change animals in land portions") {
			scrollPane.setPreferredSize(new Dimension
					(TL_Table_Sizes.Width,TL_Table_Sizes.Height));
		}
		
		else {
			scrollPane.setPreferredSize(new Dimension
					(set_up_weapon_table_width,set_up_weapon_table_height));
		}
		
		return scrollPane;


	}//End of method

	@Override
	public void actionPerformed(ActionEvent e) {

		if (casg!=null) {

			if (e.getSource()==casg.launchButton) {
				///////////////////////////////////////
				String caregiver_area1=(String) casg.first_area_cb.getSelectedItem();
				int space=caregiver_area1.indexOf(" ");
				String first_name=(String) caregiver_area1.substring(0, space);
				String family_name=caregiver_area1.substring(space+1);
				mController.getTl_caregiver_Service().SetCaregiverOfArea
				(1, first_name, family_name);
				////////////////////////////////////////

				String caregiver_area2=(String) casg.second_area_cb.getSelectedItem();
				space=caregiver_area2.indexOf(" ");
				first_name=(String) caregiver_area2.substring(0, space);
				family_name=caregiver_area2.substring(space+1);
				mController.getTl_caregiver_Service().SetCaregiverOfArea
				(2, first_name, family_name);
				////////////////////////////////////////
				String caregiver_area3=(String) casg.third_area_cb.getSelectedItem();
				space=caregiver_area3.indexOf(" ");
				first_name=(String) caregiver_area3.substring(0, space);
				family_name=caregiver_area3.substring(space+1);
				mController.getTl_caregiver_Service().SetCaregiverOfArea
				(3, first_name, family_name);
				////////////////////////////////////////
				String caregiver_area4=(String) casg.fourth_area_cb.getSelectedItem();
				space=caregiver_area4.indexOf(" ");
				first_name=(String) caregiver_area4.substring(0, space);
				family_name=caregiver_area4.substring(space+1);
				mController.getTl_caregiver_Service().SetCaregiverOfArea
				(4, first_name, family_name);
				////////////////////////////////////////
				String caregiver_area5=(String) casg.fifth_area_cb.getSelectedItem();
				space=caregiver_area5.indexOf(" ");
				first_name=(String) caregiver_area5.substring(0, space);
				family_name=caregiver_area5.substring(space+1);
				mController.getTl_caregiver_Service().SetCaregiverOfArea
				(5, first_name, family_name);
				////////////////////////////////////////
				String caregiver_area6=(String) casg.six_area_cb.getSelectedItem();
				space=caregiver_area6.indexOf(" ");
				first_name=(String) caregiver_area6.substring(0, space);
				family_name=caregiver_area6.substring(space+1);
				mController.getTl_caregiver_Service().SetCaregiverOfArea
				(6, first_name, family_name);
				////////////////////////////////////////
			}

		}

		else if (satl!=null) {
			if (e.getSource()==satl.btnSetFeature) {
				mController.getTl_caregiver_Service().SetAnimalsLandPortion
						(satl.choose_lp_cb.getSelectedItem().toString(), 
						 satl.choose_animal_cb.getSelectedItem().toString(), 
						 clean_lp_flag);
			}
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {

		if (e.getStateChange()==ItemEvent.SELECTED) {
				clean_lp_flag='Y';
		}
		
		else if (e.getStateChange()==ItemEvent.DESELECTED) {
			clean_lp_flag='F';
		}
		
	}

}//End of class