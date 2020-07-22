<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/header.jsp" />


<div class="table-responsive container portfolio-block">

    <p class="heading">Usuários Ativos</p>

    <form action="<%=request.getContextPath()%>/Usuario/FormUsuario.jsp" method="post">
        <input type="hidden" value="funcionario" name="modo" id="modo">

        <button type="submit" class="btn btn-success">
            <i class="fas fa-plus"></i>&nbsp;&nbsp;Cadastrar Funcionário
        </button>
    </form>

    <table id="usuariostable" class="table table-hover table-striped table-bordered">

        <thead class="thead">
            <tr>
                <th scope="col">Ações</th>
                <th scope="col">ID Usuario</th>
                <th scope="col">Nome</th>
                <th scope="col">CPF/CNPJ</th>
                <th scope="col">Nivel de Acesso</th>
                <th scope="col">Situação</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="usuario" items="${listUsuario}">
                <tr>
                    <c:if test = "${usuario.login.nivel != 'ADMINISTRADOR'}">
                        <td>
                            <a title="Deletar Usuário" class="btn-sm btn-danger" href="#" onClick="confirmaDelete(<c:out value="${usuario.id_usuario}" />)"><i class="fas fa-trash-alt"></i></a>

                            <a title="Editar Usuário" class="btn-sm btn-primary" href="${pageContext.servletContext.contextPath}/controle/UsuarioConsultar?id=<c:out value='${usuario.id_usuario}' />"><i class="fas fa-user-edit"></i></a>
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


    <script type="text/javascript">
        function confirmaDelete(id) {
            if (confirm('Tem certeza que deseja excluir este Usuário?')) {
                window.location.href = "${pageContext.servletContext.contextPath}/controle/UsuarioDeletar?id=" + id;
            }
        }

        $(document).ready(function () {
            $('#usuariostable').dataTable({
                "ordering": false,
                "language": {

                    "sEmptyTable": "Nenhum registro encontrado",
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