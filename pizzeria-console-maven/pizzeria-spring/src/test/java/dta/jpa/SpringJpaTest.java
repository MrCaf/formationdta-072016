package dta.jpa;

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
import dta.repository.IPizzaRepository;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringJpaConfig.class)
public class SpringJpaTest {

	@Autowired
	IPizzaRepository pizzaRepo;

	@Test
	@Transactional
	public void test() {
		
		Pizza p = new Pizza("ABC", "abc", 10, CategoriePizza.VIANDE, "img.jpg");
		
		pizzaRepo.save(p);
		
	}
		
}
