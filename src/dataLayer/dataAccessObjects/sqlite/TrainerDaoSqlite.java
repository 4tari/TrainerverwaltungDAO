package dataLayer.dataAccessObjects.sqlite;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import businessObjects.ITrainer;
import dataLayer.businessObjects.Trainer;
import dataLayer.dataAccessObjects.ITrainerDao;

public class TrainerDaoSqlite implements ITrainerDao {

	public Connection conn = null;

	private void doConnect() {

		String url = "jdbc:sqlite:trainer.db";
		try {
			conn = DriverManager.getConnection(url);
			Statement statement = conn.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			System.out.println("Connection to SQLite has been established.");
			// return conn;
		} catch (SQLException e) {
			e.printStackTrace();
			// return null;
		}
	}

	private void disconnect(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
				System.out.println("Disconnected from Database");
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public ITrainer create() {
		doConnect();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet result = stmt.getGeneratedKeys();
			result.next();
			int id = result.getInt("last_insert_rowid()");
			String sql = "SELECT * FROM trainer";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			Trainer trainer = new Trainer(id, "", 0, 0);
			System.out.println(rs.getString("name"));
			disconnect(conn);
			return trainer;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect(conn);
		System.out.print("create");
		return null;
	}

	public void delete(ITrainer trainer) {
		doConnect();
		try {
			int id = trainer.getId();
			String sql = "DELETE FROM trainer WHERE id = " + id;
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
			System.out.println("Deleted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ITrainer first() {
		doConnect();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM trainer WHERE id =(SELECT MIN(id) FROM trainer)";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			Trainer trainer = new Trainer(rs.getInt("id"), rs.getString("name"), rs.getInt("alter"),
					rs.getInt("erfahrung"));
			System.out.println(rs.getString("name"));
			disconnect(conn);
			return trainer;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect(conn);
		return null;

	}

	public ITrainer last() {
		// TODO Auto-generated method stub
		doConnect();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM trainer WHERE id =(SELECT MAX(id) FROM trainer)";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			Trainer trainer = new Trainer(rs.getInt("id"), rs.getString("name"), rs.getInt("alter"),
					rs.getInt("erfahrung"));
			System.out.println(rs.getString("name"));
			disconnect(conn);
			return trainer;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect(conn);
		return null;
	}

	public ITrainer next(ITrainer trainer) {
		doConnect();
		Statement stmt;
		int cur_id = trainer.getId();
		String sql = "SELECT * FROM trainer WHERE id =(SELECT MIN(id) FROM trainer WHERE id > " + cur_id + ")";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			Trainer nextTrainer = new Trainer(rs.getInt("id"), rs.getString("name"), rs.getInt("alter"),
					rs.getInt("erfahrung"));
			System.out.println(rs.getString("name"));
			disconnect(conn);
			return nextTrainer;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect(conn);
		return null;
	}

	public ITrainer previous(ITrainer trainer) {
		// TODO Auto-generated method stub
		doConnect();
		Statement stmt;
		int cur_id = trainer.getId();
		String sql = "SELECT * FROM trainer WHERE id =(SELECT MAX(id) FROM trainer WHERE id < " + cur_id + ")";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			Trainer prevTrainer = new Trainer(rs.getInt("id"), rs.getString("name"), rs.getInt("alter"),
					rs.getInt("erfahrung"));
			System.out.println(rs.getString("name"));
			disconnect(conn);
			return prevTrainer;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect(conn);
		return null;
	}

	public void save(ITrainer trainer) {
		int id = trainer.getId();
		String name = trainer.getName();
		int alter = trainer.getAlter();
		int erfahrung = trainer.getErfahrung();
		doConnect();
		String sql = "INSERT INTO trainer ('id', 'name', 'alter', 'erfahrung') VALUES (?, ?, ?, ?)";
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			statement.setString(2, name);
			statement.setInt(3, alter);
			statement.setInt(4, erfahrung);
			statement.executeUpdate();
			System.out.println("Update");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<ITrainer> select() {
		// TODO Auto-generated method stub
		return null;
	}

	public ITrainer select(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
