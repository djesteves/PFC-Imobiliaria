<%-- 
    Document   : CadastrarImovel
    Created on : 25/02/2020, 23:01:47
    Author     : Diego
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value = "en_US"/>
<jsp:include page="../navbar.jsp" />

<div class="container">
    <div class="text-center">
        <c:if test="${imovel != null}">
            <p>Editar Imóvel</p>
        </c:if>
        <c:if test="${imovel == null}">
            <p>Cadastro de Imóvel</p>
        </c:if>
    </div>

    <c:if test="${imovel != null}">

        <form action="<%=request.getContextPath()%>/Controle/ImovelAlterar" method="post" name="form_cadastro" class="formulario" id="formulario">
            <input type="hidden" name="id" value="<c:out value='${imovel.id_imovel}' />" />
            <input type="hidden" name="idu" value="<c:out value='${imovel.usuario.id_usuario}' />" />
            <input type="hidden" name="ide" value="<c:out value='${imovel.endereco.id_endereco}' />" />
        </c:if>
        <c:if test="${usuario == null}">
            <form action="<%=request.getContextPath()%>/Controle/ImovelCadastrar" method="post" enctype="multipart/form-data" class="formulario" id="formulario">
            </c:if>

            <div class="form-group">
                <label for="name">Titulo:</label>
                <input type="text" class="form-control" name="titulo" id="titulo"  value="<c:out value='${imovel.titulo}' />" maxlength="255"  required/>
            </div>

            <div class="form-group">
                <label for="descricao">Descrição:</label>
                <textarea class="form-control" name="descricao" id="descricao" maxlength="800" rows="3" required></textarea>
            </div>

            <div class="form-row">
                <div class="form-group col-md-4">
                    <label for="comodos">Quantidade de Comodos:</label>
                    <input type="text" class="form-control" name="comodos" id="comodos"   value="${imovel.comodos}" maxlength="4" required />
                </div>
                <div class="form-group col-md-4">
                    <label for="banheiro">Quantidade de Banheiros/Suítes:</label>
                    <input type="text" class="form-control" name="banheiro" id="banheiro"  value="${imovel.banheiros}" maxlength="4" required/>
                </div>
                <div class="form-group col-md-4">
                    <label for="garagem">Quantidade de Vagas na Garagem:</label>
                    <input type="text" class="form-control" name="garagem" id="garagem"  value="${imovel.vagas_garagem}" maxlength="4" required/>
                </div>

                <div class="form-group col-md-4">
                    <label for="areatotal">Área Total:</label>
                    <input type="text" class="form-control" name="areatotal" id="areatotal" value="<fmt:formatNumber  minFractionDigits="2" value="${imovel.area_total}"/>" maxlength="10" required/>
                </div>

                <div class="form-group col-md-4">
                    <label for="areaedificada">Área Edificada:</label>
                    <input type="text" class="form-control" name="areaedificada" id="areaedificada"  value="<fmt:formatNumber  minFractionDigits="2" value="${imovel.area_edificada}"/>"  maxlength="10" required/>
                </div>

                <div class="form-group col-md-4">
                    <label for="areaedificada">Valor de Venda/Aluguel:</label>

                    <input type="text" class="form-control" name="valorimovel" id="valorimovel" value="<fmt:formatNumber  minFractionDigits="2" value="${imovel.valor}"/>" maxlength="30" required/>
                </div>

                <div class="form-group col-md-4">
                    <label for="tpimovel">Tipo de Imóvel:</label>
                    <select class="custom-select" name="tpimovel" id="tpimovel" class="form-control" required>

                        <option value="GERMINADA">Geminada</option>
                        <option value="SOBRADO">Sobrado</option>
                        <option value="APARTAMENTO">Apartamento</option>
                        <option value="KITNET">Kitnet</option>
                        <option value="FLAT">Flat</option>
                    </select>
                </div>

                <div class="form-group col-md-4">
                    <label for="tpvenda">Tipo de Venda:</label>
                    <select class="custom-select" name="tpvenda" id="tpvenda" class="form-control" required>

                        <option value="VENDA">Venda</option>
                        <option value="ALUGUEL">Aluguel</option>
                    </select>
                </div>

                <div class="form-group col-md-4">
                    <label for="iptu">Valor do IPTU:</label>
                    <input type="text" class="form-control" name="iptu" id="iptu" value="<fmt:formatNumber  minFractionDigits="2" value="${imovel.iptu}"/>" maxlength="30" />
                </div>

                <div class="form-group col-md-4">
                    <label for="condominio">Valor do Condomínio:</label>
                    <input type="text" class="form-control" name="condominio" id="condominio" value="<fmt:formatNumber minFractionDigits="2" value="${imovel.condominio}"/>" maxlength="30" />
                </div>
            </div>

            <div class="form-row">
                <div class="form-group col-md-2">
                    <label for="cep">CEP:</label>
                    <input type="text" size="10" maxlength="9"
                           onblur="pesquisacep(this.value);" class="form-control" value="${imovel.endereco.cep}" name="cep" id="cep" required>
                </div>
                <div class="form-group col-md-8">
                    <label for="logradouro">Logradouro:</label>
                    <input type="text" class="form-control" name="logradouro" id="logradouro" value="${imovel.endereco.logradouro}" placeholder="" required>
                </div>
                <div class="form-group col-md-2">
                    <label for="numero">Número:</label>
                    <input type="text" class="form-control" name="numero" id="numero" value="${imovel.endereco.numero}" placeholder="" required>
                </div>
                <div class="form-group col-md-5">
                    <label for="complemento">Complemento:</label>
                    <input type="text" class="form-control" name="complemento" id="complemento" value="${imovel.endereco.complemento}" placeholder="">
                </div>
                <div class="form-group col-md-5">
                    <label for="cidade">Cidade:</label>
                    <input type="text" class="form-control" name="cidade" id="cidade" value="${imovel.endereco.cidade}" required>
                </div>
                <div class="form-group col-md-2">
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

                <div class="form-group col-md-5">
                    <label for="bairro">Bairro:</label>
                    <input type="text" class="form-control" name="bairro" value="${imovel.endereco.bairro}" id="bairro" required>
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

<jsp:include page="../footer.jsp" />

<script>
    $(function () {
        $('#condominio').maskMoney();
        $('#iptu').maskMoney();
        $('#valorimovel').maskMoney();
        $('#areaedificada').maskMoney();
        $('#areatotal').maskMoney();
    })
    $(document).ready(function () {
        $("#tpimovel").val("<c:out value='${imovel.tipo_imovel}' />");
        $("#estado").val("<c:out value='${imovel.endereco.estado}' />");
        $("#descricao").val("<c:out value='${imovel.descricao}' />");
        $("#tpvenda").val("<c:out value='${imovel.modalidade_imovel}' />");

        let a = "<c:out value='${imovel}' />";
        if (a !== "") {
            document.getElementById('valorimovel').readOnly = true;
            document.getElementById('areatotal').readOnly = true;
            document.getElementById('tpvenda').disabled = true;
            document.getElementById('tpimovel').disabled = true;
        }
    });
</script>