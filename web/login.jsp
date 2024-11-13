<%-- 
    Document   : login
    Created on : 10 nov 2024, 18:36:29
    Author     : msi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="latin1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=latin1">
        <link rel="stylesheet" type="text/css" href="./estilo/principal.css">
        <title>Agencia de Viajes - Login</title>
    </head>
    <body>
        <br>
        <div class="centrado">
            <h1>Inicio de Sesión</h1>
            <br>
            <form method="post">
                <label>Email:</label>
                <input type="email" name="email" required>
                <br>
                <br>
                <label>Contraseña:</label>
                <input type="password" name="contra" required>
                <br>
                <br>
                <input type="submit" value="Iniciar Sesión">
                <br>
                <br>
                <a href="Registro">Registrarse</a>
                <br>
                <br>
                <a href="MenuAnonimo">Ingresar como Anónimo</a>
            </form>
        </div>
        <br>
        <c:if test="${not empty error}">
            <div class="error">
                ${error}
            </div>
        </c:if>
    </body>
</html>
