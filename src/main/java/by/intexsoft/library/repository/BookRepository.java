package by.intexsoft.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import by.intexsoft.library.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

	/**
	 * This method returns books where column title = title
	 * 
	 * @param title
	 *            - title of book
	 * @return List<Book>
	 */
	List<Book> findByTitle(String title);
}
