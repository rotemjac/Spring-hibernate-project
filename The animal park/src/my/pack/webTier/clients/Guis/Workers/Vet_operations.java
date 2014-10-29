package my.pack.webTier.clients.Guis.Workers;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import my.pack.dataAccessTier.domain.subfacilities.Animal;
import my.pack.dataAccessTier.domain.superclasses.Worker;
import my.pack.webTier.clients.Guis.advanced.ButtonEditor;
import my.pack.webTier.clients.Guis.advanced.ButtonRenderer;
import my.pack.webTier.clients.SimpleGuis.Vet_animal_check_Gui;
import my.pack.webTier.controlers.Mock_Controller;

public class Vet_operations extends JPanel implements ActionListener {

	Mock_Controller mController;

	JLabel jl1,jl2;
	//JTable table;
	String[] title;
	Object[][] data;

	List<Worker>	vet_part_List;
	List<Animal> an_list;
	Vet_animal_check_Gui vacg;
	
	char need_button;


	public Vet_operations() {

		mController = Mock_Controller.getInstance();
		vacg=new Vet_animal_check_Gui();
		need_button='N';
	}

	public JPanel createContentPane(String operation)	{

		//In order to avoid many scrolpanels on the "this" panel
		if (this.getComponentCount()!=0) {this.remove(0);}

		if (operation=="Get animals that need check in area") { 

			vet_part_List=mController.
					getVet_chief_Service().GetYourStaff("Vet_chief");

			this.add(vacg);
			vacg.launchButton.addActionListener(this);

			String[] vets=new String[10];
			int i=0;

			/**  JComboBox operations creation	*/
			for (Worker vet:vet_part_List) {
				vets[i]=vet.getFirst_name()+" "+vet.getLast_name();		
				i++;
			}
			DefaultComboBoxModel model1 = new DefaultComboBoxModel( vets );
			vacg.chosen_vet_cb.setModel(model1);

		}

		else {	

			//Adds table to panel
			this.add(create_table(operation));
		}

		//content panes must be opaque
		this.setOpaque(true); 
		return this;  
	}


	public Component create_table (String operation) {

		if (this.getComponentCount()!=0) {this.remove(0);}
		
		switch(operation) {

		case ("Get animals that need check in area"):

		String chosen_vet=(String) vacg.chosen_vet_cb.getSelectedItem();
		int space=chosen_vet.indexOf(" ");
		String first_name=(String) chosen_vet.substring(0, space);
		String family_name=chosen_vet.substring(space+1);

		// The data used as the titles for the table.
		title=new String[5];
		title[0]="Animal Id"; 
		title[1]="Area Num"; 
		title[2]="Species";
		title[3]="Genus";
		title[4]="Checked?";

		an_list=mController.getVetService().ReadByVet(first_name, family_name);

		data=new Object[an_list.size()][5];
		int i=0;
		Boolean flag_to_index_list_not_empty=false;

		for (Animal an:an_list) {

			if (an.getNeed_a_vet_check()=='Y') {
				data[i][0]=an.getAnimal_id();
				data[i][1]=an.getArea_num();
				data[i][2]=an.getSpecies();
				data[i][3]=an.getGenus();
				data[i][4]=an.getAnimal_id()+" Checked!";
				flag_to_index_list_not_empty=true;
				i++;
				need_button='Y';
			}

			
		}
		
		if(flag_to_index_list_not_empty==false) {
			return new JLabel("No data to display"); 
		}
		
		
		break;

		}//End of switch

		// Table instantiated using the two sets of data.
		JTable table=new JTable(data, title);

		if  (need_button=='Y') {
		//If we need a button we'll get the right column
		//which is the one we've marked with "Action"
		//and on that column we'll set 2 cell operations(render and edit)
		//Notice1:the JCheckBox component in the setCellEditor is just being
		//       passed to the super class, we'll use button instead
		//Notice2:The text on the buttons was set up above in the "data[i][5]=" lines
		table.getColumn("Checked?").setCellRenderer(new ButtonRenderer());
		table.getColumn("Checked?").setCellEditor(
		        new ButtonEditor(new JCheckBox(),"Get animals that need check in area"));
		}
		
		// The is table displayed in a Scrollpane.
		JScrollPane scrollPane = new JScrollPane (table,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		scrollPane.setMaximumSize(new Dimension(400, 350));
		return scrollPane;


	}//End of method

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource()==vacg.launchButton) {
			
			this.add(create_table("Get animals that need check in area"));

		}

	}

}//End of class