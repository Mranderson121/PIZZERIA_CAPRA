package com.pizzeria.service;

import javax.transaction.Transactional;

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
}
