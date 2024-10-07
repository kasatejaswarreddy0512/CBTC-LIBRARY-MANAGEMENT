package com.jsp.LibraryApp.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.LibraryApp.entity.Author;

public interface AuthorRepo extends JpaRepository<Author, Integer>{

}
