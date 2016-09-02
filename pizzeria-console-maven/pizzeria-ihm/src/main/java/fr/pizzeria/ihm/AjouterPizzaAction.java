package fr.pizzeria.ihm;

import java.util.Arrays;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

@Act
public class AjouterPizzaAction extends Action {

	public AjouterPizzaAction(IhmHelper helper) {
		super("Ajouter une pizza", helper);
	}

	@Override
	public void execute() {

		// récuperation de la saisie
		System.out.println("Vous voulez ajoutez une pizza");
		System.out.println("Veuillez saisir le code");
		String code = helper.getScanner().next();
		System.out.println("Veuillez saisir le nom de la pizza");
		String nom = helper.getScanner().next();
		System.out.println("Veuillez saisir le prix de la pizza");
		double prix = helper.getScanner().nextDouble();
		System.out.println("Veuillez saisir la catégorie de la pizza");
		Arrays.asList(CategoriePizza.values()).forEach(System.out::println);
		String nameCat = helper.getScanner().next();
		CategoriePizza cat = CategoriePizza.valueOf(nameCat);
		// creation de la nouvelle pizza
		Pizza nouvellePizza = new Pizza(code, nom, prix, cat);
		helper.getStockagePizza().save(nouvellePizza);

		System.out.println("Pizza ajoutée avec succes" + "\n");

	}

}
