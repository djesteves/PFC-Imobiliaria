<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../header.jsp" />

<div class="container">

    <c:if test="${msg != null}">
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
            ${msg}
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    </c:if> 
    <h5 class="mt-0 text-center text-justify">Cadastro de Usuário</h5>
    <hr>
    <form action="<%=request.getContextPath()%>/controle/UsuarioCadastrar" method="post" name="form_cadastro">
        <div class="row">
            <div class="col-3">
                <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                    <a class="nav-link active" id="v-pills-login-tab" data-toggle="pill" href="#v-pills-login" role="tab" aria-controls="v-pills-login" aria-selected="true">Dados de Login</a>
                    <a class="nav-link" id="v-pills-dados-tab" data-toggle="pill" href="#v-pills-dados" role="tab" aria-controls="v-pills-dados" aria-selected="false">Dados de Cadastro</a>
                    <a class="nav-link" id="v-pills-endereco-tab" data-toggle="pill" href="#v-pills-endereco" role="tab" aria-controls="v-pills-endereco" aria-selected="false">Endereço do Usuário</a>
                </div>
            </div>
            <div class="col-9">
                <div class="tab-content" id="v-pills-tabContent">
                    <div class="tab-pane fade show active" id="v-pills-login" role="tabpanel" aria-labelledby="v-pills-login-tab">
                        <div class="card mb-3">
                            <div class="card-body">
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="mail">E-mail:</label>
                                        <input type="email" class="form-control" name="mail" id="mail" required/>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="senha">Senha:</label>
                                        <input type="password" class="form-control" name="senha" id="senha" required/>
                                        <small id="dicasenha" class="form-text text-muted">
                                            Sua senha deve ter entre 8 e 20 caracteres, os quais devem ser letras e números, sem espaços, caracteres especiais ou emojis.
                                        </small>
                                    </div>
                                    <div style="margin-bottom:10px">
                                        <small  id="loginask" class="form-text text-muted"><a href="<%=request.getContextPath()%>/Usuario/Login.jsp">Já possui conta? clique aqui.</a></small>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                    </div>




                    <div class="tab-pane fade" id="v-pills-dados" role="tabpanel" aria-labelledby="v-pills-dados-tab">
                        <div class="card mb-3">
                            <div class="card-body">
                                <div class="form-group">
                                    <label for="name">Nome:</label>
                                    <input type="text" class="form-control" name="name" id="name" maxlength="255" required/>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-4">
                                        <label for="rg">RG:</label>
                                        <input type="text" class="form-control" name="rg" id="rg" required maxlength="20" required/>
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
                                        <input type="text" class="form-control" name="cpfcnpj" OnKeyUp="cnpj_cpf(this.name, this.value, 'form_cadastro')" id="cpfcnpj" maxlength="18" required/>
                                    </div>
                                    <div class="form-group col-md-4" >
                                        <label for="tel_celular">Telefone celular:</label>
                                        <input type="text" class="form-control" name="tel_celular" id="tel_celular" required/>
                                    </div>
                                    <div class="form-group col-md-4" >
                                        <label for="contato">Telefone residencial:</label>
                                        <input type="text" class="form-control" name="tel_residencial" id="tel_residencial"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>


                    <div class="tab-pane fade" id="v-pills-endereco" role="tabpanel" aria-labelledby="v-pills-endereco-tab">
                        <div class="card mb-3">
                            <div class="card-body">
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="logradouro">Logradouro:</label>
                                        <input type="text" class="form-control" name="logradouro" id="logradouro" placeholder="" required>
                                    </div>
                                    <div class="form-group col-md-2">
                                        <label for="numero">Número:</label>
                                        <input type="text" class="form-control" name="numero" id="numero" placeholder="" required>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="complemento">Complemento:</label>
                                        <input type="text" class="form-control" name="complemento" id="complemento" placeholder="">
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="cidade">Cidade:</label>
                                        <input type="text" class="form-control" name="cidade" id="cidade" required>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="estado">Estado:</label>
                                        <select name="estado" id="estado" class="form-control" required>
                                            <option selected>Escolha...</option>
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
                                               onblur="pesquisacep(this.value);" class="form-control" name="cep" id="cep" required>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="bairro">Bairro:</label>
                                        <input type="text" class="form-control" name="bairro" id="bairro" required>
                                    </div>
                                </div>

                            </div>


                        </div>
                        <div class="button">
                            <button type="submit" class="btn btn-primary float-right">Cadastrar</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>	
</div>



</form>
</div>



<jsp:include page="../footer.jsp" />