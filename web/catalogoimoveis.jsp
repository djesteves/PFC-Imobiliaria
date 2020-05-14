<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="pt-BR" />

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Imovel"%>
<jsp:include page="header.jsp" />

<div class="container-fluid portfolio-block">
    <div class="heading">

        <p>Catálogo de Imóveis</p>

    </div>
    <div class="row">

        <div class="col-md-4 col-lg-3 mb-3" data-aos="fade-down" data-aos-delay="200">
            <form>
                <div class="heading">

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
                </select>

                <hr>

                <button type="submit" class="btn btn-primary my-1">Pesquisar</button>

            </form>

        </div>

        <div class="col-md-8 col-lg-9" data-aos="fade-left" data-aos-delay="200">

            <div class="row">
                <c:forEach var="i" items="${listaImoveis}">
                    <div class="col-sm-6 col-xl-4 mb-3 align-items-center">
                        <div class="card">
                            <img class="card-img-top" src="<%=request.getContextPath()%>/Resources/upload/${i.diretorioimg}" alt="Imagem de capa do card" height="225" width="210">
                            <div class="card-body">
                                <h2 class="card-title"><c:out value='${i.titulo}' /></h2>
                                <p class="card-text"><c:out value='${i.descricao}' /></p>
                            </div>
                            <div class="card-footer">
                                <p class="card-text float-right"><fmt:formatNumber value="${i.valor}" minFractionDigits="2" type="currency" /></p>
                                
                                
                            </div>
                        </div>
                    </div>
                </c:forEach>


                <c:if test="${empty listaImoveis}">
                    <p class="heading"> <strong> Nenhum Imóvel Cadastrado </strong> </p>
                </c:if>

            </div>
        </div>

    </div>

</div>


<jsp:include page="footer.jsp" />