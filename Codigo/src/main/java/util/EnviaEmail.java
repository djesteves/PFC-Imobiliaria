/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

/**
 * @author Diego
 */
public class EnviaEmail {


    final String senderEmailId = "";
    final String senderPassword = "";
    final String emailSMTPserver = "smtp.gmail.com";
    final String emailSMTPPort = "587";

    public void SendEmailThroughGmail(String receiverEmail,
                                      String subject, String messageText) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", emailSMTPserver);
        props.put("mail.smtp.port", emailSMTPPort);

        try {
            Authenticator auth = new SMTPAuthenticator();
            Session session = Session.getInstance(props, auth);
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmailId));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(receiverEmail));
            message.setSubject(subject);
            message.setText(messageText);

            Transport.send(message);
            System.out.println("Email send successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error in sending email.");
        }
    }


    private class SMTPAuthenticator extends
            Authenticator {
        public PasswordAuthentication
        getPasswordAuthentication() {
            return new PasswordAuthentication(senderEmailId,
                    senderPassword);
        }
    }

}
