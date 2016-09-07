package fr.pizzeria.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import fr.pizzeria.model.Client;

public class StockageClient implements Stockage<Client, Integer> {

	public Map<Integer, Client> clients = new TreeMap<>();

	public StockageClient() {
		
		this.clients.put(12, new Client(12, "Robert", "Jules", 200.00));
		this.clients.put(15, new Client(15, "Robert", "Hugues", 2.00));

	}

	@Override
	public Collection<Client> findAll() {
		return clients.values();
	}

	@Override
	public Client find(Integer code) {
		return clients.get(code);
	}

	@Override
	public void save(Client newClient) {
		this.clients.put(newClient.getId(), newClient);
	}

	@Override
	public void update(Client editCLient, Integer ancienCode) {
		Client item = this.clients.get(ancienCode);
		item.setId(editCLient.getId());
		item.setNom(editCLient.getNom());
		item.setPrenom(editCLient.getPrenom());
		item.setSolde(editCLient.getSolde());

	}

	@Override
	public void delete(String ancienCode) {
		this.clients.remove(ancienCode);
	}

	@Override
	public void importPizza(List<Client> listImport) {
		
	}
}
