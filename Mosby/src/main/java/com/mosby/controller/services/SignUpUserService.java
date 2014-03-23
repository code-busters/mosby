package main.java.com.mosby.controller.services;

import main.java.com.mosby.controller.dao.ReflectionDao;
import main.java.com.mosby.model.User;
import main.java.com.mosby.utils.EncryptionUtils;
import main.java.com.mosby.utils.MailUtils;

import java.security.SecureRandom;

public class SignUpUserService {

	@SuppressWarnings({ "unchecked", "static-access" })
	public User signUpUser(String firstName, String lastName, String email,
			String password) {

		User user = new User();
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
	        String authentication = new EncryptionUtils().toHex(code) + new EncryptionUtils().toHex(email.getBytes());
	        
			user = new User(firstName, lastName, email, encryptedPassword, authentication, false);
			usersDao.insertObjects(user);

			
			new MailUtils().sendMessage(email, authentication);
			user = usersDao.selectObjects("email", email).get(0);

			return user;
		}
	}
}
