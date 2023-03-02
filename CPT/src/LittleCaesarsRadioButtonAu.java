import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.*;

public class LittleCaesarsRadioButtonAu implements ActionListener{
	private JRadioButton [] rbSizes = new JRadioButton [4];
	protected ButtonGroup bgSizes;
	protected int sizeIndex;
	
	protected JLabel[] lblItemTotal = new JLabel[3], lblOrderTotal = new JLabel[4];
	private JLabel[] lblTitles = new JLabel [4];
	private JLabel lblFreeToppings, lblIcon, lblPaymentIcon;
	protected double[] itemTotal = {0.00,0.00,0.00};
	protected NumberFormat currency;
	protected String[] sizes = {"Small","Medium","Large","Party"};
	
	public LittleCaesarsRadioButtonAu() {
		currency = NumberFormat.getCurrencyInstance(Locale.TAIWAN);
		
		// creates label for free toppings
		lblFreeToppings = createLabel ("First three (3) toppings are free!",SwingConstants.CENTER,170,302,200,25);
		Font freeToppingFont = new Font ("Sans Serif", Font.PLAIN, 10);
		lblFreeToppings.setFont(freeToppingFont);
		
		// creates labels for icons
		lblIcon = createLabel ("", SwingConstants.CENTER, 30,30,516,97);
		lblPaymentIcon = createLabel ("", SwingConstants.CENTER, 40,380,80,166);
		lblIcon.setIcon(new ImageIcon ("LittleCaesarsLogo.png"));
		lblPaymentIcon.setIcon(new ImageIcon ("PaymentOptions.png"));
		
		bgSizes = new ButtonGroup();
		
		String[] checkoutTitle = {"SUBTOTAL:","DELIVERY FEE:","HST:","GRAND TOTAL:"};
		
		for (int i=0; i<4; i++) {
			// creates radio buttons and adds them to button group
			rbSizes[i] = createRadioButton(sizes[i]);
			bgSizes.add(rbSizes[i]);
			
			// creates labels to display subtotal, delivery fee, hst, and grand total
			lblTitles[i] = createLabel (checkoutTitle[i], SwingConstants.LEFT, 160,400+i*30, 110,25);
			lblOrderTotal[i] = createLabel ("", SwingConstants.CENTER, 280,400+i*30, 100,25);
			lblOrderTotal[i].setBorder(BorderFactory.createLineBorder(Color.black));
		}
		
		// creates labels to display item totals
		for (int i=0; i<3; i++) {
			lblItemTotal[i] = createLabel(currency.format(itemTotal[i]), SwingConstants.CENTER, 30+(i*170)+(i*i*20), 330, 100,25);
			lblItemTotal[i].setBorder(BorderFactory.createLineBorder(Color.black));
		}
	}
	
	public void addComponents(JPanel panel) {
		JPanel rbPanel = createPanel("SIZE: ",30,100);
		rbPanel.setLayout(new GridLayout(4,1));
		
		for (int i=0; i<4; i++) {
			rbPanel.add(rbSizes[i]);
			
			panel.add(lblTitles[i]);
			panel.add(lblOrderTotal[i]);
		}
		
		for (int i=0; i<3; i++) {
			panel.add(lblItemTotal[i]);
		}
		
		panel.add(rbPanel);
		panel.add(lblFreeToppings);
		panel.add(lblIcon);
		panel.add(lblPaymentIcon);
	}
	
	public void actionPerformed(ActionEvent a) {
		double[] prices = {7.99,8.99,9.99,10.99};

		// determines cost of pizza size, displays cost in label
		for (int i=0; i<4; i++) {
			if (a.getSource() == rbSizes[i]) {
				itemTotal[0] = prices[i];
				sizeIndex = i;
				break;
			}
		}
		lblItemTotal[0].setText(currency.format(itemTotal[0]));
	}
	
	public static JPanel createPanel (String title, int x, int w) {
		JPanel p = new JPanel();
		p.setBounds(x,150,w,150);
		p.setBackground(Color.white);
		p.setBorder(BorderFactory.createTitledBorder(title));
		
		return p;
	}
	
	public static JLabel createLabel(String total, int alignment, int x, int y, int w, int l) {
		JLabel lbl = new JLabel (total, alignment);
		lbl.setBounds(x,y,w,l);
		
		return lbl;
	}
	
	public JRadioButton createRadioButton (String text) {
		JRadioButton rb = new JRadioButton(text);
		rb.addActionListener(this);
		rb.setBackground(Color.white);
		
		return rb;
	}
}
