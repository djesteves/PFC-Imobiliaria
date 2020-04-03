<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Imovel"%>
<jsp:include page="../header.jsp" />

<div class="card bg-primary mb-4 text-white text-center">
        <div class="card-header">Meus Imóveis</div>
</div>

<div class="container">
    <c:if test = "${listaImovel != null}">
        
        <div class="container mt-5">   
            <div class="row">
                <c:forEach var="i" items="${listaImovel}">
                    
                    <div class="col-md-4 mb-5">
                        <div class="card h-100">
                            <img class="card-img-top" src="<%=request.getContextPath()%>/Resources/upload/${i.diretorioimg}" alt="Imagem de capa do card">
                            <div class="card-body">
                                <h2 class="card-title"><c:out value='${i.titulo}' /></h2>
                                <p class="card-text"><c:out value='${i.descricao}' /></p>
                            </div>
                            <div class="card-footer">
                                <div class="btn-group float-right" role="group" aria-label="AcoesImovel">
                                    <a title="Alterar Imóvel" href="<%=request.getContextPath()%>/controle/ImovelConsultar?id=<c:out value='${i.id_imovel}' />&idu=<c:out value='${i.usuario.id_usuario}' />" class="btn btn-primary"><i class="far fa-edit"></i></a>
                                    <a title="Excluir Imóvel" href="<%=request.getContextPath()%>/controle/ImovelConsultar?id=<c:out value='${i.id_imovel}' />" class="btn btn-Danger"><i class="fas fa-trash-alt"></i></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </c:if>
    <c:if test = "${listaImovel == null}">
        <p align="center"> <strong> Nenhum Imóvel Cadastrado </strong> </p>
    </c:if>
</div>

<jsp:include page="../footer.jsp" />
