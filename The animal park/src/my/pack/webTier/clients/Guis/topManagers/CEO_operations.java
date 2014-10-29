package my.pack.webTier.clients.Guis.topManagers;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
import my.pack.dataAccessTier.domain.superclasses.Worker;
import my.pack.webTier.clients.MainGuis.The_main_execution;
import my.pack.webTier.controlers.Mock_Controller;

public class CEO_operations extends JPanel {

	Mock_Controller mController;

	JLabel jl1,jl2;
	//JTable table;
	String[] title;
	Object[][] data;

	List<Worker> workers_list;



	public CEO_operations() {

		mController = Mock_Controller.getInstance();

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

		case ("Get All workers"):

			workers_list=mController.getCeoService().GetAllWorkers();
		
		// The data used as the titles for the table.
		title=new String[11];
		title[0]="Position";
		title[1]="Id";title[2]="First name";title[3]="Last name"; 
		title[4]="Gender";title[5]="Age";title[6]="Salary";
		title[7]="Cell number";title[8]="City";title[9]="Street";
		title[10]="House num";

		// The data used in the table, placed as a multi-dimensional array.
		data=new Object[workers_list.size()][11];

		int i=0;
		for (Worker worker:workers_list) {
			
			data[i][0]=worker.toString();
			
			//Remove the ceo from the list
			if (data[i][0]=="CEO") {
				continue;
			}
			
			data[i][1]=worker.getId();
			data[i][2]=worker.getFirst_name();
			data[i][3]=worker.getLast_name();
			data[i][4]=worker.getGender();
			data[i][5]=worker.getAge();
			data[i][6]=worker.getSalary();
			data[i][7]=worker.getCell_number();
			data[i][8]=worker.getAddress().getCity();
			data[i][9]=worker.getAddress().getStreet();
			data[i][10]=worker.getAddress().getHouse_num();
			i++;
		}
		break;


		//case ("?"):



		}//End of switch



		// Table instantiated using the two sets of data.
		JTable table=new JTable(data, title);
		// The table displayed in a Scrollpane.

		JScrollPane scrollPane = new JScrollPane (table,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);



		scrollPane.setPreferredSize(new Dimension(700, 550));
		return scrollPane;


	}//End of method

}//End of class