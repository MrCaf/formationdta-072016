package fr.pizzeria.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

@Entity
public class Pizza {

	private static int NbPizza;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="reference")
	private String code;
	@Column(name="libelle")
	private String nom;
	private double prix;
	@Enumerated(EnumType.STRING)
	private CategoriePizza categorie;

	public Pizza() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CategoriePizza getCatPizza() {
		return categorie;
	}

	public void setCatPizza(CategoriePizza catPizza) {
		this.categorie = catPizza;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public Pizza(String code, String nom, double prix, CategoriePizza catPizza) {
		super();
		this.code = code;
		this.nom = nom;
		this.prix = prix;
		this.categorie = catPizza;
	}
	
	public String toString() {
		return StringUtils.rightPad(this.getCode(), 5)  + "\t" + StringUtils.rightPad(this.getNom(),16) + "\t\t" + this.getPrix() + " �\t" + this.getCatPizza().name();
	}

}
