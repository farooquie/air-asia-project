package com.ind.airasiaproject.dto;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class BookingDetails {

	@Id
	private String pnr;
	@CreationTimestamp
	private LocalDate bookingDate;
	private LocalDate journeyDate;
	private String customerName;
	private String cutomerEmail;
	private long customerPhone;
	private String passportNumber;
	private double totalPrice;
	@Column(name = "conveniencefee")
	final private double CONVENIENCE_FEE = 300;
	private String cancel = "Yes";

	@ManyToOne
	private User user;
	@OneToOne
	private Flight flight;

}
