package fr.pizzeria.ihm;

import java.util.Collection;

import fr.pizzeria.model.Pizza;

@Act
public class ListerPizzaTriAction extends Action {

	public ListerPizzaTriAction(IhmHelper helper) {
		super("Lister les pizzas par cat�gorie", helper);
	}

	public void execute() {
		System.out.println("**** Liste de Pizzas par cat�gories ****");
		Collection<Pizza> pizzas = this.helper.getStockagePizza().findAll();
		
		////////////////////////////////////////
		// utilisation des expressions lambda //
		////////////////////////////////////////
		pizzas.stream().sorted((p1, p2) -> Integer.compare(p1.getCategorie().ordinal(), p2.getCategorie().ordinal())).forEach(System.out::println);
		System.out.println("\n");
	}

}
