package es.codeurjc.users.service;

import es.codeurjc.users.model.User;
import es.codeurjc.users.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

	private final UserRepository users;

	public UserService(UserRepository users) {
		this.users = users;
	}

	public void save(User user) {
		users.save(user);
	}

	public void replace(User updatedUser) {
		users.findById(updatedUser.getId()).orElseThrow();
		users.save(updatedUser);
	}

	public List<User> findAll() {
		return users.findAll();
	}

	public Optional<User> findById(long id) {
		return users.findById(id);
	}
	
	public boolean existsById(long id) {
		return users.existsById(id);
	}

	public void deleteById(long id) {
		// check in monolith amount of comments
		users.deleteById(id);
	}

	public Optional<User> findByNick(String nick) {
		return users.findByNick(nick);
	}
}
