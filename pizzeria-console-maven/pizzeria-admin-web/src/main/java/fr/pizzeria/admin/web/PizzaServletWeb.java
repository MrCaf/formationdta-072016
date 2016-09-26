package fr.pizzeria.admin.web;

import java.io.IOException;
import java.util.Collection;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.model.Pizza;
import fr.pizzeria.service.Stockage;

public class PizzaServletWeb extends HttpServlet {

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		super.doDelete(req, resp);
	}

	/**
	 * GET : retourne la liste des pizzas
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ResourceBundle bundle = ResourceBundle.getBundle("application");
		String classeStockagePizza = bundle.getString("stockage.pizza");
		System.out.println(classeStockagePizza);
		Class<?> classePizza;
		try {
			classePizza = Class.forName(classeStockagePizza);
			Stockage<Pizza, String> stockagePizza = (Stockage<Pizza, String>) classePizza.newInstance();
			Collection<Pizza> pizzas = stockagePizza.findAll();
			
			StringBuffer sb = new StringBuffer();
			sb.append("<HTML>\n");
			sb.append("<HEAD>\n");
			sb.append("<TITLE>Pizzas</TITLE>\n");
			sb.append("</HEAD>\n");
			sb.append("<BODY>\n");
			sb.append("<H1>Bonjour</H1>\n");
			sb.append("</BODY>\n");
			sb.append("</HTML>");
			
			resp.getWriter().write(sb.toString());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		super.doPost(req, resp);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		super.doPut(req, resp);
	}

}
