import javax.swing.*;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;

public class LittleCaesarsCheckBoxesAu extends LittleCaesarsRadioButtonAu implements ActionListener{
	protected JCheckBox[] cbTopping = new JCheckBox[8];	
	protected int numOfToppings = 0;
	protected final int[] price = {0,0,0,1,1,1,1,1};
	protected final String[] toppings = {"Mushrooms", "Pepperoni","Green Peppers","Bacon","Onions",
			"Tomatoes","Hot Peppers","Extra Cheese"};
	
	public LittleCaesarsCheckBoxesAu() {
		super();
		
		// creates checkboxes for toppings
		for (int i=0; i<8; i++) {
			cbTopping[i] = createCheckBox (toppings[i], 175+20*i);
		}
	}
	
	public void addComponents(JPanel panel) {
		super.addComponents(panel);
		
		JPanel cbPanel = createPanel("TOPPINGS:",150,250);
		cbPanel.setLayout(new GridLayout(4,2));
		
		for (int i=0; i<8; i++) {
			cbPanel.add(cbTopping[i]);
		}
		panel.add(cbPanel);
	}
	
	public void actionPerformed(ActionEvent a) {
		if (a.getSource() instanceof JRadioButton) {
			super.actionPerformed(a);
		}
		
		else {
			// adjusts total for toppings, displays new total in label
			if (((AbstractButton) a.getSource()).isSelected()) {
				itemTotal[1] += price[numOfToppings];
				numOfToppings++;
			}
			else {
				numOfToppings--;
				itemTotal[1] -= price[numOfToppings];
			}
			
			lblItemTotal[1].setText(currency.format(itemTotal[1]));
		}
	}
	
	public JCheckBox createCheckBox (String title, int y) {
		JCheckBox cb = new JCheckBox(title);
		cb.setBounds(300,y,350,25);
		cb.addActionListener(this);
		cb.setBackground(Color.white);
		
		return cb;
	}
}
