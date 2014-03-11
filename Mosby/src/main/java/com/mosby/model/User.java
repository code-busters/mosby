package main.java.com.mosby.model;

public class User {
	private BaseUserInfo baseUserInfo;
	private UserProfile userProfile;
	
	public User(BaseUserInfo baseUserInfo, UserProfile userProfile) {
		super();
		this.baseUserInfo = baseUserInfo;
		this.userProfile = userProfile;
	}
	
	public BaseUserInfo getBaseUserInfo() {
		return baseUserInfo;
	}

	public void setBaseUserInfo(BaseUserInfo baseUserInfo) {
		this.baseUserInfo = baseUserInfo;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}
	
	
}
