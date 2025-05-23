package com.userservice.externalservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.userservice.entity.Hotel;

@FeignClient(name ="HOTEL-SERVICE")
public interface HotelService {

	
	//getting Hotel data with hotel id using FeignClient
	@GetMapping("/hotels/{id}")
	Hotel getHotel(@PathVariable String id);
	
}
