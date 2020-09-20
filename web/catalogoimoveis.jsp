<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="pt-BR" />

<jsp:include page="header.jsp" />

<div class="container">
    <div class="text-center">
        <p>Catálogo de Imóveis</p>
    </div>

    <div class="row">
        <div class="col-lg-3 col-md-4 mb-4" data-aos="fade-down" data-aos-delay="200">
            <form action="<%=request.getContextPath()%>/Controle/ImovelListarAprovados" class="formulario" method="post">
                <div class="text-center">
                    <p>Filtros de Busca</p>
                </div>

                <label for="selectquartos">Nº de Quartos</label>
                <select class="form-control" id="selectquartos" name="selectquartos">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select>

                <label class="my-1 mr-2" for="selectvalor">Valor do Imóvel</label>
                <select class="custom-select my-1 mr-sm-2" id="selectvalor" name="selectvalor">
                    <option value="100000">Até R$ 100.000</option>
                    <option value="250000">Até R$ 250.000</option>
                    <option value="300000">Até R$ 300.000</option>
                    <option value="500000">Até R$ 500.000</option>
                    <option value="1000000">Até R$ 1.000.000</option>
                    <option value="1000000000000000">Maior que R$ 1.000.000</option>
                </select>

                <hr>

                <div class="text-center">
                    <button type="submit" class="btn btn-primary my-1">Pesquisar</button>
                </div>
            </form>
        </div>
        <div class="col-md-8 col-lg-9" data-aos="fade-left" data-aos-delay="200">
            <div class="row">
                <c:forEach var="i" items="${listaImoveis}">
                    <div class="col-sm-6 col-xl-4 mb-3 align-items-center">
                        <div class="card">
                            <img class="card-img-top img-fluid" src="../Resources/upload/${i.diretorio_imagem}" alt="Imagem do Imóvel" height="225" width="210">
                            <div class="card-body">
                                <h2 class="card-title">${i.titulo}</h2>
                                <p class="card-text">${i.descricao}</p>
                            </div>
                            <div class="card-footer">
                                <p class="card-text float-right"><fmt:formatNumber value="${i.valor}" minFractionDigits="2" type="currency" /></p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <c:if test="${empty listaImoveis}">
                <div class="text-center">
                    <p><strong> Nenhum Imóvel Cadastrado </strong></p>
                </div>
            </c:if>
        </div>
    </div>
</div>


<jsp:include page="footer.jsp" />