import Board from '../model/board';
import {Http} from "@angular/http";

/**
 * Service for board
 */
interface IBoardService {
  /**
   * Load all boards
   * @returns {Promise<Board[]>}
   * @see Board
   */
  findAll(): Promise<Board[]>;

  /**
   * Adds new board into database
   * @param {number} num - number of board
   * @returns {Promise<Board>}
   * @see Board
   */
  createBoard(num: number): Promise<Board>;

  /**
   * This method deletes board from database
   * @param {number} id - board id
   * @returns {Promise<Board>}
   */
  deleteById(id: number): Promise<Response>;
}

export default IBoardService;
