package com.ind.airasiaproject.dao;

import com.ind.airasiaproject.dto.BookingDetails;

public interface BookingDetailsDao {

	public BookingDetails saveBookingDetailsDao(BookingDetails bookingDetails,String flightNumber);
}
