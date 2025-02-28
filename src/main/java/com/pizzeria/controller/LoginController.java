package com.pizzeria.controller;

import java.io.IOException;
import java.util.Set;

import javax.servlet.http.HttpSession;

import com.pizzeria.model.Impasto;
import com.pizzeria.model.Ingrediente;
import com.pizzeria.model.Pizza;
import com.pizzeria.model.Utente;
import com.pizzeria.service.UtenteService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	private static final long serialVersionUID = 1L;

	public LoginController() {
		super();
	}

	@Autowired
	private UtenteService utenteService;

	@GetMapping("/login")
	public String showLoginForm() {
		return "login"; // Nome della pagina JSP o Thymeleaf (es: login.jsp o login.html)
	}

	@PostMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password, HttpSession session,
			Model model) {
		Utente utente = utenteService.verificaCredenziali(username, password);

		if (utente != null) {
			session.setAttribute("utenteLoggato", utente);
			return "redirect:/dashboard"; // Dopo il login, reindirizza alla dashboard
		} else {
			model.addAttribute("errore", "Credenziali errate!");
			return "login"; // Torna alla pagina di login con messaggio di errore
		}
	}
}