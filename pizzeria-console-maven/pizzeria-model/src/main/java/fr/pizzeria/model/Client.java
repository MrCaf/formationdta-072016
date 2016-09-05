package fr.pizzeria.model;

import fr.pizzeria.exception.CreditException;
import fr.pizzeria.exception.DebitException;

public class Client extends AbstractPersonne implements CompteStat{
	
	public Client(int id, String nom, String prenom, double solde) {
		super(id, nom, prenom, solde);
	}

	@Override
	public void crediterCompte(double montant) throws CreditException {
		double newSolde = this.solde + montant;
		if(newSolde > 5000) {
			throw new CreditException();
		}
		this.solde = newSolde;
	}

	@Override
	public void debiterCompte(double montant) throws DebitException {
		double newSolde = this.solde - montant;
		if(newSolde < 0) {
			throw new DebitException();
		}
		this.solde = newSolde;
	}
	
}
