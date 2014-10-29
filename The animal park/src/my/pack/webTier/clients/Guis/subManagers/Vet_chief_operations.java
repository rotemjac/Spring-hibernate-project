package my.pack.webTier.clients.Guis.subManagers;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import my.pack.dataAccessTier.domain.subfacilities.Animal;
import my.pack.dataAccessTier.domain.subfacilities.Land_Portion;
import my.pack.dataAccessTier.domain.superclasses.Worker;
import my.pack.serviceTier.services.facilities.FacilitiesService;
import my.pack.utils.Config_File;
import my.pack.webTier.clients.SimpleGuis.Areas_settings_Gui;
import my.pack.webTier.clients.SimpleGuis.Vet_setting_animals_food;
import my.pack.webTier.controlers.Mock_Controller;

public class Vet_chief_operations extends JPanel implements ActionListener {

	Mock_Controller mController;

	JLabel jl1,jl2;
	//JTable table;
	String[] title;
	Object[][] data;

	List<Worker> workers_list;
	List<Worker>	vet_part_List;
	List<Animal> an_list;
	List<Land_Portion> lp_List;

	Areas_settings_Gui vasg;
	Vet_setting_animals_food vsaf;

	List<Object[]> areas_vets_mapping_List;

	public Vet_chief_operations() {

		mController = Mock_Controller.getInstance();

	}

	public JPanel createContentPane(String operation)	{

		//In order to avoid many scrolpanels on the "this" panel
		if (this.getComponentCount()!=0) {this.remove(0);}


		if (operation=="Set vets to areas") { 

			if (this.getComponentCount()!=0) {this.remove(0);}

			vet_part_List=mController.
					getVet_chief_Service().GetYourStaff("Vet_chief");

			vasg=new Areas_settings_Gui();
			this.add(vasg);
			vasg.launchButton.addActionListener(this);

			String[] vets=new String[10];
			int i=0;

			/**  JComboBox operations creation	*/
			for (Worker vet:vet_part_List) {
				vets[i]=vet.getFirst_name()+" "+vet.getLast_name();		
				i++;
			}
			DefaultComboBoxModel model1 = new DefaultComboBoxModel( vets );
			DefaultComboBoxModel model2 = new DefaultComboBoxModel( vets );
			DefaultComboBoxModel model3 = new DefaultComboBoxModel( vets );
			DefaultComboBoxModel model4 = new DefaultComboBoxModel( vets );
			DefaultComboBoxModel model5 = new DefaultComboBoxModel( vets );
			DefaultComboBoxModel model6 = new DefaultComboBoxModel( vets );

			vasg.first_area_cb.setModel(model1);
			vasg.second_area_cb.setModel(model2);
			vasg.third_area_cb.setModel(model3);
			vasg.fourth_area_cb.setModel(model4);
			vasg.fifth_area_cb.setModel(model5);
			vasg.six_area_cb.setModel(model6);
		}

		else if (operation=="Set animal food") {

			if (this.getComponentCount()!=0) {this.remove(0);}

			vsaf=new Vet_setting_animals_food();
			//Add the feature gui to the panel
			this.add(vsaf);
			vsaf.btnSetCategory.addActionListener(this);
			vsaf.btnSetFeature.addActionListener(this);
			//Add the animals table to the panel
			this.add(create_table("Set animal food"));

			String[] an_catgories=
					new String[]{"Department","Series","Genus","Species"};
			DefaultComboBoxModel model_categories = new DefaultComboBoxModel( an_catgories );
			vsaf.choose_category_cb.setModel(model_categories);
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

		//The "Set animal food" have 2 components. the other operation - 1.
		if (operation=="Set animal food") {
			if (this.getComponentCount()!=1) {this.remove(0);}
		}

		else if (this.getComponentCount()!=0) {this.remove(0);}

		switch(operation) {

		case ("Get details of all vets"):

			workers_list=mController.getSec_chief_Service().GetYourStaff("Vet_chief");

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

		case ("Get vet to areas mapping"):

			areas_vets_mapping_List=mController
			.getVet_chief_Service().Get_Vets_Areas_Mapping();

		// The data used as the titles for the table.
		title=new String[3];
		title[0]="Area number"; 
		title[1]="Vet first name"; 
		title[2]="Vet last name";

		data=new Object[areas_vets_mapping_List.size()][3];
		i=0;
		for (Object[] ob:areas_vets_mapping_List) {

			data[i][0]=ob[0];
			data[i][1]=ob[1];
			data[i][2]=ob[2];
			i++;
		}
		break;

		case ("Get animals info"):

			an_list=mController.getVet_chief_Service().Get_All_Animals();

		// The data used as the titles for the table.
		title=new String[5];
		title[0]="Id"; title[1]="Genus"; title[2]="Species"; 
		title[3]="Area";title[4]="Need a check?";

		// The data used in the table, placed as a multi-dimensional array.
		data=new Object[an_list.size()][5];

		i=0;
		for (Animal an:an_list) {
			data[i][0]=an.getAnimal_id();
			data[i][1]=an.getGenus();
			data[i][2]=an.getSpecies();
			data[i][3]=String.valueOf(an.getArea_num());
			data[i][4]=an.getNeed_a_vet_check();
			i++;
		}

		break;

		case ("Get all unchecked animals"):
			an_list=mController.getVet_chief_Service().Get_All_Animals_That_Need_A_Check();

		// The data used as the titles for the table.
		title=new String[4];
		title[0]="Id"; title[1]="Genus"; 
		title[2]="Species"; title[3]="Area";

		// The data used in the table, placed as a multi-dimensional array.
		data=new Object[an_list.size()][4];

		i=0;
		for (Animal an:an_list) {

			data[i][0]=an.getAnimal_id();
			data[i][1]=an.getGenus();
			data[i][2]=an.getSpecies();
			data[i][3]=String.valueOf(an.getArea_num());

			i++;
		}
		break;

		case ("Set animal food"):

			//TODO: We could have created a special dao method which
			//		Returnes only the neccessery data

			an_list=mController.getVet_chief_Service().Get_All_Animals();

		// The data used as the titles for the table.
		title=new String[5];
		title[0]="Department"; title[1]="series";
		title[2]="Genus"; title[3]="Species";
		title[4]="Food";


		// The data used in the table, placed as a multi-dimensional array.
		data=new Object[an_list.size()][5];

		i=0;
		for (Animal an:an_list) {
			data[i][0]=an.getDepartment();
			data[i][1]=an.getSeries();
			data[i][2]=an.getGenus();
			data[i][3]=an.getSpecies();
			data[i][4]=an.getFood();
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

		if (operation !="Set animal food") {
			scrollPane.setPreferredSize(new Dimension
					(TL_Table_Sizes.Width,TL_Table_Sizes.Height));
		}
		else {
			scrollPane.setPreferredSize(new Dimension
					(540,160));
		}
			return scrollPane;


	}//End of method

	@Override
	public void actionPerformed(ActionEvent e) {

		if (vasg!=null) {

			if (e.getSource()==vasg.launchButton) {
				///////////////////////////////////////
				String vet_area1=(String) vasg.first_area_cb.getSelectedItem();
				int space=vet_area1.indexOf(" ");
				String first_name=(String) vet_area1.substring(0, space);
				String family_name=vet_area1.substring(space+1);
				mController.getVet_chief_Service().SetVetOfArea
				(1, first_name, family_name);
				////////////////////////////////////////

				String vet_area2=(String) vasg.second_area_cb.getSelectedItem();
				space=vet_area2.indexOf(" ");
				first_name=(String) vet_area2.substring(0, space);
				family_name=vet_area2.substring(space+1);
				mController.getVet_chief_Service().SetVetOfArea
				(2, first_name, family_name);
				////////////////////////////////////////
				String vet_area3=(String) vasg.third_area_cb.getSelectedItem();
				space=vet_area3.indexOf(" ");
				first_name=(String) vet_area3.substring(0, space);
				family_name=vet_area3.substring(space+1);
				mController.getVet_chief_Service().SetVetOfArea
				(3, first_name, family_name);
				////////////////////////////////////////
				String vet_area4=(String) vasg.fourth_area_cb.getSelectedItem();
				space=vet_area4.indexOf(" ");
				first_name=(String) vet_area4.substring(0, space);
				family_name=vet_area4.substring(space+1);
				mController.getVet_chief_Service().SetVetOfArea
				(4, first_name, family_name);
				////////////////////////////////////////
				String vet_area5=(String) vasg.fifth_area_cb.getSelectedItem();
				space=vet_area5.indexOf(" ");
				first_name=(String) vet_area5.substring(0, space);
				family_name=vet_area5.substring(space+1);
				mController.getVet_chief_Service().SetVetOfArea
				(5, first_name, family_name);
				////////////////////////////////////////
				String vet_area6=(String) vasg.six_area_cb.getSelectedItem();
				space=vet_area6.indexOf(" ");
				first_name=(String) vet_area6.substring(0, space);
				family_name=vet_area6.substring(space+1);
				mController.getVet_chief_Service().SetVetOfArea
				(6, first_name, family_name);
				////////////////////////////////////////
			}
		}//End of if(vasg!=null)

		if (vsaf!=null) {

			if (e.getSource()==	vsaf.btnSetCategory) {
				List<String> groups_list=mController.getVet_chief_Service().
						ReadOnlyGroupsByCategory(vsaf.choose_category_cb.getSelectedItem().toString());

				//Cast the list<String> into String array
				int i=0;
				String[] groups_arr=new String[groups_list.size()];
				for(String str:groups_list ) {
					groups_arr[i]=str;
					i++;
				}

				DefaultComboBoxModel model_groups = new DefaultComboBoxModel( groups_arr );
				vsaf.choose_group_cb.setModel(model_groups);


			}



			if (e.getSource()==vsaf.btnSetFeature) {

				mController.getVet_chief_Service().Set_Animal_Food
				(vsaf.choose_group_cb.getSelectedItem().toString(), vsaf.textField.getText());
			}

		}//End of if (vsaf!=null)




	}

}//End of class