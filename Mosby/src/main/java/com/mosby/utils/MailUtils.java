package main.java.com.mosby.utils;

import main.java.com.mosby.controller.services.EventService;
import main.java.com.mosby.model.Ticket;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Properties;

public class MailUtils {
	
	private static Logger log = Logger.getLogger(EventService.class);
	private static String SMPT_HOSTNAME = "smtp.gmail.com";
	private static String USERNAME = "mosby.events@gmail.com";
	private static String PASSWORD = "code_busters";
	private static String PORT = "465";
	private static String REGISTER = "You are register on Mosby! Welcome!\nYou register code http://localhost:8080/Mosby/authentication?authentication_code=";
	private static String AUTENTIFICATION = "Mosby autentification";
	
	private Properties props;
	private Session session;

    public MailUtils() {
        props = new Properties();
        props.put("mail.smtp.host", SMPT_HOSTNAME);
        props.put("mail.from", USERNAME);
        props.put("mail.smtp.socketFactory.port", PORT);
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.port", PORT);
        props.put("mail.smtp.auth", "true");
        session = Session.getInstance(getProps(), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });
    }
	
	public Properties getProps() {
		return props;
	}

	public void setProps(Properties props) {
		this.props = props;
	}
	
	public void sendMessage(String email, String authentication){

	    try {
	        MimeMessage msg = new MimeMessage(session);
	        msg.setFrom();
	        msg.setSubject(AUTENTIFICATION);
	        msg.setSentDate(new java.util.Date());
	        msg.setText(REGISTER + authentication);
	        msg.setRecipients(Message.RecipientType.TO,
	                          email);
            Transport.send(msg);

	     } catch (MessagingException mex) {
	        log.error("Sending is fail: " + mex);
	     }
	}

	public void sendMessage(List<String> emails, String message, String subject){

	    try {
	        MimeMessage msg = new MimeMessage(session);
	        msg.setFrom();
	        msg.setSubject(subject);
	        msg.setSentDate(new java.util.Date());
	        msg.setText(message);
	        for (String email : emails) {
	        	msg.setRecipients(Message.RecipientType.TO,
                        email);
	        	Transport.send(msg);
			}
	     } catch (MessagingException mex) {
	    	 log.error("Sending is fail: " + mex);
	     }
	}
	
	public void sendTicket(String recipient, String path, Ticket...tickets) {
        
        String content = "Tickects"; 
        String subject = "Your ticket"; 
        
        ByteArrayOutputStream outputStream = null;
		TicketGenerator ticketGenerator = null;
         
        try {           
            
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText(content);
             
            
            outputStream = new ByteArrayOutputStream();
            ticketGenerator = new TicketGenerator(outputStream, path);

            ticketGenerator.createTickets(tickets);
            byte[] bytes = outputStream.toByteArray();
             
            DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
            MimeBodyPart pdfBodyPart = new MimeBodyPart();
            pdfBodyPart.setDataHandler(new DataHandler(dataSource));
            pdfBodyPart.setFileName("ticket.pdf");
                         
            MimeMultipart mimeMultipart = new MimeMultipart();
            mimeMultipart.addBodyPart(textBodyPart);
            mimeMultipart.addBodyPart(pdfBodyPart);
             
            InternetAddress iaSender = new InternetAddress(USERNAME);
            InternetAddress iaRecipient = new InternetAddress(recipient);
             
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setSender(iaSender);
            mimeMessage.setSubject(subject);
            mimeMessage.setRecipient(Message.RecipientType.TO, iaRecipient);
            mimeMessage.setContent(mimeMultipart);
             
            Transport.send(mimeMessage);
                      
        } catch(MessagingException mex) {
        	log.error("Sending is fail: " + mex);
        } catch(Exception ex){
        	log.error(ex);
        }
        finally {
            if(null != outputStream) {
                try { outputStream.close(); outputStream = null; }
                catch(Exception ex) { }
            }
        }
    }
}
