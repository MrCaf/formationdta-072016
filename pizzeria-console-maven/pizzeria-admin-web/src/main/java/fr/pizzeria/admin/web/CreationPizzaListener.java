package fr.pizzeria.admin.web;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class CreationPizzaListener {
	
	private List<CreerPizzaEvent> pizzaCrees = new ArrayList<CreerPizzaEvent>();

	public void creationPizzaListener(@Observes CreerPizzaEvent event){
		
		pizzaCrees.add(event);
		
	}

	public List<CreerPizzaEvent> getPizzaCrees() {
		return pizzaCrees;
	}

	public void setPizzaCrees(List<CreerPizzaEvent> pizzaCrees) {
		this.pizzaCrees = pizzaCrees;
	}
	
}
