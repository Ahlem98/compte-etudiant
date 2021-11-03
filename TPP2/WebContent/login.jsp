
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Login</title>
    </head>
    <body>
        <h1>Welcome, please login</h1>
        <form action="Authentification" method="post">

            Email:    <input type="text" name="user" width="30"> </br>
            Password: <input type="password" name="password" width="18"> </br>
            <input type="submit" value="Login">

        </form>

        <p style="color: red">${errorMessage}</p>
</body>
</html>
