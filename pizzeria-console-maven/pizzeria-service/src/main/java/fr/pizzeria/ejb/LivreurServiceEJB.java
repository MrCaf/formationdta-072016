package fr.pizzeria.ejb;

import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.pizzeria.model.Livreur;

@Stateless
public class LivreurServiceEJB {
	
	@PersistenceContext private EntityManager em;

	public Collection<Livreur> findAll() {

		TypedQuery<Livreur> query = em.createQuery("SELECT l FROM Livreur l", Livreur.class);
		Collection<Livreur> livreurs = query.getResultList();

		return livreurs;
	}

	public Livreur find(Integer code) {

		TypedQuery<Livreur> query = em.createNamedQuery("livreur.findById", Livreur.class).setParameter("idL", code);
		Livreur l = query.getSingleResult();
		
		return l;
	}

	public void save(Livreur newItem) {

		em.persist(newItem);
	}

	public void update(Livreur editItem, Integer ancienCode) {

		TypedQuery<Livreur> query = em.createNamedQuery("livreur.findById", Livreur.class).setParameter("idL", ancienCode);
		Livreur l = query.getSingleResult();

		l.setNom(editItem.getNom());
		l.setPrenom(editItem.getPrenom());
		l.setSolde(editItem.getSolde());
		l.setMontantDecouvertAutorise(editItem.getMontantDecouvertAutorise());
		
		em.merge(l);
	}

	public void delete(String ancienCode) {

		TypedQuery<Livreur> query = em.createNamedQuery("livreur.findById", Livreur.class).setParameter("idL", ancienCode);
		Livreur l = query.getSingleResult();
		
		em.remove(l);
	}

	public void importPizza(List<Livreur> listImport) {
		
	}

}
