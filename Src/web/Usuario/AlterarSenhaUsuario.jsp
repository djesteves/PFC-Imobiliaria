<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../header.jsp" />

<div class="container">
    <div class="text-center">
        <p>Alterar Senha</p>
    </div>


    <form action="<%=request.getContextPath()%>/Controle/AlterarSenha" method="post" class="formulario">
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="senha">Senha:</label>
                <input type="password" class="form-control" name="senha" id="senha" required/>
            </div>
            <div class="form-group col-md-6">
                <label for="resenha">Confirmar senha:</label>
                <input type="password" class="form-control" name="resenha" id="resenha" required/>
            </div>
        </div>

        <hr>

        <div class="button">
            <button type="submit" class="btn btn-primary">Confirmar</button>
            <button type="button" onclick="window.history.back();" class="btn btn-danger">Voltar</button>
        </div>
        
        
    </form>
</div>	

<jsp:include page="../footer.jsp" />