package my.pack.webTier.clients.MainGuis;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import my.pack.dataAccessTier.domain.subfacilities.Address;
import my.pack.dataAccessTier.domain.subfacilities.Animal;
import my.pack.dataAccessTier.domain.submanagers.TL_casheer;
import my.pack.dataAccessTier.domain.superclasses.Worker;
import my.pack.dataAccessTier.domain.workers.Caregiver;
import my.pack.dataAccessTier.domain.workers.Casheer;
import my.pack.dataAccessTier.domain.workers.Cleaner;
import my.pack.dataAccessTier.domain.workers.Guard;
import my.pack.dataAccessTier.domain.workers.Janitor;
import my.pack.dataAccessTier.domain.workers.Vet;
import my.pack.webTier.clients.Guis.Workers.Caregiver_operations;
import my.pack.webTier.clients.Guis.Workers.Casheer_operations;
import my.pack.webTier.clients.Guis.Workers.Guard_operations;
import my.pack.webTier.clients.Guis.Workers.Vet_operations;
import my.pack.webTier.clients.Guis.subManagers.Security_chief_operations;
import my.pack.webTier.clients.Guis.subManagers.TL_caregiver_operations;
import my.pack.webTier.clients.Guis.subManagers.TL_casheer_operations;
import my.pack.webTier.clients.Guis.subManagers.TL_cleaner_operations;
import my.pack.webTier.clients.Guis.subManagers.TL_janitor_operations;
import my.pack.webTier.clients.Guis.subManagers.Vet_chief_operations;
import my.pack.webTier.clients.Guis.topManagers.CEO_operations;
import my.pack.webTier.clients.SimpleGuis.Adding_new_worker_Gui;
import my.pack.webTier.clients.SimpleGuis.Ceo_remove_worker;
import my.pack.webTier.controlers.Mock_Controller;

/** 
TODO:Give description here...
 **/

public class Workers_menu  extends JPanel implements ActionListener  {


	/** Items Definitions **/
	Mock_Controller mController; 
	Main_menu mm;
	
	//-------All operations----//
	Casheer_operations csr_oper;
	Guard_operations guard_oper;
	Caregiver_operations caregiver_oper;
	Vet_operations vet_oper;
	TL_casheer_operations tl_csr_oper;
	TL_cleaner_operations tl_clnr_oper;
	TL_janitor_operations tl_janitor_oper;
	TL_caregiver_operations tl_caregiver_oper;
	Security_chief_operations sec_ch_Operations;
	Vet_chief_operations vet_chief_operations;
	CEO_operations ceo_operations;

	//private JPanel main_panel;
	private JPanel panel_labels;
	private JPanel panel_comboBoxes;
	private JPanel panel_launch_button;
	private JPanel panel_changed_output;

	private JButton launch_button;
	private JButton btn_go_back;
	private List<Animal> all_animals;
	private JComboBox comboBox_positions;
	private JComboBox comboBox_operations;
	private Component horizontalStrut;
	private Component horizontalStrut_1;
	private JLabel lblChhosePosition,lblChhoseOperation;

	private String[] operations_arr;
	private DefaultComboBoxModel model;
	private String worker;
	private JButton btnRefresh;
	private Component horizontalStrut_2;

	private String current_operation;

	public Adding_new_worker_Gui anwg;
	public Ceo_remove_worker crw;

	/** Constructor **/
	public Workers_menu () {

		csr_oper=new Casheer_operations();
		guard_oper=new Guard_operations();
		caregiver_oper=new Caregiver_operations();
		vet_oper=new Vet_operations();
		tl_csr_oper=new TL_casheer_operations();
		tl_clnr_oper=new TL_cleaner_operations();
		tl_janitor_oper=new TL_janitor_operations();
		tl_caregiver_oper=new TL_caregiver_operations();
		sec_ch_Operations=new Security_chief_operations();
		vet_chief_operations=new Vet_chief_operations();
		ceo_operations=new  CEO_operations();
		crw=new Ceo_remove_worker();

		anwg=new Adding_new_worker_Gui();
		anwg.launchButton.addActionListener(this);
		mController=Mock_Controller.getInstance();
		current_operation="";

		operations_arr=new String[15];

		/** Panels creation */

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setPreferredSize(new Dimension(800, 600));
		this.setBackground(new Color(20, 20, 20));
		this.setForeground(new Color(0, 255, 0));
		
		
		panel_labels = new JPanel();
		this.add(panel_labels);
		panel_labels.setMaximumSize(new Dimension(800,60));
		//panel_labels.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		//panel_labels.setBackground(new Color(135, 206, 250));
		//panel_labels.setBounds(20, 40, 500,20 );
		//panel.add(panel_labels, BorderLayout.NORTH);
		panel_labels.setLayout(new FlowLayout());
		panel_labels.setBorder(BorderFactory.createEmptyBorder(20,0,20,0));
		panel_labels.setBackground(this.getBackground());
		

		panel_comboBoxes = new JPanel();
		panel_comboBoxes.setLayout(new FlowLayout());
		panel_comboBoxes.setMaximumSize(new Dimension(800,60));
		//panel_buttons.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		//panel_buttons.setBackground(Color.RED);
		//panel_buttons.setBounds(20, 80, 500,100 );
		//panel.add(panel_buttons, BorderLayout.CENTER);
		this.add(panel_comboBoxes);
		panel_comboBoxes.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		panel_comboBoxes.setBackground(this.getBackground());

		panel_launch_button = new JPanel();
		this.add(panel_launch_button);
		panel_launch_button.setLayout(new FlowLayout());
		panel_launch_button.setBorder(BorderFactory.createEmptyBorder(20,0,20,0));

		panel_launch_button.setMaximumSize(new Dimension(800, 60));
		panel_launch_button.setBackground(this.getBackground());

		panel_changed_output = new JPanel();
		//panel_changed_output.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		//panel_changed_output.setBackground(Color.YELLOW);
		//panel_changed_output.setBounds(20, 200, 500,500 );
		//panel.add(panel_changed_output, BorderLayout.SOUTH);
		this.add(panel_changed_output);
		panel_changed_output.setLayout(new FlowLayout());
		panel_changed_output.setBorder(BorderFactory.createEmptyBorder(20,0,20,0));
		panel_changed_output.setMaximumSize(new Dimension(800, 560));
		panel_changed_output.setBackground(this.getBackground());

		//This is a very dangerous line!!!!
		//panel.setLayout(null);
		
		//---------------------------------------------------------------------//
		//------------------------Sub components creation----------------------//
		//---------------------------------------------------------------------//

		/** Labels Creation **/
				
		lblChhosePosition = new JLabel("Your position:  ");
		lblChhosePosition.setForeground(Color.WHITE);
		lblChhosePosition.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_labels.add(lblChhosePosition);

		horizontalStrut_1 = Box.createHorizontalStrut(30);
		horizontalStrut_1.setMaximumSize(new Dimension(130, 30));
		panel_labels.add(horizontalStrut_1);

		lblChhoseOperation = new JLabel("    Choose operation:");
		lblChhoseOperation.setForeground(Color.WHITE);
		lblChhoseOperation.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_labels.add(lblChhoseOperation);

		/**  JComboBox positions creation	*/
		List<String> positions=mController.getCeoService().GetAllPositions();
		String [] pos_arr=new String[positions.size()];
		int i=0;

		for (String pos:positions) {
			pos_arr[i]=pos;
			i++;
		}
		panel_comboBoxes.setLayout(new FlowLayout());

		comboBox_positions = new JComboBox(pos_arr);
		comboBox_positions.setEditable(true);
		comboBox_positions.setMaximumRowCount(3);
		comboBox_positions.setMaximumSize(  new Dimension(130, 30)	);
		panel_comboBoxes.add(comboBox_positions);

		horizontalStrut = Box.createHorizontalStrut(30);
		horizontalStrut.setMaximumSize(new Dimension(50, 30));
		panel_comboBoxes.add(horizontalStrut);


		/**  JComboBox operations creation	*/
		comboBox_operations = new JComboBox();
		model = new DefaultComboBoxModel( operations_arr );
		comboBox_operations.setModel(model);

		comboBox_operations.setMaximumSize(new Dimension(210, 30));
		comboBox_operations.setEditable(true);
		panel_comboBoxes.add(comboBox_operations);


		/**  Button Creation **/

		btnRefresh = new JButton("Refresh");
		btnRefresh.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_launch_button.add(btnRefresh);
		btnRefresh.addActionListener(this);

		panel_launch_button.add(Box.createHorizontalStrut(24));
		launch_button=new JButton("Display");
		launch_button.addActionListener(this);
		launch_button.setFont(new Font("Tahoma", Font.BOLD, 14));

		panel_launch_button.add(launch_button);
		launch_button.addActionListener(this);

		horizontalStrut_2 = Box.createHorizontalStrut(14);
		panel_launch_button.add(horizontalStrut_2);

		btn_go_back = new JButton("Go Back to last menu");
		btn_go_back.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_launch_button.add(btn_go_back);
		btn_go_back.addActionListener(this);


	}


	public JPanel createContentPane () {

		return this;

		//---------------------------------------------------------------------//
	}


	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource()==btnRefresh) {

			clean_panel();
			worker=(String)comboBox_positions.getSelectedItem();
			get_operations(worker);
			comboBox_operations.setModel( model );
		}

		if (e.getSource()==launch_button) {

			switch( (String)comboBox_positions.getSelectedItem() ) {

			/**
			 * Workers
			 */
			case("Casheer"):					
				if     ( (comboBox_operations.getSelectedItem()=="Add Ticket") 
						&&   (current_operation!="Casheer_Add Ticket")             )
				{

					clean_panel();
					panel_changed_output.add(csr_oper.createContentPane("Add Ticket"));
					display_and_save_current_operation("Casheer_Add Ticket");
				}

			if     ( (comboBox_operations.getSelectedItem()=="Subtract Ticket") 
					&&   (current_operation!="Casheer_Subtract Ticket")             )
			{

				clean_panel();
				panel_changed_output.add(csr_oper.createContentPane("Subtract Ticket"));
				display_and_save_current_operation("Casheer_Subtract Ticket");
			}
			break;

			case("Guard"):					
				if     ( (comboBox_operations.getSelectedItem()=="Add to people exited") 
						&&   (current_operation!="Guard_Add to people exited")             )
				{

					clean_panel();
					panel_changed_output.add(guard_oper.createContentPane("Add to people exited"));
					display_and_save_current_operation("Guard_Add to people exited");
				}

			if     ( (comboBox_operations.getSelectedItem()=="Subtract from people exited") 
					&&   (current_operation!="Guard_Subtract from people exited")             )
			{

				clean_panel();
				panel_changed_output.add(guard_oper.createContentPane("Subtract from people exited"));
				display_and_save_current_operation("Guard_Subtract from people exited");
			}
			break;

			case("Caregiver"):					
				if     ( (comboBox_operations.getSelectedItem()=="Mark sub facility that need to be fix") 
						&&   (current_operation!="Caregiver_Mark sub facility that need to be fix")             )
				{

					clean_panel();
					panel_changed_output.add(caregiver_oper.createContentPane("Mark sub facility that need to be fix"));
					display_and_save_current_operation("Caregiver_Mark sub facility that need to be fix");
				}

			if     ( (comboBox_operations.getSelectedItem()=="Mark animal that need a vet check") 
					&&   (current_operation!="Caregiver_Mark animal that need a vet check")             )
			{

				clean_panel();
				panel_changed_output.add(caregiver_oper.createContentPane("Mark animal that need a vet check"));
				display_and_save_current_operation("Caregiver_Mark animal that need a vet check");
			}
			break;

			case("Vet"):					
				if     ( (comboBox_operations.getSelectedItem()=="Get animals that need check in area") 
						&&   (current_operation!="Get animals that need check in area")             )
				{

					clean_panel();
					panel_changed_output.add(vet_oper.createContentPane("Get animals that need check in area"));
					display_and_save_current_operation("Get animals that need check in area");
				}
			break;


			/**
			 * Managers
			 */
			case("TL_casheer"):					
				if     ( (comboBox_operations.getSelectedItem()=="Get details of all casheers") 
						&&   (current_operation!="TL_casheer_Get details of all casheers")             )
				{

					clean_panel();
					panel_changed_output.add(tl_csr_oper.createContentPane("Get details of all casheers"));
					display_and_save_current_operation("TL_casheer_Get details of all casheers");
				}

			if     ( (comboBox_operations.getSelectedItem()=="Add Worker to staff") 
					&&   (current_operation!="TL_casheer_Add Worker to staff")             )
			{

				clean_panel();
				anwg.setPosition("TL_casheer");
				panel_changed_output.add(anwg);
				display_and_save_current_operation("TL_casheer_Add Worker to staff");
			}

			if     ( (comboBox_operations.getSelectedItem()=="Get number of tickets sold") 
					&&   (current_operation!="TL_casheer_Get number of tickets sold")             )
			{

				clean_panel();
				panel_changed_output.add(tl_csr_oper.createContentPane("Get number of tickets sold"));
				display_and_save_current_operation("TL_casheer_Get number of tickets sold");
			}

			break;



			case("TL_cleaner"):					
				if     ( (comboBox_operations.getSelectedItem()=="Get details of all cleaners") 
						&&   (current_operation!="TL_cleaner_Get details of all cleaners")             )
				{

					clean_panel();
					panel_changed_output.add(tl_clnr_oper.createContentPane("Get details of all cleaners"));
					display_and_save_current_operation("TL_cleaner_Get details of all cleaners");
				}

			if     ( (comboBox_operations.getSelectedItem()=="Add Worker to staff") 
					&&   (current_operation!="TL_cleaner_Add Worker to staff")             )
			{

				clean_panel();
				anwg.setPosition("TL_cleaner");
				panel_changed_output.add(anwg);
				display_and_save_current_operation("TL_cleaner_Add Worker to staff");
			}
			break;

			case("TL_janitor"):					

				if     ( (comboBox_operations.getSelectedItem()=="Get details of all janitors") 
						&&   (current_operation!="TL_janitor_Get details of all janitors")             )
				{

					clean_panel();
					panel_changed_output.add(tl_janitor_oper.createContentPane("Get details of all janitors"));
					display_and_save_current_operation("TL_janitor_Get details of all janitors");
				}

			if     ( (comboBox_operations.getSelectedItem()=="Get all land portions that need a fix") 
					&&   (current_operation!="Get all land portions that need a fixs")             )
			{

				clean_panel();
				panel_changed_output.add(tl_janitor_oper.createContentPane("Get all land portions that need a fix"));
				display_and_save_current_operation("Get all land portions that need a fix");
			}

			if     ( (comboBox_operations.getSelectedItem()=="Add Worker to staff") 
					&&   (current_operation!="TL_janitor_Add Worker to staff")             )
			{

				clean_panel();
				anwg.setPosition("TL_janitor");
				panel_changed_output.add(anwg);
				display_and_save_current_operation("TL_janitor_Add Worker to staff");
			}

			break;


			case("TL_caregiver"):					
				if     ( (comboBox_operations.getSelectedItem()=="Get details of all caregivers") 
						&&   (current_operation!="TL_caregiver_Get details of all caregivers")             )
				{

					clean_panel();
					panel_changed_output.add(tl_caregiver_oper.createContentPane("Get details of all caregivers"));
					display_and_save_current_operation("TL_caregiver_Get details of all caregivers");
				}

			//TODO: I dont know why but this method (adding worker)
			//		doesn't work for TL_caregiver (only)
			if     ( (comboBox_operations.getSelectedItem()=="Add Worker to staff") 
					&&   (current_operation!="TL_caregiver_Add Worker to staff")             )
			{

				clean_panel();
				anwg.setPosition("TL_caregiver");
				panel_changed_output.add(anwg);
				display_and_save_current_operation("TL_caregiver_Add Worker to staff");
			}

			if     ( (comboBox_operations.getSelectedItem()=="Set caregivers to areas") 
					&&   (current_operation!="TL_caregiver_Set caregivers to areas")             )
			{

				clean_panel();
				panel_changed_output.add(tl_caregiver_oper.createContentPane("Set caregivers to areas"));
				display_and_save_current_operation("TL_caregiver_Set caregivers to areas");
			}

			if     ( (comboBox_operations.getSelectedItem()=="View/change animals in land portions") 
					&&   (current_operation!="TL_caregiver_View/change animals in land portions")             )
			{

				clean_panel();
				panel_changed_output.add(tl_caregiver_oper.createContentPane("View/change animals in land portions"));
				display_and_save_current_operation("TL_caregiver_View/change animals in land portions");
			}


			break;


			case("Security_chief"):	

				if     ( (comboBox_operations.getSelectedItem()=="Get details of all guards") 
						&&   (current_operation!="Get details of all guards")             )
				{
					clean_panel();
					panel_changed_output.add(sec_ch_Operations.createContentPane("Get details of all guards"));
					display_and_save_current_operation("Get details of all guards");

				}

			if     ( (comboBox_operations.getSelectedItem()=="Add Worker to staff") 
					&&   (current_operation!="Security_chief_Add Worker to staff")             )
			{

				clean_panel();
				anwg.setPosition("Security_chief");
				panel_changed_output.add(anwg);
				display_and_save_current_operation("Security_chief_Add Worker to staff");
			}

			if     ( (comboBox_operations.getSelectedItem()=="Setup weapon license numbers") 
					&&   (current_operation!="Security_chief_Setup weapon license numbers")             )
			{

				clean_panel();
				panel_changed_output.add(sec_ch_Operations.createContentPane("Setup weapon license numbers"));
				display_and_save_current_operation("Security_chief_Setup weapon license numbers");
			}



			if     ( (comboBox_operations.getSelectedItem()=="Show cameras") 
					&&   (current_operation!="Security_chief_cameras")             )
			{
				clean_panel();
				panel_changed_output.add(sec_ch_Operations.createContentPane("Show cameras"));
				display_and_save_current_operation("Security_chief_cameras");

			}

			if     ( (comboBox_operations.getSelectedItem()=="Show gates") 
					&&   (current_operation!="Security_chief_gates")             )
			{


				clean_panel();
				panel_changed_output.add(sec_ch_Operations.createContentPane("Show gates"));
				display_and_save_current_operation("Security_chief_gates");

			}

			if     ( (comboBox_operations.getSelectedItem()=="Show fences") 
					&&   (current_operation!="Security_chief_fences")             )
			{

				clean_panel();
				panel_changed_output.add(sec_ch_Operations.createContentPane("Show fences"));
				display_and_save_current_operation("Security_chief_fences");

			}

			if     ( (comboBox_operations.getSelectedItem()=="Show number of visitors") 
					&&   (current_operation!="Security_chief_Show_number_of_visitors")             )
			{

				clean_panel();
				panel_changed_output.add(sec_ch_Operations.createContentPane("Show number of visitors"));
				display_and_save_current_operation("Security_chief_Show_number_of_visitors");

			}

			break;

			case("Vet_chief"):	

				if     ( (comboBox_operations.getSelectedItem()=="Get details of all vets") 
						&&   (current_operation!="Get details of all vets")             )
				{
					clean_panel();
					panel_changed_output.add(vet_chief_operations.createContentPane("Get details of all vets"));
					display_and_save_current_operation("Get details of all vets");
				}

			if     ( (comboBox_operations.getSelectedItem()=="Add Worker to staff") 
					&&   (current_operation!="Vet_chief_Add Worker to staff")             )
			{

				clean_panel();
				anwg.setPosition("Vet_chief");
				panel_changed_output.add(anwg);
				display_and_save_current_operation("Vet_chief_Add Worker to staff");
			}

			if     ( (comboBox_operations.getSelectedItem()=="Set vets to areas") 
					&&   (current_operation!="Set vets to areas")             )
			{
				clean_panel();
				panel_changed_output.add(vet_chief_operations.createContentPane("Set vets to areas"));
				display_and_save_current_operation("Set vets to areas");
			}

			if     ( (comboBox_operations.getSelectedItem()=="Get vet to areas mapping") 
					&&   (current_operation!="Get vet to areas mapping")             )
			{
				clean_panel();
				panel_changed_output.add(vet_chief_operations.createContentPane("Get vet to areas mapping"));
				display_and_save_current_operation("Get vet to areas mapping");
			}



			if     ( (comboBox_operations.getSelectedItem()=="Get animals info") 
					&&   (current_operation!="Get animals info")             )
			{
				clean_panel();
				panel_changed_output.add(vet_chief_operations.createContentPane("Get animals info"));
				display_and_save_current_operation("Get animals info");
			}

			if     ( (comboBox_operations.getSelectedItem()=="Set animal food") 
					&&   (current_operation!="Set animal food")             )
			{
				clean_panel();
				panel_changed_output.add(vet_chief_operations.createContentPane("Set animal food"));
				display_and_save_current_operation("Set animal food");
			}

			if     ( (comboBox_operations.getSelectedItem()=="Get all unchecked animals") 
					&&   (current_operation!="Get all unchecked animals")             )
			{
				clean_panel();
				panel_changed_output.add(vet_chief_operations.createContentPane("Get all unchecked animals"));
				display_and_save_current_operation("Get all unchecked animals");
			}

			break;

			case("CEO"):	

				if     ( (comboBox_operations.getSelectedItem()=="Get All workers") 
						&&   (current_operation!="Get All workers")             )
				{
					clean_panel();
					panel_changed_output.add(ceo_operations.createContentPane("Get All workers"));
					display_and_save_current_operation("Get All workers");
				}

			if     ( (comboBox_operations.getSelectedItem()=="Fire a worker") 
					&&   (current_operation!="Fire a worker")             )
			{
				clean_panel();
				panel_changed_output.add(crw);
				display_and_save_current_operation("Fire a worker");
			}

			break;


			}//End of: switch

		}//End of: if (e.getSource()==launch_button)


		if (e.getSource()==anwg.launchButton) {

			mController.getManagerService().AddWorkerToStaff
			(anwg.getPosition(), cast_worker() );

		}//End of if
		
		if (e.getSource()==btn_go_back) {
			mm=Main_menu.get_Instane();
			mm.setBounds(10, 10, 700, 400);
			mm.setBackground(new Color(255, 255, 204));
			mm.setContentPane(mm.saved_content_container);
		}//End of if
		
		

	}//End of: actionPerformed

	private void get_operations(String worker) {

		model.removeAllElements();

		switch (worker) {

		/////////////Workers//////////////////
		case "Casheer": 
			operations_arr[0]="Add Ticket";
			operations_arr[1]="Subtract Ticket";
			operations_arr[2]="";
			operations_arr[3]="";
			operations_arr[4]="";
			operations_arr[5]="";
			operations_arr[6]="";
			operations_arr[7]="";
			operations_arr[8]="";
			break;

		case "Cleaner": 
			operations_arr[0]="";
			operations_arr[1]="";
			operations_arr[2]="";
			operations_arr[3]="";
			operations_arr[4]="";
			operations_arr[5]="";
			operations_arr[6]="";
			operations_arr[7]="";
			operations_arr[8]="";
			break;

		case "Guard": 
			operations_arr[0]="Add to people exited";
			operations_arr[1]="Subtract from people exited";
			operations_arr[2]="";
			operations_arr[3]="";
			operations_arr[4]="";
			operations_arr[5]="";
			operations_arr[6]="";
			operations_arr[7]="";
			operations_arr[8]="";
			break;

		case "Janitor": 
			operations_arr[0]="";
			operations_arr[1]="";
			operations_arr[2]="";
			operations_arr[3]="";
			operations_arr[4]="";
			operations_arr[5]="";
			operations_arr[6]="";
			operations_arr[7]="";
			operations_arr[8]="";
			break;

		case "Caregiver": 
			operations_arr[0]="Mark sub facility that need to be fix";
			operations_arr[1]="Mark animal that need a vet check";
			operations_arr[2]="";
			operations_arr[3]="";
			operations_arr[4]="";
			operations_arr[5]="";
			operations_arr[6]="";
			operations_arr[7]="";
			operations_arr[8]="";
			break;

		case "Vet": 
			operations_arr[0]="Get animals that need check in area";
			operations_arr[1]="";
			operations_arr[2]="";
			operations_arr[3]="";
			operations_arr[4]="";
			operations_arr[5]="";
			operations_arr[6]="";
			operations_arr[7]="";
			operations_arr[8]="";
			break;

			///////////Managers/////////////////	
		case "TL_casheer": 
			operations_arr[0]="Get details of all casheers";
			operations_arr[1]="Add Worker to staff";
			operations_arr[2]="Get number of tickets sold";
			operations_arr[3]="";
			operations_arr[4]="";
			operations_arr[5]="";
			operations_arr[6]="";
			operations_arr[7]="";
			operations_arr[8]="";
			break;

		case "TL_cleaner": 
			operations_arr[0]="Get details of all cleaners";
			operations_arr[1]="Add Worker to staff";
			operations_arr[2]="";
			operations_arr[3]="";
			operations_arr[4]="";
			operations_arr[5]="";
			operations_arr[6]="";
			operations_arr[7]="";
			operations_arr[8]="";
			break;

		case "Security_chief": 
			operations_arr[0]="Get details of all guards";
			operations_arr[1]="Add Worker to staff";
			operations_arr[2]="Setup weapon license numbers";
			operations_arr[3]="Show cameras";
			operations_arr[4]="Show gates";
			operations_arr[5]="Show fences";
			operations_arr[6]="Show number of visitors";
			operations_arr[7]="";
			operations_arr[8]="";
			break;

		case "TL_janitor": 
			operations_arr[0]="Get details of all janitors";
			operations_arr[1]="Add Worker to staff";
			operations_arr[2]="Get all land portions that need a fix";
			operations_arr[3]="";
			operations_arr[4]="";
			operations_arr[5]="";
			operations_arr[6]="";
			operations_arr[7]="";
			operations_arr[8]="";
			break;

		case "TL_caregiver": 
			operations_arr[0]="Get details of all caregivers";
			operations_arr[1]="Add Worker to staff";
			operations_arr[2]="Set caregivers to areas";
			operations_arr[3]="View/change animals in land portions";
			operations_arr[4]="";
			operations_arr[5]="";
			operations_arr[6]="";
			operations_arr[7]="";
			operations_arr[8]="";
			break;

		case "Vet_chief": 
			operations_arr[0]="Get details of all vets";
			operations_arr[1]="Add Worker to staff";
			operations_arr[2]="Set vets to areas";
			operations_arr[3]="Get vet to areas mapping";
			operations_arr[4]="Get animals info";
			operations_arr[5]="Get all unchecked animals";
			operations_arr[6]="Set animal food";
			operations_arr[7]="";
			operations_arr[8]="";
			break;
			///////////////Top Managers///////////////////
		case "CEO": 
			operations_arr[0]="Get All workers";
			operations_arr[1]="Fire a worker";
			operations_arr[2]="";
			operations_arr[3]="";
			operations_arr[4]="";
			operations_arr[5]="";
			operations_arr[6]="";
			operations_arr[7]="";
			operations_arr[8]="";
			break;

		}	

		model.addElement(operations_arr[0]);
		model.addElement(operations_arr[1]);
		model.addElement(operations_arr[2]);
		model.addElement(operations_arr[3]);
		model.addElement(operations_arr[4]);
		model.addElement(operations_arr[5]);
		model.addElement(operations_arr[6]);
		model.addElement(operations_arr[7]);
		model.addElement(operations_arr[8]);


	}

	//TODO: This method was creatd because i failed to cast the worker object into
	//		on of the sub objects ,for example: Vet new_vet=(Vet)new_worker;
	//I recieved the next exception:
	//java.lang.ClassCastException: my.pack.dataAccessTier.domain.superclasses.Worker 
	//cannot be cast to my.pack.dataAccessTier.domain.workers.Vet

	public Worker cast_worker() {

		Address new_adrAddress=new Address
				(anwg.tf_adrr_city.getText(), 
						anwg.tf_adrr_street.getText(), 
						Integer.parseInt(anwg.tf_adrr_house.getText()));

		switch(anwg.getPosition()) {

		case("TL_casheer"):
			Casheer new_casheer=new Casheer (
					anwg.tf_first_name.getText(), 
					anwg.tf_last_name.getText(), 
					Integer.parseInt(anwg.tf_age.getText()), 
					anwg.tf_gender.getText().charAt(0), 
					Long.parseLong(anwg.tf_id.getText()), 
					new_adrAddress, 
					Integer.parseInt(anwg.tf_salary.getText()), 
					Long.parseLong(anwg.tf_cell_num.getText())
					);
		return new_casheer;

		case("TL_cleaner"):
			Cleaner new_cleaner=new Cleaner (
					anwg.tf_first_name.getText(), 
					anwg.tf_last_name.getText(), 
					Integer.parseInt(anwg.tf_age.getText()), 
					anwg.tf_gender.getText().charAt(0), 
					Long.parseLong(anwg.tf_id.getText()), 
					new_adrAddress, 
					Integer.parseInt(anwg.tf_salary.getText()), 
					Long.parseLong(anwg.tf_cell_num.getText())
					);

		return new_cleaner;

		case("TL_janitor"):
			Janitor new_janitor=new Janitor	(
					anwg.tf_first_name.getText(), 
					anwg.tf_last_name.getText(), 
					Integer.parseInt(anwg.tf_age.getText()), 
					anwg.tf_gender.getText().charAt(0), 
					Long.parseLong(anwg.tf_id.getText()), 
					new_adrAddress, 
					Integer.parseInt(anwg.tf_salary.getText()), 
					Long.parseLong(anwg.tf_cell_num.getText())
					);

		return new_janitor;

		case("TL_caregiver"):
			Caregiver new_caregiver=new Caregiver	(
					anwg.tf_first_name.getText(), 
					anwg.tf_last_name.getText(), 
					Integer.parseInt(anwg.tf_age.getText()), 
					anwg.tf_gender.getText().charAt(0), 
					Long.parseLong(anwg.tf_id.getText()), 
					new_adrAddress, 
					Integer.parseInt(anwg.tf_salary.getText()), 
					Long.parseLong(anwg.tf_cell_num.getText())
					);

		return new_caregiver;

		//You need to add the weapon number manually after
		case("Security_chief"):
			Guard new_guard=new Guard(
					anwg.tf_first_name.getText(), 
					anwg.tf_last_name.getText(), 
					Integer.parseInt(anwg.tf_age.getText()), 
					anwg.tf_gender.getText().charAt(0), 
					Long.parseLong(anwg.tf_id.getText()), 
					new_adrAddress, 
					Integer.parseInt(anwg.tf_salary.getText()), 
					Long.parseLong(anwg.tf_cell_num.getText()),
					0);

		return new_guard;

		case("Vet_chief"):
			Vet new_vet=new Vet (
					anwg.tf_first_name.getText(), 
					anwg.tf_last_name.getText(), 
					Integer.parseInt(anwg.tf_age.getText()), 
					anwg.tf_gender.getText().charAt(0), 
					Long.parseLong(anwg.tf_id.getText()), 
					new_adrAddress, 
					Integer.parseInt(anwg.tf_salary.getText()), 
					Long.parseLong(anwg.tf_cell_num.getText())
					);
		return new_vet;

		}//End of switch
		return null;

	}//End of if

	public void clean_panel() {

		//It's better to put this here then outside
		if (panel_changed_output.getComponentCount()!=0) {
			panel_changed_output.remove(0);
			panel_changed_output.revalidate();

		}

	}

	public void display_and_save_current_operation(String operation) {
		current_operation=operation;
		panel_changed_output.setVisible(true);
		panel_changed_output.revalidate();
	}



}