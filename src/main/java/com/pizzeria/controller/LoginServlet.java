package com.pizzeria.controller;

import java.io.IOException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pizzeria.dao.Dao;
import com.pizzeria.model.Impasto;
import com.pizzeria.model.Ingrediente;
import com.pizzeria.model.Utente;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		Utente utente = Dao.verificaCredenzialiUtente(username, password);
		if (utente != null) {
			HttpSession session = request.getSession();
			session.setAttribute("utenteLoggato", utente);

			Set<Impasto> impasti = Dao.getAllImpasti();
			request.setAttribute("impasti", impasti);

			Set<Ingrediente> ingredienti = Dao.getAllIngredienti();
			request.setAttribute("ingredienti", ingredienti);

			RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("errore", "Credenziali errate. Riprova.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
	}
}