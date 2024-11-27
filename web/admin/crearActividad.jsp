<%-- 
    Document   : crearActividad
    Created on : 24 nov 2024, 12:39:22
    Author     : msi
--%>

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
        <title>Agencia de Viajes - Crear Actividad</title>
    </head>
    <body>
        
        <div class="centrado">
            
            <br>
            <h1>Crear Actividad</h1>
            <br>
            
            <form method="post" class="row g-3">
                
               <input type="hidden" name="id" value="${actividad.id}">  
                
                <div class="col-12">
                    <label for="nombre" class="form-label">Nombre</label>
                    <input type="text" class="form-control" name="nombre" id="nombre" required="">
                </div>
                
                <div class="col-12">
                    <label for="fechaAct" class="form-label">Fecha de la actividad</label>
                    <input type="date" class="form-control" name="fechaAct" id="fechaAct" required="">
                </div>
                
                <div class="col-12">
                    <button type="submit" class="btn btn-success">Crear actividad</button>
                </div>                
                
            </form>
            <br>
            
            <c:if test="${not empty error}">
                <br>
                <div class="error">
                    <c:out value="${error}"/>
                </div>
            </c:if>      
            
            <a href="./GestionActividades">Volver</a>            
            
        </div>
               
    </body>
</html>
