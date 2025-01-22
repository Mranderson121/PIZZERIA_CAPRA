package com.pizzeria.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.pizzeria.model.Impasto;

@XmlRootElement


public class UtenteDTO {
	
	private int idUtente;
	private String username;
	private String password;
	private List<Impasto> impasti;
	
	
	public List<Impasto> getImpasti() {
		return impasti;
	}

	public void setImpasti(List<Impasto> impasti) {
		this.impasti = impasti;
	}

	public UtenteDTO(){
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UtenteDTO(int idUtente, String username) {
		this.idUtente=idUtente;
		this.username=username;
		
	}
}
