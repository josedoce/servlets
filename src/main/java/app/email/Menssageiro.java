package app.email;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Menssageiro {
	public static void send(String to, String subject, String msg) {
		final String user = "setar um usuario";
		final String pass = "setar uma senha";
		
		//1-pegando o objeto da sessão
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.mailtrap.io");
		props.put("mail.smtp.auth", "true");
		
		Session session = Session.getDefaultInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, pass);
			}
		});
		
		try {
			//2-compondo o corpo da menssagem
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject);
			message.setText(msg);
			
			
			//3-enviando menssagem
			Transport.send(message);
			System.out.println("Enviado.");
		} catch(MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
