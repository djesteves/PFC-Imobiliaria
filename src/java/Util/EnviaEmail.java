/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.util.Properties;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

/**
 *
 * @author Diego
 */
public class EnviaEmail {

    public static Session criarSessionMail() {
        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", 465);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.port", 465);

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("royal.imobiliaria2020@gmail.com", "@Royal123");
            }
        });

        session.setDebug(true);

        return session;
    }
}
