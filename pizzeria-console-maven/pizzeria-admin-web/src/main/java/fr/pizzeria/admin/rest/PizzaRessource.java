package fr.pizzeria.admin.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.pizzeria.ejb.PizzaServiceEJB;
import fr.pizzeria.model.Pizza;

@Path("/pizzas")
public class PizzaRessource {

	@EJB private PizzaServiceEJB stockagePizza;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pizza> list() {
	List<Pizza> pizzas = (List<Pizza>) stockagePizza.findAll();
	
	return pizzas;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void create(Pizza p) {
	stockagePizza.save(p);
	
	}

	@PUT
	@Path("/{code}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void update(Pizza p, @PathParam("code") String code) {
	stockagePizza.update(p, code);
	
	}

	@DELETE
	@Path("/{code}")
	public void delete(@PathParam("code") String code) {
		stockagePizza.delete(code);
	
	}

}
