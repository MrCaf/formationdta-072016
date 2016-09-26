package fr.pizzeria.admin.web;

import fr.pizzeria.model.Pizza;
import fr.pizzeria.service.Stockage;
import fr.pizzeria.service.StockagePizzaJPA;

public class PersistanceUtils {

	private static PersistanceUtils instance = new PersistanceUtils();
	private Stockage<Pizza, String> stockagePizza = new StockagePizzaJPA();
	
	// constructeur privé
	private PersistanceUtils() {
	}
	
	// accéder à l'instance depuis l'extérieur de la classe
	public static PersistanceUtils getInstance(){
		return instance;
	}
	
	public Stockage<Pizza, String> getStockagePizza() {
		return stockagePizza;
	}
	
}
