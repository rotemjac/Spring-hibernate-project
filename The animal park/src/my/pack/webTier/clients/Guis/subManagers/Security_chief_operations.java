package my.pack.webTier.clients.Guis.subManagers;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.springframework.ui.Model;

import com.sun.org.apache.bcel.internal.generic.I2F;
import com.sun.xml.internal.ws.api.config.management.policy.ManagementAssertion.Setting;

import my.pack.dataAccessTier.domain.subfacilities.Animal;
import my.pack.dataAccessTier.domain.subfacilities.Camera;
import my.pack.dataAccessTier.domain.subfacilities.Fence;
import my.pack.dataAccessTier.domain.subfacilities.Gate;
import my.pack.dataAccessTier.domain.superclasses.Worker;
import my.pack.dataAccessTier.domain.workers.Guard;
import my.pack.webTier.clients.MainGuis.The_main_execution;
import my.pack.webTier.clients.SimpleGuis.Setting_specific_feature;
import my.pack.webTier.controlers.Mock_Controller;
import javax.swing.BoxLayout;

public class Security_chief_operations extends JPanel 
											implements ActionListener {

	Mock_Controller mController;

	JLabel jl1,jl2;
	//JTable table;
	String[] title;
	Object[][] data;

	List<Worker> workers_list;
	List<Object[]> guards_list;
	List<Camera> camera_list;
	List<Gate> gate_list;
	List<Fence> fence_list;
	
	int set_up_weapon_table_width,set_up_weapon_table_height;


	Setting_specific_feature spf;
	
	public Security_chief_operations() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		mController = Mock_Controller.getInstance();
		set_up_weapon_table_width=550;
		set_up_weapon_table_height=100;
	}

	public JPanel createContentPane(String operation)	{

		//In order to avoid many scrolpanels on the "this" panel
		if (this.getComponentCount()!=0) {this.remove(0);}


		if (operation=="Show number of visitors") {
			if (this.getComponentCount()!=0) {this.remove(0);}
			String num=String.valueOf(
					mController.getSec_chief_Service().Number_Of_visitors_In_Park());

			 jl1=new JLabel("The number of visitors in the park is:");
			 jl2=new JLabel(num);
			this.add(jl1);
			this.add(jl2);
		}
		
		else if (operation=="Setup weapon license numbers") {
			if (this.getComponentCount()!=0) {this.remove(0);}
			this.add(create_table(operation));
			spf=new Setting_specific_feature();
			this.add(spf);
			spf.first_area_lbl.setText("Set guard weapon number:  ");
			String[] guards_id=new String[10];
			
			int i=0;
			for(Object[] guard:guards_list) {
				guards_id[i]= String.valueOf(guard[0]);
				i++;
			}
			
			spf.btnSetFeature.addActionListener(this);
			DefaultComboBoxModel model = new DefaultComboBoxModel( guards_id );
			spf.choose_worker_cb.setModel(model);
							
			//this.add
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

		case ("Get details of all guards"):
			if (this.getComponentCount()!=0) {this.remove(0);}
			workers_list=mController.getSec_chief_Service().GetYourStaff("Security_chief");

		// The data used as the titles for the table.
		title=new String[10];
		title[0]="Id";
		title[1]="First name";
		title[2]="Last name"; 
		title[3]="Gender";
		title[4]="Age"; 
		title[5]="Salary";
		title[6]="Cell number";
		title[7]="City";
		title[8]="Street";
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
		
		
		case ("Setup weapon license numbers"):
			if (this.getComponentCount()!=0) {this.remove(0);}
			guards_list=mController.
					getSec_chief_Service().ViewAndSetupWeaponLicenseNum();
			
			// The data used as the titles for the table.
			title=new String[4];
			title[0]="Guard id";
			title[1]="Guard first name";
			title[2]="Guard last name"; 
			title[3]="Weapon number";
			
			// The data used in the table, placed as a multi-dimensional array.
			data=new Object[guards_list.size()][4];
			i=0;
			for (Object[] guard:guards_list) {
				data[i][0]=guard[0];
				data[i][1]=guard[1];
				data[i][2]=guard[2];
				data[i][3]=guard[3];
				i++;
			}
			
			//set_up_weapon_table_width,
			//set_up_weapon_table_height
		
		break;

		case ("Show cameras"):
			if (this.getComponentCount()!=0) {this.remove(0);}
			camera_list=mController.getSec_chief_Service().Get_All_Cameras();

		// The data used as the titles for the table.
		title=new String[3];
		title[0]="Camera id";
		title[1]="Angle"; 
		title[2]="Recognize people?"; 

		// The data used in the table, placed as a multi-dimensional array.
		data=new Object[camera_list.size()][3];

		 i=0;
		for (Camera cam:camera_list) {
			data[i][0]=cam.getId();
			data[i][1]=cam.getAngle();
			data[i][2]=cam.getRec_people();
			i++;
		}

		break;

		case ("Show gates"):
			if (this.getComponentCount()!=0) {this.remove(0);}
			gate_list=mController.getSec_chief_Service().Get_All_Gates();

		// The data used as the titles for the table.
		title=new String[2];
		title[0]="Gate id";
		title[1]="Close?"; 


		// The data used in the table, placed as a multi-dimensional array.
		data=new Object[gate_list.size()][2];

		i=0;
		for (Gate gate:gate_list) {
			data[i][0]=gate.getId();
			data[i][1]=gate.getIs_close();
			i++;
		}

		break;

		case ("Show fences"):
			if (this.getComponentCount()!=0) {this.remove(0);}
			fence_list=mController.getSec_chief_Service().Get_All_Fences();

		// The data used as the titles for the table.
		title=new String[2];
		title[0]="Fence id";
		title[1]="OK?"; 


		// The data used in the table, placed as a multi-dimensional array.
		data=new Object[fence_list.size()][2];

		i=0;
		for (Fence fence:fence_list) {
			data[i][0]=fence.getId();
			data[i][1]=fence.getIs_ok();
			i++;
		}

		break;
		}

		// Table instantiated using the two sets of data.
		JTable table=new JTable(data, title);
		// The table displayed in a Scrollpane.

		JScrollPane scrollPane = new JScrollPane (table,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		
		if (operation=="Setup weapon license numbers") {
			scrollPane.setPreferredSize(new Dimension
					(set_up_weapon_table_width,set_up_weapon_table_height));	
		}
		
		else {
		scrollPane.setPreferredSize(new Dimension
							(TL_Table_Sizes.Width,TL_Table_Sizes.Height));
		}
		
		return scrollPane;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource()==spf.btnSetFeature) {
			
			mController.getSec_chief_Service().SetWeaponNumber
			(spf.choose_worker_cb.getSelectedItem().toString(), Long.parseLong(spf.textField.getText()));
		}
		
	}


}