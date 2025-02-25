package com.pizzeria.controller;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pizzeria.dto.ImpastoDTO;
import com.pizzeria.model.Impasto;
import com.pizzeria.model.Utente;
import com.pizzeria.service.ImpastoService;

@RestController
@RequestMapping("/api")
public class ImpastiController {
	@Autowired
	private ImpastoService impastoService;

	@GetMapping("/impasti")
	public Set<ImpastoDTO> getAllImpasti() {

		return impastoService.getAllImpasti().stream()
				.map(impasto -> new ImpastoDTO(impasto.getIdImpasto(), impasto.getNome())).collect(Collectors.toSet());

	}

	@GetMapping("/impasto/{id}")
	public ResponseEntity<ImpastoDTO> getImpastoById(@PathVariable(value = "id") int idImpasto) {
		Impasto impasto = impastoService.findImpastoById(idImpasto);

		if (impasto != null) {
			ImpastoDTO impastoDTO = new ImpastoDTO(impasto.getIdImpasto(), impasto.getNome());
			return ResponseEntity.ok(impastoDTO);
		}	

		return ResponseEntity.notFound().build();

	}
	
	@PostMapping("/aggiungiImpasto")
	public ResponseEntity<ImpastoDTO> aggiungiImpasto(@RequestBody Impasto impasto){
		Impasto aggiungiImpasto= impastoService.aggiungiImpasto(impasto);
		ImpastoDTO nuovoImpastoDTO= new ImpastoDTO(aggiungiImpasto.getIdImpasto(),aggiungiImpasto.getNome());
		return ResponseEntity.ok(nuovoImpastoDTO);
		
	}
	
	@PutMapping("/modificaImpasto/{id}")
	public ResponseEntity<ImpastoDTO> modificaImpasto(@PathVariable(value="id") int idImpasto, @RequestBody Impasto impastoModificato){
		ImpastoDTO impasto1=impastoService.modificaImpasto(idImpasto,impastoModificato);
		
		return ResponseEntity.ok(impasto1);
	}
	
	@DeleteMapping("/eliminaImpasto/{id}")
	public ResponseEntity<?> eliminaImpasto(@PathVariable(value="id") int idImpasto){
		boolean impastoEliminato=impastoService.eliminaImpasto(idImpasto);
		if(impastoEliminato) {
			return ResponseEntity.ok("Impasto Eliminato");
		}else{
			
			return ResponseEntity.notFound().build();
		}
	}
	
	

}
