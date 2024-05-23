package com.ind.airasiaproject.service;

import com.ind.airasiaproject.dto.BookingDetails;
import com.ind.airasiaproject.response.ApplicationResponse;

public interface BookingDetailsService {
	public ApplicationResponse<BookingDetails> saveBookingDetailsService(BookingDetails bookingDetails,String flightNumber);

}
