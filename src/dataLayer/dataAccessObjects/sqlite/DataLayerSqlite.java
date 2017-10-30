package dataLayer.dataAcessObjects.sqlite;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dataLayer.dataAcessObjects.ITrainerDao;
import dataLayer.IDataLayer;
import dataLayer.businessObjects.Trainer;


public class DataLayerSqlite implements IDataLayer
 {
	public Connection conn = null;
	
	public DataLayerSqlite() {

        try {
            // db parameters
            String url = "jdbc:sqlite:sample.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            

           // connection = DriverManager.getConnection("jdbc:sqlite:sample.db");

            Statement statement = conn.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.


//            statement.executeUpdate("DROP TABLE IF EXISTS person");
//            statement.executeUpdate("CREATE TABLE person ('id' INTEGER PRIMARY KEY, 'name' STRING, 'alter' INTEGER, 'erfahrung' INTEGER)");
            
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
		// TODO Auto-generated method stub

		Statement stmt;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String sql = "SELECT * FROM Trainers";
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		String sql = "SELECT * FROM Trainers";
//		rs = conn.executeQuery(sql);
		
		return null;
	}
	
	
	//public static void main(String[] args) throws ClassNotFoundException
	//{
	//}
}
