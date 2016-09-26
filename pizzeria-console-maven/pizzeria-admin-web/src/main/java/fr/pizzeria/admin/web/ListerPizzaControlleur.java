package fr.pizzeria.admin.web;

import java.io.IOException;
import java.util.Collection;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.ejb.PizzaServiceEJB;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.service.Stockage;
import fr.pizzeria.service.StockagePizzaJPA;
import fr.pizzeria.service.StockageType;

@WebServlet("/pizzas/list")
public class ListerPizzaControlleur extends HttpServlet {

	@EJB private PizzaServiceEJB stockagePizza;
	
	/**
	 * GET : retourne la liste des pizzas et appelle listerPizzas.jsp
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//Collection<Pizza> pizzas = PersistanceUtils.getInstance().getStockagePizza().findAll();
		Collection<Pizza> pizzas = stockagePizza.findAll();
			
		req.setAttribute("listePizzas", pizzas);
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/pizzas/listerPizzasJSTL.jsp");
		dispatcher.forward(req, resp);

	}

}
