package com.userservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.userservice.entity.User;
import com.userservice.service.UserServiceImpl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;

	private Logger logger = LoggerFactory.getLogger(UserController.class);


	@GetMapping
	public ResponseEntity<?> getAllUsers() {
		List<User> result = this.userServiceImpl.getAllUsers();
		return ResponseEntity.ok(result);
	}

	@PostMapping
	public ResponseEntity<User> saveUserData(@RequestBody @Valid User user) {
		User result = this.userServiceImpl.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}

	@GetMapping("/{userId}")
	@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallBackMethod")
	public ResponseEntity<?> getUserwithId(@PathVariable String userId) {
		User result = this.userServiceImpl.getUser(userId);
		return ResponseEntity.ok(result);
	}

	// Creating FallBack Method with pass Dummy User Data
	public ResponseEntity<?> ratingHotelFallBackMethod(String userId, Exception ex) {
		logger.info("Fall Back Method is Executed because Service is down :  ", ex.getMessage());
		User dummyUser = new User();
		dummyUser.setName("Dummy");
		dummyUser.setPhoneno("Dummy Phone No");
		dummyUser.setUserId("Dummy User with Id : 123");
		return new ResponseEntity<>(dummyUser, HttpStatus.OK);
	}

	@DeleteMapping("/{userId}")
	public String deletetUser(@PathVariable String userId) {
		return this.userServiceImpl.deleteUser(userId);
	}

	@PutMapping("/{userId}")
	public ResponseEntity<?> updateStudent(@PathVariable String userId, @RequestBody @Valid User user) {
		try {
			// Call the service method to update the user
			User updatedUser = userServiceImpl.updateUser(user, userId);
			return ResponseEntity.ok(updatedUser);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

}
