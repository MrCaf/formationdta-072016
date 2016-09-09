package fr.pizzeria.ihm;

import fr.pizzeria.model.Client;

@Act
public class AjouterClientAction extends Action {


	public AjouterClientAction(IhmHelper helper) {
		super("Ajouter un compte client", helper);
	}

	@Override
	public void execute() {

		// récuperation de la saisie
		System.out.println("Vous voulez ajoutez un client");
		System.out.println("Veuillez saisir l'identifiant");
		int id = helper.getScanner().nextInt();
		System.out.println("Veuillez saisir le nom");
		String nom = helper.getScanner().next();
		System.out.println("Veuillez saisir le prénom");
		String prenom = helper.getScanner().next();
		System.out.println("Veuillez saisir le solde");
		double solde = helper.getScanner().nextDouble();
		System.out.println("Veuillez saisir l'email");
		String email = helper.getScanner().next();
		System.out.println("Veuillez saisir le mot de passe");
		String password = helper.getScanner().next();
		// creation du nouveau client
		Client nouveauClient = new Client(id, nom, prenom, solde, email, password);
		helper.getStockageClient().save(nouveauClient);

		System.out.println("Client ajouté avec succes" + "\n");

	}

}
