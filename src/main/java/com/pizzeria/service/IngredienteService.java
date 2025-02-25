package com.pizzeria.service;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pizzeria.dto.IngredienteDTO;
import com.pizzeria.model.Ingrediente;
import com.pizzeria.repository.IngredienteRepository;

@Service
@Transactional
public class IngredienteService {

	@Autowired
	private IngredienteRepository ingredienteRepository;
	@Autowired
	private IngredienteService ingredienteService;

	public Set<Ingrediente> getAllIngredienti() {
		return new HashSet<>(ingredienteRepository.findAll());
	}

	public Set<Ingrediente> getAllIngredientiById(Set<Integer> idIngrediente) {
		return ingredienteRepository.findByIdIn(idIngrediente);
	}

	public IngredienteDTO getIngredienteById(int idIngrediente) {
		Ingrediente ingrediente = ingredienteRepository.findById(idIngrediente)
				.orElseThrow(() -> new RuntimeException("Ingrediente non trovato"));
		IngredienteDTO ingredienteDTO = new IngredienteDTO(ingrediente.getIdIngrediente(), ingrediente.getNome());
		return ingredienteDTO;
	}

	public IngredienteDTO addIngrediente(IngredienteDTO ingredienteDTO) {
		if (ingredienteDTO.getNome() == null || ingredienteDTO.getNome().trim().isEmpty()) {
			throw new IllegalArgumentException("Il nome dell'ingrediente non può essere vuoto");
		}

		Ingrediente nuovoIngrediente = new Ingrediente();
		nuovoIngrediente.setNome(ingredienteDTO.getNome());
		ingredienteRepository.save(nuovoIngrediente);
		return new IngredienteDTO(nuovoIngrediente.getIdIngrediente(), nuovoIngrediente.getNome());
	}

	public boolean eliminaIngrediente(int idIngrediente) {
		Ingrediente eliminaIngrediente = ingredienteRepository.findById(idIngrediente)
				.orElseThrow(() -> new RuntimeException("Ingrediente non trovato"));
		ingredienteRepository.delete(eliminaIngrediente);
		return true;
	}

	public IngredienteDTO modificaIngrediente(int idIngrediente, IngredienteDTO ingredienteDTO) {
		Ingrediente ingrediente = ingredienteRepository.findById(idIngrediente)
				.orElseThrow(() -> new RuntimeException("Ingrediente non trovato"));
		ingrediente.setNome(ingredienteDTO.getNome());
		ingrediente = ingredienteRepository.save(ingrediente);
		return new IngredienteDTO(ingrediente.getIdIngrediente(), ingrediente.getNome());
	}

}
