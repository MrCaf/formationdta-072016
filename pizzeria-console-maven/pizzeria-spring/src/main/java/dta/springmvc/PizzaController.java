package dta.springmvc;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dta.pizzeria.StockageSpringJdbc;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {
	
	//@Autowired
	//private StockageSpringJdbc pizzaStockage;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public String findAll(HttpServletResponse response) {
		
		//pizzaStockage.findAll();
		return "ListePizzas";
		
	}

}
