/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.command;

import controle.Command;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import modelo.DAO.ImovelDAO;
import modelo.Imovel;
import modelo.Sessao;
import util.EnviaEmail;

/**
 *
 * @author Diieg
 */
public class ImovelCadastrar implements Command {

    Imovel imovel = new Imovel();

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {

        try {

            HttpSession usuarioLogado = request.getSession();
            Sessao sessao = (Sessao) usuarioLogado.getAttribute("usuarioLogado");

            Part filePart = request.getPart("uploadFile"); // 
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); //
            InputStream fileContent = filePart.getInputStream();

            imovel.setDiretorioimg(sessao.getId_usuario() + File.separator + fileName);

            ByteArrayOutputStream os = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int len;

            while ((len = fileContent.read(buffer)) != -1) {

                os.write(buffer, 0, len);
            }

            byte[] bytes = os.toByteArray();

            // cria o diretorio de upload
            // esse caminho e relativo ao diretorio da aplicacao
            String uploadPath = "C:\\Users\\tr0j4nh4x\\Documents\\PFC-Imobiliaria\\web\\Resources\\upload" + File.separator + sessao.getId_usuario();
            //String uploadPath = "D:\\Faculdade\\PFC\\PFC_Imobiliaria_Postgres\\web\\Resources\\upload" + File.separator + sessao.getId_usuario();

            /* 
            String uploadPath = request.getServletContext().getRealPath("")
                    + File.separator + UPLOAD_DIRETORIO + File.separator + usuarioid.getId_usuario();
             */
            // caso o diretorio nao exista o bloco abaixo cria o mesmo
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            //converte o array de bytes em file e grava no diretorio
            File f = new File(uploadPath + File.separator + fileName);
            try (FileOutputStream fos = new FileOutputStream(f)) {
                fos.write(bytes);
            }

            //Imovel Requests
            String titulo = request.getParameter("titulo");
            String descricao = request.getParameter("descricao");
            int comodos = Integer.parseInt(request.getParameter("comodos"));
            int banheiro = Integer.parseInt(request.getParameter("banheiro"));
            int garagem = Integer.parseInt(request.getParameter("garagem"));
            Double valor = Double.parseDouble(request.getParameter("valorimovel"));
            Double areatotal = Double.parseDouble(request.getParameter("areatotal"));
            Double areaedificada = Double.parseDouble(request.getParameter("areaedificada"));
            String tpimovel = request.getParameter("tpimovel");

            imovel.setTitulo(titulo);
            imovel.setDescricao(descricao);
            imovel.setComodos(comodos);
            imovel.setBanheiros(banheiro);
            imovel.setVagas_garagem(garagem);
            imovel.setValor(valor);
            imovel.setArea_total(areatotal);
            imovel.setArea_edificada(areaedificada);
            imovel.setTipo_imovel(tpimovel);
            imovel.getUsuario().setId_usuario(sessao.getId_usuario());

            //Endereço Requests
            String logradouro = request.getParameter("logradouro");
            int numero = Integer.parseInt(request.getParameter("numero"));
            String complemento = request.getParameter("complemento");
            String cidade = request.getParameter("cidade");
            String estado = request.getParameter("estado");
            String cep = request.getParameter("cep");
            String bairro = request.getParameter("bairro");

            //Endereço Set's
            imovel.getEndereco().setLogradouro(logradouro);
            imovel.getEndereco().setNumero(numero);
            imovel.getEndereco().setComplemento(complemento);
            imovel.getEndereco().setCidade(cidade);
            imovel.getEndereco().setEstado(estado);
            imovel.getEndereco().setCep(cep);
            imovel.getEndereco().setBairro(bairro);

            ImovelDAO dao = new ImovelDAO();

            if (dao.cadastrar(imovel)) {
                String assunto = "Cadastro de Imóvel - Royal Imobiliária";
                String msgemail = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html style=\"width:100%;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0;\"><head> <meta charset=\"UTF-8\"> <meta content=\"width=device-width, initial-scale=1\" name=\"viewport\"> <meta content=\"telephone=no\" name=\"format-detection\"> <title>Novo modelo de e-mail 2020-04-16</title> <style type=\"text/css\"> @media only screen and (max-width:600px){p, ul li, ol li, a{font-size: 16px!important; line-height: 150%!important}h1{font-size: 30px!important; text-align: center; line-height: 120%!important}h2{font-size: 26px!important; text-align: center; line-height: 120%!important}h3{font-size: 20px!important; text-align: center; line-height: 120%!important}.es-menu td a{font-size: 16px!important}.es-header-body p, .es-header-body ul li, .es-header-body ol li, .es-header-body a{font-size: 16px!important}.es-footer-body p, .es-footer-body ul li, .es-footer-body ol li, .es-footer-body a{font-size: 16px!important}.es-infoblock p, .es-infoblock ul li, .es-infoblock ol li, .es-infoblock a{font-size: 12px!important}*[class=\"gmail-fix\"]{display: none!important}.es-m-txt-c{text-align: center!important}.es-m-txt-r{text-align: right!important}.es-m-txt-l{text-align: left!important}.es-m-txt-r img, .es-m-txt-c img, .es-m-txt-l img{display: inline!important}.es-button-border{display: block!important}a.es-button{font-size: 20px!important; display: block!important; border-width: 10px 0px 10px 0px!important}.es-btn-fw{border-width: 10px 0px!important; text-align: center!important}.es-adaptive table, .es-btn-fw, .es-btn-fw-brdr, .es-left, .es-right{width: 100%!important}.es-content table, .es-header table, .es-footer table, .es-content, .es-footer, .es-header{width: 100%!important; max-width: 600px!important}.es-adapt-td{display: block!important; width: 100%!important}.adapt-img{width: 100%!important; height: auto!important}.es-m-p0{padding: 0px!important}.es-m-p0r{padding-right: 0px!important}.es-m-p0l{padding-left: 0px!important}.es-m-p0t{padding-top: 0px!important}.es-m-p0b{padding-bottom: 0!important}.es-m-p20b{padding-bottom: 20px!important}.es-mobile-hidden, .es-hidden{display: none!important}.es-desk-hidden{display: table-row!important; width: auto!important; overflow: visible!important; float: none!important; max-height: inherit!important; line-height: inherit!important}.es-desk-menu-hidden{display: table-cell!important}table.es-table-not-adapt, .esd-block-html table{width: auto!important}table.es-social td{display: inline-block!important}table.es-social{display: inline-block!important}}#outlook a{padding: 0;}.ExternalClass{width: 100%;}.ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font, .ExternalClass td, .ExternalClass div{line-height: 100%;}.es-button{mso-style-priority: 100!important; text-decoration: none!important;}a[x-apple-data-detectors]{color: inherit!important; text-decoration: none!important; font-size: inherit!important; font-family: inherit!important; font-weight: inherit!important; line-height: inherit!important;}.es-desk-hidden{display: none; float: left; overflow: hidden; width: 0; max-height: 0; line-height: 0; mso-hide: all;}</style></head><body style=\"width:100%;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0;\"> <div class=\"es-wrapper-color\" style=\"background-color:#CCCCCC;\"> <table class=\"es-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;padding:0;Margin:0;width:100%;height:100%;background-repeat:repeat;background-position:center top;\"> <tr style=\"border-collapse:collapse;\"> <td valign=\"top\" style=\"padding:0;Margin:0;\"> <table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;\"> <tr style=\"border-collapse:collapse;\"> <td align=\"center\" style=\"padding:0;Margin:0;\"> <table class=\"es-content-body\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF;\"> <tr style=\"border-collapse:collapse;\"> <td align=\"left\" style=\"padding:0;Margin:0;\"> <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> <tr style=\"border-collapse:collapse;\"> <td width=\"600\" valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;\"> <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> <tr style=\"border-collapse:collapse;\"> <td align=\"center\" style=\"padding:0;Margin:0;font-size:0;\"> <a target=\"_blank\" href=\"#\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;font-size:14px;text-decoration:underline;color:#4A7EB0;\"><img class=\"adapt-img\" src=\"https://eszmuv.stripocdn.email/content/guids/CABINET_582195ffe96844656127aeb88241b9fd/images/68581504182517779.jpeg\" alt width=\"600\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic;\"></a> </td></tr></table> </td></tr></table> </td></tr></table> </td></tr></table> <table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;\"> <tr style=\"border-collapse:collapse;\"> <td align=\"center\" style=\"padding:0;Margin:0;\"> <table class=\"es-content-body\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#4A7EB0;\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#4a7eb0\" align=\"center\"> <tr style=\"border-collapse:collapse;\"> <td align=\"left\" style=\"padding:0;Margin:0;padding-left:20px;padding-right:20px;padding-top:30px;\"> <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> <tr style=\"border-collapse:collapse;\"> <td width=\"560\" valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;\"> <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> <tr style=\"border-collapse:collapse;\"> <td align=\"center\" style=\"padding:0;Margin:0;\"> <h2 style=\"Margin:0;line-height:29px;mso-line-height-rule:exactly;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;font-size:24px;font-style:normal;font-weight:normal;color:#FFFFFF;\">Olá, Obrigado por cadastrar seu imóvel em nosso site!!</h2></td></tr><tr style=\"border-collapse:collapse;\"> <td align=\"center\" style=\"padding:0;Margin:0;padding-top:5px;padding-bottom:20px;font-size:0;\"> <table width=\"5%\" height=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> <tr style=\"border-collapse:collapse;\"> <td style=\"padding:0;Margin:0px;border-bottom:2px solid #FFFFFF;background:rgba(0, 0, 0, 0) none repeat scroll 0% 0%;height:1px;width:100%;margin:0px;\"></td></tr></table> </td></tr><tr style=\"border-collapse:collapse;\"> <td align=\"center\" style=\"padding:0;Margin:0;\"> <p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:14px;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;line-height:21px;color:#FFFFFF;\">O status do seu imóvel é: Aguardando Aprovação. Assim que o cadastro for aprovado enviaremos uma notificação por aqui :)</p><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:14px;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;line-height:21px;color:#FFFFFF;\"> <br></p></td></tr></table> </td></tr></table> </td></tr></table> </td></tr></table> </td></tr></table> </div></body></html>";

                String email = sessao.getEmail();

                String remetente = "royal.imobiliaria2020@gmail.com";
                System.out.println("__________________________________________________");
                System.out.println("Enviando email DE: " + remetente + " PARA: " + email);
                System.out.println("Assunto: " + assunto);

                Message message = new MimeMessage(EnviaEmail.criarSessionMail());
                message.setFrom(new InternetAddress(remetente)); // Remetente

                Address[] toUser = InternetAddress // Destinatário(s)
                        .parse(email.trim().toLowerCase());

                message.setRecipients(Message.RecipientType.TO, toUser);
                message.setSubject(assunto);// Assunto
                message.setContent(msgemail, "text/html");
                /**
                 * Método para enviar a mensagem criada
                 */
                Transport.send(message);

                System.out.println("Email enviado com sucesso !");
                System.out.println("__________________________________________________");

                request.setAttribute("msg", "Seu imóvel foi cadastrado e passará por uma análise, fique de olho no seu email!");
                return "index.jsp";
            } else {
                request.setAttribute("msgerro", "Ocorreu um erro ao tentar cadastrar o imóvel, tente novamente");
                return "index.jsp";
            }
        } catch (NumberFormatException | IOException | ServletException | MessagingException ex) {
            request.setAttribute("msgerro", ex.getMessage());
            return "index.jsp";
        }
    }
}
