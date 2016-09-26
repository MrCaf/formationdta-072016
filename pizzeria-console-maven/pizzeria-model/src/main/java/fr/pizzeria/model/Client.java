package fr.pizzeria.model;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;

import fr.pizzeria.exception.CreditException;
import fr.pizzeria.exception.DebitException;

@Entity
@NamedQuery(name="client.findById", query="SELECT c FROM Client c WHERE c.id =:idC")
public class Client extends AbstractPersonne implements CompteStat{
	
	private String email;
	private String password;
	
	public Client(int id, String nom, String prenom, double solde, String email, String password) {
		super(id, nom, prenom, solde);
		this.email = email;
		this.password = password;
	}

	public Client() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
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
