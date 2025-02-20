package com.pizzeria.controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pizzeria.dao.Dao;
import com.pizzeria.model.Pizza;
import com.pizzeria.model.Utente;

public class UpdatePizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
    private Dao Dao;
	
	
	public UpdatePizzaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utente utenteLoggato = (Utente) session.getAttribute("utenteLoggato");

		if (utenteLoggato != null) {
			String pizzaIdPar = request.getParameter("pizzaId");
			if (pizzaIdPar != null) {
				int pizzaId = Integer.valueOf(pizzaIdPar);
				Pizza pizza = Dao.getPizzaById(pizzaId);
				if ((pizza != null && pizza.getUtente().getId() == utenteLoggato.getId())) {
					request.setAttribute("pizza", pizza);
					request.setAttribute("impasti", Dao.getAllImpasti());
					request.setAttribute("ingredienti", Dao.getAllIngredienti());
					request.getRequestDispatcher("update.jsp").forward(request, response);
				}
			}
		} else {
			response.sendRedirect("login.jsp?error=Utente non autenticato");
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utente utenteLoggato = (Utente) session.getAttribute("utenteLoggato");
		String pizzaName = request.getParameter("pizzaName");
		String pizzaIdPar = request.getParameter("pizzaId");
		String impastoId = request.getParameter("impastoId");
		String[] ingredientiIds = request.getParameterValues("ingredientiId");

		Pizza pizzaAggiornata = Dao.pizzaAggiornata(pizzaIdPar, pizzaName, impastoId, ingredientiIds,
				utenteLoggato.getId());
		if (pizzaAggiornata == null) {
			response.sendRedirect("update.jsp?error=Errore nell'aggiornamento");
			return;
		}
		Utente utenteAggiornato = Dao.getUtenteById(utenteLoggato.getId());
		session.setAttribute("utenteLoggato", utenteAggiornato);

		request.setAttribute("impasti", Dao.getAllImpasti());
		request.setAttribute("ingredienti", Dao.getAllIngredienti());

		request.getRequestDispatcher("dashboard.jsp").forward(request, response);

	}

}
