package fr.pizzeria.model;

public class Pizza {

	private static int NbPizza;

	private String code;
	private String nom;
	private double prix;
	private CategoriePizza catPizza;

	public CategoriePizza getCatPizza() {
		return catPizza;
	}

	public void setCatPizza(CategoriePizza catPizza) {
		this.catPizza = catPizza;
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
		this.catPizza = catPizza;
	}
	
	public String toString() {
		return this.getCode() + " " + this.getNom() + " " + this.getPrix() + " € " + this.getCatPizza().name();
	}

}
