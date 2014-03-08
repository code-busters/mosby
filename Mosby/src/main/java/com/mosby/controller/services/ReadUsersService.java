package main.java.com.mosby.controller.services;

import main.java.com.mosby.controller.dao.ReflectionDao;
import main.java.com.mosby.model.BaseUserInfo;
import main.java.com.mosby.utils.EncryptionUtils;

public class ReadUsersService {

	
	public BaseUserInfo readUser(String email, String password){
		BaseUserInfo baseUserInfo = new BaseUserInfo();
		
		ReflectionDao<BaseUserInfo> usersDao = new ReflectionDao<>((Class<BaseUserInfo>) baseUserInfo.getClass());
		usersDao.selectAll("email", email);
		
		baseUserInfo = usersDao.getHashMap().get(2);
		
		String correctHash = baseUserInfo.getPassword();
		
		if (!EncryptionUtils.validatePassword(password, correctHash)){
			baseUserInfo = null;
			System.out.println("login fail");
		}
		return baseUserInfo;
	} 
}
