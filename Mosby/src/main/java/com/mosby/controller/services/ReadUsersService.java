package main.java.com.mosby.controller.services;

import main.java.com.mosby.controller.dao.ReflectionDao;
import main.java.com.mosby.model.User;
import main.java.com.mosby.utils.EncryptionUtils;

public class ReadUsersService {

	public User readUser(String email, String password) {
		User user = new User();

		ReflectionDao<User> usersDao = new ReflectionDao<>((Class<User>) user.getClass());

		if (!usersDao.selectObjects("email", email).isEmpty()) {
			user = usersDao.selectObjects("email", email).get(0);

			String correctHash = user.getPassword();

			if (!EncryptionUtils.validatePassword(password, correctHash)) {
				user = null;
				System.out.println("login fail");
			}
			return user;

		} else {
			return null;

		}

	}

	public User readSocialUser(String email) {
		User user = new User();

		ReflectionDao<User> usersDao = new ReflectionDao<>((Class<User>) user.getClass());

		if (!usersDao.selectObjects("email", email).isEmpty()) {
			user = usersDao.selectObjects("email", email).get(0);

			return user;

		} else {
			return null;

		}

	}
	

}
