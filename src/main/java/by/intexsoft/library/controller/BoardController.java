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
import by.intexsoft.library.service.BoardService;
import ch.qos.logback.classic.Logger;

/**
 * Controller for {@link Board}
 */
@RestController
@RequestMapping("/board")
public class BoardController {

	private static Logger logger = (Logger) LoggerFactory.getLogger(BoardController.class.getName());

	@Autowired
	BoardService boardService;

	/**
	 * This method adds new board into database
	 * 
	 * @param entity
	 *            - object type {@link Board}
	 * @return ResponseEntity<>
	 */
	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public ResponseEntity<?> add(@RequestBody Board entity) {
		logger.info("Creation of a new board with the number: " + entity.number);
		try {
			return new ResponseEntity<Board>(boardService.save(entity), HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Error while saving new board with number: " + entity.number);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * This method deletes board from database
	 * 
	 * @param id
	 *            - board id
	 * @return HttpStatus
	 */
	@RequestMapping("/del/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
		logger.info("Delete board with id= " + id);
		try {
			boardService.deleteById(id);
		} catch (Exception e) {
			logger.error("board with id= " + id + " is not exist");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * This method getting all boards
	 * 
	 * @return ResponseEntity<List<BoardEntity>>
	 */
	@RequestMapping("/all")
	public ResponseEntity<?> findAll() {
		logger.info("Getting all boards");
		List<Board> rezList = boardService.findAll();
		return new ResponseEntity<List<Board>>(rezList, HttpStatus.OK);
	}
}
