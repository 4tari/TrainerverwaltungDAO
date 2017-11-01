package dataLayer.dataAccessObjects.sqlite;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import businessObjects.ITrainer;
import dataLayer.businessObjects.Trainer;
import dataLayer.dataAccessObjects.ITrainerDao;

public class TrainerDaoSqlite implements ITrainerDao {

	DataLayerSqlite connection;
	
	public TrainerDaoSqlite() {
		connection = new DataLayerSqlite();
		
	}
	
	public ITrainer create() {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(ITrainer trainer) {
		// TODO Auto-generated method stub
		
	}

	public ITrainer first() {
		// TODO Auto-generated method stub
		

		Statement stmt;
		try {
			connection = new DataLayerSqlite();
			stmt = connection.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String sql = "SELECT * FROM Trainers";
			ResultSet rs = stmt.executeQuery(sql);
			
			ArrayList<Trainer> trainers = new ArrayList<Trainer>();
			while(rs.next())
			{
				Trainer trainer = new Trainer(rs.getInt("id"), rs.getString("name"), rs.getInt("alter"), rs.getInt("erfahrung"));
				trainers.add(trainer);
				System.out.print(rs.getString("name"));
			}
//			return trainers;
			return null;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print("TEST");
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
