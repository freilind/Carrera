package com.crono.mail;

import java.util.Properties;
import org.apache.log4j.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class JavaMail {
	
	private static final Logger logger = Logger.getLogger(JavaMail.class);
	private final Properties properties = new Properties();
	private Session session;
	 
	public JavaMail(){
		properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", 587);
        properties.put("mail.smtp.mail.sender", "smtp.massive@gmail.com");
        properties.put("mail.smtp.password", "massive.0.");
        properties.put("mail.smtp.user", "smtp.massive@gmail.com");
        properties.put("mail.smtp.auth", "true");
        session = Session.getDefaultInstance(properties);
		
	}
	
	public void send(String destino,String asunto, String mensaje, String pdf) {
        
        try {
        	BodyPart texto = new MimeBodyPart();
        	texto.setText(mensaje);
        	
        	BodyPart adjunto = new MimeBodyPart();
        	adjunto.setDataHandler(new DataHandler(new FileDataSource("pdf/"+pdf)));
        	adjunto.setFileName(pdf);
        	
        	MimeMultipart multiParte = new MimeMultipart();
        	multiParte.addBodyPart(texto);
        	multiParte.addBodyPart(adjunto);
        	
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress((String) properties.get("mail.smtp.mail.sender")));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destino));
            message.setSubject(asunto);
            message.setContent(multiParte);
            Transport t = session.getTransport("smtp");
            t.connect((String) properties.get("mail.smtp.user"), (String) properties.get("mail.smtp.password"));
            t.sendMessage(message, message.getAllRecipients());
            t.close();
        } catch (MessagingException me) {
        	logger.debug(me);
        }catch (Exception ex) {
        	logger.debug(ex);
        }
    }

}

