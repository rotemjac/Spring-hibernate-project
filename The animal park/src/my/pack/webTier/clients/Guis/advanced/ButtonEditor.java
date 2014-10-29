package my.pack.webTier.clients.Guis.advanced;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import my.pack.webTier.controlers.Mock_Controller;

public class ButtonEditor extends DefaultCellEditor implements ActionListener {
	
	Mock_Controller mController;
	
	protected JButton button;
	private String label;
	private String operation;
	private boolean isPushed;

	public ButtonEditor(JCheckBox checkBox,String operation) {
		super(checkBox);
		mController = Mock_Controller.getInstance();
		this.operation=operation;
		button = new JButton();
		button.setOpaque(true);
		button.addActionListener(this);

	}

	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {

		if (isSelected) {
			button.setForeground(table.getSelectionForeground());
			button.setBackground(table.getSelectionBackground());
		} 

		else {
			button.setForeground(table.getForeground());
			button.setBackground(table.getBackground());
		}

		label = (value == null) ? "" : value.toString();
		button.setText(label);
		isPushed = true;
		return button;
	}

	public Object getCellEditorValue() {
		if (isPushed) {
			JOptionPane.showMessageDialog(button, "All problems were fixed?");
		}

		isPushed = false;
		return new String(label);
	}

	public boolean stopCellEditing() {
		isPushed = false;
		return super.stopCellEditing();
	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		super.fireEditingStopped();
		if (operation=="Fixing problems in lp") {
		int space_location=e.getActionCommand().indexOf(":");
		String lp_id=e.getActionCommand().substring(space_location+2);	

		mController.getTl_janitor_Service().RemoveProblemsForLandPortion(lp_id);
		}
		
		if (operation=="Get animals that need check in area") {
			int space_location=e.getActionCommand().indexOf(" ");
			String animal_id=e.getActionCommand().substring(0,space_location);
			mController.getCaregiverService().MarkAnimalThatNeedVetCheck(animal_id, 'N');
		}
		

	}
}