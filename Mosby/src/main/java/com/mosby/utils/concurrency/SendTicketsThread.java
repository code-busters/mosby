package main.java.com.mosby.utils.concurrency;

import main.java.com.mosby.model.Ticket;
import main.java.com.mosby.utils.MailUtils;

public class SendTicketsThread implements Runnable {

	private Ticket tickets[];
	private String recipient;
	private String path;
	
	public SendTicketsThread(String recipient, String path, Ticket...tickets) {
		this.recipient = recipient;
		this.path = path;
		this.tickets = tickets;
	}

	@Override
	public void run() {
		new MailUtils().sendTicket(recipient, path, tickets);
	}

}
