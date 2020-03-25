<%-- 
    Document   : CadastrarImovel
    Created on : 25/02/2020, 23:01:47
    Author     : Diego
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../header.jsp" />


<div class="container">

    <h5 class="mt-0 text-center text-justify">Cadastro de Imóveis</h5>

    <hr>
    <form action="<%=request.getContextPath()%>/controle/ImovelCadastrar" method="post" enctype="multipart/form-data">
        <div class="row">
            <div class="col-3">
                <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                    <a class="nav-link active" id="v-pills-imovel-tab" data-toggle="pill" href="#v-pills-imovel" role="tab" aria-controls="v-pills-imovel" aria-selected="true">Dados do Imóvel</a>
                    <a class="nav-link" id="v-pills-endereco-tab" data-toggle="pill" href="#v-pills-endereco" role="tab" aria-controls="v-pills-endereco" aria-selected="false">Endereço do Imóvel</a>
                    <a class="nav-link" id="v-pills-final-tab" data-toggle="pill" href="#v-pills-final" role="tab" aria-controls="v-pills-final" aria-selected="false">Finalizar Cadastro</a>
                </div>
            </div>
            <div class="col-9">
                <div class="tab-content" id="v-pills-tabContent">
                    <div class="tab-pane fade show active" id="v-pills-imovel" role="tabpanel" aria-labelledby="v-pills-imovel-tab">

                        <div class="card mb-3">
                            <div class="card-body">
                                <div class="form-group">
                                    <label for="name">Titulo:</label>
                                    <input type="text" class="form-control" name="titulo" id="titulo"  maxlength="255"  />
                                </div>

                                <div class="form-group">
                                    <label for="descricao">Descrição:</label>
                                    <textarea class="form-control" name="descricao" id="descricao" rows="3"></textarea>
                                </div>

                                <div class="form-row">
                                    <div class="form-group col-md-4">
                                        <label for="comodos">Quantidade de Comodos:</label>
                                        <input type="text" class="form-control" name="comodos" id="comodos"   maxlength="2" />
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="banheiro">Quantidade de Banheiros/Suítes:</label>
                                        <input type="text" class="form-control" name="banheiro" id="banheiro"   maxlength="2" />
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="garagem">Quantidade de Vagas na Garagem:</label>
                                        <input type="text" class="form-control" name="garagem" id="garagem"   maxlength="2" />
                                    </div>

                                    <div class="form-group col-md-4">
                                        <label for="areatotal">Área Total:</label>
                                        <input type="text" class="form-control" name="areatotal" id="areatotal"   maxlength="2" />
                                    </div>

                                    <div class="form-group col-md-4">
                                        <label for="areaedificada">Área edificada:</label>
                                        <input type="text" class="form-control" name="areaedificada" id="areaedificada"   maxlength="2" />
                                    </div>
                                    
                                    <div class="form-group col-md-4">
                                        <label for="areaedificada">Valor do Imóvel:</label>
                                        <input type="text" class="form-control" name="valorimovel" id="valorimovel"   maxlength="2" />
                                    </div>

                                    <div class="form-group col-md-4">
                                        <label for="tpimovel">Tipo de Imóvel:</label>
                                        <select name="tpimovel" id="tpimovel" class="form-control">
                                            <option selected>Escolha...</option>
                                            <option value="germinada">Geminada</option>
                                            <option value="sobrado">Sobrado</option>
                                            <option value="apartamento">Apartamento</option>
                                            <option value="kitnet">Kitnet</option>
                                            <option value="flat">Flat</option>
                                        </select>
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
                                        <input type="text" class="form-control" name="logradouro" id="logradouro" placeholder="" >
                                    </div>
                                    <div class="form-group col-md-2">
                                        <label for="numero">Número:</label>
                                        <input type="text" class="form-control" name="numero" id="numero" placeholder="" >
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="complemento">Complemento:</label>
                                        <input type="text" class="form-control" name="complemento" id="complemento" placeholder="">
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="cidade">Cidade:</label>
                                        <input type="text" class="form-control" name="cidade" id="cidade" >
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="estado">Estado:</label>
                                        <select name="estado" id="estado" class="form-control" >
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
                                               onblur="pesquisacep(this.value);" class="form-control" name="cep" id="cep" >
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="bairro">Bairro:</label>
                                        <input type="text" class="form-control" name="bairro" id="bairro" >
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="tab-pane fade" id="v-pills-final" role="tabpanel" aria-labelledby="v-pills-final-tab">
                        <div class="card mb-3">
                            <div class="card-body">
                                <div class="form-group">
                                    <label for="uploadFile">Defina as imagens do anúncio:</label>
                                    <input type="file" class="form-control-file" id="uploadFile" name="uploadFile" required/>
                                </div>
                            </div>
                        </div>
                        <div class="button float-right">
                            <button type="submit" class="btn btn-primary">Cadastrar</button>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </form>
</div>


<%--WS DO VIACEP --%>
<script charset="UTF-8" src="<%=request.getContextPath()%>/Resources/wsviacep.js" type="text/javascript"></script>
<%-- /----------------------/ --%>
<script charset="UTF-8" src="<%=request.getContextPath()%>/Resources/scriptJS.js" type="text/javascript"></script>
<%-- /----------------------/ --%>
<script charset="UTF-8" type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.0/jquery.mask.js"></script>

<jsp:include page="../footer.jsp" />