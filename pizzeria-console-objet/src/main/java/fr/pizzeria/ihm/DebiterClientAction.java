package fr.pizzeria.ihm;

import fr.pizzeria.exception.DebitException;

@Act
public class DebiterClientAction extends Action {


	public DebiterClientAction(IhmHelper helper) {
		super("D�biter un compte client", helper);
	}
	
	@Override
	public void execute() {

		System.out.println("Vous voulez d�biter un compte client veuillez choisir lequel (ID)");
		int idClient = helper.getScanner().nextInt();
		System.out.println("Veuillez saisir le montant � d�biter");
		double montant = helper.getScanner().nextDouble();
		
		try {
			helper.getStockageClient().find(idClient).debiterCompte(montant);
		} catch (DebitException e) {
			System.err.println(e.getMessage() + "\nSolde courant : " + helper.getStockageClient().find(idClient).getSolde() + " �");
		}
	}	

}
