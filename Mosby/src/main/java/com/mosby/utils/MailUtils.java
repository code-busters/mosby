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
	private static String REGISTER = "You are register on MosbyEvent! Welcome!\nYou register code http://localhost:8080/Mosby/authentication?authentication_code=";
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
	        log.error("send failed, exception: " + mex);
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
	    	 log.error("send failed, exception: " + mex);
	     }
	}
	
	public void sendTicket(String recipient, String path, Ticket...tickets) {
        
        String content = "Tickects"; //this will be the text of the email
        String subject = "Your ticket"; //this will be the subject of the email
        
        ByteArrayOutputStream outputStream = null;
		TicketGenerator ticketGenerator = null;
         
        try {           
            //construct the text body part
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText(content);
             
            //now write the PDF content to the output stream
            outputStream = new ByteArrayOutputStream();
            //writePdf(outputStream);
            ticketGenerator = new TicketGenerator(outputStream, path);
            //ticketGenerator.generateTicket();
            ticketGenerator.createTickets(tickets);
            byte[] bytes = outputStream.toByteArray();
             
            //construct the pdf body part
            DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
            MimeBodyPart pdfBodyPart = new MimeBodyPart();
            pdfBodyPart.setDataHandler(new DataHandler(dataSource));
            pdfBodyPart.setFileName("ticket.pdf");
                         
            //construct the mime multi part
            MimeMultipart mimeMultipart = new MimeMultipart();
            mimeMultipart.addBodyPart(textBodyPart);
            mimeMultipart.addBodyPart(pdfBodyPart);
             
            //create the sender/recipient addresses
            InternetAddress iaSender = new InternetAddress(USERNAME);
            InternetAddress iaRecipient = new InternetAddress(recipient);
             
            //construct the mime message
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setSender(iaSender);
            mimeMessage.setSubject(subject);
            mimeMessage.setRecipient(Message.RecipientType.TO, iaRecipient);
            mimeMessage.setContent(mimeMultipart);
             
            //send off the email
            Transport.send(mimeMessage);
                      
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            //clean off
            if(null != outputStream) {
                try { outputStream.close(); outputStream = null; }
                catch(Exception ex) { }
            }
        }
    }
}
