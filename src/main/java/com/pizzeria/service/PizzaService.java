package com.pizzeria.service;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pizzeria.model.Impasto;
import com.pizzeria.model.Ingrediente;
import com.pizzeria.model.Pizza;
import com.pizzeria.model.Utente;
import com.pizzeria.repository.ImpastoRepository;
import com.pizzeria.repository.PizzaRepository;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PizzaService {
	@Autowired
	private PizzaRepository pizzaRepository;
	@Autowired
	private ImpastoService impastoService;
	@Autowired 
	private IngredienteService ingredienteService;
		
	public List<Pizza> getPizzeByUtente(Utente utente) {
        return pizzaRepository.findByUtente(utente);

    }

	public boolean creaPizza(String pizzaName, int impastoId, Set<Integer> ingredientiId, Utente utenteLoggato) {
		Pizza nuovaPizza = new Pizza();
		nuovaPizza.setNome(pizzaName);
		Set<Ingrediente> ingredienti = ingredienteService.getAllIngredientiById(ingredientiId);
		nuovaPizza.setIngredienti(ingredienti);
		Impasto nuovoImpasto= impastoService.findImpastoById(impastoId);
		nuovaPizza.setImpasto(nuovoImpasto);
		nuovaPizza.setUtente(utenteLoggato);
		if(pizzaRepository.save(nuovaPizza) != null) {
			return true ;
			
		}else {
			return false;
		}
	}

	public boolean eliminaPizza(int pizzaId) {
		if (pizzaRepository.existsById(pizzaId)) {
            pizzaRepository.deleteById(pizzaId);
            return true; 
        } else {
            return false; 
        }
		
	}

	public Pizza findPizzaById(int pizzaId) {
	    return pizzaRepository.findById(pizzaId)
	                          .orElseThrow(() -> new IllegalArgumentException("Pizza not found"));
	}

	public Pizza modificaPizza(int pizzaId, String pizzaName, int impastoId, Set<Integer> ingredientiId,
			Utente utenteLoggato) {
		Pizza pizzaAggiornata=findPizzaById(pizzaId);
		pizzaAggiornata.setNome(pizzaName);
		Impasto impasto = impastoService.findImpastoById(impastoId);
		pizzaAggiornata.setImpasto(impasto);
		Set<Ingrediente> ingredienti = ingredienteService.getAllIngredientiById(ingredientiId);
		
		pizzaAggiornata.setIngredienti(ingredienti);
		pizzaAggiornata.setUtente(utenteLoggato);
		return pizzaRepository.save(pizzaAggiornata);
	}

}
