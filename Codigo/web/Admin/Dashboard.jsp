<%-- 
    Document   : Dashboard
    Created on : 24/03/2020, 21:52:50
    Author     : Diego
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../navbar.jsp" />

<div class="container"> 

    <div class="text-center">
        <p>Administração</p>
    </div>

    <div id="divbotoes">
        <c:if test="${'CORRETOR'.equalsIgnoreCase(usuarioLogado.nivel)}">
            <div class="canto-curva-outer col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <div  class="canto-curva canto-curva-inner">
                    <img src="../Resources/img/worker.png" alt=""/>
                    <a href="<%=request.getContextPath()%>/Controle/AgendamentoLista?modo=Corretor""> 
                        <div tipo="_div" class="canto-bot">
                            Minha Agenda
                        </div>
                    </a>
                </div>
            </div>
        </c:if>
        
        <c:if test="${'ADMINISTRADOR'.equalsIgnoreCase(usuarioLogado.nivel)}">
            <div class="canto-curva-outer col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <div  class="canto-curva canto-curva-inner">
                    <img src="../Resources/img/worker.png" alt=""/>
                    <a href="<%=request.getContextPath()%>/Controle/UsuarioListarAtivos"> 
                        <div tipo="_div" class="canto-bot">
                            Gerenciar Usuários 
                        </div>
                    </a>
                </div>
            </div>
        </c:if>

        <div class="canto-curva-outer col-xs-12 col-sm-12 col-md-12 col-lg-12" >
            <div class="canto-curva canto-curva-inner">
                <img src="../Resources/img/search.png" alt=""/>
                <a href="<%=request.getContextPath()%>/Controle/ImovelEmAnalise">
                    <div class="canto-bot" >
                        Imóveis em Análise
                    </div>
                </a>
            </div>
        </div>

        <div class="canto-curva-outer col-xs-12 col-sm-12 col-md-12 col-lg-12" >
            <div  class="canto-curva canto-curva-inner">
                <img src="../Resources/img/analytics.png" alt=""/>
                <div  class="canto-bot" >
                    <span  class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Relatórios
                    </span>
                    <c:if test="${'ADMINISTRADOR'.equalsIgnoreCase(usuarioLogado.nivel)}">
                        <div class="dropdown-menu">
                            <a class="dropdown-item" data-toggle="modal" data-target="#modalRelAprovadosImoveis">Imóveis aprovados por período</a>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>



    </div>

    <!-- modal relatorios -->
    <div class="modal fade" id="modalRelAprovadosImoveis" tabindex="-1" role="dialog" aria-labelledby="modalRelAprovarImoveis" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle">Imóveis aprovados por período</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form action="<%=request.getContextPath()%>/Controle/EmitirRelatorio" method="post">
                    <div class="modal-body">
                        <input type="hidden" id="nomerel" name="nomerel" value="ImoveisAprovados">
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
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- fim modal relatorios -->
</div>

<jsp:include page="/footer.jsp" />                    