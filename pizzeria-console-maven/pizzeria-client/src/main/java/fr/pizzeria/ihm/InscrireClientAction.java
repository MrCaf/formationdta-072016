package fr.pizzeria.ihm;

import fr.pizzeria.model.Client;

@ActConnexion
public class InscrireClientAction extends ActionClient {


	public InscrireClientAction(IhmHelperClient helper) {
		super("S'incrire", helper);
	}

	@Override
	public void execute() {

		// recuperation de la saisie
		System.out.println("Vous voulez vous inscrire");
		System.out.println("Veuillez saisir votre nom");
		String nom = helper.getScanner().next();
		System.out.println("Veuillez saisir votre prénom");
		String prenom = helper.getScanner().next();
		System.out.println("Veuillez saisir vote solde");
		double solde = helper.getScanner().nextDouble();
		System.out.println("Veuillez saisir votre email");
		String email = helper.getScanner().next();
		System.out.println("Veuillez saisir votre mot de passe");
		String password = helper.getScanner().next();
		// creation du nouveau client
		Client nouveauClient = new Client(0, nom, prenom, solde, email, password);
		helper.getStockageClient().save(nouveauClient);

		System.out.println("Client ajouté avec succes" + "\n");

	}

}
