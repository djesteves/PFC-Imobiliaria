<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="cliente" class="modelo.Usuario"/>
<html>
    <head>
        <title>Imobili�ria</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="shortcut icon" href="<%=request.getContextPath()%>/Resources/img/icon_imob.png">

        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato:300,400,700">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <link href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css" rel="stylesheet" type="text/css"/>
        <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
        <link href="<%=request.getContextPath()%>/Resources/css/style.css" rel="stylesheet" type="text/css"/>

        <script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
        <script src="https://kit.fontawesome.com/7982155587.js" crossorigin="anonymous"></script>
        <script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
        <script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js"></script>
        <script src="<%=request.getContextPath()%>/Resources/js/wsviacep.js"></script>
        <script src="<%=request.getContextPath()%>/Resources/js/script.js"></script>
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
            <nav class="navbar navbar-dark navbar-expand-lg bg-white navbar-gradient mb-4">
                <div class="container">
                    <a class="navbar-brand logo" href="<%=request.getContextPath()%>/index.jsp">Imobili�ria</a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSite" aria-controls="navbarSite" aria-expanded="false" aria-label="Navega��o">
                        <span class="navbar-toggler-icon"></span>       
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSite">
                        <ul class="nav navbar-nav ml-auto ">
                            <li class="nav-item text-center" role="presentation"><a class="nav-link active mr-2" href="<%=request.getContextPath()%>/controle/ImoveisListar"><i class="fas fa-home"></i>&nbsp;</i>Im�veis</a></li>

                            <c:if test="${usuarioLogado == null}">
                                <li class="nav-item dropdown text-center">
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

                                        <a class="dropdown-item" href="<%=request.getContextPath()%>/controle/UsuarioConsultar?id=<c:out value='${usuarioLogado.id_usuario}' />">
                                            <i class="fas fa-user-cog"></i>
                                            Gerenciar Conta
                                        </a>

                                        <a class="dropdown-item" href="<%=request.getContextPath()%>/controle/ImovelListarPorID">
                                            <i class="fas fa-city"></i>
                                            Gerenciar Im�veis
                                        </a>

                                        <a class="dropdown-item" href="<%=request.getContextPath()%>/Usuario/AlterarSenhaUsuario.jsp">
                                            <i class="fas fa-key"></i>
                                            Alterar Senha
                                        </a>

                                        <c:if test="${'ADMINISTRADOR'.equalsIgnoreCase(usuarioLogado.nivel)}">
                                            <a class="dropdown-item" href="<%=request.getContextPath()%>/Admin/Dashboard.jsp">
                                                <i class="fa fa-tools" aria-hidden="true"></i>
                                                Administra��o
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



