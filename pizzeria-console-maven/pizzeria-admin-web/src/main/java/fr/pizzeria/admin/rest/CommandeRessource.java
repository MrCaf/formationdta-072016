package fr.pizzeria.admin.rest;

import java.util.Collection;

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

import fr.pizzeria.ejb.CommandeServiceEJB;
import fr.pizzeria.model.Commande;

@Path("/commandes")
public class CommandeRessource {

	@EJB private CommandeServiceEJB stockageCommande;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Commande> list() {
		Collection<Commande> commandes = stockageCommande.findAll();
	
	return commandes;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void create(Commande c) {
		stockageCommande.save(c);
	
	}

	@PUT
	@Path("/{code}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void update(Commande c, @PathParam("code") int code) {
		stockageCommande.update(c, code);
	
	}

	@DELETE
	@Path("/{code}")
	public void delete(@PathParam("code") int code) {
		stockageCommande.delete(code);
	
	}

}
