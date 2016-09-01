package fr.pizzeria.model;

import fr.pizzeria.exception.DebitException;

public class Livreur extends AbstractPersonne implements CompteStat{
	
	private double montantDecouvertAutorise;
	
	public Livreur(int id, String nom, String prenom, double solde, double montantDecouvertAutorise) {
		super(id, nom, prenom, solde);
		this.montantDecouvertAutorise = montantDecouvertAutorise;
	}

	@Override
	public void debiterCompte(double montant) throws DebitException {
		double newSolde = this.solde - montant;
		if(newSolde < this.montantDecouvertAutorise) {
			throw new DebitException();
		}
		this.solde = newSolde;
	}

	@Override
	public String toString() {
		String detailLivreur = this.id + " => " + this.prenom + " " + this.nom + " (" + this.solde + " € / " + this.montantDecouvertAutorise + " € autorisé)";
		return detailLivreur;
	}

	public double getMontantDecouvertAutorise() {
		return montantDecouvertAutorise;
	}

	public void setMontantDecouvertAutorise(double montantDecouvertAutorise) {
		this.montantDecouvertAutorise = montantDecouvertAutorise;
	}

}
