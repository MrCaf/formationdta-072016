package fr.pizzeria.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Commande {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int numCmd;
	private Date dateCmd;
	@Enumerated(EnumType.STRING)
	private StatutCommande statut;
	@ManyToOne @JoinColumn(name="idClient")
	private Client client;
	@ManyToOne @JoinColumn(name="idLivreur")
	private Livreur livreur;
	@ManyToMany @JoinTable(name="commandePizza",
							joinColumns=@JoinColumn(name="idCmd", referencedColumnName="id"),
							inverseJoinColumns=@JoinColumn(name="idPizza", referencedColumnName="id"))
	private Set<Pizza> pizzas;
	
	public Commande(int id, int numCmd, Date dateCmd, StatutCommande statut, Client client, Livreur livreur) {
		super();
		this.id = id;
		this.numCmd = numCmd;
		this.dateCmd = dateCmd;
		this.statut = statut;
		this.client = client;
		this.livreur = livreur;
	}

	public StatutCommande getStatut() {
		return statut;
	}

	public void setStatut(StatutCommande statut) {
		this.statut = statut;
	}

	public Set<Pizza> getPizzas() {
		return pizzas;
	}

	public void setPizzas(Set<Pizza> pizzas) {
		this.pizzas = pizzas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumCmd() {
		return numCmd;
	}

	public void setNumCmd(int numCmd) {
		this.numCmd = numCmd;
	}

	public Date getDateCmd() {
		return dateCmd;
	}

	public void setDateCmd(Date dateCmd) {
		this.dateCmd = dateCmd;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Livreur getLivreur() {
		return livreur;
	}

	public void setLivreur(Livreur livreur) {
		this.livreur = livreur;
	}
	
}
