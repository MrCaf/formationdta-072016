package fr.pizzeria.service;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import fr.pizzeria.model.Livreur;

public class StockageLivreur implements Stockage<Livreur, Integer> {

	public Map<Integer, Livreur> livreurs = new TreeMap<>();

	public StockageLivreur() {
		
		this.livreurs.put(12, new Livreur(12, "Cat", "Black", 200.00, -500));

	}

	@Override
	public Collection<Livreur> findAll() {
		return livreurs.values();
	}

	@Override
	public Livreur find(Integer code) {
		return livreurs.get(code);
	}

	@Override
	public void save(Livreur newLivreur) {
		this.livreurs.put(newLivreur.getId(), newLivreur);
	}

	@Override
	public void update(Livreur editLivreur, Integer ancienCode) {
		Livreur item = this.livreurs.get(ancienCode);
		item.setId(editLivreur.getId());
		item.setNom(editLivreur.getNom());
		item.setPrenom(editLivreur.getPrenom());
		item.setSolde(editLivreur.getSolde());

	}

	@Override
	public void delete(String ancienCode) {
		this.livreurs.remove(ancienCode);
	}
}
