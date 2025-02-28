package com.pizzeria.repository;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.TypedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pizzeria.model.Ingrediente;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Integer> {
	@Query("SELECT i FROM Ingrediente i WHERE i.idIngrediente IN :ids")
	Set<Ingrediente> findByIdIn(@Param("ids") Set<Integer> ids);
}
