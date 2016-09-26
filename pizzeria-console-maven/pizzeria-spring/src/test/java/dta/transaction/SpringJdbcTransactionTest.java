package dta.transaction;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import dta.beans.SpringConfig;
import dta.pizzeria.StockageLotSpringJdbc;
import dta.pizzeria.StockageSpringJdbc;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringJdbcTransactionConfig.class)
public class SpringJdbcTransactionTest {

	@Autowired
	private StockageLotSpringJdbc pizzaStockage;

	@Test
	@Transactional
	public void testCreationBean() {
		
		List<Pizza> pizzas = new ArrayList<>();
		pizzas.add(new Pizza("A", "aaa", 10, CategoriePizza.VIANDE, "urlImage"));
		pizzas.add(new Pizza("B", "bbb", 10, CategoriePizza.VIANDE, "urlImage"));
		pizzas.add(new Pizza("C", "ccc", 10, CategoriePizza.VIANDE, "urlImage"));
		pizzas.add(new Pizza("D", "ddd", 10, CategoriePizza.VIANDE, "urlImage"));
		pizzas.add(new Pizza("EEEEEE", "eee", 10, CategoriePizza.VIANDE, "urlImage"));
		pizzas.add(new Pizza("F", "fff", 10, CategoriePizza.VIANDE, "urlImage"));
		pizzas.add(new Pizza("G", "ggg", 10, CategoriePizza.VIANDE, "urlImage"));
		
		pizzaStockage.insererParLot(pizzas);
		
	}
		
}
