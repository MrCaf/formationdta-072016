package fr.pizzeria.ejb;

import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.pizzeria.model.Client;

@Stateless
public class ClientServiceEJB {
	
	@PersistenceContext private EntityManager em;

	public Collection<Client> findAll() {

		TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c", Client.class);
		Collection<Client> clients = query.getResultList();

		return clients;
	}

	public Client find(Integer code) {

		TypedQuery<Client> query = em.createNamedQuery("client.findById", Client.class).setParameter("idC", code);
		Client c = query.getSingleResult();
		
		return c;
	}

	public void save(Client newItem) {

		em.persist(newItem);
	}

	public void update(Client editItem, Integer ancienCode) {

		TypedQuery<Client> query = em.createNamedQuery("client.findById", Client.class).setParameter("idC", ancienCode);
		Client c = query.getSingleResult();

		c.setNom(editItem.getNom());
		c.setPrenom(editItem.getPrenom());
		c.setSolde(editItem.getSolde());
		c.setEmail(editItem.getEmail());
		c.setPassword(editItem.getPassword());
		
		em.merge(c);
	}

	public void delete(String ancienCode) {

		TypedQuery<Client> query = em.createNamedQuery("client.findById", Client.class).setParameter("idC", ancienCode);
		Client c = query.getSingleResult();
		
		em.remove(c);
	}

	public void importPizza(List<Client> listImport) {
		
	}

}
