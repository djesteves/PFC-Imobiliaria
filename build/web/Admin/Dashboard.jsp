<%-- 
    Document   : Dashboard
    Created on : 24/03/2020, 21:52:50
    Author     : Diego
--%>

<jsp:include page="/header.jsp" />

<div class="container"> 
    <a href="<%=request.getContextPath()%>/controle/UsuariosListar"> Gerenciar Úsuarios </a>
    <br>
    <a href="<%=request.getContextPath()%>/controle/UsuariosListar"> Gerenciar Imóveis </a>
</div>
<jsp:include page="/footer.jsp" />