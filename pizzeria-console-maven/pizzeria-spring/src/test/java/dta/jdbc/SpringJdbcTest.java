package dta.jdbc;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dta.beans.SpringConfig;
import dta.pizzeria.StockageSpringJdbc;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringJdbcConfig.class)
public class SpringJdbcTest {

	@Autowired
	private StockageSpringJdbc pizzaStockage;

	@Test
	public void testCreationBean() {
		
		pizzaStockage.findAll().forEach(System.out::println);
		
	}
	
	@Test
	public void testCreationPizza() {
		
		pizzaStockage.save(new Pizza("NOR", "Nordique", 15, CategoriePizza.POISSON, "nordique.jpg"));
		System.out.println(pizzaStockage.find("NOR"));
		
	}
	
}
