package my.pack.webTier.clients.Guis.Workers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import my.pack.webTier.controlers.Mock_Controller;

public class Guard_operations extends JPanel implements ActionListener {

	Mock_Controller mController;
	JButton jb_Add,jb_Sub;

	public Guard_operations() {
		mController = Mock_Controller.getInstance();

	}

	public JPanel createContentPane(String operation)	{

		//In order to avoid many scrolpanels on the "this" panel
		//Didn't work here for some reason
		if (this.getComponentCount()!=0) {this.remove(0);}


		switch(operation) {

		case ("Add to people exited"):
			
			jb_Add=new JButton();
			jb_Add.addActionListener(this);
			jb_Add.setText("Add to people exited");
			if (this.getComponentCount()!=0) {this.remove(0);}
			//Adds button to panel
			this.add(jb_Add);
		
		break;

		case ("Subtract from people exited"):

			jb_Sub=new JButton();
			jb_Sub.addActionListener(this);
			jb_Sub.setText("Subtract from people exited");
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
			mController.getGuardService().Add_Or_Sub_People_Out('A');
		}	
		if (e.getSource()==jb_Sub) {
			mController.getGuardService().Add_Or_Sub_People_Out('S');
		}
	}





}
