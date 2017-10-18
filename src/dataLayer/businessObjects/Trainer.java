package dataLayer.businessObjects;

import businessObjects.ITrainer;

public class Trainer implements ITrainer{
	private int id;
	private String name;
	private int alter;
	private int erfahrung;
	
	Trainer(int id, String name, int alter, int erfahrung){
		this.id = id;
		this.name = name;
		this.alter = alter;
		this.erfahrung = erfahrung;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAlter() {
		return alter;
	}

	public void setAlter(int alter) {
		this.alter = alter;
	}

	public int getErfahrung() {
		return erfahrung;
	}

	public void setErfahrung(int erfahrung) {
		this.erfahrung = erfahrung;
		
	}
}
