package dta.pizzeria;

import java.util.List;

import fr.pizzeria.model.Pizza;
import fr.pizzeria.service.Stockage;

public interface StockageSpring extends Stockage<Pizza, String> {
	public void importPizza(List<Pizza> listImport);
}
