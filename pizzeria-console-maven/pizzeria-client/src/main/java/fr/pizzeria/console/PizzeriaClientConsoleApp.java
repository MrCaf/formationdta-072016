package fr.pizzeria.console;

import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;

import fr.pizzeria.ihm.IhmHelperClient;
import fr.pizzeria.ihm.MenuClient;
import fr.pizzeria.ihm.MenuConnexion;
import fr.pizzeria.model.Client;
import fr.pizzeria.model.Livreur;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.service.Stockage;
import fr.pizzeria.service.StockageClient;
import fr.pizzeria.service.StockageLivreur;

public class PizzeriaClientConsoleApp {

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
		Stockage<Client, Integer> stockageClient = new StockageClient();
		Stockage<Livreur, Integer> stockageLivreur = new StockageLivreur();

		IhmHelperClient helper = new IhmHelperClient(stockagePizza, stockageClient, stockageLivreur, scanner);

		// Afficher le Menu

		MenuConnexion listMenuConnexion = new MenuConnexion(helper);
		MenuClient listMenuClient = new MenuClient(helper);
		
		listMenuConnexion.start();

		scanner.close();

	}

}
