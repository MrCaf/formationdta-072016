package fr.pizzeria.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

import fr.pizzeria.exception.SaisieCodeException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class StockagePizzaBDD implements Stockage<Pizza, String> {

	public Map<String, Pizza> pizzas = new TreeMap<>();
	public String pizzaDb;
	public String pizzaUser;
	public String pizzaMdp;

	public StockagePizzaBDD() {

		// lecture des properties
		ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
		pizzaDb = bundle.getString("pizza.db");
		pizzaUser = bundle.getString("pizza.user");
		pizzaMdp = bundle.getString("pizza.mdp");

		findAll();
	}

	@Override
	public Collection<Pizza> findAll() {
		try (Connection connection = DriverManager.getConnection(pizzaDb, pizzaUser, pizzaMdp);
				PreparedStatement selectPizzaSt = connection.prepareStatement("SELECT * FROM PIZZA");
				ResultSet resultats = selectPizzaSt.executeQuery();) {

			while (resultats.next()) {
				Integer id = resultats.getInt("id");
				String name = resultats.getString("libelle");
				String code = resultats.getString("reference");
				Double prix = resultats.getDouble("prix");
				CategoriePizza catPizza = CategoriePizza.valueOf(resultats.getString("categorie"));
				String url = resultats.getString("urlImage");

				pizzas.put(code, new Pizza(code, name, prix, catPizza, url));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return pizzas.values();
	}

	@Override
	public Pizza find(String code) {
		return pizzas.get(code);
	}

	@Override
	public void save(Pizza newPizza) {
		try (Connection connection = DriverManager.getConnection(pizzaDb, pizzaUser, pizzaMdp);
				PreparedStatement updatePizzaSt = connection.prepareStatement(
						"INSERT INTO pizza (libelle,reference,prix,url_image,categorie) VALUES (?,?,?,'',?)");) {

			updatePizzaSt.setString(1, newPizza.getNom());
			updatePizzaSt.setString(2, newPizza.getCode());
			updatePizzaSt.setDouble(3, newPizza.getPrix());
			updatePizzaSt.setString(4, newPizza.getCatPizza().toString());
			updatePizzaSt.executeUpdate();
			pizzas.put(newPizza.getCode(), newPizza);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void saisirCode(Pizza newPizza) throws SaisieCodeException {

	}

	@Override
	public void update(Pizza editPizza, String ancienCode) {
		Pizza item = pizzas.get(ancienCode);
		try (Connection connection = DriverManager.getConnection(pizzaDb, pizzaUser, pizzaMdp);
				PreparedStatement updatePizzaSt = connection.prepareStatement(
						"UPDATE pizza SET libelle = ?, reference = ?, prix = ?, categorie = ? WHERE reference = ?");) {

			updatePizzaSt.setString(1, editPizza.getNom());
			updatePizzaSt.setString(2, editPizza.getCode());
			updatePizzaSt.setDouble(3, editPizza.getPrix());
			updatePizzaSt.setString(4, editPizza.getCatPizza().toString());
			updatePizzaSt.setString(5, ancienCode);
			updatePizzaSt.executeUpdate();
			item.setCode(editPizza.getCode());
			item.setNom(editPizza.getNom());
			item.setPrix(editPizza.getPrix());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(String ancienCode) {
		try (Connection connection = DriverManager.getConnection(pizzaDb, pizzaUser, pizzaMdp);
				PreparedStatement updatePizzaSt = connection
						.prepareStatement("DELETE FROM pizza WHERE reference = ?");) {
			updatePizzaSt.setString(1, ancienCode);
			updatePizzaSt.executeUpdate();
			pizzas.remove(ancienCode);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void importPizza(List<Pizza> listImport) {
		try (Connection connection = DriverManager.getConnection(pizzaDb, pizzaUser, pizzaMdp);
				PreparedStatement updatePizzaSt = connection.prepareStatement(
						"INSERT INTO pizza (libelle,reference,prix,url_image,categorie) VALUES (?,?,?,'',?)");) {

			connection.setAutoCommit(false);
			try {
				for (Pizza p : listImport) {

					updatePizzaSt.setString(1, p.getNom());
					updatePizzaSt.setString(2, p.getCode());
					updatePizzaSt.setDouble(3, p.getPrix());
					updatePizzaSt.setString(4, p.getCatPizza().toString());
					updatePizzaSt.executeUpdate();

				}
				connection.commit();
			} catch (SQLException e) {
				connection.rollback();
				System.err.println("Import annulé !");
				throw new RuntimeException(e);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
