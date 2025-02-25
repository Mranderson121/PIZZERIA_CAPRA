package com.pizzeria.service;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pizzeria.model.Impasto;
import com.pizzeria.repository.ImpastoRepository;
import com.pizzeria.dto.*;

@Service
@Transactional
public class ImpastoService {
	
	@Autowired
	private ImpastoRepository impastoRepository;
	
	
	public Set<Impasto> getAllImpasti(){
		 return new HashSet<>(impastoRepository.findAll());
		 }
	public Impasto findImpastoById(int impastoId) {
	    return impastoRepository.findById(impastoId)
	            .orElseThrow(() -> new EntityNotFoundException("Impasto non trovato con ID: " + impastoId));
	}
	public ImpastoDTO modificaImpasto(int idImpasto, Impasto impasto) {
		Impasto impastoModificato= impastoRepository.findById(idImpasto)
				.orElseThrow(() -> new RuntimeException("Impasto non trovato"));
		impastoModificato.setNome(impasto.getNome());
		impastoRepository.save(impastoModificato);
		return new ImpastoDTO(impastoModificato.getIdImpasto(),impastoModificato.getNome());
	}
	public Impasto aggiungiImpasto(Impasto impasto) {
		Impasto impastoNuovo= new Impasto();
		impastoNuovo.setNome(impasto.getNome());
		impastoRepository.save(impastoNuovo);
		return impastoNuovo;
	}
	public boolean eliminaImpasto(int impastoId) {
		Impasto impastoEliminato=impastoRepository.findById(impastoId)
				.orElseThrow(() -> new EntityNotFoundException("Impasto non trovato"+ impastoId));
		if (impastoEliminato != null) {
			impastoRepository.deleteById(impastoId);
			return true;
			
		}
		return false;
	}
	
}