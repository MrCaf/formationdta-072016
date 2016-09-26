package fr.pizzeria.admin.web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.ejb.PizzaServiceEJB;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.service.Stockage;
import fr.pizzeria.service.StockageType;

@WebServlet("/pizzas/supprime")
public class SupprimerPizzaControlleur extends HttpServlet {

	@EJB private PizzaServiceEJB stockagePizza;
	
	/**
	 * DELETE : supprime une pizza
	 */
/*
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Stockage<Pizza, String> stockagePizza = PersistanceUtils.getInstance().getStockagePizza();
		stockagePizza.delete((String) req.getAttribute("pizza"));
			
		resp.sendRedirect(req.getContextPath() + "/pizzas/list");
		
	}
*/
	/**
	 * POST : supprime une pizza
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		stockagePizza.delete(req.getParameter("code"));
		
		resp.sendRedirect(req.getContextPath() + "/pizzas/list");
		
	}

}
