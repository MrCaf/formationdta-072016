package fr.pizzeria.model;

import javax.persistence.Entity;

import fr.pizzeria.exception.CreditException;
import fr.pizzeria.exception.DebitException;

@Entity
public class Client extends AbstractPersonne implements CompteStat{
	
	private String email;
	private String password;
	
	public Client(int id, String nom, String prenom, double solde, String email, String password) {
		super(id, nom, prenom, solde);
		this.email = email;
		this.password = password;
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
