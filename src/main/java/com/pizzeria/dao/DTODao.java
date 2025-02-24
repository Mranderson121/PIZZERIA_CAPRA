package com.pizzeria.dao;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.pizzeria.dto.ImpastoDTO;
import com.pizzeria.dto.UtenteDTO;
import com.pizzeria.model.Impasto;
import com.pizzeria.model.Utente;
import com.pizzeria.dto.*;


public class DTODao {
	
	@PersistenceContext
	private EntityManager entityManager;

	public List<ImpastoDTO> getAllImpasti() {
		List<ImpastoDTO> results = entityManager
				.createQuery("SELECT new com.pizzeria.dto.ImpastoDTO(i.idImpasto, i.nome) FROM Impasto i",
						ImpastoDTO.class)
				.getResultList();
		return results;
	}

	public List<UtenteDTO> getAllUtenti() {
		List<UtenteDTO> results = entityManager
				.createQuery("SELECT new com.pizzeria.dto.UtenteDTO(u.idUtente, u.username) FROM Utente u",
						UtenteDTO.class)
				.getResultList();
		return results;
	}

	public UtenteDTO getUtenteById(int id) {
		Utente utente = entityManager.find(Utente.class, id);
		return new UtenteDTO(utente);
	}

	public ImpastoDTO getImpastoById(int idImpasto) {
		Impasto impasto = entityManager.find(Impasto.class, Integer.valueOf(idImpasto));
		return new ImpastoDTO(impasto.getIdImpasto(), impasto.getNome());
	}

	public UtenteDTO getUtentePizze(int id) {
	    Utente utente = entityManager.find(Utente.class, id);
	    return new UtenteDTO(utente); 
	}


}