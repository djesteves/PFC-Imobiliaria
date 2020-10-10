<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<footer id="footer" class="footer py-3  text-dark-50">
    <div class="container text-center">
        <small>&copy; PFC Imobiliária</small>
    </div>
</footer>

<script charset="UTF-8" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script charset="UTF-8" src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script charset="UTF-8" src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script charset="UTF-8" src="https://kit.fontawesome.com/7982155587.js"></script>
<script charset="UTF-8" src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
<script charset="UTF-8" src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
<script charset="UTF-8" src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js"></script>
<script charset="UTF-8" src="<%=request.getContextPath()%>/Resources/js/wsviacep.js"></script>
<script charset="UTF-8" src="<%=request.getContextPath()%>/Resources/js/script.js"></script>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        AOS.init();
    });
</script>

<c:if test="${msg != null}">
    <script>
        var mensagem = "${msg}";
        mostraDialogo(mensagem, "success", 3000);
    </script>
</c:if> 

<c:if test="${msgerro != null}">
    <script>
        var mensagem = "${msgerro}";
        mostraDialogo(mensagem, "danger", 3000);
    </script>
</c:if> 

</body>
</html>