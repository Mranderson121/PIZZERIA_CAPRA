package com.pizzeria.controller;

import com.pizzeria.model.Ingrediente;
import com.pizzeria.service.IngredienteService;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pizzeria.dto.IngredienteDTO;

@RestController
@RequestMapping("/api")
public class IngredienteController {
	@Autowired
	private IngredienteService ingredienteService;

	@GetMapping("/Ingredienti")
	public ResponseEntity<Set<Ingrediente>> getAllIngredienti() {
		Set<Ingrediente> tuttiIngredienti = ingredienteService.getAllIngredienti();
		return ResponseEntity.ok(tuttiIngredienti);
	}

	@GetMapping("/ingrediente/{id}")
	public ResponseEntity<IngredienteDTO> getIngredienteById(@PathVariable(value = "id") int idIngrediente) {
		IngredienteDTO ingrediente = ingredienteService.getIngredienteById(idIngrediente);

		return ResponseEntity.ok(ingrediente);
	}

	@PostMapping("/addIngrediente")
	public ResponseEntity<IngredienteDTO> addIngrediente(@RequestBody IngredienteDTO ingredienteDTO) {
		IngredienteDTO ingredientedto = ingredienteService.addIngrediente(ingredienteDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(ingredientedto);
	}

	@DeleteMapping("/eliminaIngrediente/{id}")
	public ResponseEntity<?> eliminaUtente(@PathVariable(value = "id") int idIngrediente) {
		boolean eliminaIngrediente = ingredienteService.eliminaIngrediente(idIngrediente);
		if (eliminaIngrediente) {
			return ResponseEntity.ok(eliminaIngrediente);

		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@PutMapping("/modificaIngrediente/{id}")
	public ResponseEntity<IngredienteDTO> modificaIngrediente(@PathVariable(value = "id") int idIngrediente,
			@RequestBody IngredienteDTO ingredienteDTO) {
		IngredienteDTO ingredienteModificato = ingredienteService.modificaIngrediente(idIngrediente, ingredienteDTO);
		return ResponseEntity.ok(ingredienteModificato);
	}

}
