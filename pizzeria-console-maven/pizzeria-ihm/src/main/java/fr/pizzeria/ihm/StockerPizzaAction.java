package fr.pizzeria.ihm;

import java.util.Collection;
import fr.pizzeria.model.Pizza;

@Act
public class StockerPizzaAction extends Action {

	public StockerPizzaAction(IhmHelper helper) {
		super("Exporter les pizzas", helper);
	}

	public void execute() {
		System.out.println("**** Export des Pizzas ****");
		Collection<Pizza> pizzas = this.helper.getStockagePizza().findAll();
		/*
		////////////////////////////////////////
		// utilisation des expressions lambda //
		////////////////////////////////////////
		pizzas.forEach(pizza->{
			try {
				File fichier = new File("src/data/" + pizza.getCode() + ".txt");
				fichier.createNewFile();
				FileWriter writer = new FileWriter(fichier);
				writer.write("(" + pizza.getNom() + ";" + pizza.getPrix() + ";" + pizza.getCatPizza() + ")");
				writer.close();
			} catch (IOException e) {
				System.err.println("Export rejeté pour " + pizza.getCode());
			}
		});*/
				
		
		System.out.println("\n");
	}

}
