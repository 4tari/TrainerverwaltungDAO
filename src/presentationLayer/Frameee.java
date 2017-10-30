package presentationLayer;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.JSplitPane;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.JLabel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Frameee extends JFrame {

	Connection con;
	Statement stmt;
	ResultSet rs;
	int currRow = 0;

	private JPanel contentPane;
	private JTextField textID;
	private JTextField textName;
	private JTextField textAlter;
	private JTextField textErfahrung;

	/**
	 * Ad Connect method - Example
	 */
	/**
	 * Create the frame.
	 */
	public Frameee() {
		initComponents();
		DoConnect();
	}

	public void DoConnect() {
		try {
			// CONNECT TO DATABASE
			String host = "jdbc:derby://localhost:1527/Trainers";
			String uName = "admin";
			String uPass = "admin";
			con = DriverManager.getConnection(host, uName, uPass);

			// EXECUTE SOME SQL AND LOAD THE RECORDS INTO THE RESULTSET
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String sql = "SELECT * FROM Trainers";
			rs = stmt.executeQuery(sql);

			// MOVE THE CURSOR THE FIRST RECORD AND GET THE DATA
			rs.next();
			int id_col = rs.getInt("ID");
			String id = Integer.toString(id_col);
			String name = rs.getString("Name");
			int alter_col = rs.getInt("Alter");
			String alter = Integer.toString(alter_col);
			int erfahrung_col = rs.getInt("Erfahrung");
			String erfahrung = Integer.toString(erfahrung_col);

			// DISPLAY THE FIRST RECORD IN THE TEXT FIELDS
			textID.setText(id);
			textName.setText(name);
			textAlter.setText(alter);
			textErfahrung.setText(erfahrung);

		}
		// Exception einbauen!!!
		catch (exceptions.NoTrainerFoundException err) {
			JOptionPane.showMessageDialog(Frameee.this, err.getMessage);
		}

	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frameee frame = new Frameee();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 738, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textID = new JTextField();
		textID.setBounds(111, 62, 53, 20);
		contentPane.add(textID);
		textID.setColumns(10);

		textName = new JTextField();
		textName.setBounds(174, 62, 238, 20);
		contentPane.add(textName);

		textAlter = new JTextField();
		textAlter.setBounds(422, 62, 87, 20);
		contentPane.add(textAlter);
		textAlter.setColumns(10);

		textErfahrung = new JTextField();
		textErfahrung.setBounds(519, 62, 118, 20);
		contentPane.add(textErfahrung);
		textErfahrung.setColumns(10);

		JLabel jLabel = new JLabel("ID:");
		jLabel.setBounds(111, 34, 87, 34);
		contentPane.add(jLabel);

		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(174, 34, 87, 34);
		contentPane.add(lblName);

		JLabel lblAlter = new JLabel("Alter:");
		lblAlter.setBounds(422, 34, 87, 34);
		contentPane.add(lblAlter);

		JLabel lblErfahrung = new JLabel("Erfahrung:");
		lblErfahrung.setBounds(519, 34, 87, 34);
		contentPane.add(lblErfahrung);

		final JButton btnFirst = new JButton("First");
		btnFirst.setBounds(111, 135, 89, 23);

		final JButton btnPrevious = new JButton("Previous");
		btnPrevious.setBounds(210, 135, 89, 23);

		final JButton btnNext = new JButton("Next");
		btnNext.setBounds(442, 135, 89, 23);

		final JButton btnLast = new JButton("Last");
		btnLast.setBounds(548, 135, 89, 23);

		final JButton btnUpdateRecord = new JButton("Update Record");
		btnUpdateRecord.setBounds(155, 169, 125, 23);

		final JButton btnNewRecord = new JButton("New Record");
		btnNewRecord.setBounds(469, 169, 118, 23);

		final JButton btnDelete = new JButton("Delete Record");
		btnDelete.setBounds(319, 169, 117, 23);

		final JButton btnCancelNewRecord = new JButton("Cancel New Record");
		btnCancelNewRecord.setEnabled(false);
		btnCancelNewRecord.setBounds(383, 203, 136, 23);

		final JButton btnSaveRecord = new JButton("Save New Record");
		btnSaveRecord.setEnabled(false);
		btnSaveRecord.setBounds(235, 203, 138, 23);

		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					rs.first();
					int id_col = rs.getInt("ID");
					String id = Integer.toString(id_col);
					String name = rs.getString("Name");
					int alter_col = rs.getInt("Alter");
					String alter = Integer.toString(alter_col);
					int erfahrung_col = rs.getInt("Erfahrung");
					String erfahrung = Integer.toString(erfahrung_col);

					textID.setText(id);
					textName.setText(name);
					textAlter.setText(alter);
					textErfahrung.setText(erfahrung);
				} /**
					 * Exception einbauen!!! catch (exceptions.NoTrainerFoundException err) {
					 * JOptionPane.showMessageDialog(Frameee.this, err.getMessage()); }
					 */
				// Auto
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnFirst);

		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.previous()) {
						int id_col = rs.getInt("ID");
						String id = Integer.toString(id_col);
						String name = rs.getString("Name");
						int alter_col = rs.getInt("Alter");
						String alter = Integer.toString(alter_col);
						int erfahrung_col = rs.getInt("Erfahrung");
						String erfahrung = Integer.toString(erfahrung_col);

						textID.setText(id);
						textName.setText(name);
						textAlter.setText(alter);
						textErfahrung.setText(erfahrung);

					} else {
						rs.next();
						JOptionPane.showMessageDialog(Frameee.this, "End of File");
					}
				} /**
					 * //Exception einbauen!!! catch (exceptions.NoPreviousTrainerFoundException
					 * err) { JOptionPane.showMessageDialog(Frameee.this, err.getMessage()); }
					 */

				catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnPrevious);

		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (rs.next()) {
						int id_col = rs.getInt("ID");
						String id = Integer.toString(id_col);
						String name = rs.getString("Name");
						int alter_col = rs.getInt("Alter");
						String alter = Integer.toString(alter_col);
						int erfahrung_col = rs.getInt("Erfahrung");
						String erfahrung = Integer.toString(erfahrung_col);

						textID.setText(id);
						textName.setText(name);
						textAlter.setText(alter);
						textErfahrung.setText(erfahrung);

					} else {
						rs.previous();
						JOptionPane.showMessageDialog(Frameee.this, "End of File");
					}
				} /**
					 * //Exception einbauen!!! catch (exceptions.NoNextTrainerFoundException err) {
					 * JOptionPane.showMessageDialog(Frameee.this, err.getMessage()); }
					 */
				catch (HeadlessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		contentPane.add(btnNext);

		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					rs.last();
					int id_col = rs.getInt("ID");
					String id = Integer.toString(id_col);
					String name = rs.getString("Name");
					int alter_col = rs.getInt("Alter");
					String alter = Integer.toString(alter_col);
					int erfahrung_col = rs.getInt("Erfahrung");
					String erfahrung = Integer.toString(erfahrung_col);

					textID.setText(id);
					textName.setText(name);
					textAlter.setText(alter);
					textErfahrung.setText(erfahrung);
				} /**
					 * //Exception einbauen!!! catch (exceptions.NoTrainerFoundException err) {
					 * JOptionPane.showMessageDialog(Frameee.this, err.getMessage()); }
					 */
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnLast);

		btnUpdateRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textName.getText();
				String alter = textAlter.getText();
				String erfahrung = textErfahrung.getText();
				String id = textID.getText();

				int newAlter = Integer.parseInt(alter);
				int newErfahrung = Integer.parseInt(erfahrung);

				try {
					rs.updateString("Name", name);
					rs.updateInt("Alter", newAlter);
					rs.updateInt("Erfahrung", newErfahrung);
					rs.updateRow();
					JOptionPane.showMessageDialog(Frameee.this, "Updated");
				}

				catch (SQLException err) {
					System.out.println(err.getMessage());
				}

			}
		});
		contentPane.add(btnUpdateRecord);

		btnNewRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnFirst.setEnabled(false);
				btnPrevious.setEnabled(false);
				btnNext.setEnabled(false);
				btnLast.setEnabled(false);
				btnUpdateRecord.setEnabled(false);
				btnDelete.setEnabled(false);
				btnNewRecord.setEnabled(false);

				btnSaveRecord.setEnabled(true);
				btnCancelNewRecord.setEnabled(true);
			}
		});
		contentPane.add(btnNewRecord);

		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(btnDelete);

		btnCancelNewRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnFirst.setEnabled(true);
				btnPrevious.setEnabled(true);
				btnNext.setEnabled(true);
				btnLast.setEnabled(true);
				btnUpdateRecord.setEnabled(true);
				btnDelete.setEnabled(true);
				btnNewRecord.setEnabled(true);

				btnSaveRecord.setEnabled(false);
				btnCancelNewRecord.setEnabled(false);
			}
		});
		contentPane.add(btnCancelNewRecord);

		btnSaveRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(btnSaveRecord);
	}
}
