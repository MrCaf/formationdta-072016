package dta.springmvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dta.repository.IPizzaRepository;
import fr.pizzeria.model.Pizza;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {
	
	@Autowired
	IPizzaRepository pizzaRepo;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Pizza> findAll() {
		
		List<Pizza> listeP = pizzaRepo.findAll();
		return listeP;
		
	}

}
