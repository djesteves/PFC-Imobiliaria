<%@page import="java.io.File"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="util.DataAccess"%>
<%@page import="java.sql.Connection"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp" />

<section class="pt-3 pt-md-11 bg-light" >
    <div class="container " data-aos="fade-left" data-aos-delay="200">
        <div class="row align-items-center">
            <div class="col-12 col-md-5 col-lg-6 order-md-2">

                <!-- Imagem -->
                <img <img src="<%=request.getContextPath()%>/Resources/img/illustration-2.png" alt="" class="img-fluid mw-md-150 mw-lg-130 mb-6 mb-md-0 aos-init aos-animate" alt="..." data-aos="fade-up" data-aos-delay="100">

            </div>
            <div class="col-12 col-md-7 col-lg-6 order-md-1 aos-init aos-animate" data-aos="fade-up">

                <!-- Cabeçalho do Texto -->
                <h1 class="text-center text-sm-left">
                    Bem-vindo a <span class="text-primary">Imobiliária</span>. <br>
                    Seu novo imóvel está aqui!
                </h1>

                <!-- Texto -->
                <p class="lead text-center text-md-left text-muted mb-6 mb-lg-8">

                </p>

                <!-- Botões -->
                <div class="text-center text-md-left">
                    <c:if test="${usuarioLogado == null}">
                        <a href="<%=request.getContextPath()%>/Usuario/CadastrarUsuario.jsp" class="btn btn-primary shadow lift mr-1">
                            Crie sua conta <i class="fas fa-chevron-right d-none d-md-inline ml-3"></i>
                        </a>
                    </c:if> 
                    <a href="<%=request.getContextPath()%>/Usuario/CadastrarImovel.jsp" class="btn btn-outline-secondary shadow lift mr-1">
                        Anuncie seu imóvel
                    </a>
                </div>

            </div>
        </div> 
    </div> 
</section>

<div class="mb-5"></div>

<%
    Connection connection = DataAccess.getConexao();

    PreparedStatement smt = connection.prepareStatement("Select * from imovel order by data_cadastro desc limit 3");

    ResultSet rs = smt.executeQuery();

    connection.close();

%>

<div class="container mt-5">   
    <div class="row">
        <% while (rs.next()) {%>
        <div class="col-md-4 mb-5">
            <div class="card h-100">

                <img class="card-img-top" src="<%=request.getContextPath()%>/Resources/upload/<%=rs.getString("imagemdir")%>" alt="Imagem de capa do card">
                <div class="card-body">
                    <h2 class="card-title"><%=rs.getString("titulo")%></h2>
                    <p class="card-text"><%=rs.getString("descricao")%></p>
                </div>
                <div class="card-footer">
                    <a href="<%=request.getContextPath()%>/controle/ImovelConsultar?imovel=<%=rs.getString("id_imovel")%>" class="btn btn-primary btn-sm"> Ver Imóvel</a>
                </div>

            </div>
        </div>
        <%}%>
    </div>
</div>

<jsp:include page="footer.jsp" />
