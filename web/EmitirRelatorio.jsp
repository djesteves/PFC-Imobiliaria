<%-- 
    Document   : EmitirRelatorio
    Created on : 17/05/2020, 17:41:24
    Author     : Diego
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="<%=request.getContextPath()%>/controle/EmitirRelatorio" method="post">
            <button type="submit" class="btn btn-primary">Gerar Relatorio</button>
        </form>
    </body>
</html>
