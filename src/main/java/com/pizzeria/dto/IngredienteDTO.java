package com.pizzeria.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class IngredienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idIngrediente;
	private String nome;

	public IngredienteDTO() {

	}

	public IngredienteDTO(int idIngrediente, String nome) {
		this.idIngrediente = idIngrediente;
		this.nome = nome;
	}

	public int getIdIngrediente() {
		return idIngrediente;
	}

	public void setIdIngrediente(int idIngrediente) {
		this.idIngrediente = idIngrediente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
