<%--

<div class="mb-5"></div>

<footer class="text-muted">
    <div class="container">
         <p class="float-right">
            <a href="#">Voltar ao topo</a>
        </p>


        <p>Esta é a versão beta!</p>
    </div>
</footer>

--%>

<script charset="UTF-8" src="<%=request.getContextPath()%>/Resources/wsviacep.js" type="text/javascript"></script>
<script charset="UTF-8" src="<%=request.getContextPath()%>/Resources/scriptJS.js" type="text/javascript"></script>

<script>
    AOS.init();

    $(document).ready(function () {
        setTimeout(function () {
            $("#alert").fadeOut("slow", function () {
            });
        }, 3000);
    });

</script>

</body>
</html>