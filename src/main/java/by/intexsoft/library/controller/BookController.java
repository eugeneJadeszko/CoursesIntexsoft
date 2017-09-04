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

import by.intexsoft.library.model.Board;
import by.intexsoft.library.model.Book;
import by.intexsoft.library.service.BookService;
import ch.qos.logback.classic.Logger;

/**
 * Controller for {@link Book}
 */
@RestController
@RequestMapping("/book")
public class BookController {

	private static Logger logger = (Logger) LoggerFactory.getLogger(BookController.class.getName());

	@Autowired
	BookService bookService;

	/**
	 * This method adds new book into database
	 * 
	 * @param entity
	 *            - object type {@link Book}
	 * @return ResponseEntity<>
	 */
	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public ResponseEntity<?> add(@RequestBody Book entity) {
		logger.info("Creation of a new book with the name: " + entity.title + " and author: " + entity.author);
		try {
			return new ResponseEntity<Book>(bookService.save(entity), HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Error while saving new book with title: " + entity.title + " and author: " + entity.author);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * This method getting all books
	 * 
	 * @return ResponseEntity<List<Book>>
	 */
	@RequestMapping("/all")
	public ResponseEntity<?> findAll() {
		logger.info("Getting all books");
		return new ResponseEntity<List<Book>>(bookService.findAll(), HttpStatus.OK);
	}

	/**
	 * This method deletes all books from database
	 * 
	 * @return HttpStatus
	 */
	@RequestMapping("/all/del")
	public ResponseEntity<?> deleteAll() {
		logger.info("Delete all books");
		try {
			bookService.deleteAll();
		} catch (Exception e) {
			logger.error("Error while deleting all books");
			new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * This method deletes book from database
	 * 
	 * @param id
	 *            - book id
	 * @return HttpStatus
	 */
	@RequestMapping("/del/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
		logger.info("Delete book with id= " + id);
		try {
			bookService.deleteById(id);
		} catch (Exception e) {
			logger.error("book with id= " + id + " is not exist");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping("/board/{bookId}")
	public ResponseEntity<?> getBoard(@PathVariable("bookId") Long bookId) {
		Book book = bookService.findById(bookId);
		return new ResponseEntity<Board>(book.board, HttpStatus.OK);
	}

	/**
	 * This method get books by title
	 * 
	 * @param title
	 *            - book title
	 * @return - ResponseEntity<List<Book>>
	 */
	@RequestMapping("findT/{title}")
	public ResponseEntity<?> findByTitle(@PathVariable("title") String title) {
		logger.info("Getting books with title: " + title);
		List<Book> rezList = bookService.findByTitle(title);
		if (rezList.isEmpty()) {
			logger.info("Books with the title: " + title + " not found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Book>>(rezList, HttpStatus.OK);
	}
}
