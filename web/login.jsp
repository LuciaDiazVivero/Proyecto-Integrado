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
        <!-- Mis CSS -->
        <link rel="stylesheet" type="text/css" href="./estilo/principal.css">
        <!-- Boostrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <title>Agencia de Viajes - Login</title>
    </head>
    <body>
        <br>
        <div class="centrado">
            <h1>Inicio de Sesión</h1>
            <br>
            
            <form method="post" class="row g-3">
                
                <div class="col-12">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" name="email" id="email" required="">
                </div>
                
                <div class="col-12">
                    <label for="contra" class="form-label">Contraseña</label>
                    <input type="password" class="form-control" name="contra" id="contra" required="">
                </div>                 

                <div class="col-12">
                    <button type="submit" class="btn btn-success">Iniciar Sesión</button>
                </div>

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
