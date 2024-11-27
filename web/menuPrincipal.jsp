<%-- 
    Document   : menuPrincipal
    Created on : 12 nov 2024, 19:00:29
    Author     : msi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@page contentType="text/html" pageEncoding="latin1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=latin1">
        <!-- Mis CSS -->
        <link rel="stylesheet" type="text/css" href="./estilo/principal.css">
        <!-- Boostrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <title>Agencia de Viaje - menuPrincipal</title>
    </head>
    <body>
        <div class="centrado">
            
            <br><br>
            <a href="CerrarSesion"><button type="button" class="btn btn-danger">Cerrar Sesión</button></a>
            <br><br>
            
            <!-- LO QUE VE EL ADMIN -->
            <c:if test="${esAdmin}">
                <h1>Bienvenido Adminstrador</h1>
                <br><br>
                
                <a href="./admin/GestionUsuarios"><button type="button" class="btn btn-primary">Gestion Usuarios</button></a>
                <br><br>
                
                <a href="./admin/GestionViajes"><button type="button" class="btn btn-primary">Gestion de Viajes</button></a>
                <br><br>

                <a href="./admin/GestionAlojamiento"><button type="button" class="btn btn-primary">Gestion de Alojamiento</button></a>
                <br><br>  
                
                <a href="./admin/GestionLugares"><button type="button" class="btn btn-primary">Gestion de Lugares</button></a>
                <br><br>
                
                <a href="./admin/GestionActividades"><button type="button" class="btn btn-primary">Gestion de Actividades</button></a>
                <br><br>                
                
            </c:if>
            
            <!-- LO QUE VE EL CLIENTE -->
            <c:if test="${esCliente}">
                <h1>Eres Cliente</h1>
                
            </c:if>                
        </div>
    </body>
</html>
