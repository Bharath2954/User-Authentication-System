package com.UAuth.UserAuthSystem.service;

import com.UAuth.UserAuthSystem.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.UAuth.UserAuthSystem.model.User;
import com.UAuth.UserAuthSystem.repository.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
@RequiredArgsConstructor
public class UserService {

	private final Repository repository;

	private final JwtService jwtService;

	private final PasswordEncoder passwordEncoder;
	
	public User getUser(String userName)
	{
		return repository.findByUserName(userName);
	}

	public List<User> get()
	{
        return repository.findAll(Sort.by("userName"));
	}

	public String updateUser(String userName, User user) {
		if (user == null) {
			return "Invalid user object";
		}
		User oldUser = getUser(userName);
		if (oldUser == null) {
			return "No User Found";
		}
		oldUser.setUserName(user.getUsername());
		oldUser.setFirstname(user.getFirstname());
		oldUser.setLastname(user.getLastname());
		oldUser.setEmail(user.getEmail());
		oldUser.setRole(user.getRole());
		try {
			repository.save(oldUser);
			return "Update Successful";
		} catch (Exception e) {
			return "Failed to update user: " + e.getMessage();
		}
	}

	
	public String deleteUser(String userName)
	{
		if(userName==null)
		{
			return "Username is Empty";
		}
		User oldUser = getUser(userName);
		if(oldUser==null)
		{
			return "No User Found";
		}
		try {
			repository.delete(oldUser);
			return "Deletion Successful";
		} catch (Exception e) {
			return "Failed to delete user: " + e.getMessage();
		}
	}

	public String extractToken(String token) {
		try {
			return token.substring(16, token.length()-4);
		} catch (Exception e) {
			return null;
		}
	}

	public String expireToken(String token)
	{
		token = extractToken(token);
		if(token==null)
		{
			return "Invalid token";
		}
		return jwtService.expireToken(token);
	}
}
