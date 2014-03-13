package main.java.com.mosby.controller.services;

import main.java.com.mosby.controller.dao.*;
import main.java.com.mosby.model.*;

public class MainService {

	private User user = new User();
	private ReflectionDao<User> usersDao = new ReflectionDao<>(
			(Class<User>) user.getClass());

	public void selectUsers() {
		User user = new User();
		Class<User> typeUser = (Class<User>) user.getClass();
		System.out.println(typeUser);

		System.out.println(usersDao.selectObjects("", null));
		//System.out.println(usersDao.selectObjects("firstName", "Oleksii"));
	}

	public void insertUser(User user) {
//		Class<BaseUserInfo> typeBaseUserInfo = (Class<BaseUserInfo>) baseUserInfo
//				.getClass();
//		System.out.println(typeBaseUserInfo);
//
//		usersDao.insertObjects(baseUserInfo);
	}
	
}
