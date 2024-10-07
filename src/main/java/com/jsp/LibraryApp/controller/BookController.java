package com.jsp.LibraryApp.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jsp.LibraryApp.Repo.AuthorRepo;
import com.jsp.LibraryApp.Repo.BookRepo;
import com.jsp.LibraryApp.Repo.PublisherRepo;
import com.jsp.LibraryApp.entity.Author;
import com.jsp.LibraryApp.entity.Book;
import com.jsp.LibraryApp.entity.Publisher;
import com.jsp.LibraryApp.helper.ResponseStructure;




@RestController
@RequestMapping("/api/book")
public class BookController {
	
	@Autowired
	BookRepo bookRepo;
	
	@Autowired
	AuthorRepo authorRepo;
	
	@Autowired
	PublisherRepo publisherRepo;
	
	
	@PostMapping("saveBook")
	public ResponseStructure<Book> saveBook(@RequestBody Book book){
		
		 book.setAuthor(authorRepo.findById(book.getAuthor().getAuthorid())
	                .orElseThrow(() -> new RuntimeException("Author not found")));

	        
	     book.setPublisher(publisherRepo.findById(book.getPublisher().getPublisherid())
	                .orElseThrow(() -> new RuntimeException("Publisher not found")));
		bookRepo.save(book);
		ResponseStructure<Book> rs= new ResponseStructure<Book>();
		rs.setStatusCode(HttpStatus.CREATED.value());
		rs.setData(book);
		rs.setMessage("Book Data Saved Successfully....!");
		return rs;
	}
	
	@GetMapping("getAllBooks")
	public ResponseStructure<List<Book>> getAllBooks(){
		List<Book> book=bookRepo.findAll();
		ResponseStructure<List<Book>> rs= new ResponseStructure<List<Book>>();
		
		if(!book.isEmpty()) {
			rs.setStatusCode(HttpStatus.FOUND.value());
			rs.setData(book);
			rs.setMessage("All Books Found....!");
		}
		else {
			rs.setStatusCode(HttpStatus.NOT_FOUND.value());
			rs.setMessage("Book details are not Found......!");
		}
		return rs;
	}
	
	@GetMapping("/getBookById")
	public ResponseStructure<Book> getBookById(@RequestParam("bookid") int bookid){
		Optional<Book> o= bookRepo.findById(bookid);
		
		if(o.isPresent()) {
			Book book=o.get();
			ResponseStructure<Book> rs= new ResponseStructure<Book>();
			rs.setStatusCode(HttpStatus.FOUND.value());
			rs.setData(book);
			rs.setMessage("Book Details Found.....!");
			return rs;
		}
		else {
			ResponseStructure<Book> rs= new ResponseStructure<Book>();
			rs.setStatusCode(HttpStatus.NOT_FOUND.value());
			rs.setMessage("Book Details not Found.....!");
			return rs;
		}
		
	}
	
	
	@PutMapping("updateBook")
	public ResponseStructure<Book> updateBook(@RequestParam("bookid") int bookid,@RequestParam("authorid") int authorid,@RequestParam("publisherid") int publisherid ,@RequestBody Book book){
		Optional<Book> o=bookRepo.findById(bookid);
		
		if(o.isPresent()) {
			Book updateBook=o.get();
			updateBook.setBookName(book.getBookName());
			
			Optional<Author> a= authorRepo.findById(authorid);
			if(a.isPresent()) {
				Author author=a.get();
				updateBook.setAuthor(author);
			}else {
				ResponseStructure<Author> rs= new ResponseStructure<Author>();
				rs.setStatusCode(HttpStatus.NOT_FOUND.value());
				rs.setMessage("Author Not Found....!");
			}
			
			Optional<Publisher> p=publisherRepo.findById(publisherid);
			if(p.isPresent()) {
				Publisher publisher=p.get();
				updateBook.setPublisher(publisher);
			}else {
				ResponseStructure<Publisher> rs= new ResponseStructure<>();
				rs.setStatusCode(HttpStatus.NOT_FOUND.value());
				rs.setMessage("Publisher Not Found....!");
			}
			
			bookRepo.save(updateBook);
			ResponseStructure<Book> rs= new ResponseStructure<Book>();
			rs.setStatusCode(HttpStatus.CREATED.value());
			rs.setData(updateBook);
			rs.setMessage("Book Update Successfully......!");
			return rs;
		}
		else {
				ResponseStructure<Book> rs= new ResponseStructure<>();
				rs.setStatusCode(HttpStatus.NOT_FOUND.value());
				rs.setMessage("Book Not Found....!");
				return rs;
		}
	}
	
	
	@DeleteMapping("/deleteBook")
	public ResponseStructure<Book> deleteBook(@RequestParam("bookid") int bookid){
		bookRepo.deleteById(bookid);
		ResponseStructure<Book> rs = new ResponseStructure<Book>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Book Data Deleted Sucessfully.....!");
		return rs;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}      
