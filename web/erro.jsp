<jsp:include page="header.jsp" />


<div class="container">
    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        <h4 class="alert-heading">Erro</h4>
        <hr>

        <p class="mb-0">${msgerro}</p>
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
</div>

<jsp:include page="footer.jsp" />