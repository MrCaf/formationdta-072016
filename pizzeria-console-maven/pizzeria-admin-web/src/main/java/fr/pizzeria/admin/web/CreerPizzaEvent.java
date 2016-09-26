package fr.pizzeria.admin.web;

import java.util.Calendar;

import fr.pizzeria.model.Pizza;

public class CreerPizzaEvent {

	private Pizza pizza;
	private Calendar dateH;
	
	public Pizza getPizza() {
		return pizza;
	}
	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
	public Calendar getDateH() {
		return dateH;
	}
	public void setDateH(Calendar dateH) {
		this.dateH = dateH;
	}
	
}
