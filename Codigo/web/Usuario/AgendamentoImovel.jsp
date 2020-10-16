<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="/header.jsp" />

<div class="table-responsive container">

    <div class="text-center">
        <p>Solicitações de Visitas no Imovel Código ${param.id}</p>
    </div>

    <table id="agendatable" class="table table-sm table-striped table-bordered">

        <thead class="thead table-primary">
            <tr>
                <th scope="col">Ações</th>
                <th scope="col">Nome Solicitante</th>
                <th scope="col">Código Imóvel</th>
                <th scope="col">Data da Solicitação</th>
                <th scope="col">Data do Agendamento</th>
                <th scope="col">Status</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="agenda" items="${listaAgenda}">
                <tr>
                    <td>
                        <!--
                        <a title="Consultar Imóvel" class="btn btn-sm btn-primary" href="${pageContext.servletContext.contextPath}/Controle/ImovelListarPorID?id=${imovel.id_imovel}"><i class="fas fa-eye"></i></a>

                        <a title="Aprovar Imóvel" class="btn btn-sm btn-success" href="${pageContext.servletContext.contextPath}/Controle/ImovelAprovar?id=${imovel.id_imovel}&email=${imovel.usuario.email}"><i class="fas fa-check"></i></a>

                        <a title="Reprovar Imóvel" class="btn btn-sm btn-danger" href="#" onClick="modalReprovar(${imovel.id_imovel})" data-toggle="modal" data-target="#modalReprovar" ><i class="fas fa-times"></i></a>
                        -->
                    </td>
                    <td>${agenda.agendamento.usuario.nome}</td>
                    <td>${agenda.agendamento.imovel.id_imovel}</td>
                    <td><fmt:formatDate pattern="dd/MM/yyyy" value="${agenda.agendamento.dataSolicitacao}"/></td>
                    <td><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${agenda.agendamento.dataAgendamento}"/></td>
                    <td>${agenda.agendamento.status}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>


</div>

<jsp:include page="../footer.jsp" />

<script type="text/javascript">

    $(document).ready(function () {
        $('#agendatable').dataTable({
            "ordering": false,
            "language": {

                "sEmptyTable": "Nenhum agendamento marcado",
                "sInfo": "Mostrando de _START_ até _END_ de _TOTAL_ registros",
                "sInfoEmpty": "Mostrando 0 até 0 de 0 registros",
                "sInfoFiltered": "(Filtrados de _MAX_ registros)",
                "sInfoPostFix": "",
                "sInfoThousands": ".",
                "sLengthMenu": "_MENU_ Resultados por página",
                "sLoadingRecords": "Carregando...",
                "sProcessing": "Processando...",
                "sZeroRecords": "Nenhum registro encontrado",
                "sSearch": "Pesquisar",
                "oPaginate": {
                    "sNext": "Próximo",
                    "sPrevious": "Anterior",
                    "sFirst": "Primeiro",
                    "sLast": "Último"
                },
                "oAria": {
                    "sSortAscending": ": Ordenar colunas de forma ascendente",
                    "sSortDescending": ": Ordenar colunas de forma descendente"
                },
                "select": {
                    "rows": {
                        "_": "Selecionado %d linhas",
                        "0": "Nenhuma linha selecionada",
                        "1": "Selecionado 1 linha"
                    }
                }
            }

        });
    });
</script>