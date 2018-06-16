package br.com.netodevel.userservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.netodevel.userservice.domain.ErrorMessage;
import br.com.netodevel.userservice.domain.User;
import br.com.netodevel.userservice.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/users")
	public ResponseEntity<?> create(@RequestBody User user) {
		if(userAlreadyExits(user.getEmail())) {
			return new ResponseEntity<ErrorMessage>(new ErrorMessage("E-mail já existente"), HttpStatus.CONFLICT);
		}
		
		User userSaved = userService.save(user);
		return new ResponseEntity<User>(userSaved, HttpStatus.CREATED);
	}

	private boolean userAlreadyExits(String email) {
		return userService.findByEmail(email) != null ? true : false;
	}
	
}
