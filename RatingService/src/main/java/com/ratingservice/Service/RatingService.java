package com.ratingservice.Service;

import java.util.List;
import java.util.Optional;

import com.ratingservice.entity.Rating;

public interface RatingService {

	// get all ratings
	List<Rating> getAllRatings();

	// get one rating
	Optional<Rating> getRating(String ratingId);

	// save rating
	Rating saveRating(Rating rating);
	
	//get Rating by User Id
	List<Rating> getRatingByUserId(String userId);
	
	//get Rating By hotel id
	List<Rating> getRatingByHotelId(String hotelId);
	
	
}
