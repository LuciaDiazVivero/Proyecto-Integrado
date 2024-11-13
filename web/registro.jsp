<%-- 
    Document   : registro
    Created on : 10 nov 2024, 18:40:39
    Author     : msi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="latin1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=latin1">
        <link rel="stylesheet" type="text/css" href="./estilo/principal.css">
        <title>Agencia de Viajes - Registro</title>
    </head>
    <body>
        <h1>Registro</h1>
        <div class="centrado">
            <form method="post">
                <label>Nombre:</label>
                <input type="text" id="nombre" name="nombre" required="" maxlength="40">
                <br>
                <br>
                <label>Apellidos:</label>
                <input type="text" id="apellidos" name="apellidos" required="">
                <br>
                <br>  
                <label>Email:</label>
                <input type="email" id="email" name="email" required="">
                <br>
                <br>
                <label>Contraseña:</label>
                <input type="password" id="contra" name="contra" required="">
                <br>
                <br>
                <label>Repetir Contraseña:</label>
                <input type="password" id="repetirContra" name="repetirContra" required="">
                <br>
                <br>
                <label>Fecha de Nacimiento:</label>
                <input type="date" name="fechaNacimiento" id="fechaNacimiento" required="">
                <br>    
                <br>
                <label>Teléfono:</label>
                <input type="text" id="telefono" name="telefono" required="">
                <br> 
                <br>
                <label>Localidad:</label>
                <input type="text" id="localidad" name="localidad" required="">
                <br>
                <br>
                <label>Domicilio:</label>
                <input type="text" id="domicilio" name="domicilio" required="">
                <br>
                <br>
                <label>Tarjeta:</label>
                <input type="text" id="tarjeta" name="tarjeta" required="">
                <br> 
                <br>
                <input type="submit" value="Registrarse">
                <br>
                <br>
                <a href="Login">Iniciar Sesión</a>
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
