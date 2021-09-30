package com.example.address.services;

import com.example.address.models.Address;

public interface AddressService {
	
	Address saveAddress(Address address);
	
	Address updateAddress(Address address);
	
	Address getAddress(long userId);

}
