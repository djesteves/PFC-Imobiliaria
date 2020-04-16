<jsp:include page="header.jsp" />

<div class="container portfolio-block">
    <h4>Login</h4>
    <hr>
    <p>Para acessar está pagina é necessario estar logado!</p>
    <a href="<%=request.getContextPath()%>/Usuario/FormUsuario.jsp" class="btn btn-primary shadow lift mr-1">
        Ainda não tem uma conta ? &nbsp; Cadastre-se <i class="fas fa-chevron-right d-none d-md-inline ml-3"></i>
    </a>
</div>

<jsp:include page="footer.jsp" />
