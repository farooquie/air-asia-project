package com.ind.airasiaproject.serviceimplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ind.airasiaproject.dao.UserDao;
import com.ind.airasiaproject.dto.User;
import com.ind.airasiaproject.response.ApplicationResponse;
import com.ind.airasiaproject.service.UserService;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl  implements UserService{

	@Autowired
	private ApplicationResponse<User> applicationResponse;
	
	@Autowired
	private UserDao userDao;
 	
	@Autowired
	private HttpSession httpSession;
	
	@Override
	public ApplicationResponse<User> userRegisterService(User user) {
		
		User user2=userDao.userRegisterDao(user);
		
		if(user2!=null) {
			applicationResponse.setStatusCode(HttpStatus.ACCEPTED.value());
			applicationResponse.setMessage("User---Registered---SuccessFully");
			applicationResponse.setDescription("u can follow below data what is addedd inside table");
			applicationResponse.setData(user2);
			return applicationResponse;
		}else {
			applicationResponse.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
			applicationResponse.setMessage("User---NOT----Registered");
			applicationResponse.setDescription("check sql query or database");
			applicationResponse.setData(null);
			return applicationResponse;
		}
		
	}

	@Override
	public ApplicationResponse<User> fetchUserByEmailForLoginService(String email, String password) {
		
		User user = userDao.fetchUserByEmailForLoginDaos(email);
		
		if(user!=null) {
			
			if(user.getUserPassword().equals(password)) {
				httpSession.setAttribute("userSession", email);
				applicationResponse.setStatusCode(HttpStatus.FOUND.value());
				applicationResponse.setMessage("User---Logged--In---SuccessFully");
				applicationResponse.setDescription("User session is created ");
				applicationResponse.setData(user);
				return applicationResponse;
			}else {
				applicationResponse.setStatusCode(HttpStatus.ACCEPTED.value());
				applicationResponse.setMessage("User---Registered---SuccessFully");
				applicationResponse.setDescription("u can follow below data what is addedd inside table");
				applicationResponse.setData(user);
				return applicationResponse;
			}
			
		}else {
			applicationResponse.setStatusCode(HttpStatus.ACCEPTED.value());
			applicationResponse.setMessage("User---Registered---SuccessFully");
			applicationResponse.setDescription("u can follow below data what is addedd inside table");
			applicationResponse.setData(null);
			return applicationResponse;
		}
	}

}