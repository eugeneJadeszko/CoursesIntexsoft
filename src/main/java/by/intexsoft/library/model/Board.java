package by.intexsoft.library.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Model for board table
 */
@Entity
@Table(name = "board")
public class Board extends AbstractEntity {

	private static final long serialVersionUID = -7495528735114017752L;

	/**
	 * number of board
	 */
	@Column(name = "board_number")
	public int number;

}
