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
			if(!user.isActive()){
				return null;
			}

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

	public User readSocialUser(User user) {
		User usr = new User();

		ReflectionDao<User> usersDao = new ReflectionDao<>((Class<User>) usr.getClass());
		
		System.out.println(user.toString());
		
		if (!usersDao.selectObjects("email", user.getEmail()).isEmpty()) {
			user.setId(usersDao.selectObjects("email", user.getEmail()).get(0).getId());
			user.setAuthenticationCode("1");
			usersDao.updateObjects(user);

			return user;

		} else {
			return null;

		}

	}
	

}
