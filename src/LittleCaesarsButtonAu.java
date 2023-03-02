import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LittleCaesarsButtonAu extends LittleCaesarsComboBoxesAu implements ActionListener{
	private JButton[] btn = new JButton[4];
	private double subtotal, deliveryFee, HST, grandTotal;
	
	private JButton btnEnterPromo;
	private JTextField txtPromo;
	private JLabel lblPromoSuccess;
	private boolean promoCodeUsed = false;
	private double discount;
	private final double discountRate = 0.1;
	private final String classPromoCode = "ICS 4U1", myPromoCode = "Andrew";
	
	public LittleCaesarsButtonAu() {
		super();
		
		String[] btnTitle = {"CALCULATE","CLEAR","CHECKOUT","EXIT"};
		
		for (int i=0; i<4; i++) {
			btn[i] = createButton(btnTitle[i], 440, 390+(i*40),120,28);
		}
		
		// creates textfield, button, and label for promo code feature
		txtPromo = new JTextField("Enter promo code");
		txtPromo.setBounds(170,540,120,20);
		btnEnterPromo = createButton("Enter", 300,540,80,20);
		lblPromoSuccess = createLabel("",JLabel.CENTER,100,560,300,20);
	}
	
	public void addComponents (JPanel panel) {
		super.addComponents(panel);
		
		for (int i=0; i<4; i++) {
			panel.add(btn[i]);
		}
		
		panel.add(txtPromo);
		panel.add(lblPromoSuccess);
		panel.add(btnEnterPromo);
	}
	
	public void actionPerformed(ActionEvent a) {
		if (a.getSource() instanceof JButton) {
			if (a.getSource() == btn[1]) {
				clearButton();
			}	
			else if (a.getSource() == btn[3]) {
				exitButton();
			}
			else if (a.getSource() == btnEnterPromo) {
				promoButton();
			}
			// if pizza size is not selected when calculate or checkout button is selected, show error message
			else if (bgSizes.isSelected(null)) {
				JOptionPane.showMessageDialog(null, "Your order could not be completed!\nPlease select a pizza size.",
						"Critical error!", JOptionPane.ERROR_MESSAGE);
			}
			else {
				calculateButton();
				if (a.getSource() == btn[2]) {
					checkoutButton();
				}
			}
		}
		
		else {
			super.actionPerformed(a);
		}
	}
	
	public void calculateButton() {
		subtotal=0;
		for (int i=0; i<3; i++) {
			subtotal += itemTotal[i];
		}
		
		// if promo code is applied, 10% is taken off from subtotal
		if (promoCodeUsed) {
			discount = subtotal * discountRate;
			subtotal -= discount;
		}
		
		// determines delivery fee and displays it in a label
		if (subtotal >= 15) {
			deliveryFee = 0;
			lblOrderTotal[1].setOpaque(true);
			lblOrderTotal[1].setBackground(Color.green);
			lblOrderTotal[1].setText("FREE");
		}
		else {
			deliveryFee = 3;
			lblOrderTotal[1].setBackground(Color.white);
			lblOrderTotal[1].setText(currency.format(deliveryFee));
		}
		
		// calculates HST and grandTotal
		HST = subtotal*0.13;
		grandTotal = HST + subtotal + deliveryFee;
		
		lblOrderTotal[0].setText(currency.format(subtotal));
		lblOrderTotal[2].setText(currency.format(HST));
		lblOrderTotal[3].setText(currency.format(grandTotal));
	}

	public void clearButton() {
		// clears checkboxes
		for (int i=0; i<8; i++) {
			cbTopping[i].setSelected(false);
		}
		numOfToppings = 0;
		
		// clears combo boxes
		for (int i=0; i<4; i++) {
			cboBeverages[i].setSelectedIndex(0);
		}
		
		// clears radio buttons
		bgSizes.clearSelection();
		
		// clears labels for item totals
		for (int i=0; i<3; i++) {
			itemTotal[i] = 0;
			lblItemTotal[i].setText(currency.format(itemTotal[i]));
		}
		
		// clears labels for checkout totals
		for (int i=0; i<4; i++) {
			lblOrderTotal[i].setText("");
		}
		lblOrderTotal[1].setBackground(Color.white);
		
	}
	
	public void checkoutButton() {
		int checkout;
		
		JTextArea textAreaCheckout = createTextArea();
		
		// displays receipt with order information
		checkout = JOptionPane.showConfirmDialog(null, new JScrollPane(textAreaCheckout), 
				"Little Caesars", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		// displays thank you message
		if (checkout == 0) {
			JOptionPane.showMessageDialog(null, "Thank you for ordering from Little Caesars!\n"
					+ "Your pizza will be delivered in 30 minutes or it's free!", "Little Caesars",
					JOptionPane.INFORMATION_MESSAGE, new ImageIcon ("LittleCaesarsIcon.png"));
			System.exit(0);
		}
	}
	
	public void exitButton () {
		int exit;
		
		exit = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Little Caesars",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (exit == 0) {
			JOptionPane.showMessageDialog(null, "Thank you for choosing Little Caesars!", "Little Caesars",
					JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
	}
	
	public void promoButton() {
		String promoCode;
		promoCode =  new String(txtPromo.getText());
		
		if (!promoCodeUsed) {
			
			// determines if input matches promo code, then displays message in label
			if (promoCode.equals(classPromoCode) || promoCode.equals(myPromoCode)) {
				lblPromoSuccess.setText("Success! Your order is "+(int)(discountRate*100)+"% off!");
				promoCodeUsed = true;
			}
			else {
				lblPromoSuccess.setText("Invalid promo code");
			}
			
		}
		else {
			lblPromoSuccess.setText("Sorry! Only one promo code can be used per order.");
		}
	}
	
	public JButton createButton (String title, int x, int y, int w, int l) {
		JButton btn = new JButton (title);
		btn.setBounds(x,y,w,l);
		btn.addActionListener(this);
		
		return btn;
	}
	
	public JTextArea createTextArea () {
		int counter = 0;
		
		JTextArea textArea = new JTextArea(15,20);
		Font font = new Font("Sans Serif",Font.BOLD,12);
		textArea.setFont(font);
		textArea.setEditable(false);
		
		String output = "****************CHECKOUT****************\nSIZE\n";
		output += sizes[sizeIndex] + "\t\t" + currency.format(itemTotal[0]) + "\n";
		
		output += "\nTOPPINGS\n";
		for (int i=0; i<8; i++) {
			if (cbTopping[i].isSelected()) {
				output += toppings[i] + "\t\t" + currency.format(price[counter]) + "\n";
				counter++;
			}
		}
		
		output += "\nBEVERAGES\n";
		for (int i=0; i<4; i++) {
			if (beverageIndex[i] != 0) {
				output += beverageName[i] + " ("+beverageIndex[i]+")\t\t" + currency.format(beverageIndex[i]*0.99);
				output += "\n";
			}
		}
		
		if (promoCodeUsed) {
			output += "\nPROMO CODE\t\t"+currency.format(-(discount))+"\n";
		}
		
		output += "\nSubtotal: \t\t"+currency.format(subtotal)
				+"\nHST: \t\t"+currency.format(HST)
				+"\nDelivery Fee: \t\t"+currency.format(deliveryFee)
				+"\nTotal: \t\t"+currency.format(grandTotal);
		textArea.setText(output);
		
		return textArea;
	}
}
