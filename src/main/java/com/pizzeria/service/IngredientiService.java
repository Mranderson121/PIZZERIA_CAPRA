package com.pizzeria.service;

import java.util.Set;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;

import com.pizzeria.dao.Dao;
import com.pizzeria.model.Ingrediente;

@WebService
public class IngredientiService {

	@EJB
	private Dao Dao;

	@WebMethod
	public Set<Ingrediente> getIngredienti() {
		return Dao.getAllIngredienti();
	}

	@WebMethod
	public Ingrediente getIngredienteById(int idIngrediente) {
		return Dao.getIngredienteById(idIngrediente);
	}

	@WebMethod
	public Ingrediente addIngrediente(Ingrediente ingrediente) {
		return Dao.addIngrediente(ingrediente);
	}

	@WebMethod
	public String modificaIngrediente(int idIngrediente, Ingrediente ingrediente) {
		boolean modificatoIngrediente = Dao.modificaIngrediente(idIngrediente, ingrediente);

		if (modificatoIngrediente) {
			return "Ingrediente Modificato.";
		} else {
			return "Ingrediente non trovato o modifica fallita.";
		}
	}

	@WebMethod
	public String eliminaIngrediente(int idIngrediente) {
		boolean eliminaIngrediente = Dao.eliminaIngrediente(idIngrediente);

		if (eliminaIngrediente) {
			return "Ingrediente Eliminato";
		} else {
			return "Ingrediente non trovato";
		}
	}

}
