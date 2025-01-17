<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <style>
        .error {
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <h2>Login</h2>
    <form action="./LoginServlet" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br>
        <button type="submit">Login</button>
    </form>
    <c:if test="${not empty errore}">
        <p class="error">${errore}</p>
    </c:if>
</body>
</html>