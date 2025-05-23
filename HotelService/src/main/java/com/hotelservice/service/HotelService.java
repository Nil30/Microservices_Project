package com.hotelservice.service;

import java.util.List;

import com.hotelservice.entity.Hotel;

public interface HotelService {

	// get all hotels
	List<Hotel> getAllHotels();

	// getOne Hotel
	Hotel getOneHotel(String id);

	// save Hotel
	Hotel saveHotel(Hotel hotel);

	// delete Hotel
	String deleteHotel(String id);

	// update Hotel
	Hotel updateHotel(Hotel hotel, String id);

}
