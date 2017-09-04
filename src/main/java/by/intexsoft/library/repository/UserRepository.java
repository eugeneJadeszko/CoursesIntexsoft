package by.intexsoft.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import by.intexsoft.library.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
