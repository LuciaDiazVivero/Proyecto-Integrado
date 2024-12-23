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
        <!-- Mis CSS -->
        <link rel="stylesheet" type="text/css" href="./estilo/principal.css">
        <!-- Boostrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <title>Agencia de Viajes - Registro</title>
    </head>
    <body>
        
        <div class="centrado">
            
            <br>
            <h1>Registro</h1>
            <br>

            <form method="post" class="row g-3">
                
                <input type="hidden" name="id" value="${usuario.id}">    
                
                <div class="col-md-6">
                    <label for="nombre" class="form-label">Nombre</label>
                    <input type="text" class="form-control" name="nombre" id="nombre" required="">
                </div>
                
                <div class="col-md-6">
                    <label for="apellidos" class="form-label">Apellidos</label>
                    <input type="text" class="form-control" name="apellidos" id="apellidos" required="">
                </div>
                
                <div class="col-12">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" name="email" id="email" required="">
                </div>
                
                <div class="col-12">
                    <label for="contra" class="form-label">Contrase�a</label>
                    <input type="password" class="form-control" name="contra" id="contra" required="">
                </div>  
                
                <div class="col-12">
                    <label for="repetirContra" class="form-label">Confirmar Contrase�a</label>
                    <input type="password" class="form-control" name="repetirContra" id="repetirContra" required="">
                </div>                    
                
                <div class="col-md-4">
                    <label for="fechaNacimiento" class="form-label">Fecha de nacimiento</label>
                    <input type="date" class="form-control" name="fechaNacimiento" id="fechaNacimiento" required="">
                </div>
                
                 <div class="col-md-4">
                    <label for="localidad" class="form-label">Localidad</label>
                    <input type="text" class="form-control" name="localidad" id="localidad" required="">
                </div>               
                
                 <div class="col-md-4">
                    <label for="domicilio" class="form-label">Domicilio</label>
                    <input type="text" class="form-control" name="domicilio" id="domicilio" required="">
                </div>     
                
                 <div class="col-md-6">
                    <label for="telefono" class="form-label">Tel�fono</label>
                    <input type="text" class="form-control" name="telefono" id="telefono" required="">
                </div>                  

                 <div class="col-md-6">
                    <label for="tarjeta" class="form-label">Tarjeta</label>
                    <input type="text" class="form-control" name="tarjeta" id="tarjeta" required="">
                </div>  
                
                <div class="col-12">
                    <button type="submit" class="btn btn-success">Registrarse</button>
                </div>
            
            </form>   
            <br>    
            
            <c:if test="${not empty error}">
                <div class="error">
                    ${error}
                </div>
            </c:if>
            <br>

            <a href="Login">Volver</a> 

        </div>

    </body>
</html>
