package com.pizzeria.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonManagedReference;

@XmlRootElement
@Entity
@NamedQuery(
	    name = "Impasto.getIdAndNome",
	    query = "SELECT i.idImpasto, i.nome FROM Impasto i")
@Table(name = "impasto")
public class Impasto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_impasto", nullable = false)
	private int idImpasto;

	@Column(name = "nome", nullable = false)
	private String nome;
	
	@JsonManagedReference
	@XmlTransient 
	@OneToMany(mappedBy = "impasto")
	private Set<Pizza> pizze;

	public int getIdImpasto() {
		return idImpasto;
	}

	public void setIdImpasto(int idImpasto) {
		this.idImpasto = idImpasto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<Pizza> getPizze() {
		return pizze;
	}

	public void setPizze(Set<Pizza> pizze) {
		this.pizze = pizze;
	}

}