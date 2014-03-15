package main.java.com.mosby.controller.services;

import java.security.SecureRandom;

import main.java.com.mosby.controller.dao.ReflectionDao;
import main.java.com.mosby.model.User;
import main.java.com.mosby.utils.EncryptionUtils;
import main.java.com.mosby.utils.MailUtils;

public class SignUpUserService {

	public User signUpUser(String firstName, String lastName, String email,
			String password) {

		User user = new User();
		;
		ReflectionDao<User> usersDao = new ReflectionDao<>(
				(Class<User>) user.getClass());

		if (!usersDao.selectObjects("email", email).isEmpty()) {
			System.out.println("signUp fail! change email!");
			return null;
		} else {
			String encryptedPassword = EncryptionUtils.createHash(password);

			SecureRandom random = new SecureRandom();
	        byte[] code = new byte[24];
	        random.nextBytes(code);
	        String authentication = new EncryptionUtils().toHex(code);
	        
			user = new User(firstName, lastName, email, encryptedPassword, authentication);
			usersDao.insertObjects(user);

			
			new MailUtils().sendMessage(email, authentication);
			user = usersDao.selectObjects("email", email).get(0);

			return user;
		}
	}
}
