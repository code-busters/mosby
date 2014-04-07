package main.java.com.mosby.utils.concurrency;

import main.java.com.mosby.utils.MailUtils;

public class SendEmailThread implements Runnable {

	private String email;
	private String authentication;
	
	public SendEmailThread(String email, String authentication) {
		this.email = email;
		this.authentication = authentication;
	}

	@Override
	public void run() {
		new MailUtils().sendMessage(email, authentication);
	}

}
