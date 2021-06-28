package application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;

import javafx.scene.image.Image;

public class Student implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2000640760781455497L;
	private String nom;
	private String prenom;
	private String ddn;
	static final AtomicLong NEXT_ID = new AtomicLong(1);
	private long id;
	private Image photo;
	ArrayList<Double> notes = new ArrayList<Double>();
	Double moyenne;

	public Student(String nom, String prenom, String date, Image photo) {
		this.nom = nom;
		this.prenom = prenom;
		this.ddn = date;
		this.id = NEXT_ID.getAndIncrement();
		this.photo = photo;
	}

	public Double getMoyenne() {

		Double total = (double) 0;
		if (notes.isEmpty()) {
			return -1d;
		}
		Iterator<Double> iter = notes.iterator();

		while (iter.hasNext()) {
			total += iter.next();
		}
		return total / notes.size();
	}

	public Image getPhoto() {
		return photo;
	}

	public void setPhoto(Image photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return nom + " " + prenom + " " + ddn;
	}

	public ArrayList<Double> getNotes() {
		return notes;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getDdn() {
		return ddn;
	}

	public void setDdn(String ddn) {
		this.ddn = ddn;
	}

	public long getId() {
		return id;
	}
}
