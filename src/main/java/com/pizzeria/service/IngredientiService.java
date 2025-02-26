package com.pizzeria.service;

import java.util.Set;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pizzeria.dao.Dao;
import com.pizzeria.dto.IngredienteDTO;
import com.pizzeria.model.Ingrediente;

@WebService
@Component
public class IngredientiService {
	@Autowired
	private IngredienteService ingredienteService;

	@WebMethod
	public Set<Ingrediente> getIngredienti() {
		return ingredienteService.getAllIngredienti();	}

	@WebMethod
	public IngredienteDTO getIngredienteById(int idIngrediente) {
		return ingredienteService.getIngredienteById(idIngrediente);
	}

	@WebMethod
	public IngredienteDTO addIngrediente(IngredienteDTO ingrediente) {
		return ingredienteService.addIngrediente(ingrediente);
	}

	@WebMethod
	public IngredienteDTO modificaIngrediente(int idIngrediente, IngredienteDTO ingrediente) {
		return ingredienteService.modificaIngrediente(idIngrediente, ingrediente );

		
	}

	@WebMethod
	public String eliminaIngrediente(int idIngrediente) {
		boolean eliminaIngrediente = ingredienteService.eliminaIngrediente(idIngrediente);

		if (eliminaIngrediente) {
			return "Ingrediente Eliminato";
		} else {
			return "Ingrediente non trovato";
		}
	}

}
