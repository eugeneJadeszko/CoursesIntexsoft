package by.intexsoft.library.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Model for books table
 */
@Entity
@Table(name = "books")
public class Book extends AbstractEntity {

	private static final long serialVersionUID = 2895889130940665945L;

	/**
	 * Title of book
	 */
	@Column(name = "title")
	public String title;

	/**
	 * Author of book
	 */
	@Column(name = "author")
	public String author;

	/**
	 * Board of book
	 */
	@ManyToOne
	@JoinColumn(name = "board_id")
	public Board board;
	
	/**
	 * Users of book
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "book_user", joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"), 
	inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
	public Set<User> users;

}
