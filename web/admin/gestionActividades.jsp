<%-- 
    Document   : gestionActividades
    Created on : 24 nov 2024, 11:41:30
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
        <title>Agencia de Viajes - Gestión de Actividades</title>
    </head>
    <body>
        
        <div class="centrado">
            
            <br>
            <h1>Gestionar Actividades</h1>
            <br>
            
            <table class="table table-striped table-hover">
                
                <tr>
                    <th>Nombre</th>
                    <th>Fecha de la Actividad</th>
                    <th></th>
                    <th></th>
                </tr>     
                
                <c:forEach  items="${mostrarActividades}" var="actividad">
                    <tr>
                        <td><c:out value="${actividad.nombre}"/></td>
                        <td><fmt:formatDate pattern="dd/MM/yyyy" type="date" value="${actividad.fechaAct}"/></td>
                        <td>
                            <a href="EditarActividad?id=${actividad.id}"><button>Editar</button></a>
                        </td>
                        <td>
                            <form action="EliminarActividad" method="post">
                                <input type="hidden" value="${actividad.id}" name="id">
                                <button>Eliminar</button>
                            </form>
                        </td>                        
                    </tr>
                </c:forEach>
                
            </table>
            <br>
            
            <a href="CrearActividad"><button type="button" class="btn btn-primary">Crear Actividad</button></a>
            <br><br>
            <a href="./../MenuPrincipal"><button type="button" class="btn btn-primary">Volver</button></a>
                        
        </div>
        
    </body>
</html>
