package com.coursecube.spring;

public interface UserDAO {
	
	

	public void registerUser(User user);
	
	public  void update(User user);
	public void deleteUser(int cid);

}
