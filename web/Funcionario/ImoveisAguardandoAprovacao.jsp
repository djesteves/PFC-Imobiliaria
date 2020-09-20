<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="/header.jsp" />

<div class="table-responsive container">

    <div class="text-center">
        <p>Aprovar Imóveis</p>
    </div>

    <table id="usuariostable" class="table table-sm table-striped table-bordered">

        <thead class="thead table-primary">
            <tr>
                <th scope="col">Ações</th>
                <th scope="col">ID Imóvel</th>
                <th scope="col">Usr Cadastro</th>
                <th scope="col">Dt Cadastro</th>
                <th scope="col">Valor</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="imovel" items="${ImoveisEmAnalise}">
                <tr>
                    <td>
                        <a title="Consultar Imóvel" class="btn btn-sm btn-primary" href="${pageContext.servletContext.contextPath}/Controle/ImovelListarPorID?id=${imovel.id_imovel}&idu=${imovel.usuario.id_usuario}"><i class="fas fa-eye"></i></a>

                        <a title="Aprovar Imóvel" class="btn btn-sm btn-success" href="${pageContext.servletContext.contextPath}/Controle/ImovelAprovar?id=${imovel.id_imovel}&email=${imovel.usuario.email}"><i class="fas fa-check"></i></a>

                        <a title="Reprovar Imóvel" class="btn btn-sm btn-danger" href="#" onClick="modalReprovar(${imovel.id_imovel})" data-toggle="modal" data-target="#modalReprovar" ><i class="fas fa-times"></i></a>

                    </td>
                    <td>${imovel.id_imovel}</td>
                    <td>${imovel.usuario.nome}</td>
                    <td><fmt:formatDate pattern="dd/MM/yyyy" value="${imovel.data_cadastro}"/></td>
                    <td><fmt:formatNumber minFractionDigits="2" type="currency" value="${imovel.valor}"/></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <div class="modal fade" id="modalReprovar" tabindex="-1" role="dialog" aria-labelledby="modalReprovar" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <form action="<%=request.getContextPath()%>/Controle/ImovelReprovar" method="post">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Informe o motivo da Reprovação do Imóvel!</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" id="id_imovel" name="id_imovel">
                        
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
</div>

<script type="text/javascript">
    
    function modalReprovar(id){
        $("#id_imovel").val(id);
        $("#obs").val('');
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