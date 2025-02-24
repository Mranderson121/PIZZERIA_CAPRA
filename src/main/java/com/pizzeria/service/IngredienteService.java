package com.pizzeria.service;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pizzeria.model.Ingrediente;
import com.pizzeria.repository.IngredienteRepository;

@Service
@Transactional
public class IngredienteService {
	
	@Autowired
	private IngredienteRepository repo;
	
	 public Set<Ingrediente> getAllIngredienti() {
	        return new HashSet<>(repo.findAll());
	    }
	 public Set<Ingrediente> getAllIngredientiById(Set<Integer> idIngrediente) {
		    return  repo.findByIdIn(idIngrediente);
		}

}
