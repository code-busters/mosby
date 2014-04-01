package main.java.com.mosby.utils;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.URLName;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import main.java.com.mosby.model.Ticket;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.mail.smtp.SMTPTransport;

public class MailUtils {
	
	private static String SMPT_HOSTNAME = "smtp.gmail.com";
	private static String USERNAME = "mosby.events@gmail.com";
	private static String PASSWORD = "code_busters";
	private static String PORT = "465";
	
	
	private Properties props;
	private Session session;
	
	public MailUtils() {
		props = new Properties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.host", SMPT_HOSTNAME);
	    props.put("mail.from", USERNAME);
	    props.put("mail.smtp.socketFactory.port", PORT);
	    props.put("mail.port", PORT);
	    props.put("mail.smtp.starttls.enable", "true");
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
	        msg.setSubject("Mosby Autentification");
	        msg.setSentDate(new java.util.Date());
	        msg.setText("You are register on MosbyEvent! Welcome!\nYou register code http://localhost:8080/Mosby/authentication?authentication_code=" + authentication);
	        msg.setRecipients(Message.RecipientType.TO,
	                          email);

	        Transport transport = session.getTransport("smtps");
	        transport.connect(SMPT_HOSTNAME, Integer.parseInt(PORT), USERNAME, PASSWORD);
	        transport.send(msg);
	        transport.close();

	     } catch (MessagingException mex) {
	        System.out.println("send failed, exception: " + mex);
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
	        System.out.println("send failed, exception: " + mex);
	     }
	}
	
	public void sendTicket(String recipient, Ticket ticket, String path) {
                 
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
            ticketGenerator = new TicketGenerator(ticket, outputStream, path);
            ticketGenerator.generateTicket();
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
