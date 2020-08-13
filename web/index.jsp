<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp" />

<div class="container" data-aos="fade-left" data-aos-delay="200">
    <div class="row align-items-center">
        <div class="col-12 col-md-5 col-lg-6 order-md-2">
            <!-- Imagem -->
            <img <img src="<%=request.getContextPath()%>/Resources/img/index.png" alt="" class="img-fluid mw-md-150 mw-lg-130 mb-6 mb-md-0 aos-init aos-animate" alt="..." data-aos="fade-up" data-aos-delay="100">
        </div>

        <div class="col-12 col-md-7 col-lg-6 order-md-1" data-aos="fade-up">
            <!-- Cabe�alho do Texto -->
            <h1 class="text-center text-sm-left">
                Bem-vindo a <span class="text-primary">Imobili�ria</span>. <br>
                Seu novo im�vel est� aqui!
            </h1>
            <!-- Bot�es -->
            <div class="text-center text-md-left">
                <c:if test="${usuarioLogado == null}">
                    <a href="<%=request.getContextPath()%>/Usuario/FormUsuario.jsp" class="btn btn-primary shadow lift mr-1">
                        Crie sua conta <i class="fas fa-chevron-right d-none d-md-inline ml-3"></i>
                    </a>
                </c:if> 
                <a href="<%=request.getContextPath()%>/Usuario/FormImovel.jsp" class="btn btn-outline-secondary shadow lift mr-1">
                    Anuncie seu im�vel
                </a>
            </div>
        </div>
    </div> 
</div> 

<jsp:include page="footer.jsp" />