<%-- 
    Document   : gestionViajes
    Created on : 17 nov 2024, 18:42:55
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
        <title>Agencia de Viajes - Gestión de Viajes</title>
    </head>
    <body>
        <div class="centrado">
            
            <br>
            <h1>Gestión de Viajes</h1>
            <br>
            
            <table class="table table-striped table-hover">
                
                <tr>
                    <th>Nombre</th>
                    <th>Salida</th>
                    <th>Destino</th>
                    <th>Fecha de Inicio</th>
                    <th>Fecha de Fin</th>
                    <th>Costo</th>
                    <th>Alojamiento</th>
                    <th></th>
                    <th></th>
                </tr>
                
                <c:forEach items="${mostrarViajes}" var="viaje">
                    <tr>
                        <td><c:out value="${viaje.nombre}"/></td>
                        <td><c:out value="${viaje.idSalida}"/></td>
                        <td><c:out value="${viaje.idDestino}"/></td>
                        <td><c:out value="${viaje.fechaInicio}"/></td>
                        <td><c:out value="${viaje.fechaFin}"/></td>
                        <td><c:out value="${viaje.costo}"/></td>
                        <td><c:out value="${viaje.idAlojamiento}"/></td>
                        <td>
                            <a href="EditarViaje?=id=${viaje.id}"><button>Editar</button></a>
                        </td>
                        <td>
                            <form action="EliminarViaje" method="post">
                                <input type="hidden" value="${viaje.id}" name="id">
                                <button>Eliminar</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                
            </table>
            <br>
            
            <a href="CrearViaje"><button type="button" class="btn btn-primary">Crear Viaje</button></a>
            <br><br>
            <a href="./../MenuPrincipal"><button type="button" class="btn btn-primary">Volver</button></a>
            
        </div>
    </body>
</html>
