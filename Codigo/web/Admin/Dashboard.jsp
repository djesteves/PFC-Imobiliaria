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
                    <a href="<%=request.getContextPath()%>/Controle/AgendamentoLista?modo=Corretor"> 
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
                    <img src="../Resources/img/calendario.png" alt=""/>
                    <a href="<%=request.getContextPath()%>/Controle/AgendamentoLista?modo=Administrador"> 
                        <div tipo="_div" class="canto-bot">
                            Gerenciar Agendamentos
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
                            <a class="dropdown-item" data-toggle="modal" data-target="#modalRelImoveis">Imóveis cadastrados por período</a>
                            <a class="dropdown-item" data-toggle="modal" data-target="#modalRelUser">Usuários cadastrados por período</a>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>

    </div>

<!-- modal Rel Imovel -->
    <div class="modal fade" id="modalRelImoveis" tabindex="-1" role="dialog" aria-labelledby="modalRelImoveis" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle">Imóveis cadastrados por período</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form id="formData">
                    <div class="modal-body">
                        <div class="form-row">
                            <div class="col-md-5">
                                <label for="situacao">Situacao:</label>
                                <select class="custom-select my-1 mr-sm-2" name="situacao" id="situacao" class="form-control" required>
                                    <option value="Ativo">Ativo</option>
                                    <option value="Inativo">Inativo</option>
                                </select>
                            </div>
                        </div>
                        <br>
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
                        <button type="button" class="btn btn-primary" data-dismiss="modal" onClick="EmitirRelatorio('ImoveisCadastradosPeriodo')">Confirmar</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
<!-- fim modal Rel Imovel -->


<!-- modal Rel Usuarios -->
    <div class="modal fade" id="modalRelUser" tabindex="-1" role="dialog" aria-labelledby="modalRelUser" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle">Imóveis cadastrados por período</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form id="formData">
                    <div class="modal-body">
                        <div class="form-row">
                            <div class="col-md-5">
                                <label for="situacao">Situacao:</label>
                                <select class="custom-select my-1 mr-sm-2" name="situacaouser" id="situacaouser" class="form-control" required>

                                    <option value="Ativo">Ativo</option>
                                    <option value="Inativo">Inativo</option>

                                </select>
                            </div>
                        </div>
                        <br>
                        <div class="form-row">
                            <div class="col">
                                <label for="datainicio">Data Inicial</label>
                                <input type="date" id="datainiciouser" name="datainiciouser" required>
                            </div>
                            <div class="col">
                                <label for="datafinal">Data Final</label>
                                <input type="date" id="datafinaluser" name="datafinaluser" required>
                            </div>
                        </div>


                    </div>
                    <div class="modal-footer">


                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
                        <button type="button" class="btn btn-primary" data-dismiss="modal" onClick="EmitirRelatorio('UsuariosCadastradosPeriodo')">Confirmar</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
<!-- fim modal rel Usuarios -->
</div>

<jsp:include page="/footer.jsp" />

<script type="text/javascript">


    function EmitirRelatorio(rel) {

        var xhttp = null;
        if (window.XMLHttpRequest) {
            //code for modern browsers
            xhttp = new XMLHttpRequest();
        } else {
            // code for old IE browsers
            xhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }

        if (rel === 'ImoveisCadastradosPeriodo') {
            var datainicio = document.getElementById("datainicio").value;
            var datafinal = document.getElementById("datafinal").value;
            var situacao = document.getElementById("situacao").value;
        } else if (rel === 'UsuariosCadastradosPeriodo') {
            var datainicio = document.getElementById("datainiciouser").value;
            var datafinal = document.getElementById("datafinaluser").value;
            var situacao = document.getElementById("situacaouser").value;
        }

        xhttp.onreadystatechange = function () {

            if (this.readyState == 3) {
                console.log("emitindo rel");
            }

            if (this.readyState == 4 && this.status == 200) {
                var resposta = this.responseText;

                URL = '<%=request.getContextPath()%>/Resources/resultados/' + resposta;
                window.open(URL);
            }
        };

        xhttp.open("GET", '<%=request.getContextPath()%>/EmitirRelatorio?nomerel=' + rel + '&datainicio=' + datainicio + '&datafinal=' + datafinal + "&situacao=" + situacao, true);
        xhttp.send();
    }
</script>