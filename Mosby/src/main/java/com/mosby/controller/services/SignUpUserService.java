package main.java.com.mosby.controller.services;

import main.java.com.mosby.controller.dao.ReflectionDao;
import main.java.com.mosby.model.BaseUserInfo;
import main.java.com.mosby.utils.EncryptionUtils;

public class SignUpUserService {
	
	public BaseUserInfo signUpUser(String firstName, String lastName, String email, String password) {
		
		String encryptedPassword = EncryptionUtils.createHash(password);
		
		BaseUserInfo baseUserInfo = new BaseUserInfo(firstName, lastName, email, encryptedPassword, 0, false);
		
		ReflectionDao<BaseUserInfo> usersDao = new ReflectionDao<>((Class<BaseUserInfo>) baseUserInfo.getClass());
		usersDao.insertObjects(baseUserInfo);
		
		return baseUserInfo;
	}
}
