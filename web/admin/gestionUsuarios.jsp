<%-- 
    Document   : gestionUsuarios
    Created on : 15 nov 2024, 18:36:25
    Author     : msi
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Mis CSS -->
        <link rel="stylesheet" type="text/css" href="./../estilo/principal.css">
        <!-- Boostrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <title>Agencia de Viajes - Gestión Usuarios</title>
    </head>
    <body>
        <div class="centrado">
            
            <br>
            <h1>Gestión de usuarios</h1>
            <br>
            
            <table class="table table-striped table-hover">
                
                <tr>
                    <th>Nombre</th>
                    <th>Apellidos</th>
                    <th>Email</th>
                    <th>Contraseña</th>
                    <th>Fecha de Nacimiento</th>
                    <th>Teléfono</th>
                    <th>Localidad</th>
                    <th>Domicilio</th>
                    <th>Tarjeta</th>
                    <th>Saldo</th>
                    <th>Rol</th>
                    <th></th>
                    <th></th>
                </tr>
                
                <c:forEach items="${mostrarUsuarios}" var="usuario">
                    <tr>
                        <td><c:out value="${usuario.nombre}"/></td>
                        <td><c:out value="${usuario.apellidos}"/></td>
                        <td><c:out value="${usuario.email}"/></td>
                        <td><c:out value="${usuario.contra}"/></td>
                        <td><fmt:formatDate pattern="dd/MM/yyyy" type="date" value="${usuario.fechaNacimiento}"/></td>
                        <td><c:out value="${usuario.telefono}"/></td>
                        <td><c:out value="${usuario.localidad}"/></td>
                        <td><c:out value="${usuario.domicilio}"/></td>
                        <td><c:out value="${usuario.tarjeta}"/></td>
                        <td><c:out value="${usuario.saldo}"/></td>
                        <td><c:out value="${usuario.rol}"/></td>
                        <td>
                            <a href="EditarUsuario?id=${usuario.id}"><button>Editar</button></a>
                        </td>
                        <td>
                            <form action="EliminarUsuario" method="post">
                                <input type="hidden" value="${usuario.id}" name="id">
                                <button>Eliminar</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                
            </table>
            <br>
            
            <a href="CrearUsuario"><button type="button" class="btn btn-primary">Crear Usuario</button></a>
            <br><br>
            <a href="./../MenuPrincipal"><button type="button" class="btn btn-primary">Volver</button></a>
            
        </div>
        
        <!-- Script de Boostrap -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
        
    </body>
</html>
