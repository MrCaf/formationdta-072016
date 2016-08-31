package fr.pizzeria.ihm;

import java.util.HashMap;
import java.util.Map;

import fr.pizzeria.exception.SaisieEntierException;

public class Menu {

	private static final int CHOIX_SORTIR = 99;
	private Map<Integer, Action> actions = new HashMap<>();
	private IhmHelper ihmHelper;

	public Menu(IhmHelper helper) {
		this.actions.put(1, new ListerPizzaAction(helper));
		this.actions.put(2, new ListerPizzaTriAction(helper));
		this.actions.put(3, new AfficherPizzaChereAction(helper));
		this.actions.put(4, new AjouterPizzaAction(helper));
		this.actions.put(5, new ModifierPizzaAction(helper));
		this.actions.put(6, new SupprimerPizzaAction(helper));
		this.actions.put(7, new ListerClientAction(helper));
		this.actions.put(8, new AjouterClientAction(helper));
		this.actions.put(9, new CrediterClientAction(helper));
		this.actions.put(10, new DebiterClientAction(helper));
		this.actions.put(11, new ListerLivreurAction(helper));
		this.actions.put(12, new AfficherCompteStatAction(helper));
		this.actions.put(13, new VirementClientAction(helper));
		this.actions.put(14, new StockerPizzaAction(helper));

		this.ihmHelper = helper;
	}

	public void start() {
		boolean result = false;
		do {
			affichageM();
			result = choisir();
		} while (!result);
	}

	public void affichageM() {
		System.out.println("\n***********************************\n***** Pizzeria Administration *****\n***********************************\n");

		
		////////////////////////////////////////
		// utilisation des expressions lambda //
		////////////////////////////////////////
		actions.forEach((numero, valeur) -> {
			Action ActionEnCours = valeur;
			String libelleAction = ActionEnCours.getLibelle();
			System.out.println(numero + " " + libelleAction);			
		});
		/*
		for (Integer numero : actions.keySet()) {
			Action ActionEnCours = actions.get(numero);
			String libelleAction = ActionEnCours.getLibelle();
			System.out.println(numero + " " + libelleAction);
		}*/
		System.out.println(CHOIX_SORTIR + ". Quitter" + "\n");
	}

	public boolean choisir() {
		System.out.println("Veuillez choisir une option");
		int choix = 0;
		try {
			// Instructions susceptibles de provoquer des erreurs;

			choix = ihmHelper.saisirEntier();
			if (!actions.containsKey(choix)) {
				if (choix != CHOIX_SORTIR) {
					System.out.println("Erreur de saisie, veuillez recommencer!" + "\n");
				}
			} else {

				Action LaBonneAction = actions.get(choix);
				LaBonneAction.execute();

			}
		} catch (SaisieEntierException e) {
			System.out.println(e.getMessage());
		}

		return choix == CHOIX_SORTIR;
	}

}
