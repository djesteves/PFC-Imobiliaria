<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Imovel"%>
<jsp:include page="../header.jsp" />


<div class="container portfolio-block">
    <c:if test = "${listaImovel != null}">
        <div class="card-deck">
            <div class="row">
                <c:forEach var="i" items="${listaImovel}">
                    <div class="col-6 col-sm-4 mb-3">
                        <div class="card w-100">
                            <img class="card-img-top" src="<%=request.getContextPath()%>/Resources/upload/${i.diretorioimg}" alt="Imagem de capa do card" height="225" width="210">
                            <div class="card-body">
                                <h2 class="card-title"><c:out value='${i.titulo}' /></h2>
                                <p class="card-text"><c:out value='${i.descricao}' /></p>
                            </div>
                            <div class="card-footer">
                                <div class="btn-group float-right" role="group" aria-label="AcoesImovel">
                                    <a title="Alterar Im�vel" href="<%=request.getContextPath()%>/controle/ImovelConsultar?id=<c:out value='${i.id_imovel}' />&idu=<c:out value='${i.usuario.id_usuario}' />" class="btn btn-primary"><i class="far fa-edit"></i></a>
                                    <a title="Deletar Im�vel" href="<%=request.getContextPath()%>/controle/ImovelConsultar?id=<c:out value='${i.id_imovel}' />" class="btn btn-Danger"><i class="fas fa-trash-alt"></i></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </c:if>
    <c:if test = "${listaImovel == null}">
        <p align="center"> <strong> Nenhum Im�vel Cadastrado </strong> </p>
    </c:if>
</div>

<jsp:include page="../footer.jsp" />
