package com.pizzeria.service;

import java.util.List;
import java.util.Set;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.pizzeria.dao.DTODao;
import com.pizzeria.dao.Dao;
import com.pizzeria.dto.ImpastoDTO;
import com.pizzeria.dto.UtenteDTO;
import com.pizzeria.model.Impasto;
import com.pizzeria.model.Ingrediente;
import com.pizzeria.model.Utente;

@Path("/pizzeria")
public class TestService {
	// Read di tutti gli ingredienti
	@GET
	@Path("/ingredienti")
	@Produces(MediaType.APPLICATION_JSON)
	public Set<Ingrediente> getIngredienti() {
		return Dao.getAllIngredienti();
	}

	@GET
	@Path("/ingredienti/{idIngrediente}")
	@Produces(MediaType.APPLICATION_JSON)
	public Ingrediente getIngredienteById(@PathParam("idIngrediente") int idIngrediente) {
		return Dao.getIngredienteById(idIngrediente);
	}

	// Recupera tutti gli impasti
	@GET
	@Path("/impasti")
	@Produces(MediaType.APPLICATION_JSON)
	public Set<ImpastoDTO> getAllImpasti() {
		return DTODao.getAllImpasti(); // Metodo che recupera gli impasti dal database
	}

	@GET
	@Path("/impasti/{idImpasto}")
	@Produces(MediaType.APPLICATION_JSON)
	public ImpastoDTO getImpastoById(@PathParam("idImpasto") int idImpasto) {
		return DTODao.getImpastoById(idImpasto);

	}

	// Recupera tutti gli utenti
	@GET
	@Path("/utenti")
	@Produces(MediaType.APPLICATION_JSON)
	public List<UtenteDTO> getAllUtenti() {
		return DTODao.getAllUtenti(); // Metodo che recupera gli utenti dal database
	}

	@GET
	@Path("/utenti/{idUtente}")
	@Produces(MediaType.APPLICATION_JSON)
	public UtenteDTO getUtente(@PathParam("idUtente") int idUtente) {
		int id = Integer.valueOf(idUtente);
		return DTODao.getUtenteById(id);
	}

	@POST
	@Path("/utenti/addUtente")
	@Produces(MediaType.APPLICATION_JSON)
	public Utente addUtente(Utente utente) {
		return Dao.addUtente(utente);
	}

	@POST
	@Path("/impasti/addImpasto")
	@Produces(MediaType.APPLICATION_JSON)
	public Impasto addImpasto(Impasto Impasto) {
		return Dao.addImapsto(Impasto);
	}

	@POST
	@Path("/ingredienti/addIngredienti")
	@Produces(MediaType.APPLICATION_JSON)
	public Ingrediente addIngrediente(Ingrediente ingrediente) {
		return Dao.addIngrediente(ingrediente);
	}

	@PUT
	@Path("/utente/{idUtente}")
	@Produces(MediaType.APPLICATION_JSON)
	public UtenteDTO modificaUtente(@PathParam("idUtente") int idUtente, UtenteDTO utenteDTO) {
		return Dao.modificaUtente(idUtente, utenteDTO);
	}

	@PUT
	@Path("/impasti/{idImpasto}")
	@Produces(MediaType.APPLICATION_JSON)
	public String modificaImpasto(@PathParam("idImpasto") int idImpasto, ImpastoDTO impasto) {
		boolean modificatoImpasto = Dao.modificaImpasto(idImpasto, impasto);
		if (modificatoImpasto) {
			return "L'impasto è stato modificato con successo.";
		} else {
			return "Errore: Impasto non trovato o modifica fallita.";
		}
	}

	@PUT
	@Path("/ingredienti/{idIngrediente}")
	@Produces(MediaType.APPLICATION_JSON)
	public String modificaIngrediente(@PathParam("idIngrediente") int  idIngrediente, Ingrediente ingrediente) {
		boolean modificatoIngrediente= Dao.modificaIngrediente(idIngrediente,ingrediente);
		
		if(modificatoIngrediente) {
			return "Ingrediente Modificato.";
		}else {
			return "Ingrediente non trovato o modifica fallita.";
		}
	}

	@DELETE
	@Path("/utenti/{idUtente}")
	@Produces(MediaType.APPLICATION_JSON)
	public String eliminaUtente(@PathParam("idUtente") int idUtente){
		
		boolean eliminaUtente= Dao.eliminaUtente(idUtente);
		if(eliminaUtente) {
			return "Utente Eliminato";
		}else {
			return "Utente non trovato";
		}
	}
	
	@DELETE
	@Path("/impasti/{idImpasto}")
	@Produces(MediaType.APPLICATION_JSON)
	public String eliminaImpasto(@PathParam("idImpasto")int idImpasto) {
		boolean eliminaImpasto= Dao.eliminaImpasto(idImpasto);
		if (eliminaImpasto) {
			return "Impasto eliminato";
			
		}else {
			return "Impasto non trovato e non eliminato";
		}
	}
	
	@DELETE
	@Path("/ingredienti/{idIngrediente}")
	@Produces(MediaType.APPLICATION_JSON)
	public String eliminaIngrediente(@PathParam("idIngrediente")int idIngrediente) {
		boolean eliminaIngrediente= Dao.eliminaIngrediente(idIngrediente);
		if (eliminaIngrediente) {
			return "Ingrediente Eliminato";
			
		}else {
			return "Ingrediente non trovato";
		}
	}
	

}