package fr.pizzeria.ihm;

import java.util.Collection;

import fr.pizzeria.model.Pizza;

@Act
public class AfficherPizzaChereAction extends Action {

	public AfficherPizzaChereAction(IhmHelper helper) {
		super("Afficher la pizza la plus ch�re", helper);
	}

	public void execute() {
		System.out.println("**** Pizza la plus ch�re ****");
		Collection<Pizza> pizzas = this.helper.getStockagePizza().findAll();
		
		////////////////////////////////////////
		// utilisation des expressions lambda //
		////////////////////////////////////////
		Pizza pizza = pizzas.stream().sorted((p1, p2) -> Double.compare(p2.getPrix(), p1.getPrix())).findFirst().get();
		System.out.println(pizza.toString() + "\n");
	}

}
