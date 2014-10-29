package my.pack.webTier.clients.SimpleGuis;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;

public class Vet_setting_animals_food extends JPanel {
	
		//First row panel
		public JPanel panel_first_row;
		public JLabel category_lbl;
		private JLabel group_lbl;
		public JButton btnSetCategory;
		public JComboBox choose_category_cb;
		public JComboBox choose_group_cb;
		private JLabel food_lbl;
		public JTextField textField;
		public JButton btnSetFeature;
		private JLabel foodLabel;


		public Vet_setting_animals_food() {

			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			this.setPreferredSize(new Dimension(150, 170));
			
			///////First row panel/////////////
			panel_first_row = new JPanel();
			panel_first_row.setMaximumSize(new Dimension(160, 170));
			add(panel_first_row);
			panel_first_row.setLayout(new BoxLayout(panel_first_row, BoxLayout.Y_AXIS));

			category_lbl = new JLabel("Choose category: ");
			category_lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
			panel_first_row.add(category_lbl);
			
			choose_category_cb = new JComboBox();
			panel_first_row.add(choose_category_cb);
			choose_category_cb.setMaximumSize(new Dimension(190, 20));
			
			btnSetCategory = new JButton("Set Category");
			btnSetCategory.setAlignmentX(Component.CENTER_ALIGNMENT);
			panel_first_row.add(btnSetCategory);

			group_lbl = new JLabel("Choose group: ");
			group_lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
			panel_first_row.add(group_lbl);
			choose_group_cb = new JComboBox();
			panel_first_row.add(choose_group_cb);
			choose_group_cb.setMaximumSize(new Dimension(190, 20));
			
			foodLabel = new JLabel("Set Food: ");
			foodLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			panel_first_row.add(foodLabel);
			
						textField = new JTextField();
						panel_first_row.add(textField);
						textField.setColumns(10);
						textField.setMaximumSize(new Dimension(230, 20));
			
						btnSetFeature = new JButton("Save");
						btnSetFeature.setAlignmentX(Component.CENTER_ALIGNMENT);
						panel_first_row.add(btnSetFeature);
			///////////////////////////////////



		}


}
