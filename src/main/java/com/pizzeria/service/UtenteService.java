package com.pizzeria.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pizzeria.model.Utente;
import com.pizzeria.repository.UtenteRepository;

@Service
@Transactional
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    public Utente verificaCredenziali(String username, String password) {
        return utenteRepository.findByUsernameAndPassword(username, password);
    }
    public Set<Utente> getAllUtenti(){
    	return new HashSet<>(utenteRepository.findAll());
    	
    }

	public Utente addUtente(Utente utente) {
		if(utente==null) {
			throw new IllegalArgumentException("Utente non valido o username mancante");
		}
		Utente nuovoUtente=utenteRepository.save(utente);
		return nuovoUtente;
	}
	
	
	public Utente findUtenteById(int idUtente) {
		return utenteRepository.findById(idUtente) .orElseThrow(() -> new RuntimeException("Utente non trovato"));
	}
	public Utente modificaUtente(Utente utente, int idUtente) {
		
		Utente utenteModificato= findUtenteById(idUtente);
		utenteModificato.setPassword(utente.getPassword());
		utenteModificato.setUsername(utente.getUsername());
		utenteRepository.save(utenteModificato);
		
		
		return utenteModificato;
	}
	public Boolean eliminaUtente(int idUtente) {
		boolean eliminaUtente=utenteRepository.existsById(idUtente);
		if(!eliminaUtente) {
			return false;
		}
		utenteRepository.deleteById(idUtente);
		return true;
	}
}
