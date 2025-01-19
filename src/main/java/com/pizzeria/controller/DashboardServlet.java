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
import com.pizzeria.model.Pizza;
import com.pizzeria.model.Utente;

public class DashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DashboardServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utenteLoggato = (Utente) session.getAttribute("utenteLoggato");

        // Controllo autenticazione
        if (utenteLoggato == null) {
            response.sendRedirect("login.jsp?error=Utente non autenticato");
            return;
        }

        // Recupero dati per la dashboard
        Set<Impasto> impasti = Dao.getAllImpasti();
        Set<Ingrediente> ingredienti = Dao.getAllIngredienti();

        request.setAttribute("impasti", impasti);
        request.setAttribute("ingredienti", ingredienti);

        // Inoltro alla dashboard
        RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utenteLoggato = (Utente) session.getAttribute("utenteLoggato");

        // Controllo autenticazione
        if (utenteLoggato == null) {
            response.sendRedirect("login.jsp?error=Utente non autenticato");
            return;
        }

        // Gestione eliminazione pizza
        String pizzaIdParam = request.getParameter("pizzaId");
        if (pizzaIdParam != null) {
            gestisciEliminazionePizza(request, response, utenteLoggato);
            return; // Assicura che non venga eseguito altro codice dopo l'eliminazione
        }

        // Gestione aggiunta/modifica pizza
        String pizzaName = request.getParameter("pizzaName");
        String impastoId = request.getParameter("impastoId");
        String[] ingredientiIds = request.getParameterValues("ingredientiId");

        if (pizzaName == null || pizzaName.isEmpty() || impastoId == null || impastoId.isEmpty()
                || ingredientiIds == null || ingredientiIds.length == 0) {
            request.setAttribute("errorMessage", "Tutti i campi sono obbligatori.");
            aggiornaSessioneUtente(request, utenteLoggato);
            doGet(request, response);
            return;
        }

        Pizza nuovaPizza = Dao.aggiungiPizza(pizzaName, impastoId, ingredientiIds, utenteLoggato.getId());

        if (nuovaPizza == null) {
            request.setAttribute("errorMessage", "Errore durante l'aggiunta della pizza.");
            aggiornaSessioneUtente(request, utenteLoggato);
            doGet(request, response);
            return;
        }

        aggiornaSessioneUtente(request, utenteLoggato);
        doGet(request, response);
    }

    private void gestisciEliminazionePizza(HttpServletRequest request, HttpServletResponse response,
            Utente utenteLoggato) throws ServletException, IOException {
        String pizzaIdPar = request.getParameter("pizzaId");

        if (pizzaIdPar != null) {
            int pizzaId = Integer.valueOf(pizzaIdPar);
            boolean eliminata = Dao.pizzaEliminata(pizzaId);

            if (!eliminata) {
                request.setAttribute("errorMessage", "Errore durante l'eliminazione della pizza.");
            }
        }

        aggiornaSessioneUtente(request, utenteLoggato);
        doGet(request, response);
    }

    private void aggiornaSessioneUtente(HttpServletRequest request, Utente utenteLoggato) {
        Utente utenteAggiornato = Dao.getUtenteById(utenteLoggato.getId());
        request.getSession().setAttribute("utenteLoggato", utenteAggiornato);
        request.setAttribute("pizze", utenteAggiornato.getPizze());
    }
}