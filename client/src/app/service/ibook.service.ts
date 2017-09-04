import Book from '../model/book';

/**
 * Service for book
 */
interface IBookService {
  /**
   * Load all books
   * @returns {Promise<Book[]>}
   * @see Book
   */
  findAll(): Promise<Book[]>;

  /**
   * Adds new book into database
   * @param {string} title - title of book
   * @param {string} author - author of book
   * @returns {Promise<Book>}
   */
  createBook(title: string, author: string): Promise<Book>;

  /**
   * This method deletes book from database
   * @param {number} id - book id
   * @returns {Promise<Book>}
   */
  deleteById(id: number): Promise<Book>;
}

export default IBookService;
