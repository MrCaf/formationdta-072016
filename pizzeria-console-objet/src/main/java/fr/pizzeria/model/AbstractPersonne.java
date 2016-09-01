package fr.pizzeria.model;

import fr.pizzeria.exception.CreditException;
import fr.pizzeria.exception.DebitException;

public abstract class AbstractPersonne {
	
	protected int id;
	protected String nom;
	protected String prenom;
	protected double solde;
	
	public AbstractPersonne(int id, String nom, String prenom, double solde) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.solde = solde;
	}
	
	public void crediterCompte(double montant) throws CreditException {
		double newSolde = this.solde + montant;
		this.solde = newSolde;
	}
	
	public void debiterCompte(double montant) throws DebitException {
		double newSolde = this.solde - montant;
		this.solde = newSolde;
	}
	
	public String toString() {
		String detailPersonne = this.id + " => " + this.prenom + " " + this.nom + " (" + this.solde + " €)";
		return detailPersonne;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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
	
	public double getSolde() {
		return solde;
	}
	
	public void setSolde(double solde) {
		this.solde = solde;
	}

}
