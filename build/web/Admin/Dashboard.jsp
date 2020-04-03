<%-- 
    Document   : Dashboard
    Created on : 24/03/2020, 21:52:50
    Author     : Diego
--%>

<jsp:include page="/header.jsp" />

<div class="card bg-primary mb-4 text-white text-center">
    <div class="card-header">Dashboard</div>
</div>

<div class="container"> 
    <a href="<%=request.getContextPath()%>/controle/UsuarioListar"> Gerenciar Usuários </a>
    <br>
    <a href="<%=request.getContextPath()%>/controle/#"> Gerenciar Imóveis </a>
</div>
<jsp:include page="/footer.jsp" />