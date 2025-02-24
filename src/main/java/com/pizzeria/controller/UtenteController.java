package com.pizzeria.controller;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pizzeria.dto.PizzaDTO;
import com.pizzeria.dto.UtenteDTO;
import com.pizzeria.model.Utente;
import com.pizzeria.repository.UtenteRepository;
import com.pizzeria.service.UtenteService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UtenteController {

	@Autowired
	private UtenteRepository utenteRepository;

	@GetMapping("/utente")
	public Set<UtenteDTO> getAllUtenti() {
		return utenteRepository.findAll().stream().map(utente -> new UtenteDTO(utente.getId(), utente.getUsername()))
				.collect(Collectors.toSet());
		// ci permette di convertire ogni utente trovato in un nuovo utenteDTO,
		//per evitare i loop
	}
	
	
	@GetMapping("/utente/{id}")
	public ResponseEntity<UtenteDTO> getUtenteById(@PathVariable(value = "id") int idUtente) {
	    return utenteRepository.findById(idUtente)
	            .map(utente -> {// se l'utente viene trovato 
	                Set<PizzaDTO> pizzaDTOs = utente.getPizze()
	                        .stream()
	                        .map(pizza -> new PizzaDTO(pizza.getIdPizza(), pizza.getNome()))
	                        .collect(Collectors.toSet());

	                UtenteDTO utenteDTO = new UtenteDTO(utente.getId(), utente.getUsername(), pizzaDTOs);
	                return ResponseEntity.ok(utenteDTO);
	            })
	            .orElse(ResponseEntity.notFound().build());
	}


}