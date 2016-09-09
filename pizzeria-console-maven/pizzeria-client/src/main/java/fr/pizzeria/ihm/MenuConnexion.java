package fr.pizzeria.ihm;

import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.reflections.Reflections;

import fr.pizzeria.exception.SaisieEntierException;

public class MenuConnexion {

	private static final int CHOIX_SORTIR = 99;
	private Map<Integer, ActionClient> actions = new HashMap<>();
	private IhmHelperClient ihmHelper;

	public MenuConnexion(IhmHelperClient helper) {
		
		Reflections reflections = new Reflections();
		Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(ActConnexion.class);
		AtomicInteger index = new AtomicInteger(0);
		annotated.stream().forEachOrdered(l -> {
			try {
				this.actions.put(index.incrementAndGet(), (ActionClient) l.getConstructor(IhmHelperClient.class).newInstance(helper));
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
		});
		
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
		System.out.println("\n******************************\n***** Pizzeria Connexion *****\n******************************");
		System.out.println(DateFormatUtils.format(Calendar.getInstance(), "dd/MM - HH:mm") + "\n");

		actions.forEach((numero, valeur) -> {
			ActionClient ActionEnCours = valeur;
			String libelleAction = ActionEnCours.getLibelle();
			System.out.println(numero + " " + libelleAction);			
		});
		System.out.println(CHOIX_SORTIR + ". Quitter" + "\n");
	}

	public boolean choisir() {
		System.out.println("Veuillez choisir une option");
		int choix = 0;
		try {

			choix = ihmHelper.saisirEntier();
			if (!actions.containsKey(choix)) {
				if (choix != CHOIX_SORTIR) {
					System.out.println("Erreur de saisie, veuillez recommencer!" + "\n");
				}
			} else {

				ActionClient LaBonneAction = actions.get(choix);
				LaBonneAction.execute();

			}
		} catch (SaisieEntierException e) {
			System.out.println(e.getMessage());
		}

		return choix == CHOIX_SORTIR;
	}

}
