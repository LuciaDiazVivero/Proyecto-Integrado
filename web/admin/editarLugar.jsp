<%-- 
    Document   : editarLugar
    Created on : 27 nov 2024, 17:12:36
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
        <title>Agencia de Viajes - Editar Lugar</title>
    </head>
    <body>
        
        <div class="centrado">
            
            <br>
            <h1>Editar Lugar</h1>
            <br>
            
            <form method="post" enctype="multipart/form-data" class="row g-3">
                <input type="hidden" id="id" name="id" value="${lugar.idLugar}">
                
                <div class="col-12">
                    <label for="pais" class="form-label">País:</label>
                    <input type="text" class="form-control" name="pais" id="pais" required="" value="<c:out value="${lugar.pais}"/>">
                </div>    
                
                <div class="col-12">
                    <label for="localidad" class="form-label">Localidad:</label>
                    <input type="text" class="form-control" name="localidad" id="localidad" required="" value="<c:out value="${lugar.localidad}"/>">
                </div>           
                
                <div class="mb-3">
                    <label for="imagen" class="form-label">Imagen:</label>
                    <input type="file" class="form-control" name="imagen" id="imagen">
                </div>              
                
                <div class="col-12">
                    <button type="submit" class="btn btn-success">Actualizar lugar</button>
                </div>                  
                
            </form>
                <br>

            <c:if test="${not empty error}">
                <br>
                <div class="error">
                    <c:out value="${error}"/>
                </div>
            </c:if>              
            
            <a href="./GestionLugares">Volver</a>
            
        </div>
        
    </body>
</html>
