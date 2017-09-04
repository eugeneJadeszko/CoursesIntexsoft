package by.intexsoft.library.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import by.intexsoft.library.model.User;
import by.intexsoft.library.service.UserService;
import ch.qos.logback.classic.Logger;

/**
 * Controller for {@link User}
 */
@RestController
@RequestMapping("/user")
public class UserController {

	private static Logger logger = (Logger) LoggerFactory.getLogger(UserController.class.getName());

	@Autowired
	UserService userService;

	/**
	 * This method adds new user into database
	 * 
	 * @param entity
	 *            - object type {@link User}
	 * @return ResponseEntity<>
	 */
	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public ResponseEntity<?> addUser(@RequestBody User entity) {
		logger.info("Creation of a new user with the first name: " + entity.name);
		try {
			return new ResponseEntity<User>(userService.save(entity), HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Error while saving new user with first name: " + entity.name);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * This method deletes user from database
	 * 
	 * @param id
	 *            - user id
	 * @return HttpStatus
	 */
	@RequestMapping("/del/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
		logger.info("Delete user with id= " + id);
		try {
			userService.deleteById(id);
		} catch (Exception e) {
			logger.error("user with id= " + id + " is not exist");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * This method getting all users
	 * 
	 * @return ResponseEntity<List<User>>
	 */
	@RequestMapping("/all")
	public ResponseEntity<?> findAll() {
		logger.info("Getting all users");
		return new ResponseEntity<List<User>>(userService.findAll(), HttpStatus.OK);
	}
}
