<%@page import="util.DataAccess"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="cliente" class="modelo.Usuario"/>
<html>
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <head>
        <title>Imobiliária</title>
        <!-- Meta tags Obrigatórias -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="shortcut icon" href="<%=request.getContextPath()%>/Resources/img/icon_imob.png">

        <%-- Fontes do Site --%>
        <link href="https://fonts.googleapis.com/css?family=Chivo:400,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Roboto+Mono:400,500,700" rel="stylesheet">

        <%-- Bootstrap --%>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <%-- Data Tables --%>
        <link href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css" rel="stylesheet" type="text/css"/>
        <%-- Data AOS --%>
        <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">

        <%-- Scripts --%>
        <script charset="UTF-8" src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script charset="UTF-8" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script charset="UTF-8" src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <script charset="UTF-8" src="https://kit.fontawesome.com/7982155587.js" crossorigin="anonymous"></script>
        <script charset="UTF-8" src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
        <%-- Scripts Data Tables --%>
        <script charset="UTF-8" src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
        <script charset="UTF-8" src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js"></script>


    </head>
    <body>
        <header>
            <c:if test="${msg != null}">
                <div id="alert" class="alert alert-success alert-dismissible fade show text-center" role="alert">
                    <p class="mb-0">${msg}</p>
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </c:if> 

            <nav class="navbar navbar-expand-lg navbar-light mb-4">
                <div class="container">
                    <a class="navbar-brand" href="<%=request.getContextPath()%>/index.jsp">
                        <img src="<%=request.getContextPath()%>/Resources/img/icon_imob.png" width="50" height="50" class="d-inline-block align-top" alt="">
                    </a>
                    
                    
                    
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSite" aria-controls="navbarSite" aria-expanded="false" aria-label="Navegação">
                        <span class="navbar-toggler-icon"></span>       
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSite">
                        <ul class="navbar-nav ml-auto ">
                            

                            <c:if test="${usuarioLogado == null}">
                                <li class="nav-item dropdown">
                                    <a class="btn btn-outline-primary dropdown-toggle shadow lift mr-1" href="#" id="navbarDropdownLogin" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        <i class="far fa-user"></i>
                                        Login
                                    </a>
                                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownLogin">
                                        <form class="px-4 py-3" action="<%=request.getContextPath()%>/controle/Logar" method="post">

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
                                        <a class="dropdown-item" href="<%=request.getContextPath()%>/Usuario/CadastrarUsuario.jsp">Novo, por aqui? Registre-se.</a>
                                    </div>
                                </li>
                            </c:if>  
                            <c:if test="${usuarioLogado != null}">
                                <li class="nav-item dropdown">
                                    <a class="btn btn-outline-primary dropdown-toggle" href="#" id="navbarDropdownLogado" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        <i class="far fa-user"></i>
                                        ${usuarioLogado.nome}
                                    </a>
                                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownLogado">

                                        <a class="dropdown-item" href="<%=request.getContextPath()%>/controle/UsuarioListar">
                                            <i class="fas fa-user-cog"></i>
                                            Gerenciar Conta
                                        </a>

                                        <a class="dropdown-item" href="<%=request.getContextPath()%>/controle/ImovelListar">
                                            <i class="fas fa-city"></i>
                                            Gerenciar Imóveis
                                        </a>

                                        <c:if test="${'ADMINISTRADOR'.equalsIgnoreCase(usuarioLogado.getLogin().getNivel())}">
                                            <a class="dropdown-item" href="<%=request.getContextPath()%>/Admin/Dashboard.jsp">
                                                <i class="fa fa-tools" aria-hidden="true"></i>
                                                Administração
                                            </a>
                                        </c:if>

                                        <hr class="my-4">
                                        <a class="dropdown-item" href="<%=request.getContextPath()%>/controle/Deslogar">
                                            <i class="fas fa-sign-out-alt"></i>
                                            Deslogar
                                        </a>
                                    </div> 
                                </li>
                            </c:if>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>            



