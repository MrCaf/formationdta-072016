package fr.pizzeria.console;

import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;

import fr.pizzeria.ihm.IhmHelper;
import fr.pizzeria.ihm.Menu;
import fr.pizzeria.model.Client;
import fr.pizzeria.model.Livreur;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.service.Stockage;
import fr.pizzeria.service.StockageClient;
import fr.pizzeria.service.StockageLivreur;
import fr.pizzeria.service.StockagePizzaFichier;

public class PizzeriaAdminConsoleApp {

	public static void main(String[] args) throws Exception {

		// suppression de l'affichage des logs hibernate
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
		
		Scanner scanner = new Scanner(System.in);
		
		// lecture des properties
		ResourceBundle bundle = ResourceBundle.getBundle("application");
		String classeStockagePizza = bundle.getString("stockage.pizza");
		System.out.println(classeStockagePizza);
		Class<?> classePizza = Class.forName(classeStockagePizza);

		Stockage<Pizza, String> stockagePizza = (Stockage<Pizza, String>) classePizza.newInstance();
		//Stockage<Pizza, String> stockagePizza = new StockageTableau();
		Stockage<Client, Integer> stockageClient = new StockageClient();
		Stockage<Livreur, Integer> stockageLivreur = new StockageLivreur();

		IhmHelper helper = new IhmHelper(stockagePizza, stockageClient, stockageLivreur, scanner);

		// Afficher le Menu

		Menu listMenu = new Menu(helper);
		
		listMenu.start();

		scanner.close();

	}

}
