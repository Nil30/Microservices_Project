package com.userservice.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class User {

	@Id
	private String userId;

	@Nonnull
	@NotBlank(message = "Name cannot be empty.")
	@Size(max = 100, message = "Name should not exceed 100 characters.")
	private String name;

	@Nonnull
	@NotBlank(message = "Info cannot be empty.")
	@Size(max = 250, message = "Info should not exceed 250 characters.")
	private String info;

	@Nonnull
	@Pattern(regexp = "^[6-9][0-9]{9}$", message = "Phone number must be 10 digits and start with 6, 7, 8, or 9.")
	private String phoneno;

	@Transient
	private List<Rating> ratings = new ArrayList<>();

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public User(String userId,
			@NotBlank(message = "Name cannot be empty.") @Size(max = 100, message = "Name should not exceed 100 characters.") String name,
			@NotBlank(message = "Info cannot be empty.") @Size(max = 250, message = "Info should not exceed 250 characters.") String info,
			@Pattern(regexp = "^[6-9][0-9]{9}$", message = "Phone number must be 10 digits and start with 6, 7, 8, or 9.") String phoneno,
			List<Rating> ratings) {
		super();
		this.userId = userId;
		this.name = name;
		this.info = info;
		this.phoneno = phoneno;
		this.ratings = ratings;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", info=" + info + ", phoneno=" + phoneno + ", ratings="
				+ ratings + "]";
	}

}
