package br.com.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.model.User;

@RestController
@RequestMapping("/")
public class UserController {

	@GetMapping("users")
	public User getUsers() {
		User user = new User();
		return user;
	}
}
