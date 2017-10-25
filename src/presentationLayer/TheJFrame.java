package presentationLayer;
import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;

public class TheJFrame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("Test JFrame");
		frame.setSize(800,600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel text = new JLabel("get Content for JFrame");
		frame.add(text);

	}

}

