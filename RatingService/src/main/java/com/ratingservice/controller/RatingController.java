package com.ratingservice.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratingservice.Service.RatingServiceImpl;
import com.ratingservice.entity.Rating;

@RestController
@RequestMapping("/ratings")
public class RatingController {

	@Autowired
	private RatingServiceImpl serviceImpl;

	// getting all rating response
	@GetMapping
	public ResponseEntity<List<Rating>> getAll() {
		List<Rating> list = this.serviceImpl.getAllRatings();
		return ResponseEntity.ok(list);
	}

	@GetMapping("/{ratingId}")
	public ResponseEntity<?> getRating(@PathVariable String ratingId) {
		Optional<Rating> result = this.serviceImpl.getRating(ratingId);
		return ResponseEntity.ok(result);
	}

	@PostMapping
	public ResponseEntity<Rating> saveRating(@RequestBody Rating rating) {
		Rating result = this.serviceImpl.saveRating(rating);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}

	@GetMapping("/users/{userId}")
	public ResponseEntity<List<Rating>> getAllByUser(@PathVariable String userId) {
		return ResponseEntity.ok(serviceImpl.getRatingByUserId(userId));
	}

	@GetMapping("/hotels/{hotelId}")
	public ResponseEntity<List<Rating>> getAllByHotel(@PathVariable String hotelId) {
		return ResponseEntity.ok(serviceImpl.getRatingByHotelId(hotelId));
	}
}
