<%@page import="java.sql.ResultSet"%>
<%
    ResultSet rs = null;

    if (request.getAttribute("rsImovel") != null) {
        rs = (ResultSet) request.getAttribute("rsImovel");
    } else {
        request.setAttribute("msgerro", "Não foi possivel exibir os dados");
        request.getRequestDispatcher("/erro.jsp").forward(request, response);
    }
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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


<div class="container mb-3 border" data-aos="fade-left" data-aos-delay="200"  >
    <img src="<%=request.getContextPath()%>/Resources/upload/<%=rs.getString("imagemdir")%>" class="img-fluid imganuncio" alt="">
</div>

<div class="container">
    <div class="row">
        <div class="col-12  col-lg-8 order-md-1">
            <div class="card border-secondary mb-3">
                <div class="card-header text-center text-secondary"><b>Caracteristicas do Imóvel</b></div>
                <div class="card-body text-secondary">
                    <p class="card-text"><b><%= rs.getString("titulo")%></b></p>
                    <p class="card-text"> <b>Descrição:</b> <%= rs.getString("descricao")%> </p>
                    <p class="card-text"> <b>Comodos:</b> <%= rs.getString("comodos")%> &nbsp;&nbsp;&nbsp; <b>Banheiros:</b> <%= rs.getString("banheiros")%> &nbsp;&nbsp;&nbsp;<b>Vagas na Garagem:</b> <%= rs.getString("vagas_garagem")%></p>
                    <p class="card-text"> <b>Area Total:</b> <%= rs.getString("area_total")%> m² &nbsp;&nbsp;&nbsp; <b>Area Edificada:</b> <%= rs.getString("area_edificada")%> m² &nbsp;&nbsp;&nbsp;<b>Valor do Imóvel:</b> R$ <%= rs.getString("valor")%></p>
                    <p class="card-text"> <b>Local do Imóvel:</b> <%= rs.getString("enderecocompleto")%> </p>
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

<jsp:include page="footer.jsp" />

