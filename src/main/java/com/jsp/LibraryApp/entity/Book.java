package com.jsp.LibraryApp.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookid;
	
	private String bookName;
	
	@ManyToOne
	@JoinColumn(name="authorid")
	private Author author;
	
	@ManyToOne
	@JoinColumn(name="publisherid")
	private Publisher publisher;
	
	@OneToMany(mappedBy = "book")
	@JsonIgnore
	private Set<Borrow> borrow;
	

	public int getBookid() {
		return bookid;
	}

	public void setBookid(int bookid) {
		this.bookid = bookid;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public Set<Borrow> getBorrow() {
		return borrow;
	}

	public void setBorrow(Set<Borrow> borrow) {
		this.borrow = borrow;
	}	
}
