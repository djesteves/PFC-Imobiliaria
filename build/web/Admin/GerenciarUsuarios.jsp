<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/header.jsp" />


<div class="card bg-primary mb-4 text-white text-center">
    <div class="card-header">Dashboard - Usu�rios</div>
</div>

<div class="container">

    <table id="usuariostable" class="table table-hover table-striped table-bordered">

        <thead class="thead">
            <tr>
                <th scope="col">A��es</th>
                <th scope="col">ID Usuario</th>
                <th scope="col">Nome</th>
                <th scope="col">CPF/CNPJ</th>
                <th scope="col">Nivel de Acesso</th>
                <th scope="col">Situa��o</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="usuario" items="${listUsuario}">
                <tr>
                    <c:if test = "${usuario.login.nivel != 'ADMINISTRADOR'}">
                        <td>
                            <button type="button" class="btn-sm btn-danger" onClick="confirmaDelete(<c:out value="${usuario.id_usuario}" />)"> <span  title="Excluir Usu�rio"><i class="fa fa-trash-o" aria-hidden="true"></i></span></button>
                            <a title="Editar Usu�rio" class="btn-sm btn-primary" href="${pageContext.servletContext.contextPath}/controle/UsuarioConsultar?id=<c:out value='${usuario.id_usuario}' />"><i class="fas fa-user-edit"></i></a>
                        </td>
                    </c:if>
                    <c:if test = "${usuario.login.nivel == 'ADMINISTRADOR'}">
                        <td></td>
                    </c:if>
                    <td><c:out value="${usuario.id_usuario}" /></td>
                    <td><c:out value="${usuario.nome}" /></td>
                    <td><c:out value="${usuario.cpfcnpj}" /></td>
                    <td><c:out value="${usuario.login.nivel}" /></td>
                    <td><c:out value="${usuario.login.situacao}" /></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<script type="text/javascript">
    function confirmaDelete(id) {
        if (confirm('Tem certeza que deseja excluir este Usu�rio?')) {
            window.location.href = "${pageContext.servletContext.contextPath}/controle/UsuarioDeletar?id=" + id;
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
<jsp:include page="/footer.jsp" />