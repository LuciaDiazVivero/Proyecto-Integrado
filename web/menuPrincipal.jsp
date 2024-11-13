<%-- 
    Document   : menuPrincipal
    Created on : 12 nov 2024, 19:00:29
    Author     : msi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="./estilo/principal.css">
        <title>Agencia de Viaje - menuPrincipal</title>
    </head>
    <body>
        <div class="centrado">
            <c:if test="${esAdmin}">
                <h1>Eres Admin</h1>
            </c:if>
            <c:if test="${esCliente}">
                <h1>Eres Cliente</h1>
            </c:if>                
        </div>
    </body>
</html>
