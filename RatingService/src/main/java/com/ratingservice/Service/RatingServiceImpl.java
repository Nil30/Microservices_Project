package com.ratingservice.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ratingservice.entity.Rating;
import com.ratingservice.repository.RatingRepository;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepository ratingRepository;

	@Override
	public List<Rating> getAllRatings() {
		// TODO Auto-generated method stub
		return ratingRepository.findAll();
	}

	@Override
	public Optional<Rating> getRating(String ratingId) {
		// TODO Auto-generated method stub
		return ratingRepository.findById(ratingId);
	}

	@Override
	public Rating saveRating(Rating rating) {
		// TODO Auto-generated method stub
		String random = UUID.randomUUID().toString();
		rating.setRatingId(random);
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getRatingByUserId(String userId) {
		return this.ratingRepository.findByUserId(userId);
	}

	@Override
	public List<Rating> getRatingByHotelId(String hotelId) {
		return this.ratingRepository.findByHotelId(hotelId);
	}

}
