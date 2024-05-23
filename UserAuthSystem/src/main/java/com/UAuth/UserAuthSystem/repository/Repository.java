package com.UAuth.UserAuthSystem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.UAuth.UserAuthSystem.model.User;

@org.springframework.stereotype.Repository
public interface Repository extends MongoRepository<User, String> {

	User findByUserName(String userName);
}
