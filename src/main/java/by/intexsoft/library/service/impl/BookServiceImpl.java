package by.intexsoft.library.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.intexsoft.library.model.Book;
import by.intexsoft.library.repository.BookRepository;
import by.intexsoft.library.service.BookService;

/**
 * Implementation for {@link BookService}
 */
@Service
public class BookServiceImpl extends AbstractEntityServiceImpl<Book> implements BookService {

	@Autowired
	BookRepository repository;

	@Override
	public List<Book> findByTitle(String title) {
		return repository.findByTitle(title);
	}
}
