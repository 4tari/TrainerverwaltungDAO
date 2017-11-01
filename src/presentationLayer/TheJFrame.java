package presentationLayer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;



public class TheJFrame {
	private static void createAndShowGUI() {
		// JFrame erzeugen
		JFrame frame = new JFrame("Manager");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 200);
		frame.setVisible(true);
		
		JPanel panelMain = new JPanel();
		frame.getContentPane().add(panelMain);
		
		JPanel panelForm = new JPanel(new GridBagLayout());
		panelMain.add(panelForm);
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;
		
		panelForm.add(new JLabel("Manager Name: "), c);
		c.gridy++;
		panelForm.add(new JLabel("Manager ID: "), c);
		c.gridy++;
		panelForm.add(new JLabel("Manager Age: "), c);
		
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 1;
		c.gridy = 0;
		
		panelForm.add(new JTextField(10), c);
		c.gridy++;
		panelForm.add(new JTextField(4), c);
		c.gridy++;
		panelForm.add(new JTextField(2), c);
		c.gridy++;
		
		c.anchor = GridBagConstraints.CENTER;
		c.gridx = 1;
		
		JButton save = new JButton("Speichern");
		frame.getContentPane().add(save, BorderLayout.PAGE_END);
	}

	public static void main(String[] bla) {
		// Diese Operation muss aufgrund Swings Threading-policy
		// durchgefuehrt werden
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}