package com.example.address.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Address {
	
	@Id
	@GeneratedValue( strategy =  GenerationType.AUTO)
	private long id;
	private long userId;
	private String street;
	private String town;
	private String city;
	private String state;
	
	public Address() {
		super();
	}
	
	public Address(long id, long userId, String street, String town, String city, String state) {
		super();
		this.id=id;
		this.userId = userId;
		this.street = street;
		this.town = town;
		this.city = city;
		this.state = state;
	}
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
	

}
