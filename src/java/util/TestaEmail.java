/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Diego
 */
public class TestaEmail {
    public static void main(String[] args) throws MessagingException {
        
        String msg = "Teste";
        String assunto = "kkk";
        String email = "diiegopereiraa_@hotmail.com";

        String remetente = "diegoguedespereira@gmail.com";
        System.out.println("__________________________________________________");
        System.out.println("Enviando email DE: " + remetente + " PARA: " + email);
        System.out.println("Assunto: " + assunto);

        Message message = new MimeMessage(EnviaEmail.criarSessionMail());
        message.setFrom(new InternetAddress(remetente)); // Remetente

        Address[] toUser = InternetAddress // Destinatário(s)
                .parse(email.trim().toLowerCase());

        message.setRecipients(Message.RecipientType.TO, toUser);
        message.setSubject(assunto);// Assunto
        message.setContent(msg, "text/html");
        /**
         * Método para enviar a mensagem criada
         */
        Transport.send(message);

        System.out.println("Email enviado com sucesso !");
        System.out.println("__________________________________________________");
    }
}
