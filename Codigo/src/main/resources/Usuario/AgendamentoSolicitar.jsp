<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../navbar.jsp" />

<div data-aos="fade-right" data-aos-delay="300" class="container">
    <div class="text-center">
        <p>Solicitar visita ao imóvel</p>
    </div>

    <form action="<%=request.getContextPath()%>/Controle/AgendamentoSolicitar" method="post" class="formulario">
        <div class="form-row">
            <div class="form-group col-4">
                <label for="idimovel">Número do Imóvel</label>
                <input type="text" readOnly="true" class="form-control" value="${param.id_imovel}" name="idimovel" id="idimovel" required/>
                <input type="hidden" readOnly="true" class="form-control" value="${param.emailanunciante}" name="emailanunciante" id="emailanunciante" required/>
            </div>

            <div class="form-group col-lg-8 col-sm-12 col-md-8">
                <label for="corretores">Corretor: </label>
                <select class="custom-select" name="corretores" id="corretores" class="form-control" required>

                </select>
            </div>

            <div class="form-group col-6">
                <label for="dataagendamento">Data e Hora do Agendamento</label>
                <input type="datetime-local" class="form-control col-sm-6 col-md-4 col-lg-8" onBlur="verData()" name="dataagendamento" id="dataagendamento" required/>
            </div>
        </div>

        <div class="text-muted" >
            <p style="color: red;">
                * Use mascara e siga as regras impostas pelos orgãos competentes ao comparecer a visita do imóvel.
            </p>
        </div>

        <hr>

        <div class="button">
            <button type="submit" class="btn btn-primary">Confirmar</button>
            <button type="button" onclick="window.history.back();" class="btn btn-danger">Voltar</button>
        </div>
    </form>
</div>	

<jsp:include page="../footer.jsp" />

<script>

    document.addEventListener("DOMContentLoaded", function () {
        corretores();
    });

    function verData() {
        var data = new Date($('#dataagendamento').val());
        var dataAtual = new Date();

        if (data < dataAtual) {
            var mensagem = "A data do agendamento não pode ser inferior a data corrente";
            mostraDialogo(mensagem, "danger", 3000);
            $('#dataagendamento').val('');
        }

        if (data.getHours() < 9 || data.getHours() > 17) {
            var mensagem = "A data do agendamento deverá ocorrer em horario comercial (das 9:00 as 17:00)";
            mostraDialogo(mensagem, "danger", 3000);
            $('#dataagendamento').val('');
        }
    }

    function corretores() {
        var xhttp = null;
        if (window.XMLHttpRequest) {
            //code for modern browsers
            xhttp = new XMLHttpRequest();
        } else {
            // code for old IE browsers
            xhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }

        xhttp.onreadystatechange = function () {

            if (this.readyState == 3) {
                $("#corretores").empty();
                var html = '<option value="">Carregando...</option>';
                document.getElementById("corretores").innerHTML = html;
            }

            if (this.readyState == 4 && this.status == 200) {
                var resposta = this.responseText;
                document.getElementById("corretores").innerHTML = resposta;
            }
        };

        //envia multipart a servlet precisa estar com a anotação.
        xhttp.open("GET", '../ListarCorretores', true);
        xhttp.send();
    }

</script>