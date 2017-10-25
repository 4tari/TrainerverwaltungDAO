package dataLayer.dataAccessObjects;

import java.util.List;

import businessObjects.ITrainer;

public interface ITrainerDao {
	public ITrainer create();
	public void delete(ITrainer trainer);
	public ITrainer first();
	public ITrainer last();
	public ITrainer next(ITrainer trainer);
	public ITrainer previous(ITrainer trainer);
	public void save(ITrainer trainer);
	public List<ITrainer> select();
	public ITrainer select(int id);
}

