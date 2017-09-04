import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import IBoardService from './iboard.service';
import Board from '../model/board';

const BOARD_ALL = '/library/api/board/all';
const BOARD_CREATE = '/library/api/board/add';
const BOARD_DEL_BY_ID = '/library/api/board/del/';

@Injectable()
export default class BoardService implements IBoardService {

  constructor(private http: Http) {
  }

  board: Board;

  findAll(): Promise<Board[]> {
    return this.http.get(BOARD_ALL)
      .toPromise()
      .then(response => response.json());
  }

  createBoard(numberOfBoard: number): Promise<Board> {
    this.board = new Board();
    this.board.number = numberOfBoard;
    return this.http.post(BOARD_CREATE, this.board)
      .toPromise()
      .then(response => response.json());
  }

  deleteById(id: number): Promise<Response> {
    return this.http.get(BOARD_DEL_BY_ID + id)
      .toPromise()
      .then(response => response.json());
  }
}
