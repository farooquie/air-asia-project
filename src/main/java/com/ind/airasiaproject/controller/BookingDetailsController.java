package com.ind.airasiaproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ind.airasiaproject.dto.BookingDetails;
import com.ind.airasiaproject.response.ApplicationResponse;
import com.ind.airasiaproject.service.BookingDetailsService;

@RestController
@RequestMapping(value = "/booking")
public class BookingDetailsController {

	@Autowired
	private BookingDetailsService bookingDetailsService;

	@PostMapping(value = "bookFlight/{flightNumber}")
	public ApplicationResponse<BookingDetails> saveBookingDetails(@RequestBody BookingDetails bookingDetails,
			@PathVariable String flightNumber) {
		return bookingDetailsService.saveBookingDetailsService(bookingDetails, flightNumber);
	}
}
