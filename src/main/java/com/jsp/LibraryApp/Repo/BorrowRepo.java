package com.jsp.LibraryApp.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.LibraryApp.entity.Borrow;

public interface BorrowRepo extends JpaRepository<Borrow, Integer>{

}
