package dataLayer.dataAccessObjects.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import businessObjects.ITrainer;
import dataLayer.businessObjects.Trainer;
import dataLayer.dataAccessObjects.ITrainerDao;

public class TrainerDaoSqlite implements ITrainerDao {

	public Connection conn = null;
	private Connection doConnect(){
		
		 // db parameters
        String url = "jdbc:sqlite:trainer.db";
        // create a connection to the database
        try {
			conn = DriverManager.getConnection(url);
			Statement statement = conn.createStatement();
	        statement.setQueryTimeout(30);  // set timeout to 30 sec.
	        System.out.println("Connection to SQLite has been established.");
	        return conn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
//        statement.executeUpdate("DROP TABLE IF EXISTS person");
//        statement.executeUpdate("CREATE TABLE person ('id' INTEGER PRIMARY KEY, 'name' STRING, 'alter' INTEGER, 'erfahrung' INTEGER)");
        

	}
	
	private void disconnect(Connection conn){
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
		System.out.print("create");
		return null;
	}

	public void delete(ITrainer trainer) {
		// TODO Auto-generated method stub
		
	}

	public ITrainer first() {
		// TODO Auto-generated method stub
		
		doConnect();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM trainer WHERE id = 1";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			Trainer trainer = new Trainer(rs.getInt("id"), rs.getString("name"), rs.getInt("alter"), rs.getInt("erfahrung"));
			System.out.println(rs.getString("name"));
			disconnect(conn);
			return trainer;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		disconnect(conn);
		return null;

	}

	public ITrainer last() {
		// TODO Auto-generated method stub
		return null;
	}

	public ITrainer next(ITrainer trainer) {
		// TODO Auto-generated method stub
		return null;
	}

	public ITrainer previous(ITrainer trainer) {
		// TODO Auto-generated method stub
		return null;
	}

	public void save(ITrainer trainer) {
		// TODO Auto-generated method stub
		
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
