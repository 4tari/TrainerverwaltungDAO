package presentationLayer;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import businessObjects.ITrainer;
import dataLayer.dataAccessObjects.ITrainerDao;
import dataLayer.dataAccessObjects.sqlite.TrainerDaoSqlite;

import javax.swing.JLabel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Frameee extends JFrame {

	private static final long serialVersionUID = 1L;
	ITrainer trainer = null;
	Connection con;
	Statement stmt;
	ResultSet rs;
	int currRow = 0;

	private static ITrainerDao iTrainerDao;

	private JPanel contentPane;
	private JTextField textID;
	private JTextField textName;
	private JTextField textAlter;
	private JTextField textErfahrung;

	public Frameee() {
		initComponents();
		DoConnect();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frameee frame = new Frameee();
					frame.setVisible(true);
					iTrainerDao = new TrainerDaoSqlite();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void DoConnect(){
		Connection conn = null;
        String url = "jdbc:sqlite:sample.db";
        try {
			conn = DriverManager.getConnection(url);
	        Statement statement = conn.createStatement();
	        statement.setQueryTimeout(30);  // set timeout to 30 sec.
	        System.out.println("Connection to SQLite has been established.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
				trainer = iTrainerDao.first();
				displayTrainer(trainer);
			}
		});
		contentPane.add(btnFirst);

		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iTrainerDao.previous(trainer);
			}
		});
		contentPane.add(btnPrevious);

		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				iTrainerDao.next(trainer);
			}
		});

		contentPane.add(btnNext);

		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iTrainerDao.last();

			}
		});
		contentPane.add(btnLast);

		btnUpdateRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iTrainerDao.save(trainer);

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
				
				iTrainerDao.create();
			}
		});
		contentPane.add(btnNewRecord);

		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iTrainerDao.delete(trainer);
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
				iTrainerDao.delete(trainer);
			}
		});
		contentPane.add(btnCancelNewRecord);

		btnSaveRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iTrainerDao.save(trainer);
			}
			
		});
		contentPane.add(btnSaveRecord);
	}
	
	public void displayTrainer(ITrainer trainer) {
		int id_col = trainer.getId();
		String id = Integer.toString(id_col);
		String name = trainer.getName();
		int alter_col = trainer.getAlter();
		String alter = Integer.toString(alter_col);
		int erfahrung_col = trainer.getErfahrung();
		String erfahrung = Integer.toString(erfahrung_col);
		textID.setText(id);
		textName.setText(name);
		textAlter.setText(alter);
		textErfahrung.setText(erfahrung);
	}
}
