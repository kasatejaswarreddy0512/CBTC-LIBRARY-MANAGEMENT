package com.jsp.LibraryApp.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.LibraryApp.Repo.BookRepo;
import com.jsp.LibraryApp.Repo.BorrowRepo;
import com.jsp.LibraryApp.Repo.UserRepo;
import com.jsp.LibraryApp.entity.Author;
import com.jsp.LibraryApp.entity.Book;
import com.jsp.LibraryApp.entity.Borrow;
import com.jsp.LibraryApp.entity.User;
import com.jsp.LibraryApp.helper.ResponseStructure;

@RestController
@RequestMapping("/api/borrow")
public class BorrowController {
	
	@Autowired
	BorrowRepo borrowRepo;
	
	@Autowired
	BookRepo bookRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@PostMapping("saveBorrow")
	public ResponseStructure<Borrow> saveBorrow(@RequestBody Borrow borrow){
		
		borrow.setBook(bookRepo.findById(borrow.getBook().getBookid())
				.orElseThrow(() -> new RuntimeException("Book not found")));
		
		borrow.setUser(userRepo.findById(borrow.getUser().getUserid())
				.orElseThrow(() -> new RuntimeException("User not found")));
		
		borrowRepo.save(borrow);
		ResponseStructure<Borrow> rs= new ResponseStructure<Borrow>();
		rs.setStatusCode(HttpStatus.CREATED.value());
		rs.setData(borrow);
		rs.setMessage("Borrow Details Save Successfully........!");
		return rs;
	} 
	
	  @GetMapping("getAllBorrow")
	  public ResponseStructure<List<Borrow>> getAllBorrow(){
		  List<Borrow> borrows= borrowRepo.findAll();
		  ResponseStructure<List<Borrow>> rs= new ResponseStructure<List<Borrow>>();
		  
		  if(!borrows.isEmpty()) {
			  rs.setStatusCode(HttpStatus.FOUND.value());
			  rs.setData(borrows);
			  rs.setMessage("Borrow Details found....!");
		  }
		  else {
			  rs.setStatusCode(HttpStatus.NOT_FOUND.value());
			  rs.setMessage("Borrow Details Not Found....!");
		  }
		  return rs;
	  }
	
	@GetMapping("getBorrowById")
	public  ResponseStructure<Borrow> getBorrowById(@RequestParam("borrowid") int borrowid){
		Optional<Borrow> o= borrowRepo.findById(borrowid);
		
		if(o.isPresent()) {
			Borrow borrow=o.get();
			ResponseStructure<Borrow> rs= new ResponseStructure<Borrow>();
			rs.setStatusCode(HttpStatus.FOUND.value());
			rs.setData(borrow);
			rs.setMessage("Borrow Details are Found....!");
			return rs;
		}
		else{
			ResponseStructure<Borrow> rs= new ResponseStructure<Borrow>();
			rs.setStatusCode(HttpStatus.NOT_FOUND.value());
			rs.setMessage("Borrow Details are NOT Found....!");
			return rs;
		}
	}
	
	@PutMapping("updateBorrow")
	public ResponseStructure<Borrow> updateBorrow(@RequestParam("borrowid")int borrowid, @RequestParam("bookid")int bookid,@RequestParam("userid")int userid, @RequestBody Borrow borrow){
		Optional<Borrow> o=borrowRepo.findById(borrowid);
		
		if(o.isPresent()) {
			Borrow updateBorrow=o.get();
			
			Optional<Book> b=bookRepo.findById(bookid);
			if(b.isPresent()) {
				Book book=b.get();
				updateBorrow.setBook(book);
			}
			else {
				ResponseStructure<Author> rs= new ResponseStructure<Author>();
				rs.setStatusCode(HttpStatus.NOT_FOUND.value());
				rs.setMessage("Book Not Found....!");
			}
			
			Optional<User> u= userRepo.findById(userid);
			if(u.isPresent()) {
				User user=u.get();
				updateBorrow.setUser(user);
			}
			else {
				ResponseStructure<Author> rs= new ResponseStructure<Author>();
				rs.setStatusCode(HttpStatus.NOT_FOUND.value());
				rs.setMessage("Author Not Found....!");
			}
			updateBorrow.setBorrowDate(borrow.getBorrowDate());
			updateBorrow.setReturnDate(borrow.getReturnDate());
			
			borrowRepo.save(updateBorrow);
				
			ResponseStructure<Borrow> rs= new ResponseStructure<Borrow>();
			rs.setStatusCode(HttpStatus.CREATED.value());
			rs.setData(updateBorrow);
			rs.setMessage("Update Successfully......!");
			return rs;
		}
		else {
				ResponseStructure<Borrow> rs= new ResponseStructure<>();
				rs.setStatusCode(HttpStatus.NOT_FOUND.value());
				rs.setMessage("Borrow Not Found....!");
				return rs;
		}
	}
	
	@DeleteMapping("deleteBorrow")
	public ResponseStructure<Borrow> deleteBorrow(@RequestParam("borrowid") int borrowid){
		borrowRepo.deleteById(borrowid);
		
		ResponseStructure<Borrow> rs= new ResponseStructure<Borrow>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Borrow Details Delete Successfully.......!");
		return rs;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
