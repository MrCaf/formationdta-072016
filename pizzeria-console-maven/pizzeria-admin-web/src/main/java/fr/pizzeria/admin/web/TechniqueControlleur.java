package fr.pizzeria.admin.web;

import java.io.IOException;
import java.util.Collection;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.model.Pizza;
import fr.pizzeria.service.Stockage;
import fr.pizzeria.service.StockagePizzaJPA;
import fr.pizzeria.service.StockageType;

@WebServlet("/technique")
public class TechniqueControlleur extends HttpServlet {
	
	@Inject private CreationPizzaListener sessionListener;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Collection<CreerPizzaEvent> pizzaCrees = sessionListener.getPizzaCrees();
		System.err.println("Pizzas créées : " + pizzaCrees.toString());
		
		req.setAttribute("listePizzas", pizzaCrees);
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/pizzas/techniqueJSTL.jsp");
		dispatcher.forward(req, resp);
		
	}

}
