<jsp:include page="header.jsp" />

<c:if test="${msg != null}">
    <script>
        var mensagem = "${msgerro}";
        mostraDialogo(mensagem, "danger", 2500);
    </script>
</c:if> 

<jsp:include page="footer.jsp" />