<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp" />

<header>
    <nav class="navbar navbar-dark navbar-expand-lg bg-white navbar-gradient mb-4">
        <div class="container">
            <a class="navbar-brand logo" href="<%=request.getContextPath()%>/index.jsp">Imobiliária</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSite" aria-controls="navbarSite" aria-expanded="false" aria-label="Navegação">
                <span class="navbar-toggler-icon"></span>       
            </button>
            <div class="collapse navbar-collapse" id="navbarSite">
                <ul class="nav navbar-nav ml-auto ">
                    <li class="nav-item text-center" role="presentation"><a class="nav-link active mr-2" href="<%=request.getContextPath()%>/catalogoimoveis.jsp"><i class="fas fa-home"></i>&nbsp;</i>Imóveis</a></li>

                    <c:if test="${usuarioLogado == null}">
                        <li class="nav-item text-center">
                            <a class="btn btn-outline-light" href="<%=request.getContextPath()%>/login.jsp">
                                <i class="far fa-user"></i>
                                Entrar
                            </a>
                        </li>
                    </c:if>  
                    <c:if test="${usuarioLogado != null}">
                        <div class="dropdown text-center">
                            <a class="btn btn-outline-light dropdown-toggle" href="#" id="navbarDropdownLogado" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="far fa-user"></i>
                                ${usuarioLogado.nome}

                            </a>

                            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownLogado">

                                <a class="dropdown-item" href="<%=request.getContextPath()%>/Controle/UsuarioListarPorID?id=<c:out value='${usuarioLogado.id}' />">
                                    <i class="fas fa-user-cog"></i>
                                    Meus Dados
                                </a>

                                <a class="dropdown-item" href="<%=request.getContextPath()%>/Controle/ImovelListarPorIDAtivos">
                                    <i class="fas fa-city"></i>
                                    Meus Imóveis
                                </a>

                                <a class="dropdown-item" href="<%=request.getContextPath()%>/Usuario/AlterarSenha.jsp">
                                    <i class="fas fa-key"></i>
                                    Alterar Senha
                                </a>

                                <a class="dropdown-item" href="<%=request.getContextPath()%>/Controle/AgendamentoLista?modo=Usuario">
                                    <i class="fas fa-calendar-alt" aria-hidden="true"></i>
                                    Agendamentos
                                </a>

                                <c:if test="${'CORRETOR'.equalsIgnoreCase(usuarioLogado.nivel) || 'ADMINISTRADOR'.equalsIgnoreCase(usuarioLogado.nivel)}">
                                    <a class="dropdown-item" href="<%=request.getContextPath()%>/Admin/Dashboard.jsp">
                                        <i class="fa fa-tools" aria-hidden="true"></i>
                                        Administração
                                    </a>
                                </c:if>

                                <hr >

                                <a class="dropdown-item" href="<%=request.getContextPath()%>/Controle/Deslogar">
                                    <i class="fas fa-sign-out-alt"></i>
                                    Sair
                                </a>
                            </div> 
                        </div>
                    </c:if>
                </ul>
            </div>
        </div>
    </nav>
</header>            
