<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>Imobiliária</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="shortcut icon" href="<%=request.getContextPath()%>/Resources/img/icon_imob.png">

        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato:300,400,700">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css">
        <link rel="stylesheet" href="https://unpkg.com/aos@2.3.1/dist/aos.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/Resources/css/style.css">
    </head>
    <body>
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
                                <li class="nav-item dropdown text-center">
                                    <a class="btn btn-outline-light dropdown-toggle" href="#" id="navbarDropdownLogin" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        <i class="far fa-user"></i>
                                        Login
                                    </a>
                                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownLogin">
                                        <form class="px-4 py-3" action="<%=request.getContextPath()%>/Controle/Logar" method="post">

                                            <div class="form-group">
                                                <label for="mail">E-mail:</label>
                                                <input type="email" class="form-control" name="loginmail" placeholder="email@exemplo.com" id="loginmail" required/>
                                            </div>
                                            <div class="form-group">
                                                <label for="senha">Senha:</label>
                                                <input type="password" class="form-control" name ="loginsenha" placeholder="Senha" id="loginsenha" required/>
                                            </div>

                                            <button type="submit" class="btn btn-primary">Entrar</button>
                                        </form>
                                        <div class="dropdown-divider"></div>
                                        <a class="dropdown-item" href="<%=request.getContextPath()%>/Usuario/FormUsuario.jsp">Novo, por aqui? Registre-se.</a>
                                    </div>
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

                                        <a class="dropdown-item" href="<%=request.getContextPath()%>/Usuario/AlterarSenhaUsuario.jsp">
                                            <i class="fas fa-key"></i>
                                            Alterar Senha
                                        </a>

                                        <c:if test="${'CORRETOR'.equalsIgnoreCase(usuarioLogado.nivel)}">
                                            <a class="dropdown-item" href="<%=request.getContextPath()%>/Controle/AgendaCorretor">
                                                <i class="fas fa-book" aria-hidden="true"></i>
                                                Minha Agenda
                                            </a>
                                        </c:if>

                                        <c:if test="${'CORRETOR'.equalsIgnoreCase(usuarioLogado.nivel) || 'ADMINISTRADOR'.equalsIgnoreCase(usuarioLogado.nivel)}">
                                            <a class="dropdown-item" href="<%=request.getContextPath()%>/Admin/Dashboard.jsp">
                                                <i class="fa fa-tools" aria-hidden="true"></i>
                                                Administração
                                            </a>
                                        </c:if>

                                        <hr >

                                        <a class="dropdown-item" href="<%=request.getContextPath()%>/Controle/Deslogar">
                                            <i class="fas fa-sign-out-alt"></i>
                                            Deslogar
                                        </a>
                                    </div> 
                                </div>
                            </c:if>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>            



