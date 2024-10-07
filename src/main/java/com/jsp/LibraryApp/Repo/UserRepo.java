package com.jsp.LibraryApp.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.LibraryApp.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	
	@Query
	Optional<User> findByUserName(String userName);

}
