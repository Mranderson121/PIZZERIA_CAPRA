package com.pizzeria.controller;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	private UtenteService utenteService;


	@GetMapping("/utente")
	public Set<UtenteDTO> getAllUtenti() {
		return utenteService.getAllUtenti().stream().map(utente -> new UtenteDTO(utente.getId(), utente.getUsername()))
				.collect(Collectors.toSet());
		// ci permette di convertire ogni utente trovato in un nuovo utenteDTO,
		//per evitare i loop
	}
	
	
	@GetMapping("/utente/{id}")
	public ResponseEntity<UtenteDTO> getUtenteById(@PathVariable(value = "id") int idUtente) {
	    Utente utente = utenteService.findUtenteById(idUtente); 
	    if (utente != null) {
	        Set<PizzaDTO> pizzaDTOs = utente.getPizze()
	                .stream()
	                .map(pizza -> new PizzaDTO(pizza.getIdPizza(), pizza.getNome()))
	                .collect(Collectors.toSet());

	        UtenteDTO utenteDTO = new UtenteDTO(utente.getId(), utente.getUsername(), pizzaDTOs);
	        return ResponseEntity.ok(utenteDTO);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	@PostMapping("/utente/addUtente")
	public ResponseEntity<Utente> addUtente(@RequestBody Utente utente) {
		Utente nuovoUtente= utenteService.addUtente(utente);
		return ResponseEntity.ok(nuovoUtente);
	}
	@PutMapping("/utente/{id}")
	public ResponseEntity<UtenteDTO> modificaUtente(@RequestBody Utente utente,@PathVariable(value = "id") int idUtente){
		Utente modificaUtente= utenteService.modificaUtente(utente,idUtente);
		 UtenteDTO utenteDTO = new UtenteDTO(modificaUtente.getId(), modificaUtente.getUsername());
		    // Restituisci il DTO come risposta
		    return ResponseEntity.ok(utenteDTO);
	}
	
	@DeleteMapping("/eliminaUtente/{id}")
	public ResponseEntity<?> eliminaUtente(@PathVariable(value="id") int idUtente){
		Boolean eliminaUtente= utenteService.eliminaUtente(idUtente);
		if(!eliminaUtente) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().build();
		
	}
	
	


}