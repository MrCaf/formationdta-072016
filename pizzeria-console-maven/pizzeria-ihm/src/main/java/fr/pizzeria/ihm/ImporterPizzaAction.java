package fr.pizzeria.ihm;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.collections4.ListUtils;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

@Act
public class ImporterPizzaAction extends Action {

	public ImporterPizzaAction(IhmHelper helper) {
		super("Importer les pizzas", helper);
	}

	public void execute() {
		System.out.println("**** Import des Pizzas ****");
		List<Pizza>pizzas = new ArrayList<>();
		
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
						pizzas.add(new Pizza(code, attr.get(0), Float.valueOf(attr.get(1)), CategoriePizza.valueOf(attr.get(2)), attr.get(3)));
					});
				} catch (IOException f) {
					System.err.println("Import rejeté pour " + p.getFileName());
				}
			});
		} catch (IOException e) {
			System.err.println("Import rejeté");
		}
        // on découpe la map par groupes de 3 éléments
        List newList = ListUtils.partition(pizzas, 3);
        newList.forEach(l -> {
        	this.helper.getStockagePizza().importPizza((List<Pizza>) l);
        });
	}

}
