package dta.pizzeria;

import java.util.List;

import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.pizzeria.model.Pizza;

@Repository
public class StockageLotSpringJdbc {

	@Autowired 
	private StockageSpringJdbc stockagePizza;
	
	@Transactional
	public void insererParLot(List<Pizza> pizzas) {
		

        List<List<Pizza>> newList = ListUtils.partition(pizzas, 3);
        newList.forEach(l -> {
        	stockagePizza.importPizza( l);
        });
	}

	public void setStockagePizza(StockageSpringJdbc stockagePizza) {
		this.stockagePizza = stockagePizza;
	}
	
	
	
}
