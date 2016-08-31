package fr.pizzeria.ihm;

import java.util.Collection;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class ListerPizzaTriAction extends Action {

	public ListerPizzaTriAction(IhmHelper helper) {
		super("Lister les pizzas par catégorie", helper);
	}

	public void execute() {
		System.out.println("**** Liste de Pizzas par catégories ****");
		Collection<Pizza> pizzas = this.helper.getStockagePizza().findAll();
		
		////////////////////////////////////////
		// utilisation des expressions lambda //
		////////////////////////////////////////
		pizzas.stream().sorted((p1, p2) -> Integer.compare(p1.getCatPizza().ordinal(), p2.getCatPizza().ordinal())).forEach(System.out::println);
		System.out.println("\n");
	}

}
