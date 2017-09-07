package by.intexsoft.library.controller;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.io.IOException;
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

import by.intexsoft.library.model.Board;
import by.intexsoft.library.model.Book;
import by.intexsoft.library.service.BookService;

/**
 * This class tests methods of class BookController
 */
@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {
	@Mock
	private static BookService bookService;

	@InjectMocks
	private static BookController bookController = new BookController();
	private static List<Book> bookList;
	private static Book validBook;
	private static Board board;
	private static final Book EMPTY_BOOK = new Book();
	private static final Long ID = 4L;
	private static final Long ID_IS_NULL = null;

	/**
	 * This method prepare class to test
	 */
	@BeforeClass
	public static void init() {
		board = new Board();
		board.number = 4;
		validBook = new Book();
		validBook.author = "validAuthor";
		validBook.title = "title";
		validBook.board = board;
		Book book = new Book();
		book.author = "author";
		book.title = "title";
		Book book1 = new Book();
		book1.author = "author1";
		book1.title = "title";
		bookList = new ArrayList<>();
		bookList.add(validBook);
		bookList.add(book);
		bookList.add(book1);
	}

	/**
	 * This method tests add method from BookController class when book is valid
	 */
	@Test
	public void addPositiveTest() {
		when(bookService.save(validBook)).thenReturn(validBook);
		ResponseEntity<?> result = bookController.add(validBook);
		assertEquals(validBook, result.getBody());
		assertEquals(HttpStatus.CREATED, result.getStatusCode());
	}

	/**
	 * This method tests add method from BookController class when book is empty
	 */
	@Test
	public void addUserNegativeTest() {
		when(bookService.save(EMPTY_BOOK)).thenThrow(new RuntimeException());
		ResponseEntity<?> result = bookController.add(EMPTY_BOOK);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
	}

	/**
	 * This method tests getBoard method from BookController class
	 */
	@Test
	public void getBoardTest() {
		when(bookService.findById(ID)).thenReturn(validBook);
		ResponseEntity<?> result = bookController.getBoard(ID);
		assertEquals(board, result.getBody());
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}
	
	/**
	 * This method tests deleteAll method from BookController class
	 */
	@Test
	public void deleteAllNegativeTest() {
		doThrow(new RuntimeException()).when(bookService).deleteAll();
		ResponseEntity<?> response = bookController.deleteAll();
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	/**
	 * This method tests deleteById method from BookController class
	 */
	@Test
	public void deleteByIdNegativeTest() {
		doThrow(new RuntimeException()).when(bookService).deleteById(ID_IS_NULL);
		ResponseEntity<?> response = bookController.deleteById(ID_IS_NULL);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	/**
	 * This method tests add method from BookController class when book list is not
	 * empty
	 */
	@Test
	public void findByTitlePositiveTest() {
		when(bookService.findByTitle(validBook.title)).thenReturn(bookList);
		ResponseEntity<?> response = bookController.findByTitle(validBook.title);
		List<Book> resList = (List<Book>) response.getBody();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertArrayEquals(new Book[bookList.size()], new Book[resList.size()]);
	}

	/**
	 * This method tests add method from BookController class when book list is
	 * empty
	 */
	@Test
	public void findByTitleNegativeTest() {
		when(bookService.findByTitle(validBook.title)).thenReturn(new ArrayList<>());
		ResponseEntity<?> response = bookController.findByTitle(validBook.title);
		List<Book> resList = (List<Book>) response.getBody();
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	/**
	 * This method tests findAll method from BookController
	 */
	@Test
	public void findAllTest() {
		when(bookService.findAll()).thenReturn(bookList);
		ResponseEntity<?> response = bookController.findAll();
		List<Book> resList = (List<Book>) response.getBody();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertArrayEquals(new Book[bookList.size()], new Book[resList.size()]);
	}
}
