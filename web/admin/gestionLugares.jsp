<%-- 
    Document   : gestionLugares
    Created on : 24 nov 2024, 17:11:54
    Author     : msi
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="latin1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=latin1">
        <!-- Mis CSS -->
        <link rel="stylesheet" type="text/css" href="./../estilo/principal.css">
        <!-- Boostrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <title>Agencia de Viajes - Gestión de Lugares</title>
    </head>
    <body>
        
        <div class="centrado">
            
            <br>
            <h1>Gestionar Lugares</h1>
            <br>
            
            <table class="table table-striped table-hover">
                
                <tr>
                    <th>País</th>
                    <th>Localidad</th>
                    <th>Imagen</th>
                    <th></th>
                    <th></th>
                </tr>
                
                <c:forEach items="${mostrarLugares}" var="lugar">
                    <tr>
                        <td><c:out value="${lugar.pais}"/></td>
                        <td><c:out value="${lugar.localidad}"/></td>
                        <td><img src="./../imgLugares/${lugar.idLugar}" height="250" width="500"></td>
                        <td>
                            <a href="EditarLugar?id=${lugar.idLugar}"><button>Editar</button></a>
                        </td>
                        <td>
                            <form action="EliminarLugar" method="post">
                                <input type="hidden" value="${lugar.idLugar}" name="id">
                                <button>Eliminar</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                
            </table>
            <br>
            
            <a href="CrearLugar"><button type="button" class="btn btn-primary">Crear Lugar</button></a>
            <br><br>
            <a href="./../MenuPrincipal"><button type="button" class="btn btn-primary">Volver</button></a>
             
            
        </div>
        
    </body>
</html>
