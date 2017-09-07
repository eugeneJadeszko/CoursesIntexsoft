package by.intexsoft.library.controller;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import by.intexsoft.library.model.User;
import by.intexsoft.library.service.UserService;

/**
 * This class tests methods of class UserController
 */
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
	@Mock
	private static UserService userService;

	@InjectMocks
	private static UserController userController = new UserController();
	private static final Long ID_IS_NULL = null;
	private static List<User> userList;
	private static User validUser;
	private static User emptyUser;

	/**
	 * This method prepare class to test
	 */
	@BeforeClass
	public static void init() {
		validUser = new User();
		validUser.name = "validUser";
		User user = new User();
		user.name = "user";
		User user1 = new User();
		user.name = "user1";
		userList = new ArrayList<>();
		userList.add(validUser);
		userList.add(user);
		userList.add(user1);
		emptyUser = new User();
	}

	/**
	 * This method tests addUser method from UserController class when user is valid
	 */
	@Test
	public void addUserPositiveTest() {
		when(userService.save(validUser)).thenReturn(validUser);
		ResponseEntity<?> result = userController.addUser(validUser);
		assertEquals(validUser, result.getBody());
		assertEquals(HttpStatus.CREATED, result.getStatusCode());
	}

	/**
	 * This method tests addUser method from UserController class when user is empty
	 */
	@Test
	public void addUserNegativeTest() {
		when(userService.save(emptyUser)).thenThrow(new RuntimeException());
		ResponseEntity<?> result = userController.addUser(emptyUser);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
	}

	/**
	 * This method tests deleteById method from UserController class
	 */
	@Test
	public void deleteByIdNegativeTest() {
		doThrow(new RuntimeException()).when(userService).deleteById(ID_IS_NULL);
		ResponseEntity<?> response = userController.deleteById(ID_IS_NULL);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	/**
	 * This method tests findAll method from UserController
	 */
	@Test
	public void findAllTest() {
		when(userService.findAll()).thenReturn(userList);
		ResponseEntity<?> response = userController.findAll();
		List<User> resUserList = (List<User>) response.getBody();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertArrayEquals(new User[userList.size()], new User[resUserList.size()]);
	}
}
