<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../header.jsp" />

<div class="container">
    <div class="text-center">
        <c:if test="${usuario != null}">
            <p>Editar Usuário</p>
        </c:if>
        <c:if test="${usuario == null}">
            <p>Cadastro de Usuário</p>
        </c:if>
    </div>

    <c:if test="${usuario != null}">
 
        <form action="<%=request.getContextPath()%>/Controle/UsuarioAlterar" method="post" class="formulario">
            <input type="hidden" name="id" value="${usuario.id_usuario}" />
            <input type="hidden" name="ide" value="${usuario.endereco.id_endereco}" />
        </c:if>
        <form action="<%=request.getContextPath()%>/Controle/UsuarioCadastrar" method="post" class="formulario">
            <div class="form-row">
                <c:if test="${'ADMINISTRADOR'.equalsIgnoreCase(usuarioLogado.nivel)}">
                   
                    <div class="form-group col-md-12">
                        <label for="nivel">Nivel de Acesso: </label>
                        <select class="custom-select" name="nivel" id="nivel" class="form-control col-md-6" required>
                            <option value="">Escolha...</option>
                            <option value="USUARIO">Usuário</option>
                            <option value="FUNCIONARIO">Funcionário</option>
                            <option value="ADMINISTRADOR">Administrador</option>
                        </select>
                    </div>
                </c:if>

                <c:if test="${usuario == null}">
                    <div class="form-group col-md-6">
                    </c:if>
                    <c:if test="${usuario != null}">
                        <div class="form-group col-md-12">
                        </c:if>
                        <label for="mail">E-mail:</label>
                        <input type="email" value="${usuario.email}" class="form-control" name="mail" id="mail" required/>
                    </div>


                    <c:if test="${usuario == null}">
                        <div class="form-group col-md-6">
                            <label for="senha">Senha:</label>
                            <input type="password" class="form-control" name="senha" id="senha" required/>
                        </div>
                    </c:if>

                </div>

                <div class="form-group">
                    <label for="name">Nome:</label>
                    <input type="text" class="form-control" name="name" id="name" value="${usuario.nome}" maxlength="255" required/>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label for="rg">RG:</label>
                        <input type="text" class="form-control" name="rg" id="rg" value="${usuario.rg}" required maxlength="20" required/>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="tppessoa">Tipo de Pessoa:</label>
                        <select class="custom-select" name="tipo_pessoa" id="tppessoa" class="form-control">
                            <option value="F">Física</option>
                            <option value="J">Jurídica</option>
                        </select>
                    </div>
                    <div id="divcpfcnpj" class="form-group col-md-4">
                        <label for="cpf">CPF/CNPJ:</label>
                        <input type="text" class="form-control" value="${usuario.cpfcnpj}" name="cpfcnpj" onkeypress='mascaraMutuario(this, cpfCnpj)' onblur='execValidaCpfCnpj(this.value)' id="cpfcnpj" maxlength="18" required/>
                    </div>
                    <div class="form-group col-md-4" >
                        <label for="tel_celular">Telefone celular:</label>
                        <input type="text" class="form-control" name="tel_celular" id="tel_celular" value="${usuario.tel_celular}" required/>
                    </div>
                    <div class="form-group col-md-4" >
                        <label for="contato">Telefone residencial:</label>
                        <input type="text" class="form-control" name="tel_residencial" id="tel_residencial" value="${usuario.tel_residencial}"/>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="logradouro">Logradouro:</label>
                        <input type="text" class="form-control" name="logradouro" id="logradouro" value="${usuario.endereco.logradouro}" placeholder="" required>
                    </div>
                    <div class="form-group col-md-2">
                        <label for="numero">Número:</label>
                        <input type="text" class="form-control" name="numero" id="numero" value="${usuario.endereco.numero}" placeholder="" required>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="complemento">Complemento:</label>
                        <input type="text" class="form-control" name="complemento" id="complemento" value="${usuario.endereco.complemento}" placeholder="">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="cidade">Cidade:</label>
                        <input type="text" class="form-control" name="cidade" id="cidade" value="${usuario.endereco.cidade}" required>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="estado">Estado:</label>
                        <select class="custom-select" name="estado" id="estado" class="form-control" required>
                            <option value="AC">Acre</option>
                            <option value="AL">Alagoas</option>
                            <option value="AP">Amapá</option>
                            <option value="AM">Amazonas</option>
                            <option value="BA">Bahia</option>
                            <option value="CE">Ceará</option>
                            <option value="DF">Distrito Federal</option>
                            <option value="ES">Espírito Santo</option>
                            <option value="GO">Goiás</option>
                            <option value="MA">Maranhão</option>
                            <option value="MT">Mato Grosso</option>
                            <option value="MS">Mato Grosso do Sul</option>
                            <option value="MG">Minas Gerais</option>
                            <option value="PA">Pará</option>
                            <option value="PB">Paraíba</option>
                            <option value="PR">Paraná</option>
                            <option value="PE">Pernambuco</option>
                            <option value="PI">Piauí</option>
                            <option value="RJ">Rio de Janeiro</option>
                            <option value="RN">Rio Grande do Norte</option>
                            <option value="RS">Rio Grande do Sul</option>
                            <option value="RO">Rondônia</option>
                            <option value="RR">Roraima</option>
                            <option value="SC">Santa Catarina</option>
                            <option value="SP">São Paulo</option>
                            <option value="SE">Sergipe</option>
                            <option value="TO">Tocantins</option>
                        </select>
                    </div>
                    <div class="form-group col-md-2">
                        <label for="cep">CEP:</label>
                        <input type="text" size="10" maxlength="9"
                                class="form-control" value="${usuario.endereco.cep}" name="cep" id="cep" required>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="bairro">Bairro:</label>
                        <input type="text" class="form-control" name="bairro" value="${usuario.endereco.bairro}" id="bairro" required>
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

<script>
    document.addEventListener('DOMContentLoaded', function () {
        document.getElementById('tppessoa').value = "<c:out value='${usuario.tipoPessoa}' />";
        document.getElementById('estado').value = "<c:out value='${usuario.endereco.estado}' />";
        document.getElementById('nivel').value = "<c:out value='${usuario.nivel}' />";
        let a = "<c:out value='${usuario}' />";
        if (a !== "") {
            document.getElementById('cpfcnpj').readOnly = true;
            document.getElementById("tppessoa").disabled = true;
        }
    });
</script>