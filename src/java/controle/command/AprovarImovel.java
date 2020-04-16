/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.command;

import controle.Command;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import modelo.DAO.ImovelDAO;
import modelo.Sessao;
import util.EnviaEmail;

/**
 *
 * @author danil
 */
public class AprovarImovel implements Command {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            HttpSession usuarioLogado = request.getSession();
            Sessao sessao = (Sessao) usuarioLogado.getAttribute("usuarioLogado");
            sessao.getId_usuario();

            ImovelDAO dao = new ImovelDAO();

            boolean cadastro = dao.aprovarImovel(id);
            
            String msg = (String) request.getAttribute("msg");

            if (cadastro) {
                String assunto = "Aprovação do Imóvel - Royal Imobiliária";
                String msgemail = "Olá, Obrigado por cadastrar seu imóvel em nosso site!! <br>"
                        + "O imóvel: " + id + " foi aprovado! <br> "
                        + "Seu imóvel já está em nosso catálogo, boas vendas ^^ <br><br> "
                        + "*Esté email foi gerado automaticamente, por favor não o responda.*";

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

                request.setAttribute("msg", "Imóvel aprovado com Sucesso");
                return "index.jsp";
            } else {
                request.setAttribute("msgerro", msg);
                return "erro.jsp";
            }
        } catch (NumberFormatException | SQLException | MessagingException ex) {
            Logger.getLogger(ImovelCadastrar.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("msgerro", ex.getMessage());
            return "erro.jsp";
        }

    }

}
