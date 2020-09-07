<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="/header.jsp" />

<div class="table-responsive container">

    <div class="text-center">
        <p>Aprovar Im�veis</p>
    </div>

    <table id="usuariostable" class="table table-sm table-striped table-bordered">

        <thead class="thead table-primary">
            <tr>
                <th scope="col">A��es</th>
                <th scope="col">ID Im�vel</th>
                <th scope="col">Usr Cadastro</th>
                <th scope="col">Dt Cadastro</th>
                <th scope="col">Valor</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="imovel" items="${ImoveisEmAnalise}">
                <tr>
                    <td>
                        <a title="Consultar Im�vel" class="btn-sm btn-primary" href="${pageContext.servletContext.contextPath}/Controle/ImovelListarPorID?id=<c:out value='${imovel.id_imovel}' />&idu=<c:out value='${imovel.usuario.id_usuario}' />"><i class="fas fa-eye"></i></a>

                        <a title="Aprovar Im�vel" class="btn-sm btn-success" href="${pageContext.servletContext.contextPath}/Controle/ImovelAprovar?id=<c:out value='${imovel.id_imovel}' />&email=<c:out value='${imovel.usuario.login.email}' />"><i class="fas fa-check"></i></a>

                        <a title="Reprovar Im�vel" class="btn-sm btn-danger" href="#" onClick="confirmaDelete(${imovel.id_imovel})"><i class="fas fa-times"></i></a>

                    </td>
                    <td><c:out value="${imovel.id_imovel}" /></td>
                    <td><c:out value="${imovel.usuario.nome}" /></td>
                    <td><c:out value="${imovel.data_cadastro}" /></td>
                    <td><fmt:formatNumber minFractionDigits="2" type="currency" value="${imovel.valor}"/>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<script type="text/javascript">
    function confirmaDelete(id) {
        if (confirm('Tem certeza que deseja reprovar este im�vel?')) {
            window.location.href = "${pageContext.servletContext.contextPath}/Controle/ImovelReprovar?id=" + id;
        }
    }

    $(document).ready(function () {
        $('#usuariostable').dataTable({
            "ordering": false,
            "language": {

                "sEmptyTable": "Nenhum registro encontrado",
                "sInfo": "Mostrando de _START_ at� _END_ de _TOTAL_ registros",
                "sInfoEmpty": "Mostrando 0 at� 0 de 0 registros",
                "sInfoFiltered": "(Filtrados de _MAX_ registros)",
                "sInfoPostFix": "",
                "sInfoThousands": ".",
                "sLengthMenu": "_MENU_ Resultados por p�gina",
                "sLoadingRecords": "Carregando...",
                "sProcessing": "Processando...",
                "sZeroRecords": "Nenhum registro encontrado",
                "sSearch": "Pesquisar",
                "oPaginate": {
                    "sNext": "Pr�ximo",
                    "sPrevious": "Anterior",
                    "sFirst": "Primeiro",
                    "sLast": "�ltimo"
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