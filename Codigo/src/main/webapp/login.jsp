<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <jsp:include page="header.jsp" />

    <style>
        body {
            background-image: linear-gradient(120deg, #7f70f5, #0ea0ff);
            background-repeat: no-repeat;
        }

        .formulario {
            border-radius: 8px;
        }

        @media (max-width: 400px) {
            .formulario {
                width: 280px;
            }
        }

        @media (min-width: 400px) and (max-width: 1199.98px) {
            .formulario {
                width: 380px;
            }
        }

        @media (min-width: 1200px) {
            .formulario {
                width: 400px;
            }
        }
    </style>


    <form class="formulario" action="<%=request.getContextPath()%>/controle/Logar" method="post">

        <c:if test="${usuarioLogado == null}">
            <div class="text-center">
                <img class="mb-4" src="<%=request.getContextPath()%>/assets/img/icon_imob.png" alt="" width="72"
                    height="72">
                <h1 class="h3 mb-3 font-weight-normal">Login</h1>
            </div>
            <div class="form-group">
                <label for="mail">E-mail:</label>
                <input type="email" class="form-control" name="loginmail" placeholder="email@exemplo.com" id="loginmail"
                    required />
            </div>
            <div class="form-group">
                <label for="senha">Senha:</label>
                <input type="password" class="form-control" name="loginsenha" placeholder="Senha" id="loginsenha"
                    required />
            </div>

            <button type="submit" class="btn btn-primary btn-block">Entrar</button>

            <br>

            <div class="text-right">
                <a href="<%=request.getContextPath()%>/usuario/FormUsuario.jsp">
                    Cadastrar |
                </a>
                <a href="#">
                    Esqueceu a senha?
                </a>

            </div>
        </c:if>

        <c:if test="${usuarioLogado != null}">
            <h1> Você já está logado </h1>
        </c:if>



    </form>


    <jsp:include page="footer.jsp" />