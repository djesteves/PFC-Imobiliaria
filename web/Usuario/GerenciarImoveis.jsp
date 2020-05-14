<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Imovel"%>
<jsp:include page="../header.jsp" />


<div class="container portfolio-block">

    <div class="row">
        <c:forEach var="i" items="${listaImovel}">
            <div class="col-sm-6 col-xl-4 mb-3">
                <div class="card">
                    <img class="card-img-top" src="<%=request.getContextPath()%>/Resources/upload/${i.diretorioimg}" alt="Imagem de capa do card" height="225" width="210">
                    <div class="card-body">
                        <h2 class="card-title"><c:out value='${i.titulo}' /></h2>
                        <p class="card-text"><c:out value='${i.descricao}' /></p>
                    </div>
                    <div class="card-footer">
                        <div class="float-left">
                            <p class="card-text"><c:out value='${i.status}' /></p>
                        </div>   
                        <div class="btn-group float-right" role="group" aria-label="AcoesImovel">
                            <a title="Alterar Imóvel" href="<%=request.getContextPath()%>/controle/ImovelConsultar?id=<c:out value='${i.id_imovel}' />&idu=<c:out value='${i.usuario.id_usuario}' />" class="btn btn-primary"><i class="far fa-edit"></i></a>
                            <a title="Deletar Imóvel" href="<%=request.getContextPath()%>/controle/ImovelConsultar?id=<c:out value='${i.id_imovel}' />" class="btn btn-Danger"><i class="fas fa-trash-alt"></i></a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <c:if test="${empty listaImovel}">
        <p class="heading"> <strong> Nenhum Imóvel Cadastrado </strong> </p>
    </c:if>

</div>

<jsp:include page="../footer.jsp" />
