package com.ind.airasiaproject.serviceimplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ind.airasiaproject.dao.AdminDao;
import com.ind.airasiaproject.dto.Admin;
import com.ind.airasiaproject.response.ApplicationResponse;
import com.ind.airasiaproject.service.AdminService;

import jakarta.servlet.http.HttpSession;

@Service
public class AdminServiceImplementation implements AdminService {

	@Autowired
	private AdminDao adminDao;

	@Autowired
	private ApplicationResponse<Admin> applicationResponse;

	@Autowired
	private HttpSession httpSession;

	@Override
	public ApplicationResponse<Admin> fetchAdminByEmailForLoginService(String email, String password) {
		Admin admin = adminDao.fetchAdminByEmailForLoginDao(email);
		if (admin != null) {

			if (admin.getAdminPassword().equals(password)) {
				httpSession.setAttribute("admin", admin.getAdminEmail());
				applicationResponse.setStatusCode(HttpStatus.ACCEPTED.value());
				applicationResponse.setMessage("Login Successfull");
				applicationResponse.setDescription("Admin Logged In And Session Is Active");
				admin.setAdminPassword("************");
				applicationResponse.setData(admin);
				return applicationResponse;
				
			} else {
				applicationResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
				applicationResponse.setMessage("Password Is Incorrect");
				applicationResponse.setDescription("Enter Correct Password");
				applicationResponse.setData(null);
				return applicationResponse;

			}
		} else {

			applicationResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
			applicationResponse.setMessage("Email Is Incorrect");
			applicationResponse.setDescription("Pass Correct Id And Password");
			applicationResponse.setData(null);
		}
		return null;
	}

}
