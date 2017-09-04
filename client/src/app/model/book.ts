import Board from './board';
import User from './user';

/**
 * Book model
 */
export default class Book {
  id: number;
  tittle: string;
  author: string;
  board: Board;
  users: User[];
}
