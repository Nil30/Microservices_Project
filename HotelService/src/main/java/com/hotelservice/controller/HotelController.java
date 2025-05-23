package com.hotelservice.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelservice.entity.Hotel;
import com.hotelservice.service.HotelServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/hotels")
public class HotelController {

	@Autowired
	private HotelServiceImpl serviceImpl;

	// getting all hotel data
	@GetMapping
	public ResponseEntity<List<Hotel>> getHotels() {
		List<Hotel> list = this.serviceImpl.getAllHotels();
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

	// get One Hotel
	@GetMapping("/{id}")
	public ResponseEntity<?> getHotel(@PathVariable String id) {
		Hotel result = this.serviceImpl.getOneHotel(id);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	// save Hotel
	@PostMapping
	public ResponseEntity<Hotel> saveHotelData(@RequestBody @Valid Hotel hotel) {
		Hotel result = this.serviceImpl.saveHotel(hotel);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}

	// delete hotel data
	@DeleteMapping("/{id}")
	public String deleteHotelData(@PathVariable String id) {
		return this.serviceImpl.deleteHotel(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateHotelData(@PathVariable String id, @RequestBody @Valid Hotel hotel) {
		try {
			Hotel result = this.serviceImpl.updateHotel(hotel, id);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

}
