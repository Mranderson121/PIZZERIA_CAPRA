<%@ page import="com.pizzeria.model.*"%>
<%@ page import="java.util.*"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modifica Pizza</title>
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
            border-collapse: collapAse;
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
            display: flex;
            flex-direction: column;
            margin-top: 20px;
        }

        .form-container input[type="text"],
        .form-container button {
            padding: 10px;
            margin-top: 10px;
            width: 300px;
            font-size: 14px;
        }

        .form-container button {
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }

        .form-container button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <% 	
        Pizza pizza = (Pizza) request.getAttribute("pizza");
        Set<Impasto> impasti = (Set<Impasto>) request.getAttribute("impasti");
      	Set<Ingrediente> ingredienti = (Set<Ingrediente>) request.getAttribute("ingredienti");
    %>

    <h1>Modifica Pizza</h1>

    <form action="./UpdatePizzaServlet" method="post">
      
        <input type="hidden" name="pizzaId" value="<%= pizza.getIdPizza() %>">

        <div class="table-container">
           
            <table>
                <caption><strong>Seleziona Impasto</strong></caption>
                <thead>
                    <tr>
                        <th>Nome Impasto</th>
                        <th>Seleziona</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Impasto impasto : impasti) { %>
                        <tr>
                            <td><%= impasto.getNome() %></td>
                            <td>
                                <input type="radio" name="impastoId" value="<%= impasto.getIdImpasto() %>"
                                    <%= impasto.getIdImpasto() == pizza.getImpasto().getIdImpasto() ? "checked" : "" %>>
                            </td>
                        </tr>
                    <% } %>
                </tbody>
            </table>

           
            <table>
                <caption><strong>Seleziona Ingredienti</strong></caption>
                <thead>
                    <tr>
                        <th>Nome Ingrediente</th>
                        <th>Seleziona</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Ingrediente ingrediente : ingredienti) { 
                        boolean isSelected = false;
                        for (Ingrediente ingrPizza : pizza.getIngredienti()) {
                            if (ingrediente.getIdIngredientes() == ingrPizza.getIdIngredientes()) {
                                isSelected = true;
                                break;
                            }
                        }
                    %>
                        <tr>
                            <td><%= ingrediente.getNome() %></td>
                            <td>
                                <input type="checkbox" name="ingredientiId" value="<%= ingrediente.getIdIngredientes() %>"
                                    <%= isSelected ? "checked" : "" %>>
                            </td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        </div>

     
        <div class="form-container">
            <label for="pizzaName">Nome Pizza:</label>
            <input type="text" id="pizzaName" name="pizzaName" value="<%= pizza.getNome() %>" required>

            <button type="submit">Salva Modifiche</button>
        </div>
    </form>
</body>
</html>
