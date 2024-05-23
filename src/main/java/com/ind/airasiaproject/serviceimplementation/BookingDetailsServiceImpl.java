package com.ind.airasiaproject.serviceimplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ind.airasiaproject.dao.BookingDetailsDao;
import com.ind.airasiaproject.dao.FlightDao;
import com.ind.airasiaproject.dto.BookingDetails;
import com.ind.airasiaproject.dto.User;
import com.ind.airasiaproject.repository.UserRepository;
import com.ind.airasiaproject.response.ApplicationResponse;
import com.ind.airasiaproject.service.BookingDetailsService;

import jakarta.servlet.http.HttpSession;

@Service
public class BookingDetailsServiceImpl implements BookingDetailsService{

	@Autowired
	BookingDetailsDao bookingDetailsDao;
	@Autowired
	private ApplicationResponse<BookingDetails> applicationResponse;
	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private UserRepository userRepository;
	@Override
	public ApplicationResponse<BookingDetails> saveBookingDetailsService(BookingDetails bookingDetails, String flightNumber) {
		String email = (String) httpSession.getAttribute("userSession");
		if (email != null) {
			
			User user = userRepository.findByUserEmail(email);
			bookingDetails.setUser(user);
			BookingDetails bookingDetails2 = bookingDetailsDao.saveBookingDetailsDao(bookingDetails, flightNumber);
		
			if (bookingDetails2 != null) {
					applicationResponse.setStatusCode(HttpStatus.ACCEPTED.value());
					applicationResponse.setMessage("Tickets--Successfully--Booked");
					applicationResponse.setDescription("Ticket Sent To Your Eail");
					applicationResponse.setData(bookingDetails2);
				} else {
					applicationResponse.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
					applicationResponse.setMessage("Booking Details Not Found");
					applicationResponse.setDescription("Try---Again");
					applicationResponse.setData(null);
					return applicationResponse;
				}
			} else {
				applicationResponse.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
				applicationResponse.setMessage("Yor Are Not Logged-In");
				applicationResponse.setDescription("Login With User And Then Add Details");
				applicationResponse.setData(null);
				return applicationResponse;
			}
		
		return null;
		

			
		
		
	}

}
