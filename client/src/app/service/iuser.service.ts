import User from '../model/user';

/**
 * Service for book
 */
interface IUserService {
  /**
   * Load all users
   * @returns {Promise<User[]>}
   * @see User
   */
  findAll(): Promise<User[]>;

  /**
   * Adds new user into database
   * @param {string} name - user name
   * @returns {Promise<User>}
   */
  createUser(name: string): Promise<User>;

  /**
   * This method deletes user from database
   * @param {number} id - user id
   * @returns {Promise<User>}
   */
  deleteById(id: number): Promise<User>;
}

export default IUserService;
