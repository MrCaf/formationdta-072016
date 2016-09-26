package fr.pizzeria.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import fr.pizzeria.exception.SaisieCodeException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class StockagePizzaFichier implements Stockage<Pizza, String> {

	public Map<String, Pizza> pizzas = new TreeMap<>();

	public StockagePizzaFichier() {
		findAll();
	}

	@Override
	public Collection<Pizza> findAll() {
		// chercher des pizzas depuis des fichiers
        try {
        	// on parcourt les fichiers
			Files.list(Paths.get("src", "data")).forEach(p -> {
				try {
					// pour chaque fichier, on parcourt les lignes
					Files.lines(p).forEach(l -> {
						// on d�coupe les lignes pour retrouver les informations et les enregistrer
						List<String> attr = Arrays.asList(l.split(";"));
						String code = p.getFileName().toString().substring(0, 3);
						pizzas.put(code, new Pizza(code, attr.get(0), Float.valueOf(attr.get(1)), CategoriePizza.valueOf(attr.get(2)), attr.get(3)));
					});
				} catch (IOException f) {
					System.err.println("Import rejet� pour " + p.getFileName());
				}
			});
		} catch (IOException e) {
			System.err.println("Import rejet�");
		}
		return pizzas.values();
	}

	@Override
	public Pizza find(String code) {
		return pizzas.get(code);
	}

	@Override
	public void save(Pizza newPizza) {
		this.pizzas.put(newPizza.getCode(), newPizza);
		// sauvegarder des pizzas dans des fichiers
		// g�n�ration du chemin
		Path cheminFichier = Paths.get("src","data",newPizza.getCode() + ".txt");
        try {
        	// cr�ation et �criture dans le fichier
        	Files.deleteIfExists(cheminFichier);
        	Files.createFile(cheminFichier);
			Files.write(cheminFichier, Arrays.asList(newPizza.getNom() + ";" + newPizza.getPrix() + ";" + newPizza.getCategorie()));
		} catch (IOException e) {
			System.err.println("Export rejet� pour " + newPizza.getCode());
		}
	}

	public void saisirCode(Pizza newPizza) throws SaisieCodeException {
		
	}

	@Override
	public void update(Pizza editPizza, String ancienCode) {
		Pizza item = this.pizzas.get(ancienCode);
		item.setCode(editPizza.getCode());
		item.setNom(editPizza.getNom());
		item.setPrix(editPizza.getPrix());
		this.save(item);
	}

	@Override
	public void delete(String ancienCode) {
		this.pizzas.remove(ancienCode);
		Path cheminFichier = Paths.get("src","data",ancienCode + ".txt");
        try {
        	// suppression
        	Files.deleteIfExists(cheminFichier);
		} catch (IOException e) {
			System.err.println("Export rejet� pour " + ancienCode);
		}
	}
	
	@Override
	public void importPizza(List<Pizza> listImport) {
		System.err.println("Veuillez configurer l�application avec une impl�mentation base de donn�es !");
	}
}
