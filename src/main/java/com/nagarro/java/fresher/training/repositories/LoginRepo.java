package com.nagarro.java.fresher.training.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.nagarro.java.fresher.training.modals.User;

public interface LoginRepo extends CrudRepository<User, Integer> {
	List<User> findByUserIDAndPassword(int userID, String password);
}
