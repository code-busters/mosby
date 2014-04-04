package main.java.com.mosby.utils.concurrency;

import main.java.com.mosby.model.Ticket;

public class RunThread {

	public void runSendEmail(String email, String authentication) {
		Thread thread = new Thread(new SendEmailThread(email, authentication));
		thread.start();
		System.out.println("After Start");
		try {
			thread.sleep(10000);
			System.out.println("After Sleep");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		thread.interrupt();
		System.out.println("After Interrupt " + thread.isAlive());
	}
	
	public void runSendTicket(String recipient, String path, Ticket...tickets) {
		Thread thread = new Thread(new SendTicketsThread(recipient, path, tickets));
		thread.start();
		System.out.println("After Start");
		try {
			thread.sleep(10000);
			System.out.println("After Sleep");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		thread.interrupt();
		System.out.println("After Interrupt " + thread.isAlive());
	}
}
