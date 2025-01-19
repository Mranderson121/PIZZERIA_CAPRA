<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.pizzeria.model.*"%>
<%@ page import="java.util.*"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pizzeria Dashboard</title>
<style>
body {
	font-family: Arial, sans-serif;
	margin: 20px;
}

.error-message {
	color: red;
	margin-bottom: 10px;
	font-weight: bold;
}

.table-container {
	display: flex;
	justify-content: space-between;
	margin-bottom: 20px;
}

table {
	border-collapse: collapse;
	width: 48%;
	margin-bottom: 20px;
}

table, th, td {
	border: 1px solid #ddd;
}

th, td {
	padding: 8px;
	text-align: left;
}

th {
	background-color: #f4f4f4;
}

.form-container {
	margin-bottom: 20px;
}

.form-container input[type="text"] {
	padding: 5px;
	margin-right: 10px;
}

.form-container button {
	padding: 5px 10px;
	cursor: pointer;
}
</style>
</head>
<body>
	<% 
        Utente utenteLoggato = (Utente) session.getAttribute("utenteLoggato");
        Set<Impasto> impasti = (Set<Impasto>) request.getAttribute("impasti");
      	Set<Ingrediente> ingredienti = (Set<Ingrediente>) request.getAttribute("ingredienti");
        String errorMessage = (String) request.getAttribute("errorMessage");
    %>

	<h1>Gestione Pizzeria</h1>
	<div class="form-container" style="margin-top: 10px;">
		<h2>
			Benvenuto,
			<%=utenteLoggato.getUsername() %>
		
		</h2>
		<a href="login.jsp"><button type="button">Esci</button></a>
	</div>
	
		

		

	<% if (errorMessage != null) { %>
	<div class="error-message"><%= errorMessage %></div>
	<% } %>

	<form action="./DashboardServlet" method="post">
		<div class="table-container"
			style="display: flex; justify-content: space-between;">

			<table style="margin-right: 20px;">
				<caption>
					<strong>Lista Impasti</strong>
				</caption>
				<thead>
					<tr>
						<th>Nome Impasto</th>
						<th>Seleziona</th>
					</tr>
				</thead>
				<tbody>
					<% if (impasti != null && !impasti.isEmpty()) { 
                       	for (Impasto impasto : impasti) { %>
					<tr>
						<td><%= impasto.getNome() %></td>
						<td><input type="radio" name="impastoId"
							value="<%= impasto.getIdImpasto() %>" /></td>
					</tr>
					<% 
					} 
                       } else { 
                     %>
					<tr>
						<td colspan="2">Nessun impasto disponibile.</td>
					</tr>
					<% } %>
				</tbody>
			</table>


			<table>
				<caption>
					<strong>Lista Ingredienti</strong>
				</caption>
				<thead>
					<tr>
						<th>Nome Ingrediente</th>
						<th>Seleziona</th>
					</tr>
				</thead>
				<tbody>
					<% 
                    if (ingredienti != null && !ingredienti.isEmpty()) { 
                       for (Ingrediente ingrediente : ingredienti) { 
                %>
					<tr>
						<td><%= ingrediente.getNome() %></td>
						<td><input type="checkbox" name="ingredientiId"
							value="<%= ingrediente.getIdIngredientes() %>" /></td>
					</tr>
					<% 
                       } 
                    } else { 
                %>
					<tr>
						<td colspan="2">Nessun ingrediente disponibile.</td>
					</tr>
					<% 
                    } 
                %>
				</tbody>
			</table>
		</div>

		<div class="form-container" style="margin-top: 20px;">
			<label for="pizzaName">Nome Pizza:</label> <input type="text"
				id="pizzaName" name="pizzaName"
				placeholder="Inserisci il nome della pizza" required>
		</div>


		<div class="form-container" style="margin-top: 10px;">
			<button type="submit">Crea Pizza</button>
		</div>
	</form>
	<table>
		<thead>
			<tr>
				<th>Nome Pizza</th>
				<th>Tipo Impasto</th>
				<th>Ingredienti</th>
				<th>Azioni</th>
			</tr>
		</thead>
		<tbody>
			<% 
	            if (utenteLoggato.getPizze() != null && !utenteLoggato.getPizze().isEmpty()) {
	                for (Pizza pizza : utenteLoggato.getPizze()) {
	        %>
			<tr>
				<td><%= pizza.getNome()%></td>
				<td><%= pizza.getImpasto().getNome() %></td>
				<td><%= pizza.getIngredienti() %></td>
				<td>
					<form action="./DashboardServlet" method="post"
						style="display: inline;">
						<input type="hidden" name="pizzaId" value="<%= pizza.getIdPizza() %>">
						<button type="submit">Elimina</button>
					</form>
					<form action="./UpdatePizzaServlet" method="get"
						style="display: inline;">
						<input type="hidden" name="pizzaId" value="<%= pizza.getIdPizza() %>">
						<button type="submit">Modifica</button>
					</form>
					
				</td>
			</tr>
			<%
	        }
	        } else {
	        %>
			<tr>
				<td colspan="3">Non hai ancora creato una Pizza</td>
			</tr>
			<% } %>
		</tbody>
	</table>
</body>
</html>
