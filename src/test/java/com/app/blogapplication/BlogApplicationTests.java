package com.app.blogapplication;

import com.app.blogapplication.Services.CustomUserDetailService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

@SpringBootTest
class BlogApplicationTests {

	@Autowired
	private CustomUserDetailService customUserDetailService;

	@Test
	void contextLoads() {
	}

	@Test
	public void loadUserbyUsername(){
		String email = "john@gmail.com";
		UserDetails userDetails = customUserDetailService.loadUserByUsername(email);
		System.out.println(userDetails);
	}
}
