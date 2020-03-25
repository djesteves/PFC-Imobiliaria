<%@page import="java.sql.ResultSet"%>
<%
    ResultSet rs = null;

    if (request.getAttribute("rsUsuarios") != null) {
        rs = (ResultSet) request.getAttribute("rsUsuarios");
    } else {
        request.setAttribute("msgerro", "Não foi possivel exibir os dados");
        request.getRequestDispatcher("/erro.jsp").forward(request, response);
    }
%>

<jsp:include page="/header.jsp" />

<div class="container">

    <!-- Abas nav -->
    <ul class="nav nav-tabs" id="tabletab" role="tablist">
        <li class="nav-item">
            <a class="nav-link active" id="dados-tab" data-toggle="tab" href="#tabledados" role="tab" aria-controls="tabledados" aria-selected="false">Usuários</a>
        </li>

    </ul>

    <div class="tab-content" id="nav-tabContent">
        <div class="tab-pane fade show active" id="tabledados" role="tabpanel" aria-labelledby="tabledados-tab">
            <div class="card mb-3">
                <div class="card-body">

                    <form action="<%=request.getContextPath()%>/controle/AlterarSituacaoUsuario" id="formusuario" method="POST">

                        <%-- Recebe o ID seleciona da table para mandar para a controller --%>
                        <input type="hidden" name="id" id="id"/>

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

                                <% while (rs.next()) { %>
                                <tr>
                                    <% if (!"ADMINISTRADOR".equalsIgnoreCase(rs.getString("nivel_acesso"))) {%>
                                    <td>
                                        <button type="submit"  class="btn btn-danger btn-sm" onClick="alteraSituacao(this, 2)">
                                            <span title="Inativar Usuário"><i class="fa fa-trash-o" aria-hidden="true"></i></span>
                                        </button>
                                    </td>
                                    <% } else {%>
                                    <td></td>
                                    <% } %>
                                    <td><%out.print(rs.getString("id_usuario"));%></td>
                                    <td><%out.print(rs.getString("nome"));%></td>
                                    <td><%out.print(rs.getString("cpf_cnpj"));%></td>
                                    <td><%out.print(rs.getString("nivel_acesso"));%></td>
                                    <td><%out.print(rs.getString("situacao"));%></td>
                                </tr>

                                <% }%>

                            </tbody>

                        </table>
                    </form>
                </div>
            </div>
        </div>

        <div class="tab-pane fade" id="tableedicao" role="tabpanel" aria-labelledby="tableedicao-tab">

        </div>

        <script type="text/javascript">

            function alteraSituacao(obj, param) {
                var column = $(obj).parents("tr").find("td:nth-child(" + param + ")");
                $("#id").val(column.html());
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

        <jsp:include page="/footer.jsp" />