<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="/navbar.jsp" />

<div class="table-responsive container">

    <div class="text-center">
        <p>Lista de Agendamentos ${usuarioLogado.nome}</p>
    </div>

    <div style="display: none;" id="loader" class="loader"></div>

    <table id="agendatable" class="table table-sm table-striped table-bordered">
        <thead class="thead table-primary">
            <tr>
                <th scope="col">Ações do Agendamento</th>
                <th scope="col">Nome Solicitante</th>
                <th scope="col">Nome Corretor</th>
                <th scope="col">Código Imóvel</th>
                <th scope="col">Data da Solicitação</th>
                <th scope="col">Data do Agendamento</th>
                <th scope="col">Status</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="agendamento" items="${listaAgendamento}">
                <tr>
                    <td>
                        <c:if test="${usuarioLogado.nivel != 'USUARIO'}">
                            <c:if test="${agendamento.status == 'Em Andamento'}">
                                <a title="Concluir Agendamento" class="btn btn-sm btn-success" href="#"
                                    onClick="modalConcluir('${agendamento.id_agendamento}', '${agendamento.usuario.email}', '${agendamento.usuarioCorretor.email}', '${agendamento.imovel.usuario.email}', '${agendamento.imovel.id_imovel}')"
                                    data-toggle="modal" data-target="#modalConcluir"><i class="fas fa-check"></i></a>
                            </c:if>
                        </c:if>

                        <c:if test="${agendamento.status == 'Em Andamento'}">
                            <a title="Cancelar Agendamento" class="btn btn-sm btn-danger" href="#"
                                onClick="modalCancelar('${agendamento.id_agendamento}', '${agendamento.dataAgendamento}', '${agendamento.usuario.email}', '${agendamento.usuarioCorretor.email}', '${agendamento.imovel.usuario.email}')"
                                data-toggle="modal" data-target="#modalCancelar"><i class="fas fa-times"></i></a>
                        </c:if>

                        <c:if test="${agendamento.status == 'Concluido'}">
                            <a title="Emitir Minuta Préliminar" class="btn btn-sm btn-primary" href="#"
                                onClick="EmitirRelatorio('ContratoVenda', '${agendamento.id_agendamento}')"><i
                                    class="fas fa-print"></i></a>
                        </c:if>

                        <a title="Emitir Ficha de Solicitação" class="btn btn-sm btn-primary" href="#"
                            onClick="EmitirRelatorio('FichaAgendamento', '${agendamento.id_agendamento}')"><i
                                class="far fa-file-alt"></i></a>
                    </td>
                    <td>${agendamento.usuario.nome}</td>
                    <td>${agendamento.usuarioCorretor.nome}</td>
                    <td>${agendamento.imovel.id_imovel}</td>
                    <td>
                        <fmt:formatDate pattern="dd/MM/yyyy" value="${agendamento.dataSolicitacao}" />
                    </td>
                    <td>
                        <fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${agendamento.dataAgendamento}" />
                    </td>
                    <td>${agendamento.status}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <div class="modal fade" id="modalCancelar" tabindex="-1" role="dialog" aria-labelledby="modalCancelar"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <form action="<%=request.getContextPath()%>/controle/AgendamentoCancelar" method="post">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Informe o motivo do cancelamento do Agendamento!</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" id="idagendamento" name="idagendamento">
                        <input type="hidden" id="dataagendamento" name="dataagendamento">
                        <input type="hidden" id="emailsolicitante" name="emailsolicitante">
                        <input type="hidden" id="emailcorretor" name="emailcorretor">
                        <input type="hidden" id="emailanunciante" name="emailanunciante">

                        <div class="form-group">
                            <label for="obs">Observação</label>
                            <textarea class="form-control" id="obs" name="obs" rows="3" required></textarea>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
                        <button type="submit" class="btn btn-primary">Confirmar</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <div class="modal fade" id="modalConcluir" tabindex="-1" role="dialog" aria-labelledby="modalConcluir"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <form action="<%=request.getContextPath()%>/controle/AgendamentoConcluir" method="post">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <h5 class="modal-title">Deseja realmente concluir o agendamento?</h5>
                        <input type="hidden" id="idagendamentoconcluir" name="idagendamentoconcluir">
                        <input type="hidden" id="idimovelconcluir" name="idimovelconcluir">
                        <input type="hidden" id="emailsolicitanteconcluir" name="emailsolicitanteconcluir">
                        <input type="hidden" id="emailcorretorconcluir" name="emailcorretorconcluir">
                        <input type="hidden" id="emailanuncianteconcluir" name="emailanuncianteconcluir">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
                        <button type="submit" class="btn btn-primary">Confirmar</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<jsp:include page="../footer.jsp" />

<script type="text/javascript">
    function modalCancelar(id, data, emailuser, emailcorretor, emailanunciante) {
        $("#idagendamento").val(id);
        $("#dataagendamento").val(data);
        $("#emailsolicitante").val(emailuser);
        $("#emailcorretor").val(emailcorretor);
        $("#emailanunciante").val(emailanunciante);
        $("#obs").val('');
    }

    function modalConcluir(id, emailuser, emailcorretor, emailanunciante, idimovel) {
        $("#idagendamentoconcluir").val(id);
        $("#emailsolicitanteconcluir").val(emailuser);
        $("#emailcorretorconcluir").val(emailcorretor);
        $("#emailanuncianteconcluir").val(emailanunciante);
        $("#idimovelconcluir").val(idimovel);
    }

    function confirmaConclusao(id) {
        if (confirm('Tem certeza que deseja concluir este Agendamento?')) {
            window.location.href = "${pageContext.servletContext.contextPath}/controle/AgendamentoConcluir?id=" + id;
        }
    }

    $(document).ready(function () {
        $('#agendatable').dataTable({
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

    function EmitirRelatorio(rel, param1) {
        var xhttp = null;
        if (window.XMLHttpRequest) {
            //code for modern browsers
            xhttp = new XMLHttpRequest();
        } else {
            // code for old IE browsers
            xhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }

        xhttp.onreadystatechange = function () {

            if (this.readyState == 3) {
                document.getElementById("loader").style.display = "block";
            }

            if (this.readyState == 4 && this.status == 200) {
                var resposta = this.responseText;

                setTimeout(function () {
                    document.getElementById("loader").style.display = "none";
                    URL = '<%=request.getContextPath()%>/resultados/' + resposta;
                    window.open(URL);
                }, 500);
            }
        };

        xhttp.open("GET", '<%=request.getContextPath()%>/EmitirRelatorio?nomerelatorio=' + rel + '&id_agendamento=' + param1, true);
        xhttp.send();
    }
</script>