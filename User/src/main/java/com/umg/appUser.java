package com.umg;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.umg.dao.*;
import com.umg.entidad.UsersDTO;

import Exception.CustomErrorType;

@RestController
@RequestMapping("/api/user")
public class appUser {

	public static final Logger logger = LoggerFactory.getLogger(appUser.class);

	@Autowired
	private UserJpaRepository userJpaRepository;

	//lista de user 
	@GetMapping("/")
	public ResponseEntity<List<UsersDTO>> listAllUsers() {
		List<UsersDTO> users = userJpaRepository.findAll();
		if (users.isEmpty()) {
			return new ResponseEntity<List<UsersDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UsersDTO>>(users, HttpStatus.OK);
	}

	//crear un nuevo user
	@PostMapping
	public ResponseEntity<UsersDTO> crearUser(@RequestBody UsersDTO user) {
		logger.info("Creating User : {}", user);
		 if (userJpaRepository.findByName(user.getName()) != null) {
		 logger.error("Unable to create. A User with name {} already exist",
		 user.getName());
		 return new ResponseEntity<UsersDTO>(
		 new CustomErrorType(
		 "Unable to create new user. A User with name "
		 + user.getName() + " already exist."),
		HttpStatus.CONFLICT);
		 }
		 userJpaRepository.save(user);
		 return new ResponseEntity<UsersDTO>(user, HttpStatus.CREATED);
	}

	//actualizar un user
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<UsersDTO> updateUser(@PathVariable("id") Long id, @RequestBody UsersDTO user) {
		// fetch user based on id and set it to currentUser object of type UserDTO
		Optional<UsersDTO> User = userJpaRepository.findById(id);
		UsersDTO currentUser = User.get();
		// update currentUser object data with user object data
		currentUser.setName(user.getName());
		currentUser.setAddress(user.getAddress());
		currentUser.setEmail(user.getEmail());
		// save currentUser
		userJpaRepository.saveAndFlush(currentUser);
		// return ResponseEntity object
		return new ResponseEntity<UsersDTO>(currentUser, HttpStatus.OK);
	}

	//eliminar un user
	@DeleteMapping(value = "{id}")
	public ResponseEntity<UsersDTO> deleteUser(@PathVariable("id") Long id) {
		Optional<UsersDTO> user = userJpaRepository.findById(id);
		 if (user.isEmpty()) {
		 return new ResponseEntity<UsersDTO>(
		 new CustomErrorType("Unable to delete. User with id "
		 + id + " not found."), HttpStatus.NOT_FOUND);
		 }
		 userJpaRepository.deleteById(id);
		 return new ResponseEntity<UsersDTO>(
		 new CustomErrorType("Deleted User with id "
		 + id + "."), HttpStatus.NO_CONTENT);
	}

	//retorna una entidad user 
	@GetMapping("/{id}")
	public ResponseEntity<UsersDTO> getUserById(@PathVariable("id") final Long id) {
		Optional<UsersDTO> usuario = userJpaRepository.findById(id);
		UsersDTO user = usuario.get();
		if (user == null) {	
			  return new ResponseEntity<UsersDTO>( new CustomErrorType("User with id " + id
			 + " not found"), HttpStatus.NOT_FOUND);
			 
		}
		return new ResponseEntity<UsersDTO>(user, HttpStatus.OK);
	}
}
