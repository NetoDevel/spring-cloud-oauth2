package br.com.netodevel.authservice.web;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.netodevel.authservice.domain.User;
import br.com.netodevel.authservice.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Controller
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<?> auth(@RequestBody User user) throws ServletException {
		User userLogged = userService.findByEmailAndPassword(user.getEmail(), user.getPassword());
		if (userLogged == null) {
			return new ResponseEntity<String>("Não autorizado.", org.springframework.http.HttpStatus.UNAUTHORIZED);
		}
		String token = Jwts.builder().setSubject(user.getEmail()).signWith(SignatureAlgorithm.HS512, "chave").compact();
		return new ResponseEntity<String>(token, org.springframework.http.HttpStatus.OK);
	}
	
}
