package dataLayer.dataAccessObjects.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dataLayer.dataAccessObjects.ITrainerDao;
import dataLayer.IDataLayer;
import dataLayer.businessObjects.Trainer;


public class DataLayerSqlite implements IDataLayer
 {
	public Connection conn = null;
	
	public DataLayerSqlite() {

        try {
            String url = "jdbc:sqlite:trainer.db";
            conn = DriverManager.getConnection(url);


            Statement statement = conn.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.


//            statement.executeUpdate("DROP TABLE IF EXISTS Trainers");
//            statement.executeUpdate("CREATE TABLE Trainers ('id' INTEGER PRIMARY KEY, 'name' STRING, 'alter' INTEGER, 'erfahrung' INTEGER)");
            
            System.out.println("Connection to SQLite has been established.");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
	}
	
	

	public ITrainerDao getTrainerDao() {
		Statement stmt;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String sql = "SELECT * FROM trainer";
			ResultSet rs = stmt.executeQuery(sql);
			
			ArrayList<Trainer> trainers = new ArrayList<Trainer>();
			while(rs.next())
			{
				Trainer trainer = new Trainer(rs.getInt("id"), rs.getString("name"), rs.getInt("alter"), rs.getInt("erfahrung"));
				trainers.add(trainer);
			}
//			return trainers;
			return null;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		return null;
	}
	
}
