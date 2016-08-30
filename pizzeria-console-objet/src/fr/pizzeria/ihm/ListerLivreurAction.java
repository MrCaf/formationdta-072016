package fr.pizzeria.ihm;

import java.util.Collection;

import fr.pizzeria.model.Livreur;

public class ListerLivreurAction extends Action {

	public ListerLivreurAction(IhmHelper helper) {
		super("Lister les livreurs", helper);
	}

	public void execute() {
		System.out.println("**** Liste de Livreurs ****");
		Collection<Livreur> livreurs = this.helper.getStockageLivreur().findAll();
		for (Livreur livreurEnCours : livreurs) {
			System.out.println(livreurEnCours.toString());
		}
		System.out.println("\n");
	}

}
