import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

public class LittleCaesarsComboBoxesAu extends LittleCaesarsCheckBoxesAu implements ActionListener{
	protected JComboBox <Integer> [] cboBeverages = new JComboBox [4];;
	private JLabel[] lblBeverages = new JLabel[4];
	protected int[] beverageIndex = new int [4];
	protected String[] beverageName = {"Coke","Sprite","Orange","Root Beer"};
	
	public LittleCaesarsComboBoxesAu() {
		super();
		
		// creates combo boxes and labels for beverage names
		for (int i=0; i<4; i++) {
			cboBeverages[i] = createComboBox();
			lblBeverages[i] = createLabel(beverageName[i],JLabel.LEFT, 420, 180, 50,25);
		}
	}
	
	public void addComponents(JPanel panel) {
		super.addComponents(panel);
		
		GridLayout glComboBox = new GridLayout(4,2);
		glComboBox.setVgap(8);
		glComboBox.setHgap(20);
		
		JPanel cboPanel = createPanel("BEVERAGES:",420,160);
		cboPanel.setLayout(glComboBox);
		
		for (int i=0; i<4; i++) {
			cboPanel.add(lblBeverages[i]);
			cboPanel.add(cboBeverages[i]);
		}
		panel.add(cboPanel);
	}
	
	public void actionPerformed (ActionEvent a) {
		if (a.getSource() instanceof JComboBox) {
			
			// adjusts total for beverages, displays new total in label
			for (int i=0; i<4; i++) {
				if (a.getSource() == cboBeverages[i]) {
					itemTotal[2] += cboBeverages[i].getSelectedIndex()*0.99;
					itemTotal[2] -= beverageIndex[i]*0.99;
					beverageIndex[i] = cboBeverages[i].getSelectedIndex();
					
					break;
				}
			}
			lblItemTotal[2].setText(currency.format(itemTotal[2]));
			
		}
		
		else {
			super.actionPerformed(a);
		}
	}
	
	public JComboBox<Integer> createComboBox () {
		Integer[] num = {0,1,2,3,4,5,6};
		
		JComboBox<Integer> cbo = new JComboBox <Integer> (num);
		cbo.addActionListener(this);
		
		return cbo;
	}
}
