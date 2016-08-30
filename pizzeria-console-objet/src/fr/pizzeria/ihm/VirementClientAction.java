package fr.pizzeria.ihm;

import fr.pizzeria.exception.CreditException;
import fr.pizzeria.exception.DebitException;

public class VirementClientAction extends Action {


	public VirementClientAction(IhmHelper helper) {
		super("Virement entre deux comptes client", helper);
	}

	@Override
	public void execute() {

		System.out.println("Vous voulez faire un virement entre deux comptes client");
		// introduire le code du client à débiter
		System.out.println("Saisissez le code client à débiter");
		Integer codeDeb = helper.getScanner().nextInt();
		// introduire le code du client à créditer
		System.out.println("Saisissez le code client à créditer");
		Integer codeCre = helper.getScanner().nextInt();
		
		// saisi du montant à créditer
		System.out.println("Veuillez saisir le montant à virer.");
		double montant = helper.getScanner().nextDouble();
		
		// débiter le compte du client choisi par le montant saisi
		try {
			helper.getStockageClient().find(codeDeb).debiterCompte(montant);
			// créditer le compte du client choisi par le montant saisi
			try {
				helper.getStockageClient().find(codeCre).crediterCompte(montant);
				System.out.println("Virement effectué !");
			} catch (CreditException e1) {
				System.err.println(e1.getMessage() + "\nSolde courant : " + helper.getStockageClient().find(codeCre).getSolde() + " €");
				// si l'action créditer échoue, on recrédite le compte débiteur
				try {
					helper.getStockageClient().find(codeDeb).crediterCompte(montant);
				} catch (CreditException e2) {
					System.err.println(e2.getMessage());
				}
			}
		} catch (DebitException e3) {
			System.err.println(e3.getMessage() + "\nSolde courant : " + helper.getStockageClient().find(codeDeb).getSolde() + " €");
		}

	}

}
