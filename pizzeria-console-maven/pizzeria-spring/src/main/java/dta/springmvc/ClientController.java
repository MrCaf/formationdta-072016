package dta.springmvc;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dta.repository.IClientRepository;
import fr.pizzeria.exception.CreditException;
import fr.pizzeria.exception.DebitException;
import fr.pizzeria.model.Client;

@Controller
@RequestMapping("/clients")
public class ClientController {

	@Autowired
	IClientRepository clientRepo;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Client> findAll() {

		return clientRepo.findAll();

	}

	@RequestMapping(value = "/{clientId}/solde", method = RequestMethod.POST)
	@ResponseBody
	public ReponseBody solde(@PathVariable int clientId, @RequestBody RequeteBody operation) {

		ReponseBody reponse = new ReponseBody();
		Client client = clientRepo.findOne(clientId);

		boolean debit = operation.isDebit();
		double montant = operation.getMontant();

		if (debit) {
			try {
				client.debiterCompte(montant);
				reponse.setSuccess(true);
				reponse.setSolde(client.getSolde());
				reponse.setRetour("");
			} catch (DebitException e) {
				reponse.setSuccess(false);
				reponse.setSolde(client.getSolde());
				reponse.setRetour("Débit impossible, solde insuffisant : " + client.getSolde());
			}
		} else {
			try {
				client.crediterCompte(montant);
				reponse.setSuccess(true);
				reponse.setSolde(client.getSolde());
				reponse.setRetour("");
			} catch (CreditException e) {
				reponse.setSuccess(false);
				reponse.setSolde(client.getSolde());
				reponse.setRetour("Crédit impossible, solde trop élevé : " + client.getSolde());
			}
		}

		return reponse;

	}

}
