package fr.pizzeria.admin.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.service.Stockage;

public class PizzaServletWebApi extends HttpServlet {

	/**
	 * DELETE : supprime une pizza
	 * param 1 : code
	 */
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ResourceBundle bundle = ResourceBundle.getBundle("application");
		String classeStockagePizza = bundle.getString("stockage.pizza");
		System.out.println(classeStockagePizza);
		Class<?> classePizza;
		try {
			classePizza = Class.forName(classeStockagePizza);
			Stockage<Pizza, String> stockagePizza = (Stockage<Pizza, String>) classePizza.newInstance();
			Collection<Pizza> pizzas = stockagePizza.findAll();
			
			stockagePizza.delete(req.getParameter("code"));
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
			
			resp.getWriter().write(pizzas.toString());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * POST : création d'une pizza
	 * param 1 : code
	 * param 2 : nom
	 * param 3 : prix
	 * param 4 : catpizza
	 * param 5 : url
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ResourceBundle bundle = ResourceBundle.getBundle("application");
		String classeStockagePizza = bundle.getString("stockage.pizza");
		System.out.println(classeStockagePizza);
		Class<?> classePizza;
		try {
			classePizza = Class.forName(classeStockagePizza);
			Stockage<Pizza, String> stockagePizza = (Stockage<Pizza, String>) classePizza.newInstance();
			
			double prix = Double.valueOf(req.getParameter("prix"));
			CategoriePizza categorie = CategoriePizza.valueOf(req.getParameter("catpizza"));
			
			Pizza newPizza = new Pizza(req.getParameter("code"), req.getParameter("nom"), prix, categorie, req.getParameter("url"));
			
			stockagePizza.save(newPizza);
			
			//resp.getWriter().write(pizzas.toString());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * PUT : modification d'une pizza
	 * param 1 : ancienCode
	 * param 2 : code
	 * param 3 : nom
	 * param 4 : prix
	 * param 5 : catpizza
	 * param 6 : url
	 */
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResourceBundle bundle = ResourceBundle.getBundle("application");
		String classeStockagePizza = bundle.getString("stockage.pizza");
		System.out.println(classeStockagePizza);
		Class<?> classePizza;
		try {
			classePizza = Class.forName(classeStockagePizza);
			Stockage<Pizza, String> stockagePizza = (Stockage<Pizza, String>) classePizza.newInstance();
			/*
			double prix = Double.valueOf(req.getParameter("prix"));
			CategoriePizza categorie = CategoriePizza.valueOf(req.getParameter("catpizza"));
			
			Pizza newPizza = new Pizza(req.getParameter("code"), req.getParameter("nom"), prix, categorie, "");
			
			stockagePizza.update(newPizza, req.getParameter("ancienCode"));
			*/
			String ancienCode = "";
			String code = "";
			String nom = "";
			double prix = 0;
			CategoriePizza catpizza = CategoriePizza.VIANDE;
			String url = "";
			
			BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
			String retour = br.readLine();
			String[] params = retour.split("&");
			for(String attr : params){
				String[] param2 = attr.split("=");
				switch(param2[0]){
				case "ancienCode" :
					ancienCode = param2[1];
					break;
				case "code" :
					code = param2[1];
					break;
				case "nom" :
					nom = param2[1];
					break;
				case "prix" :
					prix = Double.valueOf(param2[1]);
					break;
				case "catpizza" :
					catpizza = CategoriePizza.valueOf(param2[1]);
					break;
				case "url" :
					url = param2[1];
					break;
				}
			}
			
			Pizza newPizza = new Pizza(code, nom, prix, catpizza, url);
			
			stockagePizza.update(newPizza, ancienCode);
			
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
