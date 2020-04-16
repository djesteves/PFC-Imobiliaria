<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../header.jsp" />

<div class="portfolio-block">
    <div class="heading">
        <c:if test="${usuario != null}">
            <p>Editar Usuário</p>
        </c:if>
        <c:if test="${usuario == null}">
            <p>Cadastro de Usuário</p>
        </c:if>
    </div>


    <c:if test="${usuario != null}">

        <form action="<%=request.getContextPath()%>/controle/UsuarioAlterar" method="post" name="form_cadastro">
            <input type="hidden" name="id" value="<c:out value='${usuario.id_usuario}' />" />
            <input type="hidden" name="ide" value="<c:out value='${usuario.endereco.id_endereco}' />" />
        </c:if>
        <form action="<%=request.getContextPath()%>/controle/UsuarioCadastrar" method="post" name="form_cadastro">
            <div class="form-row">
                <c:if test="${usuario == null}">
                    <div class="form-group col-md-6">
                    </c:if>
                    <c:if test="${usuario != null}">
                        <div class="form-group col-md-12">
                        </c:if>
                        <label for="mail">E-mail:</label>
                        <input type="email" value="<c:out value='${usuario.login.email}' />" class="form-control" name="mail" id="mail" required/>
                    </div>
                    <c:if test="${usuario == null}">
                        <div class="form-group col-md-6">
                            <label for="senha">Senha:</label>
                            <input type="password" class="form-control" name="senha" id="senha" required/>
                            <small id="dicasenha" class="form-text text-muted">
                                Sua senha deve ter entre 8 e 20 caracteres, os quais devem ser letras e números, sem espaços, caracteres especiais ou emojis.
                            </small>
                        </div>
                    </c:if>

                </div>

                <div class="form-group">
                    <label for="name">Nome:</label>
                    <input type="text" class="form-control" name="name" id="name" value="<c:out value='${usuario.nome}' />" maxlength="255" required/>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label for="rg">RG/Inscr Estadual:</label>
                        <input type="text" class="form-control" name="rg" id="rg" value="<c:out value='${usuario.rg}' />" required maxlength="20" required/>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="tppessoa">Tipo de Pessoa:</label>
                        <select name="tipo_pessoa" id="tppessoa" class="form-control">
                            <option value="F">Física</option>
                            <option value="J">Jurídica</option>
                        </select>
                    </div>
                    <div id="divcpfcnpj" class="form-group col-md-4">
                        <label for="cpf">CPF/CNPJ:</label>
                        <input type="text" class="form-control" value="<c:out value='${usuario.cpfcnpj}' />" name="cpfcnpj" OnKeyUp="cnpj_cpf(this.name, this.value, 'form_cadastro')" id="cpfcnpj" maxlength="18" required/>
                    </div>
                    <div class="form-group col-md-4" >
                        <label for="tel_celular">Telefone celular:</label>
                        <input type="text" class="form-control" name="tel_celular" id="tel_celular" value="<c:out value='${usuario.tel_celular}' />" required/>
                    </div>
                    <div class="form-group col-md-4" >
                        <label for="contato">Telefone residencial:</label>
                        <input type="text" class="form-control" name="tel_residencial" id="tel_residencial" value="<c:out value='${usuario.tel_residencial}' />"/>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="logradouro">Logradouro:</label>
                        <input type="text" class="form-control" name="logradouro" id="logradouro" value="<c:out value='${usuario.endereco.logradouro}' />" placeholder="" required>
                    </div>
                    <div class="form-group col-md-2">
                        <label for="numero">Número:</label>
                        <input type="text" class="form-control" name="numero" id="numero" value="<c:out value='${usuario.endereco.numero}' />" placeholder="" required>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="complemento">Complemento:</label>
                        <input type="text" class="form-control" name="complemento" id="complemento" value="<c:out value='${usuario.endereco.complemento}' />" placeholder="">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="cidade">Cidade:</label>
                        <input type="text" class="form-control" name="cidade" id="cidade" value="<c:out value='${usuario.endereco.cidade}' />" required>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="estado">Estado:</label>
                        <select name="estado" id="estado" class="form-control" required>
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
                            <option value="EX">Estrangeiro</option>
                        </select>
                    </div>
                    <div class="form-group col-md-2">
                        <label for="cep">CEP:</label>
                        <input type="text" size="10" maxlength="9"
                               onblur="pesquisacep(this.value);" class="form-control" value="<c:out value='${usuario.endereco.cep}' />" name="cep" id="cep" required>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="bairro">Bairro:</label>
                        <input type="text" class="form-control" name="bairro" value="<c:out value='${usuario.endereco.bairro}' />" id="bairro" required>
                    </div>
                </div>

                <hr>

                <div class="button">
                    <button type="submit" class="btn btn-primary">Confirmar</button>
                </div>
        </form>
</div>	

<script>
    $(document).ready(function () {
        $("#tppessoa").val("<c:out value='${usuario.tipoPessoa}' />");
        $("#estado").val("<c:out value='${usuario.endereco.estado}' />");
        var a = '<c:out value='${usuario}' />';
        if (a !== "") {
            $('#cpfcnpj').prop('readOnly', true);
            $("#tppessoa").prop("disabled", true);
        }
    });
</script>

<jsp:include page="../footer.jsp" />