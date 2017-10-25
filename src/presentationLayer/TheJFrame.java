package presentationLayer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.BorderLayout;

public class TheJFrame {
	private static void createAndShowGUI() {
		// JFrame erzeugen
		JFrame frame = new JFrame("Fu�ballmanager");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		// frame.setSize(800,600);

		// Buttons erzeugen
		JLabel pageStart = new JLabel("Manager Anlegen");
		JLabel managerName = new JLabel("Insert Name:");
		JLabel managerId = new JLabel("Insert Id:");
		JLabel managerAge = new JLabel("Insert Age:");
		JButton buttonSafe = new JButton("Speichern");

		// ContentPane haelt standardmae�ig ein BorderLayout
		// Hinzufuegen der Buttons zum Content Pane des JFrames
		frame.getContentPane().add(pageStart, BorderLayout.PAGE_START);
		frame.getContentPane().add(managerName, BorderLayout.CENTER);
		frame.getContentPane().add(managerId, BorderLayout.LINE_START);
		frame.getContentPane().add(managerAge, BorderLayout.AFTER_LAST_LINE);
		frame.getContentPane().add(buttonSafe, BorderLayout.PAGE_END);

		// Frame sichtbar machen
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] bla) {
		// Diese Operation muss aufgrund Swings Threading-policy
		// durchgef�hrt werden
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}