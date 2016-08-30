package fr.pizzeria.service;

import java.util.Collection;

public interface Stockage<T, E> {

	Collection<T> findAll();
	
	T find(E code);

	void save(T newItem);

	void update(T editItem, E code);

	void delete(String ancienCode);
	
}
