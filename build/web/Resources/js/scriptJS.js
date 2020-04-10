$('.datepicker').each(function(){
	var picker = new Pikaday({
		field: this
	});
});

function mostraDialogo(mensagem, tipo, tempo) {

    // se houver outro alert desse sendo exibido, cancela essa requisição
    if ($("#message").is(":visible")) {
        return false;
    }

    // se não setar o tempo, o padrão é 3 segundos
    if (!tempo) {
        var tempo = 3000;
    }

    // se não setar o tipo, o padrão é alert-info
    if (!tipo) {
        var tipo = "info";
    }

    // monta o css da mensagem para que fique flutuando na frente de todos elementos da página
    var cssMessage = "display: block; position: fixed; top: 0; left: 20%; right: 20%; width: 60%; padding-top: 10px; z-index: 9999";
    var cssInner = "margin: 0 auto; box-shadow: 1px 1px 5px black;";

    // monta o html da mensagem com Bootstrap
    var dialogo = "";
    dialogo += '<div id="message" style="' + cssMessage + '">';
    dialogo += '    <div class="alert alert-' + tipo + ' alert-dismissable" style="' + cssInner + '">';
    dialogo += '    <a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>';
    dialogo += mensagem;
    dialogo += '    </div>';
    dialogo += '</div>';

    // adiciona ao body a mensagem com o efeito de fade
    $("body").append(dialogo);
    $("#message").hide();
    $("#message").fadeIn(200);

    // contador de tempo para a mensagem sumir
    setTimeout(function () {
        $('#message').fadeOut(300, function () {
            $(this).remove();
        });
    }, tempo); // milliseconds

}



/*function cnpj_cpf verifica qual das funcoes tem que chamar cpf ou cnpj*/

function cnpj_cpf(campo, documento, f) {


    var tppessoa = $("#tppessoa").val();

    if (tppessoa === "F") {
        mascara_cpf(campo, documento, f);
    } else {
        mascara_cnpj(campo, documento, f);
    }

}

function mascara_cnpj(campo, documento, f) {
    var mydata = '';
    mydata = mydata + documento;

    if (mydata.length == 2) {
        mydata = mydata + '.';

        ct_campo = eval("document." + f + "." + campo + ".value = mydata");
        ct_campo;
    }

    if (mydata.length == 6) {
        mydata = mydata + '.';

        ct_campo = eval("document." + f + "." + campo + ".value = mydata");
        ct_campo;
    }

    if (mydata.length == 10) {
        mydata = mydata + '/';

        ct_campo1 = eval("document." + f + "." + campo + ".value = mydata");
        ct_campo1;
    }

    if (mydata.length == 15) {
        mydata = mydata + '-';

        ct_campo1 = eval("document." + f + "." + campo + ".value = mydata");
        ct_campo1;
    }

    if (mydata.length == 18) {

        if (verCNPJ($("#cpfcnpj").val())) {
            $("#cpfcnpj").css("border", "1px solid #008000");
        } else {
            $("#cpfcnpj").val("");
            $("#cpfcnpj").css("border", "1px solid #FF0000");
            alert('Digite um CNPJ válido');
        }


    }
}


function mascara_cpf(campo, documento, f) {
    var mydata = '';
    mydata = mydata + documento;

    if (mydata.length == 3) {
        mydata = mydata + '.';

        ct_campo = eval("document." + f + "." + campo + ".value = mydata");
        ct_campo;
    }

    if (mydata.length == 7) {
        mydata = mydata + '.';

        ct_campo = eval("document." + f + "." + campo + ".value = mydata");
        ct_campo;
    }

    if (mydata.length == 11) {
        mydata = mydata + '-';

        ct_campo1 = eval("document." + f + "." + campo + ".value = mydata");
        ct_campo1;
    }

    if (mydata.length == 14) {

        if (vercpf($("#cpfcnpj").val())) {
            $("#cpfcnpj").css("border", "1px solid #008000");
        } else {
            $("#cpfcnpj").val("");
            $("#cpfcnpj").css("border", "1px solid #FF0000");
            alert('Digite um CPF válido');
        }


    }

}


function vercpf(req) {
    cpf = req.replace(/[^\d]+/g, '');
    if (cpf == '')
        return false;

    console.log(cpf);

    if (cpf.length != 11 ||
            cpf == "00000000000" ||
            cpf == "11111111111" ||
            cpf == "22222222222" ||
            cpf == "33333333333" ||
            cpf == "44444444444" ||
            cpf == "55555555555" ||
            cpf == "66666666666" ||
            cpf == "77777777777" ||
            cpf == "88888888888" ||
            cpf == "99999999999")
        return false;

    add = 0;

    for (i = 0; i < 9; i++)
        add += parseInt(cpf.charAt(i)) * (10 - i);
    rev = 11 - (add % 11);
    if (rev == 10 || rev == 11)
        rev = 0;
    if (rev != parseInt(cpf.charAt(9)))
        return false;
    add = 0;
    for (i = 0; i < 10; i++)
        add += parseInt(cpf.charAt(i)) * (11 - i);
    rev = 11 - (add % 11);
    if (rev == 10 || rev == 11)
        rev = 0;
    if (rev != parseInt(cpf.charAt(10)))
        return false;
    return true;
}

function verCNPJ(cnpj) {

    cnpj = cnpj.replace(/[^\d]+/g, '');

    if (cnpj == '')
        return false;

    if (cnpj.length != 14)
        return false;

    // Elimina CNPJs invalidos conhecidos
    if (cnpj == "00000000000000" ||
            cnpj == "11111111111111" ||
            cnpj == "22222222222222" ||
            cnpj == "33333333333333" ||
            cnpj == "44444444444444" ||
            cnpj == "55555555555555" ||
            cnpj == "66666666666666" ||
            cnpj == "77777777777777" ||
            cnpj == "88888888888888" ||
            cnpj == "99999999999999")
        return false;

    // Valida DVs
    tamanho = cnpj.length - 2
    numeros = cnpj.substring(0, tamanho);
    digitos = cnpj.substring(tamanho);
    soma = 0;
    pos = tamanho - 7;
    for (i = tamanho; i >= 1; i--) {
        soma += numeros.charAt(tamanho - i) * pos--;
        if (pos < 2)
            pos = 9;
    }
    resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
    if (resultado != digitos.charAt(0))
        return false;

    tamanho = tamanho + 1;
    numeros = cnpj.substring(0, tamanho);
    soma = 0;
    pos = tamanho - 7;
    for (i = tamanho; i >= 1; i--) {
        soma += numeros.charAt(tamanho - i) * pos--;
        if (pos < 2)
            pos = 9;
    }
    resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
    if (resultado != digitos.charAt(1))
        return false;

    return true;

}

