<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Imovel"%>
<jsp:include page="../header.jsp" />


<div class="container">
    <%
        ArrayList<Imovel> arrImovel = (ArrayList<Imovel>) request.getAttribute("listaImovel");
    %>

    <% if (arrImovel.size() > 0) { %>
    <div class="container mt-5">   
        <div class="row">
            <% for (Imovel i : arrImovel) {%>
            <div class="col-md-4 mb-5">
                <div class="card h-100">

                    <img class="card-img-top" src="<%=request.getContextPath()%>/Resources/upload/<%=i.getDiretorioimg()%>" alt="Imagem de capa do card">
                    <div class="card-body">
                        <h2 class="card-title"><%=i.getTitulo()%></h2>
                        <p class="card-text"><%=i.getDescricao()%></p>
                    </div>
                    <div class="card-footer">
                        <a href="<%=request.getContextPath()%>/controle/ImovelConsultar?imovel=<%=i.getId_imovel()%>" class="btn btn-secondary btn-sm"> Ver Imóvel</a>
                        <a href="<%=request.getContextPath()%>/controle/ImovelConsultar?imovel=<%=i.getId_imovel()%>" class="btn btn-primary btn-sm"> Editar Imóvel</a>
                    </div>

                </div>
            </div>
            <%}%>
        </div>
    </div>
    <%} else {%>
    <p align="center"> <strong> Nenhum Imóvel Cadastrado </strong> </p>
    <%}%>

</div>

<jsp:include page="../footer.jsp" />
