<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
                        <c:if test = "${usuarioLogado.nivel == 'CORRETOR'}">
                            <a title="Concluir Agendamento" class="btn btn-sm btn-success" href="#" onClick="confirmaConclusao(${agendamento.id_agendamento})"><i class="fas fa-check" ></i></a>
                            </c:if>

                        <a title="Emitir Ficha de Solicitação" class="btn btn-sm btn-primary" href="#" onClick="EmitirRelatorio('FichaAgendamento', ${agendamento.id_agendamento})"><i class="far fa-file-alt" ></i></a>

                        <a title="Cancelar Agendamento" class="btn btn-sm btn-danger" href="#" onClick="confirmaDelete(${agendamento.id_agendamento}, '${agendamento.dataAgendamento}')"><i class="fas fa-times" ></i></a>

                    </td>
                    <td>${agendamento.usuario.nome}</td>
                    <td>${agendamento.usuarioCorretor.nome}</td>
                    <td>${agendamento.imovel.id_imovel}</td>
                    <td><fmt:formatDate pattern="dd/MM/yyyy" value="${agendamento.dataSolicitacao}"/></td>
                    <td><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${agendamento.dataAgendamento}"/></td>
                    <td>${agendamento.status}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</div>

<jsp:include page="../footer.jsp" />

<script type="text/javascript">

    function confirmaDelete(id, data) {
        if (confirm('Tem certeza que deseja excluir este Agendamento?')) {
            window.location.href = "${pageContext.servletContext.contextPath}/Controle/AgendamentoCancelar?id=" + id + "&data=" + data;
        }
    }

    function confirmaConclusao(id) {
        if (confirm('Tem certeza que deseja concluir este Agendamento?')) {
            window.location.href = "${pageContext.servletContext.contextPath}/Controle/AgendamentoConcluir?id=" + id;
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
                    URL = '<%=request.getContextPath()%>/Resources/resultados/' + resposta;
                    window.open(URL);
                }, 500);
            }
        };

        xhttp.open("GET", '<%=request.getContextPath()%>/EmitirRelatorio?nomerel=' + rel + '&id_agendamento=' + param1, true);
        xhttp.send();
    }
</script>