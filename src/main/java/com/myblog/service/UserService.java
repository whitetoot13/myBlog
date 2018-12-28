package com.myblog.service;

import java.util.List;

import com.myblog.entity.User;

public interface UserService {
	
	List<User> getAllUsers();
	
	List<User> getUsersByName(String name);

	void addUser(String name, int age);
	
	

}
