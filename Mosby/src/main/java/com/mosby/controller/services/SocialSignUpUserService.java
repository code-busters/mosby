package main.java.com.mosby.controller.services;

import main.java.com.mosby.controller.dao.ReflectionDao;
import main.java.com.mosby.model.User;
import main.java.com.mosby.utils.EncryptionUtils;
import main.java.com.mosby.utils.MailUtils;

public class SocialSignUpUserService {
	
	public User signUpUser(String firstName, String lastName, String email, String password) {

		User user = new User();
		
		ReflectionDao<User> usersDao = new ReflectionDao<>(
				(Class<User>) user.getClass());

		if (!usersDao.selectObjects("email", email).isEmpty()) {
			
			return null;
		} else {
			String encryptedPassword = EncryptionUtils.createHash(password);
			
			user = new User(firstName, lastName, email, encryptedPassword, 0, false);
			usersDao.insertObjects(user);

			new MailUtils().sendMessage(email);
			user = usersDao.selectObjects("email", email).get(0);

			return user;
		}
	}
}
