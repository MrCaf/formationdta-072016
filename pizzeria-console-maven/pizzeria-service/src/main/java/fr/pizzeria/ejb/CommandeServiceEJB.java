package fr.pizzeria.ejb;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.pizzeria.model.Commande;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.StatutCommande;

@Stateless
public class CommandeServiceEJB {
	
	@PersistenceContext private EntityManager em;

	@EJB private PizzaServiceEJB stockagePizza;
	@EJB private ClientServiceEJB stockageClient;
	@EJB private LivreurServiceEJB stockageLivreur;

	public Collection<Commande> findAll() {

		TypedQuery<Commande> query = em.createQuery("SELECT c FROM Commande c join fetch c.pizzas", Commande.class);
		Collection<Commande> commandes = new HashSet<>(query.getResultList());

		return commandes;
	}

	public Commande find(Integer code) {

		TypedQuery<Commande> query = em.createNamedQuery("commande.findByNum", Commande.class).setParameter("numC", code);
		Commande c = query.getSingleResult();
		
		return c;
	}

	public void save(Commande newItem) {

		em.persist(newItem);
	}

	public void update(Commande editItem, Integer ancienCode) {

		TypedQuery<Commande> query = em.createNamedQuery("commande.findByNum", Commande.class).setParameter("numC", ancienCode);
		Commande c = query.getSingleResult();

		c.setNumCmd(editItem.getNumCmd());
		c.setDateCmd(editItem.getDateCmd());
		c.setStatut(editItem.getStatut());
		c.setClient(editItem.getClient());
		c.setLivreur(editItem.getLivreur());
		c.setPizzas(editItem.getPizzas());
		
		em.merge(c);
	}

	public void delete(int ancienCode) {

		TypedQuery<Commande> query = em.createNamedQuery("commande.findByNum", Commande.class).setParameter("numC", ancienCode);
		Commande c = query.getSingleResult();
		
		em.remove(c);
	}

	@Schedule(second="*/30", minute="*", hour="*")
	public void insererCommande() {
		Date date = new Date();
		Commande newCom = new Commande(0, 0, date, StatutCommande.ENCOURS, stockageClient.find(1), stockageLivreur.find(1));
		Set<Pizza> pizzas = new HashSet<Pizza>();
		pizzas.add(stockagePizza.find("REG"));
		pizzas.add(stockagePizza.find("BOL"));
		newCom.setPizzas(pizzas);
		save(newCom);
	}
	
	public void importPizza(List<Commande> listImport) {
		
	}

}
