<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<footer id="footer" class="footer py-3 text-dark-50">
    <div class="container text-center">
        <small>&copy; PFC Imobiliária</small>
    </div>
</footer>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-maskmoney/3.0.2/jquery.maskMoney.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/7982155587.js"></script>
<script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
<script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/wsviacep.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/script.js"></script>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        AOS.init();
    });
</script>

<c:if test="${msg != null}">
    <script>
        var mensagem = "${msg}";
        mostraDialogo(mensagem, "success", 5000);
    </script>
</c:if> 

<c:if test="${msgerro != null}">
    <script>
        var mensagem = "${msgerro}";
        mostraDialogo(mensagem, "danger", 6000);
    </script>
</c:if> 

</body>
</html>