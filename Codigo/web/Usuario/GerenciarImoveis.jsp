<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../header.jsp" />

<div class="container">
    <div class="text-center">

        <p>Meus Im�veis</p>

    </div>

    <div class="row">
        <c:forEach var="i" items="${listaImovel}">
            <div class="col-lg-4 col-md-6 mb-4">
                <div class="card">
                    <img class="card-img-top" src="../Resources/upload/${i.diretorio_imagem}" alt="Imagem do Im�vel" height="225" width="210">
                    <div class="card-body">
                        <h2 class="card-title">${i.titulo}</h2>
                        <p class="card-text">${i.descricao}</p>
                    </div>
                    <div class="card-footer">
                        <div class="float-left">
                            <p class="card-text">${i.status}</p>
                        </div>   
                        <div class="btn-group float-right" role="group" aria-label="AcoesImovel">
                            <a title="Verificar Agendamentos" href="<%=request.getContextPath()%>/Controle/AgendamentoImovel?id=${i.id_imovel}" class="btn btn-info"><i class="fas fa-book"></i></a>
                            <a title="Alterar Im�vel" href="<%=request.getContextPath()%>/Controle/ImovelListarPorID?id=${i.id_imovel}" class="btn btn-primary"><i class="far fa-edit"></i></a>
                            <a title="Deletar Im�vel" href="#" onClick="confirmaDelete(${i.id_imovel})" class="btn btn-Danger"><i class="fas fa-trash-alt"></i></a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <c:if test="${empty listaImovel}">
        <div class="text-center">
            <p><strong> Nenhum Im�vel Cadastrado </strong> </p>
        </div>
    </c:if>

</div>

<jsp:include page="../footer.jsp" />

<script>
    function confirmaDelete(id) {
        if (confirm('Tem certeza que deseja excluir este im�vel?')) {
            window.location.href = "${pageContext.servletContext.contextPath}/Controle/ImovelExcluir?id=" + id;
        }
    }
</script>
