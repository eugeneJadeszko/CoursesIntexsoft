package by.intexsoft.library.service;

import java.util.List;

import by.intexsoft.library.model.Book;
import by.intexsoft.library.repository.BookRepository;

/**
 * Service for {@link BookRepository}
 */
public interface BookService extends AbstractEntityService<Book> {

	/**
	 * This method returns books where column title = title
	 * 
	 * @param title
	 *            - title of book
	 * @return List<Book>
	 */
	List<Book> findByTitle(String title);
}
