package com.app.controller;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.Service.IUserService;
import com.app.pojos.CustomerRoleType;
import com.app.pojos.User;

@RestController
@CrossOrigin
public class UserController {

	@Autowired
	IUserService userService;

	@PostConstruct
	public void init() {
		System.out.println("In User controller");
	}
	
	/*@PostMapping("/login")
	public ResponseEntity<?> validateUser(@RequestBody User u,HttpSession hs)
	{
		System.out.println("in validate user");
		try {
			u=userService.validateUser(u.getEmail(),u.getPassword());
			if (u.getRole().equals(CustomerRoleType.ADMIN))
				hs.setAttribute("adminDetails",u);
			else if (u.getRole().equals(CustomerRoleType.CUSTOMER))
				hs.setAttribute("userDetails",u);
			System.out.println("validated");
			return new ResponseEntity<User>(u,HttpStatus.OK);
				
		}catch (Exception e) {
			return new ResponseEntity<String>("User Invalid",HttpStatus.OK);
		}
	}*/
	
	@PostMapping("/login")
	public ResponseEntity<?> validateUser(@RequestBody User u)
	{
		System.out.println("in validate user");
		try {
			u=userService.validateUser(u.getEmail(),u.getPassword());
			return new ResponseEntity<User>(u,HttpStatus.OK);
				
		}catch (Exception e) {
			return new ResponseEntity<String>("User Invalid",HttpStatus.OK);
		}
	}
	
	@PostMapping("/getUser")
	public ResponseEntity<?> getUser(@RequestBody String email)
	{
		System.out.println("in get user"+ email);
		try {
			User u=userService.getUser(email);
			return new ResponseEntity<User>(u,HttpStatus.OK);
		}catch (Exception e) {
		return new ResponseEntity<String>("User not found",HttpStatus.OK);
		}
		
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User u)
	{
		System.out.println("in register user" + u);
		try {
			userService.registerUser(u);
			return new ResponseEntity<String>("Registered successfully",HttpStatus.OK);
			
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Not registered",HttpStatus.OK);
		}
	}
	
	@PutMapping("/changePassword")
	public ResponseEntity<?> changePassword(@RequestBody User u)
	{
		System.out.println("In change password");
		try {
			userService.updatePassword(u.getEmail(),u.getPassword());
			return new ResponseEntity<String>("Password updated successfully",HttpStatus.OK);
		}catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("updation failed",HttpStatus.OK);
		}
	}
	
	@PutMapping("/updateUser")
	public ResponseEntity<?> updateUser(@RequestBody User u)
	{
		System.out.println("in update user");
		try {
			userService.updateUser(u);
			return new ResponseEntity<String>("User updated successfully",HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("User cannot be updated",HttpStatus.OK);
		}
	}

}
