package fr.pizzeria.ihm;

import java.util.Collection;

import fr.pizzeria.model.Client;

public class ListerClientAction extends Action {

	public ListerClientAction(IhmHelper helper) {
		super("Lister les clients", helper);
	}

	public void execute() {
		System.out.println("**** Liste de Clients ****");
		Collection<Client> clients = this.helper.getStockageClient().findAll();
		
		////////////////////////////////////////
		// utilisation des expressions lambda //
		////////////////////////////////////////
		clients.forEach(System.out::println);
		/*
		for (Client clientEnCours : clients) {
			System.out.println(clientEnCours.toString());
		}*/
		System.out.println("\n");
	}

}
