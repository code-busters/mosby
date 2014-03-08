package main.java.com.mosby.controller.services;

import main.java.com.mosby.controller.dao.*;
import main.java.com.mosby.model.*;

public class MainService {

	private BaseUserInfo baseUserInfo = new BaseUserInfo();
	private ReflectionDao<BaseUserInfo> usersDao = new ReflectionDao<>(
			(Class<BaseUserInfo>) baseUserInfo.getClass());

	public void selectUsers() {
		BaseUserInfo baseUserInfo = new BaseUserInfo();
		Class<BaseUserInfo> typeBaseUserInfo = (Class<BaseUserInfo>) baseUserInfo
				.getClass();
		System.out.println(typeBaseUserInfo);

		//usersDao.selectObjects("id",2);
		System.out.println(usersDao.selectObjects("id",2));
		//usersDao.selectObjects("firstName", "Oleh");
		System.out.println(usersDao.selectObjects("firstName", "Oleh"));
		//usersDao.selectObjects("admin", 1);
		System.out.println(usersDao.selectObjects("admin", 1));
	}

	public void insertUser(BaseUserInfo baseUserInfo) {
		Class<BaseUserInfo> typeBaseUserInfo = (Class<BaseUserInfo>) baseUserInfo
				.getClass();
		System.out.println(typeBaseUserInfo);

		usersDao.insertObjects(baseUserInfo);
	}
	
}
