<%-- 
    Document   : CadastrarImovel
    Created on : 25/02/2020, 23:01:47
    Author     : Diego
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="../header.jsp" />

<div class="container portfolio-block">
    <div class="heading">
        <c:if test="${imovel != null}">
            <p>Editar Imóvel</p>
        </c:if>
        <c:if test="${imovel == null}">
            <p>Cadastro de Imóvel</p>
        </c:if>
    </div>

    <c:if test="${imovel != null}">

        <form action="<%=request.getContextPath()%>/controle/ImovelAlterar" method="post" name="form_cadastro">
            <input type="hidden" name="id" value="<c:out value='${imovel.id_imovel}' />" />
            <input type="hidden" name="idu" value="<c:out value='${imovel.usuario.id_usuario}' />" />
            <input type="hidden" name="ide" value="<c:out value='${imovel.endereco.id_endereco}' />" />
        </c:if>
        <c:if test="${usuario == null}">
            <form action="<%=request.getContextPath()%>/controle/ImovelCadastrar" method="post" enctype="multipart/form-data">
            </c:if>


            <div class="form-group">
                <label for="name">Titulo:</label>
                <input type="text" class="form-control" name="titulo" id="titulo"  value="<c:out value='${imovel.titulo}' />" maxlength="255"  />
            </div>

            <div class="form-group">
                <label for="descricao">Descrição:</label>
                <textarea class="form-control" name="descricao" id="descricao" maxlength="800" rows="3"></textarea>
            </div>

            <div class="form-row">
                <div class="form-group col-md-4">
                    <label for="comodos">Quantidade de Comodos:</label>
                    <input type="text" class="form-control" name="comodos" id="comodos"   value="<c:out value='${imovel.comodos}' />" maxlength="2" />
                </div>
                <div class="form-group col-md-4">
                    <label for="banheiro">Quantidade de Banheiros/Suítes:</label>
                    <input type="text" class="form-control" name="banheiro" id="banheiro"  value="<c:out value='${imovel.banheiros}' />" maxlength="2" />
                </div>
                <div class="form-group col-md-4">
                    <label for="garagem">Quantidade de Vagas na Garagem:</label>
                    <input type="text" class="form-control" name="garagem" id="garagem"  value="<c:out value='${imovel.vagas_garagem}' />" maxlength="2" />
                </div>

                <div class="form-group col-md-4">
                    <label for="areatotal">Área Total:</label>
                    <input type="text" class="form-control" name="areatotal" id="areatotal"   value="<c:out value='${imovel.area_total}' />" maxlength="5" />
                </div>

                <div class="form-group col-md-4">
                    <label for="areaedificada">Área Edificada:</label>
                    <input type="text" class="form-control" name="areaedificada" id="areaedificada"  value="<c:out value='${imovel.area_edificada}' />"  maxlength="5" />
                </div>

                <div class="form-group col-md-4">
                    <label for="areaedificada">Valor do Imóvel:</label>
                    <input type="text" class="form-control" name="valorimovel" id="valorimovel"  value="${imovel.valor}" maxlength="15" />
           
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

            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="logradouro">Logradouro:</label>
                    <input type="text" class="form-control" name="logradouro" id="logradouro" value="<c:out value='${imovel.endereco.logradouro}' />" placeholder="" >
                </div>
                <div class="form-group col-md-2">
                    <label for="numero">Número:</label>
                    <input type="text" class="form-control" name="numero" id="numero" value="<c:out value='${imovel.endereco.numero}' />" placeholder="" >
                </div>
                <div class="form-group col-md-4">
                    <label for="complemento">Complemento:</label>
                    <input type="text" class="form-control" name="complemento" id="complemento" value="<c:out value='${imovel.endereco.complemento}' />" placeholder="">
                </div>
                <div class="form-group col-md-6">
                    <label for="cidade">Cidade:</label>
                    <input type="text" class="form-control" name="cidade" id="cidade" value="<c:out value='${imovel.endereco.cidade}' />" >
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
                    </select>
                </div>
                <div class="form-group col-md-2">
                    <label for="cep">CEP:</label>
                    <input type="text" size="10" maxlength="9"
                           onblur="pesquisacep(this.value);" class="form-control" value="<c:out value='${imovel.endereco.cep}' />" name="cep" id="cep" >
                </div>
                <div class="form-group col-md-6">
                    <label for="bairro">Bairro:</label>
                    <input type="text" class="form-control" name="bairro" value="<c:out value='${imovel.endereco.bairro}' />" id="bairro" >
                </div>
            </div>
            <c:if test="${imovel == null}">
                <div class="form-group">
                    <label for="uploadFile">Defina a imagem do anúncio:</label>
                    <input type="file" class="form-control-file" accept="image/png, image/jpeg" id="uploadFile" name="uploadFile" required/>
                </div>
            </c:if>

            <hr>
            <div class="button">
                <button type="submit" class="btn btn-primary">Confirmar</button>
                <button type="button" onclick="window.history.back();" class="btn btn-danger">Voltar</button>
            </div>

        </form>
</div>
<script>
    $(document).ready(function () {
        $("#tpimovel").val("<c:out value='${imovel.tipo_imovel}' />");
        $("#estado").val("<c:out value='${imovel.endereco.estado}' />");
        $("#descricao").val("<c:out value='${imovel.descricao}' />");
    });
</script>


<jsp:include page="../footer.jsp" />