package com.pizzeria.dao;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.pizzeria.dto.ImpastoDTO;
import com.pizzeria.model.Impasto;
import com.pizzeria.util.JPAUtil;
public class DTODao {
	
	public static Set<ImpastoDTO> getAllImpasti() {
	    EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    
	    // Query per selezionare i campi desiderati
	    List<Object[]> results = entityManager.createQuery(
	        "SELECT i.idImpasto, i.nome FROM Impasto i", Object[].class)
	        .getResultList();
	    
	    // Trasformare i risultati in oggetti ImpastoDTO
	    Set<ImpastoDTO> impastiDTO = results.stream()
	        .map(result -> new ImpastoDTO((int) result[0], (String) result[1]))
	        .collect(Collectors.toSet());
	    
	    return impastiDTO;
	}
}
