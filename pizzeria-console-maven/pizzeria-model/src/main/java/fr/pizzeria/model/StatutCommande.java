package fr.pizzeria.model;

public enum StatutCommande {
	ENCOURS("En cours"),
	LIVREE("Livr�e");
	
	private String name = "";
	
	private StatutCommande(String name) {
		this.name = name;
	}
	/*
	public String toString() {
		return name;
	}*/
}
