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
						// on découpe les lignes pour retrouver les informations et les enregistrer
						List<String> attr = Arrays.asList(l.split(";"));
						String code = p.getFileName().toString().substring(0, 3);
						pizzas.put(code, new Pizza(code, attr.get(0), Float.valueOf(attr.get(1)), CategoriePizza.valueOf(attr.get(2))));
					});
				} catch (IOException f) {
					System.err.println("Import rejeté pour " + p.getFileName());
				}
			});
		} catch (IOException e) {
			System.err.println("Import rejeté");
		}
		return pizzas.values();
	}

	@Override
	public Pizza find(String code) {
		return pizzas.get(code);
	}

	@Override
	public void save(Pizza newPizza) {
		// sauvegarder des pizzas dans des fichiers
		// génération du chemin
		Path cheminFichier = Paths.get("src","data",newPizza.getCode() + ".txt");
        try {
        	// création et écriture dans le fichier
        	Files.createFile(cheminFichier);
			Files.write(cheminFichier, Arrays.asList(newPizza.getNom() + ";" + newPizza.getPrix() + ";" + newPizza.getCatPizza()));
		} catch (IOException e) {
			System.err.println("Export rejeté pour " + newPizza.getCode());
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

	}

	@Override
	public void delete(String ancienCode) {
		this.pizzas.remove(ancienCode);
	}
}
