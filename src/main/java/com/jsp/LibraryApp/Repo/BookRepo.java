package com.jsp.LibraryApp.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.LibraryApp.entity.Book;

public interface BookRepo extends JpaRepository<Book, Integer>{

}
