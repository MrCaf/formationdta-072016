package fr.pizzeria.admin.web;

import java.io.IOException;
import java.util.Calendar;

import javax.ejb.EJB;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.ejb.PizzaServiceEJB;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.service.Stockage;
import fr.pizzeria.service.StockageType;

@WebServlet("/pizzas/cree")
public class CreerPizzaControlleur extends HttpServlet {

	@EJB private PizzaServiceEJB stockagePizza;
	@Inject private Event<CreerPizzaEvent> evt;
	
	/**
	 * GET : affiche un formulaire pour la création d'une pizza
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/pizzas/creerPizzasJSTL.jsp");
		dispatcher.forward(req, resp);
		
	}

	/**
	 * POST : creation d'une pizza
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//Stockage<Pizza, String> stockagePizza = PersistanceUtils.getInstance().getStockagePizza();

		String code = req.getParameter("code");
		String nom = req.getParameter("nom");
		double prix = Double.valueOf(req.getParameter("prix"));
		CategoriePizza catpizza = CategoriePizza.valueOf(req.getParameter("categorie"));
		String url = req.getParameter("url");
		
		Pizza newPizza = new Pizza(code, nom, prix, catpizza, url);
		stockagePizza.save(newPizza);
		
		CreerPizzaEvent creationEvent = new CreerPizzaEvent();
		creationEvent.setPizza(newPizza);
		creationEvent.setDateH(Calendar.getInstance());
		
		evt.fire(creationEvent);
		
		resp.sendRedirect(req.getContextPath() + "/pizzas/list");
		
	}
	
}
