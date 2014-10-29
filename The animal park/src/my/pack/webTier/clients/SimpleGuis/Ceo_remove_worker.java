package my.pack.webTier.clients.SimpleGuis;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JTextField;

import my.pack.webTier.controlers.Mock_Controller;

public class Ceo_remove_worker extends JPanel implements ActionListener{

	Mock_Controller mController; 
	
	public JPanel panel_fire_worker;
	JLabel lbl;
	private JTextField tf_fired_worker_id;
	public JButton launchButton;
	private JPanel panel_for_dialog;


	public Ceo_remove_worker() {

		mController=Mock_Controller.getInstance();
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		///////First row panel/////////////
		panel_fire_worker = new JPanel();
		add(panel_fire_worker);
		panel_fire_worker.setLayout(new BoxLayout(panel_fire_worker, BoxLayout.X_AXIS));

		lbl = new JLabel(" The id of the fired worker:  ");
		panel_fire_worker.add(lbl);

		tf_fired_worker_id = new JTextField();
		panel_fire_worker.add(tf_fired_worker_id);
		tf_fired_worker_id.setColumns(10);
		tf_fired_worker_id.setMaximumSize(new Dimension(100,20 ));
		/////////////////////////////////

		launchButton = new JButton("Send");
		launchButton.addActionListener(this);
		add(launchButton);
		/////////////////////////////////

		panel_for_dialog = new JPanel();
		add(panel_for_dialog);
		///////////////////////////////////
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==launchButton) {
			String password=JOptionPane.
					showInputDialog(panel_for_dialog,"Enter CEO password");
			if ("CEO".equals(password)) {
				JOptionPane.showMessageDialog(panel_for_dialog, "Worker's data was removed");	
				mController.getCeoService().FireWorker(tf_fired_worker_id.getText());
			}
			else {
				JOptionPane.showMessageDialog(panel_for_dialog, "Wrong password!!");	
			}
		}
		
	}



}



