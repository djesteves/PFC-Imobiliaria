<%@page import="util.DataAccess"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="cliente" class="modelo.Usuario"/>
<html>
    <head>
        <title>Imobiliária</title>
        <!-- Meta tags Obrigatórias -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="shortcut icon" href="<%=request.getContextPath()%>/Resources/img/icon_imob.png">

        <!-- Fontes do Site -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato:300,400,700">

        <!-- Bootstrap -->
        <link href="<%=request.getContextPath()%>/Resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <!-- Data Tables -->
        <link href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css" rel="stylesheet" type="text/css"/>
        <!-- Data AOS -->
        <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
        <!-- Custom CSS -->
        <link href="<%=request.getContextPath()%>/Resources/css/Style.css" rel="stylesheet" type="text/css"/>
        <!-- Outros CSS -->
        <link href="<%=request.getContextPath()%>/Resources/fonts/ionicons.min.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/pikaday/1.6.1/css/pikaday.min.css">


        <!-- Scripts -->
        <script charset="UTF-8" src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script charset="UTF-8" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="<%=request.getContextPath()%>/Resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script charset="UTF-8" src="https://kit.fontawesome.com/7982155587.js" crossorigin="anonymous"></script>
        <script charset="UTF-8" src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
        <!-- Scripts Data Tables -->
        <script charset="UTF-8" src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
        <script charset="UTF-8" src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js"></script>
        <script charset="UTF-8" src="<%=request.getContextPath()%>/Resources/js/wsviacep.js" type="text/javascript"></script>
        <script charset="UTF-8" src="<%=request.getContextPath()%>/Resources/js/scriptJS.js" type="text/javascript"></script>
    </head>
    <body>
        <header>

            <c:if test="${msg != null}">
                <script>
                    var mensagem = "${msg}";
                    mostraDialogo(mensagem, "success", 3000);
                </script>
            </c:if> 
            <c:if test="${msgerro != null}">
                <script>
                    var mensagem = "${msgerro}";
                    mostraDialogo(mensagem, "danger", 3000);
                </script>
            </c:if> 


            <nav class="navbar navbar-dark navbar-expand-lg bg-white portfolio-navbar gradient">
                <!--<nav class="navbar navbar-expand-lg navbar-light bg-light">-->
                <div class="container">

                    <a class="navbar-brand logo" href="<%=request.getContextPath()%>/index.jsp">Imobiliária</a>


                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSite" aria-controls="navbarSite" aria-expanded="false" aria-label="Navegação">
                        <span class="navbar-toggler-icon"></span>       
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSite">
                        <ul class="nav navbar-nav ml-auto ">
                            <li class="nav-item text-center" role="presentation"><a class="nav-link active" href="#"><i class="fas fa-home"></i>&nbsp;</i>Imóveis</a></li>

                            <c:if test="${usuarioLogado == null}">
                                <li class="nav-item dropdown">
                                    <a class="btn btn-outline-light dropdown-toggle" href="#" id="navbarDropdownLogin" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
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
                                <div class="dropdown text-center">
                                    <a class="btn btn-outline-light dropdown-toggle" href="#" id="navbarDropdownLogado" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        <i class="far fa-user"></i>
                                        ${usuarioLogado.nome}
                                    </a>

                                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownLogado">


                                        <a class="dropdown-item" href="<%=request.getContextPath()%>/controle/UsuarioConsultar?id=<c:out value='${usuarioLogado.id_usuario}' />">
                                            <i class="fas fa-user-cog"></i>
                                            Gerenciar Conta
                                        </a>

                                        <a class="dropdown-item" href="<%=request.getContextPath()%>/controle/ImovelListarPorID">
                                            <i class="fas fa-city"></i>
                                            Gerenciar Imóveis
                                        </a>

                                        <a class="dropdown-item" href="<%=request.getContextPath()%>/Usuario/AlterarSenhaUsuario.jsp">
                                            <i class="fas fa-key"></i>
                                            Alterar Senha
                                        </a>

                                        <c:if test="${'ADMINISTRADOR'.equalsIgnoreCase(usuarioLogado.nivel)}">
                                            <a class="dropdown-item" href="<%=request.getContextPath()%>/Admin/Dashboard.jsp">
                                                <i class="fa fa-tools" aria-hidden="true"></i>
                                                Administração
                                            </a>
                                        </c:if>

                                        <hr >

                                        <a class="dropdown-item" href="<%=request.getContextPath()%>/controle/Deslogar">
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



