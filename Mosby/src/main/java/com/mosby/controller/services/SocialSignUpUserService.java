package main.java.com.mosby.controller.services;

import main.java.com.mosby.controller.dao.ReflectionDao;
import main.java.com.mosby.model.User;
import main.java.com.mosby.model.UserProfile;
import main.java.com.mosby.utils.EncryptionUtils;
import main.java.com.mosby.utils.MailUtils;

public class SocialSignUpUserService {
	
	public User signUpUser(User user) {

		//User user = new User();
		
		ReflectionDao<User> usersDao = new ReflectionDao<>(
				(Class<User>) user.getClass());
		ReflectionDao<UserProfile> usersProfilesDao = new ReflectionDao<>(
				(Class<UserProfile>) user.getUserProfile().getClass());

		if (!usersDao.selectObjects("email", user.getEmail()).isEmpty()) {
			return null;
		} else {
			UserProfile userProfile = user.getUserProfile();
			
			
			
			String encryptedPassword = EncryptionUtils.createHash("");
			user.setPassword(encryptedPassword);
			usersDao.insertObjects(new User(user.getFirstName(), user.getLastName(), user.getEmail(), encryptedPassword, 0, false));
			usersProfilesDao.insertObjects(userProfile);

			new MailUtils().sendMessage(user.getEmail());
			user = usersDao.selectObjects("email", user.getEmail()).get(0);

			return user;
		}
	}
}
