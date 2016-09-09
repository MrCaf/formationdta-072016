package fr.pizzeria.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.pizzeria.exception.CreditException;
import fr.pizzeria.exception.DebitException;

public class ClientTest {

	public Client client;
	
	@Before
	public void setUp(){
		client = new Client(1, "", "", 200, "", "");
	}
	
	@Test
	public void testCrediterCompte() throws CreditException{
		double montant = 50;
		double soldeOld = client.getSolde();
		client.crediterCompte(montant);
		assertEquals(soldeOld+montant, client.getSolde(), 0);
	}

	@Test(expected = CreditException.class)
	public void testFailCrediterCompte() throws CreditException{
		double montant = 5000;
		client.crediterCompte(montant);
	}
	
	@Test
	public void testDebiterCompte() throws DebitException {
		double montant = 50;
		double soldeOld = client.getSolde();
		client.debiterCompte(montant);
		assertEquals(soldeOld-montant, client.getSolde(), 0);
	}

	@Test(expected = DebitException.class)
	public void testFailDebiterCompte() throws DebitException{
		double montant = 500;
		client.debiterCompte(montant);
	}
	
}
