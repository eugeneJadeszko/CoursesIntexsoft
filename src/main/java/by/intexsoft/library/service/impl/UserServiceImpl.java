package by.intexsoft.library.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.intexsoft.library.model.User;
import by.intexsoft.library.repository.UserRepository;
import by.intexsoft.library.service.UserService;

/**
 * Implementation for {@link UserService}
 */
@Service
public class UserServiceImpl extends AbstractEntityServiceImpl<User> implements UserService {

	@Autowired
	UserRepository repository;
}
