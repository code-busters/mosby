package main.java.com.mosby.utils.concurrency;

import main.java.com.mosby.model.Ticket;

public class RunThread {

	public void runSendEmail(String email, String authentication) {
		Thread thread = new Thread(new SendEmailThread(email, authentication));
		thread.start();
		thread.interrupt();
	}
	
	public void runSendTicket(String recipient, String path, Ticket...tickets) {
		Thread thread = new Thread(new SendTicketsThread(recipient, path, tickets));
		thread.start();
		thread.interrupt();
	}
}
