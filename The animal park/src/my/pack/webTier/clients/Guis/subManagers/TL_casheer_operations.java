package my.pack.webTier.clients.Guis.subManagers;

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

public class TL_casheer_operations extends JPanel {

	Mock_Controller mController;

	JLabel jl1,jl2;
	//JTable table;
	String[] title;
	Object[][] data;

	List<Worker> workers_list;



	public TL_casheer_operations() {

		mController = Mock_Controller.getInstance();

	}

	public JPanel createContentPane(String operation)	{

		//In order to avoid many scrolpanels on the "this" panel
		if (this.getComponentCount()!=0) {this.remove(0);}


		if (operation=="Get number of tickets sold") { 
			
			String tickets_sold=String.valueOf(
			mController.getTl_casheer_Service().Get_Number_Of_Tickets_Sold());
			
			jl1=new JLabel("The number of tickets sold: ");
			jl2=new JLabel(tickets_sold);
			
			this.add(jl1);
			this.add(jl2);
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

		switch(operation) {

		case ("Get details of all casheers"):

			workers_list=mController.getTl_casheer_Service().GetYourStaff("TL_casheer");

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


		//case ("?"):

			

		}//End of switch



		// Table instantiated using the two sets of data.
		JTable table=new JTable(data, title);
		// The table displayed in a Scrollpane.

		JScrollPane scrollPane = new JScrollPane (table,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);



		scrollPane.setPreferredSize(new Dimension
								(TL_Table_Sizes.Width,TL_Table_Sizes.Height));
		return scrollPane;


	}//End of method

}//End of class