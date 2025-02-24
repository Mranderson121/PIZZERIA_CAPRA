package com.pizzeria.controller;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.pizzeria.dao.Dao;
import com.pizzeria.model.Ingrediente;
import com.pizzeria.model.Pizza;
import com.pizzeria.model.Utente;
import com.pizzeria.service.ImpastoService;
import com.pizzeria.service.IngredienteService;
import com.pizzeria.service.PizzaService;

@Controller
public class UpdatePizzaServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ImpastoService impastoService;
	@Autowired
	private IngredienteService ingredienteService;
	@Autowired
	private PizzaService pizzaService;

	@PostMapping("/modificaPizza")
	public String mostraModificaPizza(@SessionAttribute(name = "utenteLoggato") Utente utenteLoggato, @RequestParam("pizzaId") int pizzaId, Model model) {
		Pizza pizza = pizzaService.findPizzaById(pizzaId);
		if (pizza != null) {
			model.addAttribute("pizza", pizza);
			model.addAttribute("utenteLoggato", utenteLoggato);
	        model.addAttribute("impasti", impastoService.getAllImpasti());
	        model.addAttribute("ingredienti", ingredienteService.getAllIngredienti());
	        model.addAttribute("pizze", pizzaService.getPizzeByUtente(utenteLoggato));
			return "update"; // La tua pagina update.jsp
		} else {
			// Gestisci il caso in cui la pizza non esiste
			return "redirect:/dashboard"; // Oppure un altro percorso
		}
	}
	 @PostMapping("/updatePizza")
	    public String updatePizza(@RequestParam("pizzaId") int pizzaId, 
	                              @RequestParam("pizzaName") String pizzaName,
	                              @RequestParam("impastoId") int impastoId,
	                              @RequestParam("ingredientiId") Set<Integer> ingredientiId,
	                              @SessionAttribute(name = "utenteLoggato") Utente utenteLoggato) {
	        
	        Pizza pizza = pizzaService.modificaPizza(pizzaId,pizzaName,impastoId, ingredientiId,utenteLoggato);
	        
	        return "redirect:/dashboard"; 
	    }

}
