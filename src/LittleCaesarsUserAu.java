import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.*;

public class LittleCaesarsUserAu implements WindowListener {
	static LittleCaesarsButtonAu lcba;
	
	public static void main (String[] args) {
		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setLayout(null);
		
		lcba = new LittleCaesarsButtonAu ();
		lcba.addComponents(panel);
		
		new LittleCaesarsUserAu (panel);
	}
	
	public LittleCaesarsUserAu (JPanel p) {
		JFrame frame = new JFrame ("Little Caesars");
		
		frame.setContentPane(p);
		frame.addWindowListener(this);
		frame.setSize(610,620);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setResizable(false);
	}
	
	public void windowOpened(WindowEvent e) {
	}

	public void windowClosing(WindowEvent e) {
		lcba.exitButton();
	}

	public void windowClosed(WindowEvent e) {
	}

	public void windowIconified(WindowEvent e) {
	}

	public void windowDeiconified(WindowEvent e) {
	}

	public void windowActivated(WindowEvent e) {
	}

	public void windowDeactivated(WindowEvent e) {
	}
}


