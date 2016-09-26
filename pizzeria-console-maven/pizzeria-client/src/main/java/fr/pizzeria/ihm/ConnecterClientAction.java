package fr.pizzeria.ihm;

import org.apache.commons.codec.digest.DigestUtils;

import fr.pizzeria.model.Client;

@ActConnexion
public class ConnecterClientAction extends ActionClient {


	public ConnecterClientAction(IhmHelperClient helper) {
		super("Se connecter", helper);
	}

	@Override
	public void execute() {

		// recuperation de la saisie
		System.out.println("Vous voulez vous connecter");
		System.out.println("Veuillez saisir votre email");
		String email = helper.getScanner().next();
		System.out.println("Veuillez saisir votre mot de passe");
		String password = helper.getScanner().next();
		// authentification
		DigestUtils

		System.out.println("Client ajouté avec succes" + "\n");

	}

}
