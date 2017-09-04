package by.intexsoft.library.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.intexsoft.library.model.Board;
import by.intexsoft.library.repository.BoardRepository;
import by.intexsoft.library.service.BoardService;

/**
 * Implementation for {@link BoardService}
 */
@Service
public class BoardServiceImpl extends AbstractEntityServiceImpl<Board> implements BoardService {

	@Autowired
	BoardRepository repository;
}
