package main.java.com.mosby.utils;

import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

public class MailUtils {
	
	private static String SMPT_HOSTNAME = "smtp.gmail.com";
	private static String USERNAME = "mosby.events@gmail.com";
	private static String PASSWORD = "code_busters";
	private static String PORT = "465";
	
	
	private Properties props;
	
	public MailUtils() {
		props = new Properties();
	    props.put("mail.smtp.host", SMPT_HOSTNAME);
	    props.put("mail.from", USERNAME);
	    props.put("mail.port", PORT);
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.auth", "true");
	}
	
	public Properties getProps() {
		return props;
	}

	public void setProps(Properties props) {
		this.props = props;
	}
	
	public void sendMessage(String email, String authentication){

	    Session session = Session.getInstance(getProps(), new Authenticator() {
	        @Override
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(USERNAME, PASSWORD);
	        }
	    });
	    try {
	        MimeMessage msg = new MimeMessage(session);
	        msg.setFrom();
	        msg.setSubject("Mosby Autentification");
	        msg.setSentDate(new java.util.Date());
	        msg.setText("You are register on MosbyEvent! Welcome!\nYou register code http://localhost:8080/Mosby/authentication?authentication_code=" + authentication);
	        msg.setRecipients(Message.RecipientType.TO,
	                          email);
	        Transport.send(msg);
	     } catch (MessagingException mex) {
	        System.out.println("send failed, exception: " + mex);
	     }
	}

	public void sendMessage(List<String> emails, String message, String subject){

	    Session session = Session.getInstance(getProps(), new Authenticator() {
	        @Override
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(USERNAME, PASSWORD);
	        }
	    });
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
}
