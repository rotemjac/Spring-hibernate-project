package my.pack.webTier.clients.Guis.subManagers;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import my.pack.webTier.clients.Guis.advanced.ButtonEditor;
import my.pack.webTier.clients.Guis.advanced.ButtonRenderer;
import my.pack.webTier.clients.MainGuis.The_main_execution;
import my.pack.webTier.controlers.Mock_Controller;

public class TL_janitor_operations extends JPanel  {

	Mock_Controller mController;

	JLabel jl1,jl2;
	//JTable table;
	String[] title;
	Object[][] data;

	List<Worker> workers_list;
	List<Land_Portion> lp_list;

	char need_button;

	public TL_janitor_operations() {

		mController = Mock_Controller.getInstance();
		need_button='N';

	}

	public JPanel createContentPane(String operation)	{

		//In order to avoid many scrolpanels on the "this" panel
		if (this.getComponentCount()!=0) {this.remove(0);}


		if (operation=="??") { }

		else {	

			//Adds table to panel
			this.add(create_table(operation));
		}

		//content panes must be opaque
		this.setOpaque(true); 
		return this;  
	}


	public JScrollPane create_table (String operation) {

		switch(operation) {

		case ("Get details of all janitors"):

			workers_list=mController.getTl_janitor_Service().GetYourStaff("TL_janitor");

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


		case ("Get all land portions that need a fix"):

			lp_list=mController.getTl_janitor_Service().GetAllLPWithProblemsAndAreas();

		// The data used as the titles for the table.
		title=new String[6];
		title[0]="LP Id";
		title[1]="Area";
		title[2]="Problem 1"; 
		title[3]="Problem 2";
		title[4]="Problem 3";
		title[5]="Action";



		// The data used in the table, placed as a multi-dimensional array.
		data=new Object[lp_list.size()][6];

		i=0;

		for (Land_Portion lp:lp_list) {
			data[i][0]=lp.getLand_portion_id();
			data[i][1]=lp.getArea().getNumber();
			
			if (lp.getList_of_problems().size()==1) {
				data[i][2]=lp.getList_of_problems().get(0);
				data[i][3]="None";
				data[i][4]="None";
				data[i][5]="Fix problems in: " +lp.getLand_portion_id();
			}

			else if (lp.getList_of_problems().size()==2){
				data[i][2]=lp.getList_of_problems().get(0);
				data[i][3]=lp.getList_of_problems().get(1);
				data[i][4]="None";
				data[i][5]="Fix problems in: " +lp.getLand_portion_id();
			}

			else  {
				data[i][2]=lp.getList_of_problems().get(0);
				data[i][3]=lp.getList_of_problems().get(1);
				data[i][4]=lp.getList_of_problems().get(2);
				data[i][5]="Fix problems in: " +lp.getLand_portion_id();
			}

			i++;
		}
		
		
		need_button='Y';
		
		break;
		}//End of switch

		// Table instantiated using the two sets of data.
		JTable table=new JTable(data, title);
		
		//////////////////////////////////////////////////////////////////
		if  (need_button=='Y') {
		//If we need a button we'll get the right column
		//which is the one we've marked with "Action"
		//and on that column we'll set 2 cell operations(render and edit)
		//Notice1:the JCheckBox component in the setCellEditor is just being
		//       passed to the super class, we'll use button instead
		//Notice2:The text on the buttons was set up above in the "data[i][5]=" lines
		table.getColumn("Action").setCellRenderer(new ButtonRenderer());
		table.getColumn("Action").setCellEditor(
		        new ButtonEditor(new JCheckBox(),"Fixing problems in lp"));
		}
		//////////////////////////////////////////////////////////////////

		    // The table displayed in a Scrollpane.
		JScrollPane scrollPane = new JScrollPane (table,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);



		scrollPane.setPreferredSize(new Dimension
								(TL_Table_Sizes.Width,TL_Table_Sizes.Height));
		return scrollPane;


	}//End of method



}//End of class