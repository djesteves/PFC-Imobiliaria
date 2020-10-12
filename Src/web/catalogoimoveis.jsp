<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="pt-BR" />

<jsp:include page="header.jsp" />

<div class="container">
    <div class="text-center">
        <p>Catálogo de Imóveis</p>
    </div>

    <div class="row">
        <div class="col-lg-3 col-md-4 mb-4" data-aos="fade-down" data-aos-delay="200">
            <form id="pesquisa" class="formulario">
                <div class="text-center">
                    <p>Filtros</p>
                </div>

                <label class="my-1 mr-2" for="valor">Valor</label>
                <select onChange="filtro()" class="custom-select my-1 mr-sm-2" id="valor" name="valor">
                    <option value="" selected>Escolha...</option>
                    <option value="100000">Até R$ 100.000</option>
                    <option value="250000">Até R$ 250.000</option>
                    <option value="300000">Até R$ 300.000</option>
                    <option value="500000">Até R$ 500.000</option>
                    <option value="1000000">Até R$ 1.000.000</option>
                    <option value="1000000000000000">Maior que R$ 1.000.000</option>
                </select>

                <label class="my-1 mr-2" for="tpvenda">Pretendo:</label>
                <select onChange="filtro()" class="custom-select my-1 mr-sm-2" name="tpvenda" id="tpvenda" class="form-control">
                    <option value="" selected>Escolha...</option>
                    <option value="VENDA">Comprar</option>
                    <option value="ALUGUEL">Alugar</option>
                </select>

                <label for="quartos">Nº de Quartos</label>
                <select onChange="filtro()" class="custom-select my-1 mr-sm-2" name="quartos" id="quartos" class="form-control">
                    <option value="" selected>Escolha...</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="5">Maior que 5</option>
                </select>

            </form>
        </div>
        <div class="col-md-8 col-lg-9" data-aos="fade-left" data-aos-delay="200">
            <div style="display: none;" id="loader" class="loader"></div>

            <div id="imovel" class="row">
                <!-- Imoveis ajax aqui -->
            </div>
        </div>
    </div>
</div>

<!-- Modal Imóvel-->
<div class="modal fade" id="modalImovel" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="modalImovelLabel" aria-hidden="true">
    <div class="modal-dialog modal-xl modal-dialog-centered">

        <div id="modal-content" class="modal-content">
            <div class="d-flex justify-content-center">
                <div style="display: none;" id="loader-modal-imovel" class="loader justify-content-center ">
                </div>
            </div>
            <div id="imovel-content">
                <!-- Imovel ajax aqui -->
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />

<script>
    document.addEventListener("DOMContentLoaded", function () {
        filtro();
    });

    function filtro() {
        var xhttp = null;
        if (window.XMLHttpRequest) {
            //code for modern browsers
            xhttp = new XMLHttpRequest();
        } else {
            // code for old IE browsers
            xhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }

        var form = document.getElementById("pesquisa"),
                formData = null;

        formData = new FormData(form);

        xhttp.onreadystatechange = function () {

            if (this.readyState == 3) {
                document.getElementById("loader").style.display = "block";
                $("#imovel").hide();
            }

            if (this.readyState == 4 && this.status == 200) {
                var resposta = this.responseText;
                setTimeout(function () {
                    document.getElementById("loader").style.display = "none";
                    document.getElementById("imovel").innerHTML = resposta;
                    $("#imovel").show();
                }, 500);
            }
        };

        //envia multipart a servlet precisa estar com a anotação.
        xhttp.open("POST", 'ImovelListarAprovados', true);
        xhttp.send(formData);
    }

    function carregarImovel(id) {
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
                $("#imovel-content").empty();
                document.getElementById("loader-modal-imovel").style.display = "block";
            }

            if (this.readyState == 4 && this.status == 200) {
                var resposta = this.responseText;
                setTimeout(function () {
                    document.getElementById("loader-modal-imovel").style.display = "none";
                    document.getElementById("imovel-content").innerHTML = resposta;
                }, 1000);
            }
        };

        //envia multipart a servlet precisa estar com a anotação.
        xhttp.open("GET", 'VisualizarImovel?id=' + id, true);
        xhttp.send();
    }


</script>