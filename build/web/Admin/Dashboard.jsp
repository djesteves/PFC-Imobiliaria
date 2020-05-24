<%-- 
    Document   : Dashboard
    Created on : 24/03/2020, 21:52:50
    Author     : Diego
--%>

<jsp:include page="/header.jsp" />

<div class="container portfolio-block"> 
    <a class="btn btn-primary" href="<%=request.getContextPath()%>/controle/UsuarioListar"> Gerenciar Usuários </a>
    <br>
    <br>
    <a class="btn btn-primary" href="<%=request.getContextPath()%>/controle/ImoveisEmAnalise"> Aprovar Imóveis </a>
    <br>
    <br>

    <div class="btn-group">
        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            Relatórios
        </button>
        <div class="dropdown-menu">
            <a class="dropdown-item" data-toggle="modal" data-target="#modalRelAprovadosImoveis">Imóveis Aprovados por Período </a>
        </div>
    </div>

    <div class="modal fade" id="modalRelAprovadosImoveis" tabindex="-1" role="dialog" aria-labelledby="modalRelAprovarImoveis" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle">Imóveis Aprovados por Período</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="<%=request.getContextPath()%>/controle/EmitirRelatorio" method="post">
                        <input type="hidden" id="nomerel" name="nomerel" value="RelAprovadosImoveis">
                        <div class="form-row">
                            <div class="col">
                                <label for="datainicio">Data Inicial</label>
                                <input type="date" id="datainicio" name="datainicio" required>
                            </div>
                            <div class="col">
                                <label for="datafinal">Data Final</label>
                                <input type="date" id="datafinal" name="datafinal" required>
                            </div>
                        </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
                    <button type="submit" class="btn btn-primary">Confirmar</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
