package com.hotelservice.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Hotel {

	@Id
	private String id;

	@Nonnull
	@NotBlank(message = "Name cannot be empty.")
	@Size(max = 100, message = "Name should not exceed 100 characters.")
	private String name;

	@Nonnull
	@NotBlank(message = "location cannot be empty.")
	@Size(max = 100, message = "location should not exceed 100 characters.")
	private String location;

	@Nonnull
	@NotBlank(message = "About cannot be empty.")
	@Size(max = 100, message = "About should not exceed 100 characters.")
	private String about;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	

}
