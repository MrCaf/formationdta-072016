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
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.service.Stockage;
import fr.pizzeria.service.StockageType;

@WebServlet("/pizzas/edite")
public class EditerPizzaControlleur extends HttpServlet {

	@EJB private PizzaServiceEJB stockagePizza;
	
	/**
	 * GET : affiche un formulaire pré-remplis pour la mise à jour d'une pizza
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Pizza pizza = stockagePizza.find(req.getParameter("code"));
		
		req.setAttribute("pizza", pizza);
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/pizzas/editerPizzasJSTL.jsp");
		dispatcher.forward(req, resp);
		
	}

	/**
	 * POST : mise à jour d'une pizza
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String ancienCode = req.getParameter("ancienCode");
		Pizza pizza = stockagePizza.find(ancienCode);
		String code = req.getParameter("code");
		String nom = req.getParameter("nom");
		double prix = Double.valueOf(req.getParameter("prix"));
		CategoriePizza catpizza = CategoriePizza.valueOf(req.getParameter("categorie"));
		String url = req.getParameter("url");
			
			
		Pizza newPizza = new Pizza(code, nom, prix, catpizza, url);
		stockagePizza.update(newPizza, ancienCode);
			
		resp.sendRedirect(req.getContextPath() + "/pizzas/list");
		
	}

}
