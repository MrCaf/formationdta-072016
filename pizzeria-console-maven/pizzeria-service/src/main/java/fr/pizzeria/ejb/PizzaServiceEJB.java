package fr.pizzeria.ejb;

import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.pizzeria.model.Pizza;
import fr.pizzeria.service.Stockage;

@Stateless
public class PizzaServiceEJB {
	
	@PersistenceContext private EntityManager em;

	public Collection<Pizza> findAll() {
		
		TypedQuery<Pizza> query = em.createQuery("SELECT p FROM Pizza p", Pizza.class);
		Collection<Pizza> pizzas = query.getResultList();

		return pizzas;
	}

	public Pizza find(String code) {

		TypedQuery<Pizza> query = em.createNamedQuery("pizza.findByCode", Pizza.class).setParameter("codeP", code);
		Pizza p = query.getSingleResult();
		
		return p;
	}

	public void save(Pizza newPizza) {

		em.persist(newPizza);
	}

	public void update(Pizza editPizza, String ancienCode) {
		
		TypedQuery<Pizza> query = em.createNamedQuery("pizza.findByCode", Pizza.class).setParameter("codeP", ancienCode);
		Pizza p = query.getSingleResult();

		p.setCode(editPizza.getCode());
		p.setNom(editPizza.getNom());
		p.setPrix(editPizza.getPrix());
		p.setCategorie(editPizza.getCategorie());
		p.setUrlImage(editPizza.getUrlImage());
		
		em.merge(p);
	}

	public void delete(String ancienCode) {
		
		TypedQuery<Pizza> query = em.createNamedQuery("pizza.findByCode", Pizza.class).setParameter("codeP", ancienCode);
		Pizza p = query.getSingleResult();
		
		em.remove(p);
	}

	public void importPizza(List<Pizza> listImport) {
		
	}

}
