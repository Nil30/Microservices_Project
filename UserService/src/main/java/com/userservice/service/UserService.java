package com.userservice.service;

import java.util.List;

import com.userservice.entity.User;

public interface UserService {

	// save User
	User saveUser(User user);

	// Get All Users
	List<User> getAllUsers();

	// Get One User
	User getUser(String userId);

	// Delete User
	String deleteUser(String userId);

	// update User
	User updateUser(User user, String userId);

}
