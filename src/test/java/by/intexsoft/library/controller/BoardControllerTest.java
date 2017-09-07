package by.intexsoft.library.controller;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

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
import by.intexsoft.library.service.BoardService;

/**
 * This class tests methods of class BoardController
 */
@RunWith(MockitoJUnitRunner.class)
public class BoardControllerTest {

	@Mock
	private static BoardService boardService;

	@InjectMocks
	private static BoardController boardController = new BoardController();
	private static List<Board> boardList;
	private static Board validBoard;
	private static Board emptyBoard = new Board();
	private static final int NUMBER = 4;
	private static final Long ID_IS_NULL = null;

	/**
	 * This method prepare class to test
	 */
	@BeforeClass
	public static void init() {
		validBoard = new Board();
		validBoard.number = NUMBER;
		Board board = new Board();
		board.number = 0;
		Board board1 = new Board();
		board1.number = 1;
		boardList = new ArrayList<>();
		boardList.add(validBoard);
		boardList.add(board);
		boardList.add(board1);
	}

	/**
	 * This method tests add method from BoardController class when board is valid
	 */
	@Test
	public void addPositiveTest() {
		when(boardService.save(validBoard)).thenReturn(validBoard);
		ResponseEntity<?> result = boardController.add(validBoard);
		assertEquals(validBoard, result.getBody());
		assertEquals(HttpStatus.CREATED, result.getStatusCode());
	}

	/**
	 * This method tests add method from BoardController class when board is empty
	 */
	@Test
	public void addNegativeTest() {
		when(boardService.save(emptyBoard)).thenThrow(new RuntimeException());
		ResponseEntity<?> result = boardController.add(emptyBoard);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
	}
	
	/**
	 * This method tests deleteById method from BoardController class
	 */
	@Test
	public void	deleteByIdNegativeTest() {
		doThrow(new RuntimeException()).when(boardService).deleteById(ID_IS_NULL);
		ResponseEntity<?> response = boardController.deleteById(ID_IS_NULL);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	/**
	 * This method tests findAll method from BoardController
	 */
	@Test
	public void findAllTest() {
		when(boardService.findAll()).thenReturn(boardList);
		ResponseEntity<?> response = boardController.findAll();
		List<Board> resList = (List<Board>) response.getBody();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertArrayEquals(new Board[boardList.size()], new Board[resList.size()]);
	}
}
