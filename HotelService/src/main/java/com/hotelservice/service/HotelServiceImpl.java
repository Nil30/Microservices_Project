package com.hotelservice.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelservice.Exception.ResourceNotFoundException;
import com.hotelservice.entity.Hotel;
import com.hotelservice.entity.HotelRepository;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepository repository;

	@Override
	public List<Hotel> getAllHotels() {
		return this.repository.findAll();
	}

	@Override
	public Hotel getOneHotel(String id) {
		return this.repository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("hotel data with given Id is not found on Server !!" + id));
	}

	@Override
	public Hotel saveHotel(Hotel hotel) {
		String uuid = UUID.randomUUID().toString();
		hotel.setId(uuid);
		return repository.save(hotel);
	}

	@Override
	public String deleteHotel(String id) {
		repository.deleteById(id);
		return "Hotel Data Delete for provided Id from Database : " + id;
	}

	@Override
	public Hotel updateHotel(Hotel hotel, String id) {
		Optional<Hotel> HotelData = this.repository.findById(id);

		if (HotelData.isPresent()) {
			Hotel result = HotelData.get();

			if (hotel.getName() != null) {
				result.setName(hotel.getName());
			}
			if (hotel.getAbout() != null) {
				result.setAbout(hotel.getAbout());
			}
			if (hotel.getLocation() != null) {
				result.setLocation(hotel.getLocation());
			}
			return this.repository.save(result);
		} else {
			throw new RuntimeException("User with id " + id + " not found");
		}
	}

}
