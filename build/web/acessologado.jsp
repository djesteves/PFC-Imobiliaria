<jsp:include page="header.jsp" />

<div class="container portfolio-block">
    <h4>Login</h4>
    <hr>
    <p>Para acessar est� pagina � necessario estar logado!</p>
    <a href="<%=request.getContextPath()%>/Usuario/FormUsuario.jsp" class="btn btn-primary shadow lift mr-1">
        Ainda n�o tem uma conta ? &nbsp; Cadastre-se <i class="fas fa-chevron-right d-none d-md-inline ml-3"></i>
    </a>
</div>

<jsp:include page="footer.jsp" />
