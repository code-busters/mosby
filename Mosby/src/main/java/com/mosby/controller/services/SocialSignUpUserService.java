package main.java.com.mosby.controller.services;

import main.java.com.mosby.controller.dao.ReflectionDao;
import main.java.com.mosby.model.User;
import main.java.com.mosby.utils.EncryptionUtils;

public class SocialSignUpUserService {
	
	@SuppressWarnings("unchecked")
	public User signUpUser(User user) {
		
		ReflectionDao<User> usersDao = new ReflectionDao<>(
				(Class<User>) user.getClass());

		if (!usersDao.selectObjects("email", user.getEmail()).isEmpty()) {
			return null;
		} else {
			
			String encryptedPassword = EncryptionUtils.createHash("");
			user.setPassword(encryptedPassword);
			user.setAuthenticationCode("1");
			usersDao.insertObjects(user);

			user = usersDao.selectObjects("email", user.getEmail()).get(0);

			return user;
		}
	}
}
