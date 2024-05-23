package com.ind.airasiaproject.daoimplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ind.airasiaproject.dao.BookingDetailsDao;
import com.ind.airasiaproject.dto.BookingDetails;
import com.ind.airasiaproject.dto.Flight;
import com.ind.airasiaproject.repository.BookingDetailsRepository;
import com.ind.airasiaproject.repository.FlightRepository;

@Repository
public class BookingDetailsDaoImpl implements BookingDetailsDao {

	@Autowired
	private BookingDetailsRepository bookingDetailsRepository;

	@Autowired
	private FlightRepository flightRepository;

	@Override
	public BookingDetails saveBookingDetailsDao(BookingDetails bookingDetails, String flightNumber) {
		Flight flight = flightRepository.findByFlightNumber(flightNumber);
		if (flight != null) {
			bookingDetails.setTotalPrice(bookingDetails.getCONVENIENCE_FEE() + flight.getFlightPrice());
			bookingDetails.setFlight(flight);
			return bookingDetailsRepository.save(bookingDetails);
		} else {
			return null;
		}
	}

}
