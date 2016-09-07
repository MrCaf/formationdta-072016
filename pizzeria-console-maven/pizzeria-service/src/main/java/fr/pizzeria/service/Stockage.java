package fr.pizzeria.service;

import java.util.Collection;
import java.util.List;

public interface Stockage<T, E> {

	Collection<T> findAll();
	
	T find(E code);

	void save(T newItem);

	void update(T editItem, E code);

	void delete(String ancienCode);
	
	public void importPizza(List<T> listImport);
}
