<%-- 
    Document   : Dashboard
    Created on : 24/03/2020, 21:52:50
    Author     : Diego
--%>

<jsp:include page="/header.jsp" />

<div class="container"> 
    <a href="<%=request.getContextPath()%>/controle/UsuariosListar"> Gerenciar �suarios </a>
    <br>
    <a href="<%=request.getContextPath()%>/controle/UsuariosListar"> Gerenciar Im�veis </a>
</div>
<jsp:include page="/footer.jsp" />