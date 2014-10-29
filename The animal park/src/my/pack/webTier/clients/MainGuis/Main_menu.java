package my.pack.webTier.clients.MainGuis;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import my.pack.advices.photos.Window_with_photos;
import my.pack.cache_performance.Cache_methods_data;
import my.pack.test.unitTest.CEODao_hibernate_impl_UnitTest;
import my.pack.webTier.clients.SimpleGuis.BackgroundPanel;
import my.pack.webTier.clients.creation.Creating_Park;
import my.pack.webTier.controlers.Mock_Controller;
import java.awt.Color;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;


/** 

Window with lable,textfield and button
 
**/

public class Main_menu  extends JFrame implements ActionListener  {

	private static Main_menu objMain_menu;
	Mock_Controller mController;
	
	public Container saved_content_container;
	public JPanel starting_panel;
	public JLabel lable;
	public JButton btnCreateThePark,btnWorkersMenu;
	BackgroundPanel bg;
	private JButton btnPerformence;
	
	/** ---------------- **/
	
	/** Constructor **/
	private Main_menu () {
		setBounds(10, 10, 700, 400);
		setBackground(new Color(255, 255, 204));
		
		getContentPane().setPreferredSize(new Dimension(700, 400));
		
		starting_panel = new JPanel();
		starting_panel.setMaximumSize(new Dimension(720, 60));
		starting_panel.setBackground(new Color(204, 255, 255));
		starting_panel.setBounds(30, 30, 700, 450);
		mController=Mock_Controller.getInstance();
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		getContentPane().add(starting_panel);
		
		lable=new JLabel("Choose your menu:");
		starting_panel.add(lable);
		lable.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		
		btnWorkersMenu = new JButton("Workers menu");
		starting_panel.add(btnWorkersMenu);
		btnWorkersMenu.addActionListener(this);
		btnWorkersMenu.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		
		btnCreateThePark=new JButton("Create the park");
		starting_panel.add(btnCreateThePark);
		btnCreateThePark.addActionListener(this);
		btnCreateThePark.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		
		btnPerformence = new JButton("Performence");
		btnPerformence.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		starting_panel.add(btnPerformence);
		btnPerformence.addActionListener(this);
		
		
		bg = new BackgroundPanel();
		FlowLayout flowLayout = (FlowLayout) bg.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		bg.setBounds(10, 10, this.getWidth(), this.getHeight());
		getContentPane().add(bg);
		
		saved_content_container=new Container();
		saved_content_container=getContentPane();
		
		//Set when the window closed, stop wasting system resource 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setTitle("Welcome to the animal park");
		
		
		pack();
		
		//Important-without whis we will not see the window
		setVisible(true);
		//------------------------------//
	}
	
	public static Main_menu get_Instane () {
		
		if (objMain_menu==null) {
		
			objMain_menu=new Main_menu();
		}
		
		return objMain_menu;
		
	}


	
	public JPanel getPanel() {
		return starting_panel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	
		if (e.getSource()==btnWorkersMenu) {
			
			//Vet_chief_menu vcm=new Vet_chief_menu();
			//////////this.getContentPane().add(vcm);
			//this.setContentPane(	vcm.createContentPane()	);
			Workers_menu wMenu=new Workers_menu();
			this.setContentPane(wMenu.createContentPane());
			this.setSize(new Dimension(800, 600));
		}
		
		if (e.getSource()==btnCreateThePark) {
			
			Creating_Park cp=new Creating_Park(
					mController.getClient_Creating_Animals(),
					mController.getCeoService(),
					mController.getManagerService(),
					mController.getWorkerService(),
					mController.getFacService(),
					mController.getPd_service()
					);
			
			cp.Create();
		}
		
		if (e.getSource()==btnPerformence) {
			Cache_methods_data.show_statics();
		}
		
		

		
	}	
}