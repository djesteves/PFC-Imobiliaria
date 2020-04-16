<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/header.jsp" />


<div class="table-responsive container portfolio-block">

    <p class="heading">Aprovar Im�veis</p>

    <table id="usuariostable" class="table table-hover table-striped table-bordered">

        <thead class="thead">
            <tr>
                <th scope="col">A��es</th>
                <th scope="col">ID Im�vel</th>
                <th scope="col">Titulo</th>
                <th scope="col">Valor</th>
                <th scope="col">Status</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="imovel" items="${ImoveisEmAnalise}">
                <tr>
                    <td>
                        <a title="Consultar Im�vel" class="btn-sm btn-primary" href="${pageContext.servletContext.contextPath}/controle/UsuarioConsultar?id=<c:out value='${imovel.id_imovel}' />"><i class="fas fa-eye"></i></a>

                        <a title="Aprovar Im�vel" class="btn-sm btn-success" href="${pageContext.servletContext.contextPath}/controle/AprovarImovel?id=<c:out value='${imovel.id_imovel}' />"><i class="fas fa-check"></i></a>

                        <a title="Reprovar Im�vel" class="btn-sm btn-danger" href="#" onClick="confirmaDelete(<c:out value="${imovel.id_imovel}" />)"><i class="fas fa-times"></i></a>
                        
                    </td>
                    <td><c:out value="${imovel.id_imovel}" /></td>
                    <td><c:out value="${imovel.titulo}" /></td>
                    <td>R$ <c:out value="${imovel.valor}" /></td>
                    <td><c:out value="${imovel.status}" /></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<script type="text/javascript">
    function confirmaDelete(id) {
        if (confirm('Tem certeza que deseja excluir este Usu�rio?')) {
            window.location.href = "${pageContext.servletContext.contextPath}/controle/ImovelDeletar?id=" + id;
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