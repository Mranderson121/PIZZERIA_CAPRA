package com.pizzeria.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.pizzeria.model.Pizza;

@XmlRootElement
public class PizzaDTO implements Serializable {

	private static final long serialVersionUID = 2126909939726769935L;
	
	public PizzaDTO() {
		
	}
	
	public PizzaDTO(int idPizza, String nome) {
		this.idPizza=idPizza;
		this.nome=nome;
    }
	public int getIdPizza() {
		return idPizza;
	}
	public void setIdPizza(int idPizza) {
		this.idPizza = idPizza;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	private int idPizza;
	private String nome;
	
	public PizzaDTO(Pizza pizza) {
        this.idPizza = pizza.getIdPizza();
        this.nome = pizza.getNome();
    }

	
}
