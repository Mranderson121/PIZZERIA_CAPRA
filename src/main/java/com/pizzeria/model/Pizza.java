package com.pizzeria.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "pizza")
public class Pizza implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pizza", nullable = false)
	private int idPizza;

	@Column(name = "nome", nullable = false)
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "id_impasto", nullable = false)
	private Impasto impasto;

	
	@XmlTransient 
	@ManyToOne
	@JoinColumn(name = "id_utente", nullable = false)
	private Utente utente;
	
	
	@XmlTransient 
	@OneToMany(fetch = FetchType.EAGER )
	@JoinTable(name = "pizza_ingrediente", joinColumns = @JoinColumn(name = "id_pizza"), inverseJoinColumns = @JoinColumn(name = "id_ingrediente"))
	private Set<Ingrediente> ingredienti;

	// Costruttori, getter e setter
	public Pizza() {
	}

	public Pizza(String nome, Impasto impasto, Utente utente) {
		this.nome = nome;
		this.impasto = impasto;
		this.utente = utente;
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

	public Impasto getImpasto() {
		return impasto;
	}

	public void setImpasto(Impasto impasto) {
		this.impasto = impasto;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Set<Ingrediente> getIngredienti() {
		return ingredienti;
	}

	public void setIngredienti(Set<Ingrediente> ingredienti) {
		this.ingredienti = ingredienti;
	}
	
	@Override
    public String toString() {
        return nome;  // Per stampare il nome della pizza
    }

}