package fr.pizzeria.ihm;

import java.util.Collection;

import fr.pizzeria.exception.CreditException;
import fr.pizzeria.model.Client;

public class CrediterClientAction extends Action {
	

	public CrediterClientAction(IhmHelper helper) {
		super("Cr�diter un compte client", helper);
	}
	
	@Override
	public void execute() {

		System.out.println("Vous voulez cr�diter un compte client veuillez choisir lequel (ID)");
		int idClient = helper.getScanner().nextInt();
		System.out.println("Veuillez saisir le montant � cr�diter");
		double montant = helper.getScanner().nextDouble();
		
		try {
			helper.getStockageClient().find(idClient).crediterCompte(montant);
		} catch (CreditException e) {
			System.err.println(e.getMessage() + "\nSolde courant : " + helper.getStockageClient().find(idClient).getSolde() + " �");
		}
	}

}
