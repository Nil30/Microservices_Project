package com.userservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.userservice.Exceptions.ResourceNotFoundExceptions;
import com.userservice.entity.Hotel;
import com.userservice.entity.Rating;
import com.userservice.entity.User;
import com.userservice.externalservices.HotelService;
import com.userservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;
	
	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);	
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HotelService hotelService;

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		String randomUUID = UUID.randomUUID().toString();
		user.setUserId(randomUUID);
		return repository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public User getUser(String userId) {
		// TODO Auto-generated method stub

		User user = repository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundExceptions("User with given Id is not found on Server !!" + userId));

		// getting Rating of User which is define in RATING-SERVICE ("/users/userId")
		// calling Rating Service with help of Rest Template

		Rating[] ratingOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(),
				Rating[].class);

		List<Rating> ratings = Arrays.stream(ratingOfUser).toList();

		List<Rating> ratingList = ratings.stream().map(rating -> {

			// calling Hotel Service with help of Rest Template.

			/*
			 * ResponseEntity<Hotel> forEnitEntity = restTemplate
			 * .getForEntity("http://HOTEL_SERVICE/hotels/" + rating.getHotelId(),
			 * Hotel.class);
			 */

			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			logger.debug("{} " , hotel );
			rating.setHotel(hotel);
			return rating;

		}).collect(Collectors.toList());

		user.setRatings(ratingList);

		return user;
	}

	@Override
	public String deleteUser(String userId) {
		// TODO Auto-generated method stub
		repository.deleteById(userId);
		return "User Delete Successfully with give User Id : " + userId;
	}

	@Override
	public User updateUser(User user, String userId) {
		// Retrieve the existing user
		Optional<User> existingUserOptional = repository.findById(userId);

		if (existingUserOptional.isPresent()) {
			User existingUser = existingUserOptional.get();

			// Update the existing user's fields
			if (user.getName() != null) {
				existingUser.setName(user.getName());
			}
			if (user.getInfo() != null) {
				existingUser.setInfo(user.getInfo());
			}
			if (user.getPhoneno() != null) {
				existingUser.setPhoneno(user.getPhoneno());
			}

			// Save the updated user back to the repository
			return repository.save(existingUser);
		} else {
			throw new RuntimeException("User with id " + userId + " not found");
		}
	}

}
