package com.pizzeria.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.pizzeria.dto.ImpastoDTO;
import com.pizzeria.dto.UtenteDTO;
import com.pizzeria.model.Impasto;
import com.pizzeria.model.Ingrediente;
import com.pizzeria.model.Pizza;
import com.pizzeria.model.Utente;
import com.pizzeria.util.JPAUtil;

public class Dao {

	public static Utente verificaCredenzialiUtente(String username, String password) {
		List<Utente> listaUtenti = new ArrayList<>();
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		TypedQuery<Utente> query = entityManager.createQuery(
				"select u from Utente u LEFT JOIN FETCH u.pizze where u.username = :username and u.password = :password",
				Utente.class).setParameter("username", username).setParameter("password", password);
		listaUtenti = query.getResultList();
		return listaUtenti.isEmpty() ? null : listaUtenti.get(0);
	}

	public static Set<Impasto> getAllImpasti() {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		TypedQuery<Impasto> query = entityManager.createQuery("select i from Impasto i", Impasto.class);
		return new HashSet<>(query.getResultList());
	}

	public static Set<Ingrediente> getAllIngredienti() {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		TypedQuery<Ingrediente> query = entityManager.createQuery("select i from Ingrediente i", Ingrediente.class);
		return new HashSet<>(query.getResultList());
	}

	public static Pizza aggiungiPizza(String pizzaName, String impastoId, String[] ingredientiIds, int utenteId) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();

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
		entityManager.getTransaction().commit();

		return nuovaPizza;
	}

	public static Impasto trovaImpastoPerId(int impastoId) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		return entityManager.find(Impasto.class, impastoId);
	}

	public static Utente getUtenteById(int utenteId) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		return entityManager.find(Utente.class, utenteId);
	}

	public static Pizza getPizzaById(int pizzaId) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		return entityManager.find(Pizza.class, pizzaId);
	}

	public static boolean pizzaEliminata(int pizzaId) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Pizza pizza = entityManager.find(Pizza.class, pizzaId);
		if (pizza != null) {
			entityManager.remove(pizza);
			transaction.commit();
			return true;
		} else {
			return false;
		}
	}

	public static Pizza pizzaAggiornata(String pizzaId, String pizzaName, String impastoId, String[] ingredientiIds,
			int utenteId) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();

		Pizza modificaPizza = entityManager.find(Pizza.class, Integer.valueOf(pizzaId));

		modificaPizza.setImpasto(entityManager.find(Impasto.class, Integer.valueOf(impastoId)));
		Set<Ingrediente> listaIngredienti = new HashSet<>();
		for (String id : ingredientiIds) {
			listaIngredienti.add(entityManager.find(Ingrediente.class, Integer.valueOf(id)));
		}
		modificaPizza.setIngredienti(listaIngredienti);
		modificaPizza.setUtente(entityManager.find(Utente.class, utenteId));
		modificaPizza.setNome(pizzaName);
		entityManager.getTransaction().commit();
		return modificaPizza;
	}

	public static List<Utente> getAllUtenti() {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		TypedQuery<Utente> query = entityManager.createQuery("SELECT u FROM Utente u", Utente.class);
		List<Utente> utenti = query.getResultList();
		entityManager.close();
		return utenti;
	}

	public static Utente addUtente(Utente utente) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();

		Utente nuovoUtente = new Utente();
		nuovoUtente.setUsername(utente.getUsername());
		nuovoUtente.setPassword(utente.getPassword());
		entityManager.persist(nuovoUtente);
		entityManager.getTransaction().commit();
		return nuovoUtente;
	}

	public static Ingrediente getIngredienteById(int idIngrediente) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		return entityManager.find(Ingrediente.class, Integer.valueOf(idIngrediente));

	}

	public static Impasto addImapsto(Impasto impasto) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();

		impasto.setNome(impasto.getNome());
		entityManager.persist(impasto);
		entityManager.getTransaction().commit();
		return impasto;

	}

	public static Ingrediente addIngrediente(Ingrediente ingrediente) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		ingrediente.setNome(ingrediente.getNome());
		entityManager.persist(ingrediente);
		entityManager.getTransaction().commit();
		return ingrediente;
	}

	public static UtenteDTO modificaUtente(int idUtente, UtenteDTO utenteDTO) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();

		Utente utente = entityManager.find(Utente.class, idUtente);

		utente.setUsername(utenteDTO.getUsername());
		utente.setPassword(utenteDTO.getPassword());

		entityManager.getTransaction().commit();

		UtenteDTO result = new UtenteDTO();
		result.setIdUtente(utente.getId());
		result.setUsername(utente.getUsername());
		result.setPassword(utente.getPassword());
		return result;
	}

	public static boolean modificaImpasto(int idImpasto, ImpastoDTO impastoDTO) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		Impasto modificaImpasto = entityManager.find(Impasto.class, idImpasto);
		if (modificaImpasto == null) {
			return false; 
		}
		modificaImpasto.setNome(impastoDTO.getNome());
		entityManager.getTransaction().commit();

		return true;
	}

	public static boolean modificaIngrediente(int idIngrediente, Ingrediente ingrediente) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		Ingrediente modificaIngrediente = entityManager.find(Ingrediente.class, idIngrediente);
		if (modificaIngrediente == null) {
			return false; 
		}
		modificaIngrediente.setNome(ingrediente.getNome());
		entityManager.getTransaction().commit();

		return true;
	}

	public static boolean eliminaUtente(int idUtente) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();

		Utente eliminaUtente = entityManager.find(Utente.class, idUtente);

		if (eliminaUtente != null) {
			entityManager.remove(eliminaUtente);
			entityManager.getTransaction().commit();
			return true;
		}

		entityManager.getTransaction().rollback();
		return false;
	}

	public static boolean eliminaImpasto(int idImpasto) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		
		Impasto eliminaImpasto= entityManager.find(Impasto.class, idImpasto);
		if(eliminaImpasto!=null) {
			entityManager.remove(eliminaImpasto);
			entityManager.getTransaction().commit();
			return true;
		}
		return false;
	}

	public static boolean eliminaIngrediente(int idIngrediente) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		
		Ingrediente eliminaIngrediente =entityManager.find(Ingrediente.class, idIngrediente);
		if(eliminaIngrediente!=null) {
			entityManager.remove(eliminaIngrediente);
			entityManager.getTransaction().commit();
			return true;
		}
		return false;
	}

}