package fr.pizzeria.console;

import java.util.Scanner;

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

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		Stockage<Pizza, String> stockagePizza = new StockagePizzaFichier();
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
