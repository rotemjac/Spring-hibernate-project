package my.pack.webTier.clients.Guis.Workers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import my.pack.webTier.controlers.Mock_Controller;

public class Casheer_operations extends JPanel implements ActionListener {

	Mock_Controller mController;
	JButton jb_Add,jb_Sub;

	public Casheer_operations() {
		mController = Mock_Controller.getInstance();

	}

	public JPanel createContentPane(String operation)	{

		//In order to avoid many scrolpanels on the "this" panel
		//Didn't work here for some reason
		if (this.getComponentCount()!=0) {this.remove(0);}


		switch(operation) {

		case ("Add Ticket"):
			
			jb_Add=new JButton();
			jb_Add.addActionListener(this);
			jb_Add.setText("Add A ticket");
			if (this.getComponentCount()!=0) {this.remove(0);}
			//Adds button to panel
			this.add(jb_Add);
		
		break;

		case ("Subtract Ticket"):

			jb_Sub=new JButton();
			jb_Sub.addActionListener(this);
			jb_Sub.setText("Subtract A ticket");
			if (this.getComponentCount()!=0) {this.remove(0);}
			//Adds button to panel
			this.add(jb_Sub);
		break;
		}

		//content panes must be opaque
		this.setOpaque(true); 
		this.revalidate();
		return this;  
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource()==jb_Add) {
			mController.getCsrService().Add_Or_SubTicket('A');
		}	
		if (e.getSource()==jb_Sub) {
			mController.getCsrService().Add_Or_SubTicket('S');
		}
	}





}
