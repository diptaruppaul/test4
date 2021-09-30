package com.example.address.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.address.models.Address;
import com.example.address.services.AddressService;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;

@RestController
public class AddressController {
	
	@Autowired
	AddressService addressService;
	
	private static final Tracer TRACER = GlobalOpenTelemetry.getTracer("com.example.address.OpenTelemetryConfiguration");
	
	  @GetMapping("/address/{userId}") 
	  public Address getAddress(@PathVariable long userId) { 
		  Span span = TRACER.spanBuilder("userSpan").startSpan();
		  Address address = addressService.getAddress(userId); 
		  span.end(); 
		  return address;
	  
	  }
	 
	
	/*
	 * @GetMapping("/address/{userId}") public Address getAddress(@PathVariable long
	 * userId) { return addressService.getAddress(userId);
	 * 
	 * 
	 * }
	 */
	
	@PostMapping("/address")
	public Address getAddress(@RequestBody Address address) {
		return addressService.saveAddress(address);
	}
	
	@PutMapping("address/{userId}")
	public Address getAddress(@RequestBody Address address, @PathVariable long userId) {
		return addressService.saveAddress(address);
	}

}
