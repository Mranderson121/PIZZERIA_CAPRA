package com.pizzeria.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.pizzeria.dto.ImpastoDTO;
import com.pizzeria.dto.UtenteDTO;
import com.pizzeria.model.Impasto;
import com.pizzeria.model.Ingrediente;
import com.pizzeria.model.Pizza;
import com.pizzeria.model.Utente;

public class Dao {

	@PersistenceContext
	private EntityManager entityManager;

	public Utente verificaCredenzialiUtente(String username, String password) {
		List<Utente> listaUtenti = new ArrayList<>();
		TypedQuery<Utente> query = entityManager.createQuery(
				"SELECT u FROM Utente u LEFT JOIN FETCH u.pizze WHERE u.username = :username AND u.password = :password",
				Utente.class).setParameter("username", username).setParameter("password", password);
		listaUtenti = query.getResultList();
		return listaUtenti.isEmpty() ? null : listaUtenti.get(0);

	}

	public Set<Impasto> getAllImpasti() {
		TypedQuery<Impasto> query = entityManager.createQuery("select i from Impasto i", Impasto.class);
		return new HashSet<>(query.getResultList());
	}

	public Set<Ingrediente> getAllIngredienti() {
		TypedQuery<Ingrediente> query = entityManager.createQuery("select i from Ingrediente i", Ingrediente.class);
		return new HashSet<>(query.getResultList());
	}

	public Pizza aggiungiPizza(String pizzaName, String impastoId, String[] ingredientiIds, int utenteId) {

		Pizza nuovaPizza = new Pizza();
		nuovaPizza.setNome(pizzaName);
		nuovaPizza.setImpasto(entityManager.find(Impasto.class, Integer.valueOf(impastoId)));
		Set<Ingrediente> listaIngredienti = new HashSet<>();
		for (String id : ingredientiIds) {
			listaIngredienti.add(entityManager.find(Ingrediente.class, Integer.valueOf(id)));
		}
		nuovaPizza.setIngredienti(listaIngredienti);
		nuovaPizza.setUtente(entityManager.find(Utente.class, utenteId));

		entityManager.persist(nuovaPizza);
		return nuovaPizza;
	}

	public Impasto trovaImpastoPerId(int impastoId) {
		return entityManager.find(Impasto.class, impastoId);
	}

	public Utente getUtenteById(int utenteId) {
		return entityManager.find(Utente.class, utenteId);
	}

	public Pizza getPizzaById(int pizzaId) {
		return entityManager.find(Pizza.class, pizzaId);
	}

	public boolean pizzaEliminata(int pizzaId) {
		Pizza pizza = entityManager.find(Pizza.class, pizzaId);
		if (pizza != null) {
			entityManager.remove(pizza);
			return true;
		} else {
			return false;
		}
	}

	public Pizza pizzaAggiornata(String pizzaId, String pizzaName, String impastoId, String[] ingredientiIds,
			int utenteId) {

		Pizza modificaPizza = entityManager.find(Pizza.class, Integer.valueOf(pizzaId));

		modificaPizza.setImpasto(entityManager.find(Impasto.class, Integer.valueOf(impastoId)));
		Set<Ingrediente> listaIngredienti = new HashSet<>();
		for (String id : ingredientiIds) {
			listaIngredienti.add(entityManager.find(Ingrediente.class, Integer.valueOf(id)));
		}
		modificaPizza.setIngredienti(listaIngredienti);
		modificaPizza.setUtente(entityManager.find(Utente.class, utenteId));
		modificaPizza.setNome(pizzaName);
		return modificaPizza;
	}

	public List<Utente> getAllUtenti() {
		TypedQuery<Utente> query = entityManager.createQuery("SELECT u FROM Utente u", Utente.class);
		List<Utente> utenti = query.getResultList();
		return utenti;
	}

	public Utente addUtente(Utente utente) {

		Utente nuovoUtente = new Utente();
		nuovoUtente.setUsername(utente.getUsername());
		nuovoUtente.setPassword(utente.getPassword());
		entityManager.persist(nuovoUtente);
		return nuovoUtente;
	}

	public Ingrediente getIngredienteById(int idIngrediente) {
		return entityManager.find(Ingrediente.class, Integer.valueOf(idIngrediente));
	}

	public Impasto addImapsto(Impasto impasto) {
		impasto.setNome(impasto.getNome());
		entityManager.persist(impasto);
		return impasto;
	}

	public Ingrediente addIngrediente(Ingrediente ingrediente) {
		ingrediente.setNome(ingrediente.getNome());
		entityManager.persist(ingrediente);
		return ingrediente;
	}

	public UtenteDTO modificaUtente(int idUtente, UtenteDTO utenteDTO) {

		Utente utente = entityManager.find(Utente.class, idUtente);

		utente.setUsername(utenteDTO.getUsername());

		UtenteDTO result = new UtenteDTO();
		result.setIdUtente(utente.getId());
		result.setUsername(utente.getUsername());
		return result;
	}

	public boolean modificaImpasto(int idImpasto, ImpastoDTO impastoDTO) {

		Impasto modificaImpasto = entityManager.find(Impasto.class, idImpasto);
		if (modificaImpasto == null) {
			return false;
		}
		modificaImpasto.setNome(impastoDTO.getNome());

		return true;
	}

	public boolean modificaIngrediente(int idIngrediente, Ingrediente ingrediente) {

		Ingrediente modificaIngrediente = entityManager.find(Ingrediente.class, idIngrediente);
		if (modificaIngrediente == null) {
			return false;
		}
		modificaIngrediente.setNome(ingrediente.getNome());

		return true;
	}

	public boolean eliminaUtente(int idUtente) {

		Utente eliminaUtente = entityManager.find(Utente.class, idUtente);

		if (eliminaUtente != null) {
			entityManager.remove(eliminaUtente);
			return true;
		}
		return false;
	}

	public boolean eliminaImpasto(int idImpasto) {

		Impasto eliminaImpasto = entityManager.find(Impasto.class, idImpasto);
		if (eliminaImpasto != null) {
			entityManager.remove(eliminaImpasto);
			return true;
		}
		return false;
	}

	public boolean eliminaIngrediente(int idIngrediente) {
		entityManager.createNativeQuery("DELETE FROM pizza_ingrediente WHERE id_ingrediente = ?")
	      .setParameter(1, idIngrediente)
	      .executeUpdate();

		Ingrediente eliminaIngrediente = entityManager.find(Ingrediente.class, idIngrediente);
		if (eliminaIngrediente != null) {
			entityManager.remove(eliminaIngrediente);
			return true;
		}
		return false;
	}
	

}