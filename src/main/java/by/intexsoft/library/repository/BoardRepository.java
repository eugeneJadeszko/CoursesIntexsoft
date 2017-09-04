package by.intexsoft.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import by.intexsoft.library.model.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
