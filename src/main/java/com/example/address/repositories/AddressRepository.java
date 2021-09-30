package com.example.address.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.address.models.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{
	
	Address getAddressByUserId(long userId);

}
