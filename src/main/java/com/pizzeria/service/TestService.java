package com.pizzeria.service;



import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.pizzeria.dao.*;
import com.pizzeria.dto.ImpastoDTO;
import com.pizzeria.model.*;


@Path("/pizzeria")
public class TestService {
		// Read di tutti gli ingredienti
	 	@GET
	 	@Path("/ingredienti")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Set<Ingrediente> getIngredienti() {
	        return Dao.getAllIngredienti();
	    }
	 // Recupera tutti gli impasti
	    @GET
	    @Path("/impasti")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Set<ImpastoDTO> getAllImpasti() {
	        return DTODao.getAllImpasti() ;  // Metodo che recupera gli impasti dal database
	    }

	    // Recupera tutti gli utenti
//	    @GET
//	    @Path("/utenti")
//	    @Produces(MediaType.APPLICATION_JSON)
//	    public Set<Utente> getAllUtenti() {
//	        return Dao.getAllUtenti();  // Metodo che recupera gli utenti dal database
//	    }
}