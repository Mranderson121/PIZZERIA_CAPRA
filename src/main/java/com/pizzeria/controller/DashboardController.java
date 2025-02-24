package com.pizzeria.controller;

import com.pizzeria.model.*;
import com.pizzeria.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

@Controller
public class DashboardController {

    @Autowired
    private ImpastoService impastoService;
    @Autowired
    private IngredienteService ingredienteService;
    @Autowired
    private PizzaService pizzaService;

    @GetMapping("/dashboard")
    public String showDashboard(@SessionAttribute(name = "utenteLoggato", required = true) Utente utenteLoggato, Model model) {
		if (utenteLoggato == null) {
			return "redirect:/login";
		}

        model.addAttribute("utenteLoggato", utenteLoggato);
        model.addAttribute("impasti", impastoService.getAllImpasti());
        model.addAttribute("ingredienti", ingredienteService.getAllIngredienti());
        model.addAttribute("pizze", pizzaService.getPizzeByUtente(utenteLoggato));

        return "dashboard";
    }

    @PostMapping("/dashboard")
    public String creaPizza(
            @RequestParam String pizzaName,
            @RequestParam int impastoId,
            @RequestParam Set<Integer> ingredientiId,
            @SessionAttribute(name = "utenteLoggato", required = true) Utente utenteLoggato) {

        if (utenteLoggato == null) {
            return "redirect:/login";
        }

        if (pizzaName.trim().isEmpty()) {
            return "redirect:/dashboard?error=NomePizzaVuoto";
        }

        boolean pizzaCreata=pizzaService.creaPizza(pizzaName, impastoId, ingredientiId, utenteLoggato);
        if(!pizzaCreata) {
        	 return "redirect:/dashboard?error=CreazioneFallita";
        }
        return "redirect:/dashboard?success=PizzaCreata";
    }

    @PostMapping("/eliminaPizza")
    public String eliminaPizza(@RequestParam int pizzaId) {
        pizzaService.eliminaPizza(pizzaId);
        return "redirect:/dashboard";
    }
    

    
}

