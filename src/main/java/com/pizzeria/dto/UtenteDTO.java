package com.pizzeria.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import com.pizzeria.model.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UtenteDTO implements Serializable {

	private static final long serialVersionUID = 8777458183118839081L;
	private int idUtente;
	private String username;
	private Set<PizzaDTO> listaPizze;

	public Set<PizzaDTO> getListaPizze() {
		return listaPizze;
	}

	public void setListaPizze(Set<PizzaDTO> listaPizze) {
		this.listaPizze = listaPizze;
	}

	public UtenteDTO() {
	}

	public int getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public UtenteDTO(int idUtente, String username) {
		this.idUtente = idUtente;
		this.username = username;
	}
	
	public UtenteDTO(int idUtente, String username,Set<PizzaDTO> listaPizze) {
		this.idUtente = idUtente;
		this.username = username;
		this.listaPizze=listaPizze;
	}
	public UtenteDTO(Utente utente) {
        this.idUtente = utente.getId();
        this.username = utente.getUsername();
        this.listaPizze = utente.getPizze().stream()
            .map(PizzaDTO::new)
            .collect(Collectors.toSet());
    }
}