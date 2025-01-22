package com.pizzeria.dao;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.eclipse.persistence.jaxb.xmlmodel.ObjectFactory;

import com.pizzeria.dto.ImpastoDTO;
import com.pizzeria.dto.UtenteDTO;
import com.pizzeria.model.Impasto;
import com.pizzeria.model.Utente;
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
	
	public static List<UtenteDTO> getAllUtenti(){
		EntityManager entityManager= JPAUtil.getEntityManagerFactory().createEntityManager();
		List<Object[]> results= entityManager.createQuery("SELECT u.idUtente, u.username FROM Utente u", Object[].class)
			.getResultList();
		List<UtenteDTO> utenti = new ArrayList<>();

	    // Trasforma i risultati in UtenteDTO
	    for (Object[] result : results) {
	        int id = (int) result[0];
	        String username = (String) result[1];
	        utenti.add(new UtenteDTO(id, username));
	    }

	    entityManager.close(); // Chiudi l'EntityManager
	    return utenti; // 
		
	}
	
	public static UtenteDTO getUtenteById(int id) {
		EntityManager entityManager= JPAUtil.getEntityManagerFactory().createEntityManager();
		 Utente utente = entityManager.find(Utente.class, id);
		 return new UtenteDTO(utente.getId(),utente.getUsername());
	}

	public static ImpastoDTO getImpastoById(int idImpasto) {
		EntityManager entityManager= JPAUtil.getEntityManagerFactory().createEntityManager();
		Impasto impasto = entityManager.find(Impasto.class, Integer.valueOf(idImpasto));
		return new ImpastoDTO(impasto.getIdImpasto(),impasto.getNome());
	}
	
	
	
	
}
