package com.UAuth.UserAuthSystem.controller;

import com.UAuth.UserAuthSystem.service.UserService;
import com.UAuth.UserAuthSystem.model.User;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class Controller {

	private final UserService service;
	
	@GetMapping("/get/{id}")
	public User getUser(@PathVariable String id)
	{
		return service.getUser(id);
	}

	@GetMapping("/get")
	public List<User> getUsers()
	{
		return service.get();
	}
	
	@PutMapping("/update/{id}")
	public String updateUser(@PathVariable String id, @RequestBody User user)
	{
		System.out.println(id);
		return service.updateUser(id, user);
	}
	
	@DeleteMapping("/remove/{id}")
	public String deleteUser(@PathVariable String id)
	{
		return service.deleteUser(id);
	}

	@PutMapping("/logout")
	public String logoutUser(@RequestBody String token)
	{
		return service.expireToken(token);
	}
}
