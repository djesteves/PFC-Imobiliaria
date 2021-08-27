<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="navbar.jsp" />

<div class="container" data-aos="fade-left" data-aos-delay="200">
    <div class="row align-items-center">
        <div class="col-12 col-md-5 col-lg-6 order-md-2">
            <!-- Imagem -->
            <img <img src="<%=request.getContextPath()%>/Assets/img/index.png" alt="" class="img-fluid mw-md-150 mw-lg-130 mb-6 mb-md-0 aos-init aos-animate" alt="..." data-aos="fade-up" data-aos-delay="100">
        </div>

        <div class="col-12 col-md-7 col-lg-6 order-md-1">
            <!-- Cabeçalho do Texto -->
            <h1 class="text-center text-sm-left">
                Bem-vindo a <span class="text-primary">Imobiliária</span>. <br>

            </h1>
            <p> Compre ou venda/alugue de maneira descomplicada!</p>
            <!-- Botões -->
            <div class="text-center text-md-left">
                <a href="<%=request.getContextPath()%>/catalogoimoveis.jsp" class="btn btn-radius btn-outline-primary shadow lift mr-1">
                    <i class="fas fa-home"></i>&nbsp;
                    </i>Catalogo de Imóveis</a>
                </a>
                <a href="<%=request.getContextPath()%>/Usuario/FormImovel.jsp" class="btn btn-radius btn-outline-secondary shadow lift mr-1">
                    Anunciar um imóvel
                </a>
            </div>
        </div>
    </div> 
</div> 

<jsp:include page="footer.jsp" />