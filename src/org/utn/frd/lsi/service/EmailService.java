package org.utn.frd.lsi.service;

import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.utn.frd.lsi.domain.User;
import org.utn.frd.lsi.manager.UserManager;

public class EmailService {
	
    private static final Logger log = Logger.getLogger(EmailService.class.getName());
    
    private static String URL = "https://emprendelta.appspot.com";
    private static String me = "emprendelta.noreply@gmail.com";
    private static String senderAddress = "emprendelta.noreply@gmail.com";
    private static String senderName = "Emprendelta - UTN";
    private static final String HEADER = "<p style=\"margin: 0;\"><img "
    		+ "src=\"http://emprendelta.appspot.com/img/logo.jpg\" height=\"60px\" width=\"184px\" "
    		+ "alt=\"emprendelta\" title=\"emprendelta\" ></p><p "
    		+ "style=\"margin:0;padding:50px;\">";
    private static final String FOOTER = ""; 
    private static final String loginURL = URL + "/login";

    public static void send(String toAddress, String toName, String subject, String body) throws Exception{
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(senderAddress, senderName));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress, toName));
            msg.addRecipient(Message.RecipientType.BCC, new InternetAddress(me, me));
            msg.setSubject(subject);
            msg.setContent( HEADER+body+FOOTER, "text/html; charset=utf-8");
            Transport.send(msg);

            log.info("EMAIL SENT TO: "+toAddress+" - BODY: "+body);
        } catch (AddressException e) {
            log.severe("Error en dirección de email: "+e.getMessage());
        } catch (MessagingException e) {
            log.severe("Error en mensaje de email: "+e.getMessage());
        } catch (Exception e) {
            log.severe("Error genérico de email: "+e.getMessage());
        }
    }

    public static void sendWelcome(org.utn.frd.lsi.domain.User user) {
        try {
            send(user.getEmail(), user.getName(), 
                    "Bienvenid@ a Emprendelta!", 
                    "Felicitaciones! Tu cuenta ya ha sido activada.<br><br>");
        } catch (Exception e) {
            log.severe("Error en el envio del mail de bienvenida: "+e.getMessage());
        }

    }

    public static void sendMessage(String email, String msg) {
        try {
            send(email, email, "Mensaje de emprendelta ", msg);
        } catch (Exception e) {
            log.severe("Error al enviar mensaje por email a una dirección: "+e.getMessage());
        } 
    }

	public static void sendActivation(String userEmail, String activationKey) {
		try {
			//send activation email
			User user = (new UserManager()).getByEmail(userEmail);
			send(user.getEmail(), user.getName(),
					"Activación de cuenta - Emprendelta", 
					"<h1>Activación de cuenta</h1>"
					+ "<p>Tu c&oacute;digo de activaci&oacute;n es:</p>"
					+ "<h2>"+activationKey+"</h2>"
					+ "<p>Para activar tu cuenta puedes "
					+ "<a href=\"" + URL + "/activate?activationKey=" + activationKey + "&activationEmail=" + userEmail + "\">hacer clic aqui</a>"
					+ "</p>");
		} catch (Exception e) {
			log.severe("Error en el envio del mail de activación: "+e.getMessage());
		}
	}
	
	public static void sendPasswordReset(String email, String code) {
		try {
			send(email,
					"",
					"Recuperación de contraseña - Emprendelta", 
					"Para recuperar tu contraseña, haz click en el siguiente enlace:"
					+ "</br>"
					+ "<a href=\"" + URL + "/setPassword" + "?resetCode=" + code
					+ "\">Recuperar contraseña</a>");
		} catch (Exception e) {
			log.severe("Error en el envio de mail de reestablecimiento de password \n" + e.getMessage() );
		}
	}
	
	public static void contactAdmin(String message, String sender) {
		try {
			send(me, senderName, "", message
					+ "<br><br>"
					+ "Responder a: "
					+ "<a href=\"mailto:" + sender + "\"target=\"_top\">" + sender + "</a>");
		} catch (Exception e) {
			log.severe("Error en el envío de mail de contacto al administrador \n" + e.getMessage());
		}
	}

}
