package com.myblog;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.myblog.service.UserService;


public class App {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
		UserService userService = context.getBean(UserService.class);

		System.out.println(userService.getAllUsers());
		
		String username = null;
		System.out.println(userService.getUsersByName("Derek"));
	
		context.close();	
		
	}

}
