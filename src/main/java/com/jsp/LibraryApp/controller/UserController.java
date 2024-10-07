package com.jsp.LibraryApp.controller;

import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.LibraryApp.Repo.UserRepo;
import com.jsp.LibraryApp.entity.User;
import com.jsp.LibraryApp.helper.ResponseStructure;

@RestController
@RequestMapping("/api/library")
public class UserController {
	
	@Autowired
	UserRepo userRepo;
	
	@PostMapping("saveUser")
	public ResponseStructure<User> saveUser(@RequestBody User user){
		userRepo.save(user);
		
		ResponseStructure<User> rs= new ResponseStructure<User>();
		rs.setStatusCode(HttpStatus.CREATED.value());
		rs.setData(user);
		rs.setMessage("User Successfully Created....!");
		return rs;
	}
	
	@GetMapping("getAllUsers")
	public ResponseStructure<List<User>> getAllUsers(){
		List<User> user=userRepo.findAll();
		ResponseStructure<List<User>> rs= new ResponseStructure<>();
		
		if(!user.isEmpty()) {
			rs.setStatusCode(HttpStatus.FOUND.value());
			rs.setData(user);
			rs.setMessage("User Found Successfully....!");
		}
		else {
			rs.setStatusCode(HttpStatus.NOT_FOUND.value());
			rs.setMessage("User Details are Not Found....!");
		}
		return rs;
	}
	
	
	@GetMapping("getUserById")
	public ResponseStructure<User> getUserById(@RequestParam("id") int id){
		Optional<User> o =userRepo.findById(id);
		
		if(o.isPresent()) {
			User user=o.get();
			ResponseStructure<User> rs= new ResponseStructure<User>();
			rs.setStatusCode(HttpStatus.FOUND.value());
			rs.setData(user);
			rs.setMessage("User details found successfully......!");
			return rs;
		}
		else {
			ResponseStructure<User> rs= new ResponseStructure<User>();
			rs.setStatusCode(HttpStatus.NOT_FOUND.value());
			rs.setMessage("User Details Not Found....!");
			return rs;
		}
	}
	
	@GetMapping("userByName")
	public ResponseStructure<User> userByName(@RequestParam("userName") String userName){
		Optional<User> o= userRepo.findByUserName(userName);
		
		if(o.isPresent()) {
			User user=o.get();
			ResponseStructure<User> rs= new ResponseStructure<>();
			rs.setStatusCode(HttpStatus.FOUND.value());
			rs.setData(user);
			rs.setMessage("User Details are Found......!");
			return rs;
		}
		else {
			ResponseStructure<User> rs= new ResponseStructure<>();
			rs.setStatusCode(HttpStatus.NOT_FOUND.value());
			rs.setMessage("User Details are Not Found......!");
			return rs;
			
		}
	}
	
	@PutMapping("updateUser")
	public ResponseStructure<User> updateUser(@RequestParam("id") int id, @RequestBody User user){
		Optional<User> o= userRepo.findById(id);
		
		if(o.isPresent()) {
			User updateUser=o.get();
			updateUser.setUserName(user.getUserName());
			updateUser.setEmail(user.getEmail());
			updateUser.setPhone(user.getPhone());
			
			userRepo.save(updateUser);
			ResponseStructure<User> rs= new ResponseStructure<User>();
			rs.setStatusCode(HttpStatus.CREATED.value());
			rs.setData(updateUser);
			rs.setMessage("User Update Successfully....!");
			return rs;
		}
		else {
			ResponseStructure<User> rs= new ResponseStructure<User>();
			rs.setStatusCode(HttpStatus.NOT_FOUND.value());
			rs.setMessage("User Details Not Found.....!");
			return rs;
		}
	}
	
	@PutMapping("updateByName")
	public ResponseStructure<User> updateByName(@RequestParam("userName") String userName, @RequestBody User user){
		Optional<User> o=userRepo.findByUserName(userName);
		if(o.isPresent()) {
			User updateUser=o.get();
			updateUser.setUserName(user.getUserName());
			updateUser.setEmail(user.getEmail());
			updateUser.setPhone(user.getPhone());
			
			userRepo.save(updateUser);
			ResponseStructure<User> rs = new ResponseStructure<User>();
			rs.setStatusCode(HttpStatus.CREATED.value());
			rs.setData(updateUser);
			rs.setMessage("User Update Successfully....!");
			return rs;
		}
		else {
			ResponseStructure<User> rs= new ResponseStructure<User>();
			rs.setStatusCode(HttpStatus.NOT_FOUND.value());
			rs.setMessage("User Details are Not Found....!");
			return rs;
		}
		
	}
	
	@DeleteMapping("deleteById")
	public ResponseStructure<User> deleteById(@RequestParam("id") int id){
		userRepo.deleteById(id);
		ResponseStructure<User> rs= new ResponseStructure<User>();
		rs.setStatusCode(HttpStatus.ACCEPTED.value());
		rs.setMessage("User Delete Successfully.....!");
		return rs;
	}

}
