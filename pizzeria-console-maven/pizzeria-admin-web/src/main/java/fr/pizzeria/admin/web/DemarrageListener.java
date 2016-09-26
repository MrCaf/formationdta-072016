package fr.pizzeria.admin.web;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import fr.pizzeria.ejb.ClientServiceEJB;
import fr.pizzeria.ejb.CommandeServiceEJB;
import fr.pizzeria.ejb.LivreurServiceEJB;
import fr.pizzeria.ejb.PizzaServiceEJB;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Client;
import fr.pizzeria.model.Livreur;
import fr.pizzeria.model.Pizza;

@WebListener
public class DemarrageListener implements ServletContextListener {

	@EJB private PizzaServiceEJB stockagePizza;
	@EJB private ClientServiceEJB stockageClient;
	@EJB private LivreurServiceEJB stockageLivreur;
	@EJB private CommandeServiceEJB stockageCommande;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		stockagePizza.save(new Pizza("CAL", "Calzone", 13, CategoriePizza.VIANDE, "Pizza_Calzone.jpg"));
		stockagePizza.save(new Pizza("BOL", "Bolognese", 11, CategoriePizza.VIANDE, "Pizza_Bolognese.jpg"));
		stockagePizza.save(new Pizza("4SA", "4 Saisons", 13, CategoriePizza.VIANDE, "Pizza_4saisons.jpg"));
		stockagePizza.save(new Pizza("REG", "Regina", 13, CategoriePizza.VIANDE, "Pizza_Regina.jpg"));
		
		stockageClient.save(new Client(0, "SMITH", "Will", 450, "john@smith.com", "john"));
		stockageClient.save(new Client(0, "DUPONT", "Pierre", 50, "pierre@dupont.com", "pierre"));
		stockageClient.save(new Client(0, "BALOURD", "Gudule", 90, "gudule@balourd.com", "gudule"));

		stockageLivreur.save(new Livreur(0, "TRUMP", "Donald", 300, 500));
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

}
