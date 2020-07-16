package com.coursecube.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {
    @Autowired
	JdbcTemplate JdbcTemplate;
	
	
	public void registerUser(User user) {
		
		String sql="insert into myuser values(?,?,?,?,?,?,?)";
		JdbcTemplate.update(sql,user.getUsername(),user.getPassword(),user.getFirstName(),user.getLastName(),user.getEmail(),user.getPhone(),user.getActive());
		
	}


	@Override
	public void update(User user) {
		System.out.println("User update(User user) ");
		System.out.println("User update(User user) ");
		System.out.println("User update(User user) ");
		System.out.println("User update(User user) ");
		System.out.println("User update(User user) ");
		
	}


	@Override
	public void deleteUser(int cid) {
		System.out.println("User deleted");
		System.out.println("User deleted");
		System.out.println("User deleted");
		System.out.println("User deleted");
		System.out.println("User deleted");
		
	}
	
	

}
