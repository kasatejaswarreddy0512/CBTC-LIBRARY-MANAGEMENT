package com.jsp.LibraryApp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.LibraryApp.Repo.PublisherRepo;
import com.jsp.LibraryApp.entity.Publisher;
import com.jsp.LibraryApp.helper.ResponseStructure;

@RestController
@RequestMapping("api/publisher")
public class PublisherController {
	
	@Autowired
	PublisherRepo publisherRepo;
	
	@PostMapping("savePublisher")
	public ResponseStructure<Publisher> savePublisher(@RequestBody Publisher publisher){
		publisherRepo.save(publisher);
		ResponseStructure<Publisher> rs =new ResponseStructure<Publisher>();
		rs.setStatusCode(HttpStatus.CREATED.value());
		rs.setData(publisher);
		rs.setMessage("Publisher Created Successfully....!");
		return rs;
	}
	
	@GetMapping("getAllPublishers")
	public ResponseStructure<List<Publisher>> getAllPublisher(){
		List<Publisher> publisher=publisherRepo.findAll();
		ResponseStructure<List<Publisher>> rs= new ResponseStructure<>();
		if(!publisher.isEmpty()) {
			rs.setStatusCode(HttpStatus.FOUND.value());
			rs.setData(publisher);
			rs.setMessage("Publisher Details Found....!");
		}
		else {
			rs.setStatusCode(HttpStatus.NOT_FOUND.value());
			rs.setMessage("Publisher Details Not Found....!");
		}
		return rs;
		
	}
	
	@GetMapping("getPublisherById")
	public ResponseStructure<Publisher> getPublisherByid(@RequestParam("publisherid") int publisherid){
		Optional<Publisher> o=publisherRepo.findById(publisherid);
		if(o.isPresent()) {
			Publisher publisher=o.get();
			ResponseStructure<Publisher> rs= new ResponseStructure<Publisher>();
			rs.setStatusCode(HttpStatus.FOUND.value());
			rs.setData(publisher);
			rs.setMessage("User Details Found....!");
			return rs;
		}
		else {
			ResponseStructure<Publisher> rs= new ResponseStructure<Publisher>();
			rs.setStatusCode(HttpStatus.NOT_FOUND.value());
			rs.setMessage("Publisher Details Not Found.....!");
			return rs;
		}
	}
	
	
	@PutMapping("upadtePublisher")
	public ResponseStructure<Publisher> updatePublisher(@RequestParam("publisherid") int publisherid, @RequestBody Publisher publisher){
		Optional<Publisher> o=publisherRepo.findById(publisherid);
		if(o.isPresent()) {
			Publisher updatePublisher=o.get();
			updatePublisher.setPublisherName(publisher.getPublisherName());
			updatePublisher.setEmail(publisher.getEmail());
			publisherRepo.save(updatePublisher);
			
			ResponseStructure<Publisher> rs= new ResponseStructure<Publisher>();
			rs.setStatusCode(HttpStatus.CREATED.value());
			rs.setData(updatePublisher);
			rs.setMessage("Publisher Update Successfully....!");
			return rs;
		}
		else {
			ResponseStructure<Publisher> rs= new ResponseStructure<Publisher>();
			rs.setStatusCode(HttpStatus.NOT_FOUND.value());
			rs.setMessage("Publisher Details Not Found....!");
			return rs;
			
		}
	}
	
	
	@DeleteMapping("deletePublisher")
	public ResponseStructure<Publisher> deletePublisher(@RequestParam("publisherid")int publisherid){
		publisherRepo.deleteById(publisherid);
		
		ResponseStructure<Publisher> rs= new ResponseStructure<Publisher>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Delete Publisher Successfully....!");
		return rs;
	}	
}
