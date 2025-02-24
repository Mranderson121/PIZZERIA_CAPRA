package com.pizzeria.service;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pizzeria.model.Impasto;
import com.pizzeria.repository.ImpastoRepository;

@Service
@Transactional
public class ImpastoService {
	
	@Autowired
	private ImpastoRepository repo;
	
	
	public Set<Impasto> getAllImpasti(){
		 return new HashSet<>(repo.findAll());
		 }
	public Impasto findImpastoById(int impastoId) {
	    return repo.findById(impastoId)
	            .orElseThrow(() -> new EntityNotFoundException("Impasto non trovato con ID: " + impastoId));
	}
}