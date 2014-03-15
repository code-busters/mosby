package main.java.com.mosby.controller.services;

import main.java.com.mosby.controller.dao.ReflectionDao;
import main.java.com.mosby.model.User;
import main.java.com.mosby.utils.EncryptionUtils;
import main.java.com.mosby.utils.MailUtils;

public class SocialSignUpUserService {
	
	public User signUpUser(User user) {
		
		ReflectionDao<User> usersDao = new ReflectionDao<>(
				(Class<User>) user.getClass());

		if (!usersDao.selectObjects("email", user.getEmail()).isEmpty()) {
			return null;
		} else {
			
			String encryptedPassword = EncryptionUtils.createHash("");
			user.setPassword(encryptedPassword);
			usersDao.insertObjects(user);

			new MailUtils().sendMessage(user.getEmail());
			user = usersDao.selectObjects("email", user.getEmail()).get(0);

			return user;
		}
	}
}
