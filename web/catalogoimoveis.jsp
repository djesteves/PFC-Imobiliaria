<jsp:include page="header.jsp" />

<div class="portfolio-block">
    <form>

        <label class="my-1 mr-2" for="inlineFormCustomSelectPref">Tipo de Imóvel</label>
        <select class="custom-select my-1 mr-sm-2" id="inlineFormCustomSelectPref">
            <option selected>Escolha...</option>
            <option value="germinada">Geminada</option>
            <option value="sobrado">Sobrado</option>
            <option value="apartamento">Apartamento</option>
            <option value="kitnet">Kitnet</option>
            <option value="flat">Flat</option>
        </select>

        <label class="my-1 mr-2" for="inputSearch">Pesquisar por nome</label>
        <input id="inputSearch" class="form-control mr-sm-2" type="search" placeholder="" aria-label="Search">
        
        <hr>

        <button type="submit" class="btn btn-primary my-1">Pesquisar</button>

    </form>

</div>


<jsp:include page="footer.jsp" />