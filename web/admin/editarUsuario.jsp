<%-- 
    Document   : editarUsuario
    Created on : 15 nov 2024, 19:06:08
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
        <title>Agencia de Viajes - Editar Usuario</title>
    </head>
    <body>
        
        <div class="centrado">
            
            <br>
            <h1>Editar Usuario</h1>
            <br>
            
            <form method="post" class="row g-3">
                
                <input type="hidden" name="id" value="${usuario.id}">    
                
                <div class="col-md-6">
                    <label for="nombre" class="form-label">Nombre</label>
                    <input type="text" class="form-control" name="nombre" id="nombre" required="" value="<c:out value="${usuario.nombre}"/>">
                </div>
                
                <div class="col-md-6">
                    <label for="apellidos" class="form-label">Apellidos</label>
                    <input type="text" class="form-control" name="apellidos" id="apellidos" required="" value="<c:out value="${usuario.apellidos}"/>">
                </div>
                
                <div class="col-12">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" name="email" id="email" required="" value="<c:out value="${usuario.email}"/>">
                </div>
                
                <div class="col-12">
                    <label for="contra" class="form-label">Contraseña</label>
                    <input type="password" class="form-control" name="contra" id="contra" required="" value="<c:out value="${usuario.contra}"/>">
                </div>                
                
                <div class="col-md-4">
                    <label for="fechaNacimiento" class="form-label">Fecha de nacimiento (opcional)</label>
                    <input type="date" class="form-control" name="fechaNacimiento" id="fechaNacimiento" value="<fmt:formatDate pattern="dd/MM/yyyy" type="date" value="${usuario.fechaNacimiento}"/>">
                </div>
                
                 <div class="col-md-4">
                    <label for="localidad" class="form-label">Localidad</label>
                    <input type="text" class="form-control" name="localidad" id="localidad" required="" value="<c:out value="${usuario.localidad}"/>">
                </div>               
                
                 <div class="col-md-4">
                    <label for="domicilio" class="form-label">Domicilio</label>
                    <input type="text" class="form-control" name="domicilio" id="domicilio" required="" value="<c:out value="${usuario.domicilio}"/>">
                </div>     
                
                 <div class="col-md-4">
                    <label for="telefono" class="form-label">Teléfono</label>
                    <input type="text" class="form-control" name="telefono" id="telefono" required="" value="<c:out value="${usuario.telefono}"/>">
                </div>                  

                 <div class="col-md-4">
                    <label for="tarjeta" class="form-label">Tarjeta</label>
                    <input type="text" class="form-control" name="tarjeta" id="tarjeta" required="" value="<c:out value="${usuario.tarjeta}"/>">
                </div>  

                 <div class="col-md-4">
                    <label for="saldo" class="form-label">Saldo</label>
                    <input type="number" class="form-control" name="saldo" id="saldo" required="" value="<c:out value="${usuario.saldo}"/>">
                </div>  
                
                <div class="col-12">
                    <label for="rol" class="form-label">Rol</label>
                    <select name="rol" id="rol" class="form-select" required="">
                        <c:forEach items="${roles}" var="rol">
                            <option value="${rol}" ${rol == usuario.rol?'selected':''}>${rol}</option>
                        </c:forEach>
                    </select>
                </div>
                
                <div class="col-12">
                    <button type="submit" class="btn btn-success">Actualizar usuario</button>
                </div>
            
            </form>
            <br>    
                
            <c:if test="${not empty error}">
                <br>
                <div class="error">
                    <c:out value="${error}"/>
                </div>
            </c:if>      
            
            <a href="./GestionUsuarios">Volver</a>
            
        </div>
        
    </body>
</html>
