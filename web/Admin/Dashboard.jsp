<%-- 
    Document   : Dashboard
    Created on : 24/03/2020, 21:52:50
    Author     : Diego
--%>

<jsp:include page="/header.jsp" />

<div class="container portfolio-block"> 
    <a href="<%=request.getContextPath()%>/controle/UsuarioListar"> Gerenciar Usu�rios </a>
    <br>
    <a href="<%=request.getContextPath()%>/controle/ImoveisEmAnalise"> Aprovar Im�veis </a>
</div>
