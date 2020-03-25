<%@page import="java.sql.ResultSet"%>
<%
    ResultSet rs = (ResultSet) request.getAttribute("rsUser");

    String nome = "";
    String rg = "";
    String tipopessoa = "";
    String cpfcnpj = "";
    String telcelular = "";
    String telresidencial = "";
    String logradouro = "";
    String numero = "";
    String complemento = "";
    String cidade = "";
    String estado = "";
    String cep = "";
    String bairro = "";

    if (rs != null) {
        nome = rs.getString("nome");
        rg = rs.getString("rg");

        if (rs.getString("tipo_pessoa") == null || rs.getString("tipo_pessoa").length() == 0) {
            tipopessoa = "Escolha";
        } else {
            tipopessoa = rs.getString("tipo_pessoa");
        }

        cpfcnpj = rs.getString("cpf_cnpj");
        telcelular = rs.getString("tel_celular");
        telresidencial = rs.getString("tel_residencial");
        logradouro = rs.getString("logradouro");
        numero = rs.getString("numero");
        complemento = rs.getString("complemento");
        cidade = rs.getString("cidade");
        estado = rs.getString("estado");
        cep = rs.getString("cep");
        bairro = rs.getString("bairro");
    } else {
        request.setAttribute("msgerro", "É necessario acessar as configurações da conta pelo Menu");
        request.getRequestDispatcher("/erro.jsp").forward(request, response);
    }
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../header.jsp" />


<div class="container">
    
    <c:if test="${msgupdate != null}">
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
            ${msgupdate}
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    </c:if> 

    <c:if test="${msgerro != null}">
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
            ${msgerro}
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    </c:if> 


    <div class="row">
        <div class="col-3">
            <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                <a class="nav-link active" id="v-pills-usuariodados-tab" data-toggle="pill" href="#v-pills-usuariodados" role="tab" aria-controls="v-pills-usuariodados" aria-selected="true">Dados</a>
                <a class="nav-link" id="v-pills-usuarioendereco-tab" data-toggle="pill" href="#v-pills-usuarioendereco" role="tab" aria-controls="v-pills-usuarioendereco" aria-selected="false">Endereço</a>
                <a class="nav-link" id="v-pills-usuarioalterarsenha-tab" data-toggle="pill" href="#v-pills-usuarioalterarsenha" role="tab" aria-controls="v-pills-usuarioalterarsenha" aria-selected="false">Alterar Senha</a>
            </div>
        </div>

        <div class="col-9">
            <div class="tab-content" id="v-pills-tabContent">

                <div class="tab-pane fade show active" id="v-pills-usuariodados" role="tabpanel" aria-labelledby="v-pills-usuariodados-tab">

                    <form action="<%=request.getContextPath()%>/controle/UsuarioAlterar" method="post">
                        <div class="card mb-3">
                            <div class="card-body">
                                <div class="form-group">
                                    <label for="name">Nome:</label>
                                    <input type="text" class="form-control" name="name" id="name" value="<%out.print(nome);%>" maxlength="255" required readonly/>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-4">
                                        <label for="rg">RG:</label>
                                        <input type="text" class="form-control" name="rg" id="rg" value="<%out.print(rg);%>" required maxlength="20" readonly/>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="tppessoa">Tipo de Pessoa:</label>
                                        <select name="tipo_pessoa" id="tppessoa" class="form-control" disabled>
                                            <option value="<%out.print(tipopessoa);%>" selected>
                                                <%
                                                    String tppessoa = "";
                                                    if (tipopessoa.equalsIgnoreCase("F")) {
                                                        tppessoa = "Física";
                                                    } else if (tipopessoa.equalsIgnoreCase("J")) {
                                                        tppessoa = "Jurídica";
                                                    } else
                                                %>
                                                <% out.print(tppessoa); %>
                                            </option>
                                        </select>
                                    </div>

                                    <div id="divcpfcnpj" class="form-group col-md-4">
                                        <label for="cpf">CPF/CNPJ:</label>
                                        <input type="text" class="form-control" name="cpfcnpj" value="<%out.print(cpfcnpj);%>" onblur="cnpj_cpf(this.name, this.value, 'form_cadastro')" id="cpfcnpj" maxlength="18" readonly/>
                                    </div>
                                    <div class="form-group col-md-4" >
                                        <label for="tel_celular">Telefone celular:</label>
                                        <input type="text" class="form-control" name="tel_celular" id="tel_celular" value="<%out.print(telcelular);%>" maxlength="11" required readonly/>
                                    </div>
                                    <div class="form-group col-md-4" >
                                        <label for="contato">Telefone residencial:</label>
                                        <input type="text" class="form-control" name="tel_residencial" id="tel_residencial" value="<%out.print(telresidencial);%>" maxlength="10" readonly/>
                                    </div>
                                    <!-- input hidden criado para saber em que tabela o a dao precisa fazer update apos enviar no post -->
                                    <div class="form-group col-md-4" >
                                        <input type="hidden" value="userdados" name="acao" id="acao"/>
                                    </div>

                                </div>
                            </div>
                        </div>
                        <hr>
                        <div class="button">
                            <button id="btnAlterar" type="submit" class="btn btn-primary float-right" disabled>Confirmar</button>
                            <button id="btnHEditar" type="button" class="btn btn-secondary float-right" onclick="editausuario();"><i class="fas fa-user-edit"></i> Alterar</button>
                        </div>
                    </form>

                </div>

                <div class="tab-pane fade" id="v-pills-usuarioendereco" role="tabpanel" aria-labelledby="v-pills-usuarioendereco-tab">
                    <form action="<%=request.getContextPath()%>/controle/UsuarioAlterar" method="post">
                        <div class="card mb-3">
                            <div class="card-body">
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="logradouro">Logradouro:</label>
                                        <input type="text" class="form-control" name="logradouro" id="logradouro" value="<%out.print(logradouro);%>" placeholder="" readonly required>
                                    </div>
                                    <div class="form-group col-md-2">
                                        <label for="numero">Número:</label>
                                        <input type="text" class="form-control" name="numero" id="numero" value="<%out.print(numero);%>" placeholder="" readonly required>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="complemento">Complemento:</label>
                                        <input type="text" class="form-control" name="complemento" id="complemento" value="<%out.print(complemento);%>" readonly placeholder="">
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="cidade">Cidade:</label>
                                        <input type="text" class="form-control" name="cidade" id="cidade" value="<%out.print(cidade);%>" readonly required>
                                    </div>

                                    <div id="divestadodb" class="form-group col-md-4">
                                        <label for="estadodb">Estado:</label>
                                        <input type="text" class="form-control" name="estadodb" id="estadodb" value="<%out.print(estado);%>" readonly required>
                                    </div>

                                    <div id="divestado" class="form-group col-md-4" style="display : none;">
                                        <label for="estado">Estado:</label>
                                        <select name="estado" id="estado" class="form-control" disabled required>
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
                                               onblur="pesquisacep(this.value);" class="form-control" name="cep" id="cep" value="<%out.print(cep);%>" readonly required>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="bairro">Bairro:</label>
                                        <input type="text" class="form-control" name="bairro" id="bairro" value="<%out.print(bairro);%>" readonly required>
                                    </div>
                                    <!-- input hidden criado para saber em que tabela a dao precisa fazer update apos enviar no post -->
                                    <div class="form-group col-md-4" >
                                        <input type="hidden" value="userendereco" name="acao" id="acao"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <hr>
                        <div class="button">
                            <button id="btnAlterarEndereco" type="submit" class="btn btn-primary float-right" disabled>Confirmar</button>
                            <button id="btnHEditarEndereco" type="button" class="btn btn-secondary float-right" onclick="editausuarioendereco();"><i class="fas fa-user-edit"></i> Alterar</button>
                        </div>
                    </form>
                </div>


                <div class="tab-pane fade" id="v-pills-usuarioalterarsenha" role="tabpanel" aria-labelledby="v-pills-usuarioalterarsenha-tab">      
                    <form action="<%=request.getContextPath()%>/controle/UsuarioAlterar" method="post">
                        <div class="card mb-3">
                            <div class="card-body">
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="senha">Nova Senha:</label>
                                        <input type="password" class="form-control" name="senha" id="senha" required/>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="resenha">Confirme a Senha:</label>
                                        <input type="password" class="form-control" name ="resenha" id="resenha" required/>
                                        <small id="avisosenha" class="form-text text-muted">
                                            Sua senha deve ter entre 8 e 20 caracteres, os quais devem ser letras e números, sem espaços, caracteres especiais ou emojis.
                                        </small>
                                    </div>
                                    <!-- input hidden criado para saber em que tabela a dao precisa fazer update apos enviar no post -->
                                    <div class="form-group col-md-4" >
                                        <input type="hidden" value="usersenha" name="acao" id="acao"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <hr>
                        <div class="button">
                            <button id="btnAlterarSenha" type="submit" class="btn btn-primary float-right" >Confirmar</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
                        
    <jsp:include page="../footer.jsp" />
