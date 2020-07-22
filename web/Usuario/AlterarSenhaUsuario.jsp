<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../header.jsp" />

<div class="portfolio-block">
    <div class="heading">
        <p>Alterar Senha</p>
    </div>

    <form action="<%=request.getContextPath()%>/controle/AlterarSenha" method="post" class="formulario">
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="senha">Senha:</label>
                <input type="password" class="form-control" name="senha" id="senha" required/>
            </div>
            <div class="form-group col-md-6">
                <label for="resenha">Confirmar senha:</label>
                <input type="password" class="form-control" name="resenha" id="resenha" required/>
                <small id="dicasenha" class="form-text text-muted">
                    Sua senha deve ter entre 8 e 20 caracteres, os quais devem ser letras e números, sem espaços, caracteres especiais ou emojis.
                </small>
            </div>
        </div>

        <hr>

        <div class="button">
            <button type="submit" class="btn btn-primary">Confirmar</button>
        </div>
    </form>
</div>	

<jsp:include page="../footer.jsp" />