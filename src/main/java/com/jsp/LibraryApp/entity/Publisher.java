package com.jsp.LibraryApp.entity;





import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Publisher {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int publisherid;
	
	private String publisherName;
	private String email;
	
	
	@OneToMany(mappedBy = "publisher")
	@JsonIgnore
	private Set<Book> book;
	

	public int getPublisherid() {
		return publisherid; 
	}

	public void setPublisherid(int publisherid) {
		this.publisherid = publisherid;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Book> getBook() {
		return book;
	}

	public void setBook(Set<Book> book) {
		this.book = book;
	}
	
	
	
	
	
}
