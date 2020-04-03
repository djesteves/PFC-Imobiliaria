<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Imovel"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<jsp:include page="header.jsp" />

<style>
    .imganuncio {

        max-height:270px;
        width: auto;
        height: auto;

        display: block;
        margin-left: auto;
        margin-right: auto;
    }
</style>  
<%
    List<Imovel> arrImovel = (ArrayList<Imovel>) request.getAttribute("ConsultaImovel");
%>

<% for (Imovel i : arrImovel) {%>
<div class="container mb-3 border" data-aos="fade-left" data-aos-delay="200"  >
    <img src="<%=request.getContextPath()%>/Resources/upload/<%= i.getDiretorioimg() %>" class="img-fluid imganuncio" alt="">
</div>

<div class="container">
    <div class="row">
        
        <div class="col-12  col-lg-8 order-md-1">
            <div class="card border-secondary mb-3">
                <div class="card-header text-center text-secondary"><b>Caracteristicas do Imóvel</b></div>
                <div class="card-body text-secondary">
                    <p class="card-text"><b><%= i.getTitulo()%></b></p>
                    <p class="card-text"> <b>Descrição:</b> <%= i.getDescricao()%> </p>
                    <p class="card-text"> <b>Comodos:</b> <%= i.getComodos()%> &nbsp;&nbsp;&nbsp; <b>Banheiros:</b> <%= i.getBanheiros()%> &nbsp;&nbsp;&nbsp;<b>Vagas na Garagem:</b> <%= i.getVagas_garagem()%></p>
                    <p class="card-text"> <b>Area Total:</b> <%= i.getArea_total() + "m²"%> &nbsp;&nbsp;&nbsp; <b>Area Edificada:</b> <%= i.getArea_edificada() + "m²"%></p>
                    <p class="card-text"> <b>Endereço:</b> <%= i.getEndereco().getLogradouro() + ", " + i.getEndereco().getNumero() + " - " + i.getEndereco().getComplemento()%> </p>
                    <p class="card-text"> <b>Bairro / Cidade:</b> <%= i.getEndereco().getBairro() + " - " + i.getEndereco().getCidade() + " - " + i.getEndereco().getEstado()%> </p>
                    <p class="card-text"> <b>CEP:</b> <%= i.getEndereco().getCep()%> </p>
                    <p class="card-text" align="right"> <b>Valor:</b> <%= "R$ " + i.getValor() %> </p>
                </div>
            </div>
        </div>

        <div class="col-12 col-lg-4 order-md-2">
            <div class="card text-white bg-primary ">
                <div class="card-header text-center">Agendar visita ao Imóvel</div>
                <div class="card-body">
                    <h5 class="card-title">Gostou deste imóvel?</h5>
                    <p class="card-text">Agende a visita diretamente com um de nossos corretores.</p>
                    <p class="card-text">clique em agendar para ver os horarios disponíveis.</p>
                </div>
                <div class="card-footer">
                    <a href="#" class="btn btn-outline-light float-right">
                        Agendar
                    </a>
                </div>
            </div>
        </div>
    </div> 
</div>             
<% }%>

<jsp:include page="footer.jsp" />

