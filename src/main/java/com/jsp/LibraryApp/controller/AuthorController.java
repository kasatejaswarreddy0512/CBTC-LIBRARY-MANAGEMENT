package com.jsp.LibraryApp.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.LibraryApp.Repo.AuthorRepo;
import com.jsp.LibraryApp.entity.Author;
import com.jsp.LibraryApp.helper.ResponseStructure;

@RestController
@RequestMapping("/api/author")
public class AuthorController {
	
	@Autowired
	AuthorRepo authorRepo;
	
	
	@PostMapping("saveAuthor")
	public ResponseStructure<Author> saveAuthor(@RequestBody Author author){
		
		authorRepo.save(author);
		ResponseStructure<Author> rs = new ResponseStructure<Author>();
		rs.setStatusCode(HttpStatus.CREATED.value());
		rs.setData(author);
		rs.setMessage("Author created Successfully....!");
		return rs;
	}
	
	@GetMapping("getAllAuthors")
	public ResponseStructure<List<Author>> getAllAuthors(){
		List<Author> author=authorRepo.findAll();
		ResponseStructure<List<Author>> rs= new ResponseStructure<>();
		
		if(!author.isEmpty()) {
			rs.setStatusCode(HttpStatus.FOUND.value());
			rs.setData(author);
			rs.setMessage("Author Details Found....!");
		}
		else {
			rs.setStatusCode(HttpStatus.NOT_FOUND.value());
			rs.setMessage("Author Deatial Not Found......!");
		}
		return rs;
	}
	
	@GetMapping("getAuthorById")
	public ResponseStructure<Author> getAuthorById(@RequestParam("authorid") int authorid){
		Optional<Author> o=authorRepo.findById(authorid);
		
		if(o.isPresent()) {
			Author author=o.get();
			ResponseStructure<Author> rs= new ResponseStructure<Author>();
			rs.setStatusCode(HttpStatus.FOUND.value());
			rs.setData(author);
			rs.setMessage("Author deatils Found.....!");
			return rs;
		}
		else {
			ResponseStructure<Author> rs =new ResponseStructure<Author>();
			rs.setStatusCode(HttpStatus.NOT_FOUND.value());
			rs.setMessage("Author Datails Not Found.....!");
			return rs;
		}
		
	}
	
	
	@PutMapping("updateAuthor")
	public ResponseStructure<Author> updateAuthor(@RequestParam("authorid") int authorid, @RequestBody Author author ){
		Optional<Author> o=authorRepo.findById(authorid);
		
		if(o.isPresent()) {
			Author updateAuthor=o.get();
			updateAuthor.setAuthorName(author.getAuthorName());
			updateAuthor.setEmail(author.getEmail());
			
			authorRepo.save(updateAuthor);
			
			ResponseStructure<Author> rs= new ResponseStructure<Author>();
			rs.setStatusCode(HttpStatus.CREATED.value());
			rs.setData(updateAuthor);
			rs.setMessage("Author Update Successfully.....!");
			return rs;
		}
		else {
			ResponseStructure<Author> rs= new ResponseStructure<Author>();
			rs.setStatusCode(HttpStatus.NOT_FOUND.value());
			rs.setMessage("Author Details Not Found.....!");
			return rs;
		}
	}
	
	@DeleteMapping("deleteAuthor")
	public ResponseStructure<Author> deleteAuthor(@RequestParam("authorid") int authorid){
		authorRepo.deleteById(authorid);
		ResponseStructure<Author> rs= new ResponseStructure<Author>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Author Delete Successfully.....!");
		return rs;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
