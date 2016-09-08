package fr.pizzeria.service;

import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import fr.pizzeria.exception.SaisieCodeException;
import fr.pizzeria.model.Pizza;

public class StockagePizzaJPA implements Stockage<Pizza, String> {

	private EntityManagerFactory emf;

	public StockagePizzaJPA() {

		emf = Persistence.createEntityManagerFactory("pizzeria-unit");
		
		findAll();
	}

	@Override
	public Collection<Pizza> findAll() {
		
		EntityManager em = emf.createEntityManager();
		TypedQuery<Pizza> query = em.createQuery("SELECT p FROM Pizza p", Pizza.class);
		
		Collection<Pizza> pizzas = query.getResultList();
		em.close();

		return pizzas;
	}

	@Override
	public Pizza find(String code) {
		
		EntityManager em = emf.createEntityManager();
		TypedQuery<Pizza> query = em.createQuery("SELECT p FROM Pizza p WHERE p.code =:codeP", Pizza.class);
		query.setParameter("codeP", code);
		
		Pizza p = query.getSingleResult();
		em.close();
		
		return p;
	}

	@Override
	public void save(Pizza newPizza) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		
		em.persist(newPizza);
		
		et.commit();
		em.close();
	}

	public void saisirCode(Pizza newPizza) throws SaisieCodeException {

	}

	@Override
	public void update(Pizza editPizza, String ancienCode) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();

		TypedQuery<Pizza> query = em.createQuery("SELECT p FROM Pizza p WHERE p.code =:codeP", Pizza.class);
		query.setParameter("codeP", ancienCode);
		
		Pizza p = query.getSingleResult();

		p.setCode(editPizza.getCode());
		p.setNom(editPizza.getNom());
		p.setPrix(editPizza.getPrix());
		p.setCatPizza(editPizza.getCatPizza());
		
		em.merge(p);
		
		et.commit();
		em.close();
	}

	@Override
	public void delete(String ancienCode) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		
		TypedQuery<Pizza> query = em.createQuery("SELECT p FROM Pizza p WHERE p.code =:codeP", Pizza.class);
		query.setParameter("codeP", ancienCode);
		
		Pizza p = query.getSingleResult();
		
		em.remove(p);
		
		et.commit();
		em.close();
	}

	@Override
	public void importPizza(List<Pizza> listImport) {
		/*try (Connection connection = DriverManager.getConnection(pizzaDb, pizzaUser, pizzaMdp);
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
		}*/
	}
}
