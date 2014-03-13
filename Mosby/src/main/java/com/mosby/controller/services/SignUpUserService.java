package main.java.com.mosby.controller.services;

import main.java.com.mosby.controller.dao.ReflectionDao;
import main.java.com.mosby.model.User;
import main.java.com.mosby.utils.EncryptionUtils;

public class SignUpUserService {
	
	public User signUpUser(String firstName, String lastName, String email, String password) {
//		
//		BaseUserInfo baseUserInfo = new BaseUserInfo();;
//		ReflectionDao<BaseUserInfo> usersDao = new ReflectionDao<>((Class<BaseUserInfo>) baseUserInfo.getClass());	
//
//		if(!usersDao.selectObjects("email", email).isEmpty()){
//			System.out.println("signUp fail! change email!");
//			return null;
//		} else {
//		String encryptedPassword = EncryptionUtils.createHash(password);
//        
//		baseUserInfo = new BaseUserInfo(firstName, lastName, email, encryptedPassword, 0, false);
//		usersDao.insertObjects(baseUserInfo);
//		
//		baseUserInfo = usersDao.selectObjects("email", email).get(0);
//		
//		return baseUserInfo;
//		}
		return null;
	}
}
