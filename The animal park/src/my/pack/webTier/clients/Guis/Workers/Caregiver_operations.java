package my.pack.webTier.clients.Guis.Workers;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import my.pack.webTier.controlers.Mock_Controller;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Caregiver_operations extends JPanel
implements ActionListener {

	Mock_Controller mController;

	/**
	 * Setting the land portion problem panel
	 */

	//Set problem panel
	private JPanel set_prob_Panel;

	//First row panel
	private JPanel panel1;
	private JTextField textField1;
	private JLabel lbl1;

	//Second row panel
	private JPanel panel2;
	private JTextField textField2;
	private JLabel lbl2;

	//Third row panel
	private JPanel panel3;
	private JTextField textField3;
	private JLabel lbl3;

	//The launch panel 
	private JPanel panel4;
	private JButton jb_launch_lp_problems;

	/**
	 * Setting the animal check panel
	 */

	private JPanel panel_animal_check;

	//First row panel
	private JPanel panel_animal_check_row;
	private JTextField textField_animal_check;
	private JLabel lbl_animal_check;

	//The launch panel 
	private JPanel panel_launch_animal_check;
	private JButton jb_launch_animal_check;

	public Caregiver_operations() {

		mController = Mock_Controller.getInstance();	
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		/**
		 * Setting the land portion problem panel
		 */
		set_prob_Panel=new JPanel();
		//add(set_prob_Panel); --will be added dynamicaly
		set_prob_Panel.setLayout(new BoxLayout(set_prob_Panel, BoxLayout.Y_AXIS));

		//Setting first row panel
		panel1 = new JPanel();
		panel1.setAlignmentX(1.5f);
		set_prob_Panel.add(panel1);
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));


		lbl1 = new JLabel("The Area of land portion:");
		panel1.add(lbl1);
		lbl1.setMaximumSize(new Dimension(160,30));

		textField1 = new JTextField();
		panel1.add(textField1);
		textField1.setColumns(1);
		textField1.setMaximumSize(new Dimension(30,30));

		//Setting second row panel
		panel2 = new JPanel();
		panel2.setAlignmentX(1.5f);
		set_prob_Panel.add(panel2);
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));

		lbl2 = new JLabel("The land portion's num:");
		panel2.add(lbl2);
		lbl2.setMaximumSize(new Dimension(160,30));

		textField2 = new JTextField();
		panel2.add(textField2);
		textField2.setColumns(1);
		textField2.setMaximumSize(new Dimension(30,30));

		//Setting third row panel
		panel3 = new JPanel();
		set_prob_Panel.add(panel3);
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));

		lbl3 = new JLabel("The land portion's problem:");
		panel3.add(lbl3);
		lbl3.setMaximumSize(new Dimension(160,30));

		textField3 = new JTextField();
		panel3.add(textField3);
		textField3.setColumns(1);
		textField3.setMaximumSize(new Dimension(200,30));

		//Setting launch panel
		panel4 = new JPanel();
		set_prob_Panel.add(panel4);
		panel4.setLayout(new BoxLayout(panel4, BoxLayout.X_AXIS));

		jb_launch_lp_problems=new JButton("Send");
		jb_launch_lp_problems.addActionListener(this);
		panel4.add(jb_launch_lp_problems);

		/**
		 * Setting the animal check panel
		 */
		//
		panel_animal_check=new JPanel();
		//
		panel_animal_check_row=new JPanel();
		lbl_animal_check=new JLabel("Enter id of animal that need a check:");
		panel_animal_check_row.add(lbl_animal_check);
		textField_animal_check=new JTextField();
		panel_animal_check_row.add(textField_animal_check);
		textField_animal_check.setPreferredSize(new Dimension(100,20));
		//
		panel_animal_check.add(panel_animal_check_row);
		
		//The launch panel 
		panel_launch_animal_check=new JPanel();
		jb_launch_animal_check=new JButton("Send");
		jb_launch_animal_check.addActionListener(this);
		panel_launch_animal_check.add(jb_launch_animal_check);
		//
		panel_animal_check.add(panel_launch_animal_check);
	}

	public JPanel createContentPane(String operation)	{

		//In order to avoid many scrolpanels on the "this" panel
		if (this.getComponentCount()!=0) {
			this.remove(0);
			//This part didn't help
			textField1.setText("");
			panel1.revalidate();
			textField2.setText("");
			panel2.revalidate();
			textField3.setText("");
			panel3.revalidate();
			set_prob_Panel.revalidate();	
		}


		switch(operation) {

		case ("Mark sub facility that need to be fix"):
			//Adds panel to the "this" panel
			add(set_prob_Panel);
			break;
			
		case ("Mark animal that need a vet check"):
			//Adds panel to the "this" panel
			add(panel_animal_check);
			break;

		}
		
		
		//content panes must be opaque
		this.setOpaque(true); 
		return this;  
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource()==jb_launch_lp_problems) {
			mController.getCaregiverService().SetProblemToLandPortion
			(textField3.getText(), textField1.getText(), textField2.getText());
		}
		
		if (e.getSource()==jb_launch_animal_check) {
			mController.getCaregiverService().
			MarkAnimalThatNeedVetCheck(textField_animal_check.getText(), 'Y');
		}
	}





}
