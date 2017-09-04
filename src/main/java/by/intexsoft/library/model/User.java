package by.intexsoft.library.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Model for users table
 */
@Entity
@Table(name = "users")
public class User extends AbstractEntity {

	private static final long serialVersionUID = 7178967472320069080L;

	/**
	 * First name of user
	 */
	@Column(name = "first_name")
	public String name;

}
