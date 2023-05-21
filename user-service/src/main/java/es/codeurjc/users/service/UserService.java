package es.codeurjc.users.service;

import es.codeurjc.users.client.CommentClient;
import es.codeurjc.users.exception.UserHasCommentsException;
import es.codeurjc.users.model.User;
import es.codeurjc.users.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

	Logger logger = LoggerFactory.getLogger(UserService.class);
	private final UserRepository users;
	private final CommentClient commentClient;

	public UserService(UserRepository users, CommentClient commentClient) {
		this.users = users;
		this.commentClient = commentClient;
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

	public User deleteById(long id) {
		User user = users.findById(id).orElseThrow();
		if (!commentClient.userHasComments(id)) {
			logger.info("user has 0 comment, then it will be deleted");
			users.deleteById(id);
			return user;
		}
		logger.info("user has comments, therefore it will not be deleted");
		throw new UserHasCommentsException();
	}

	public Optional<User> findByNick(String nick) {
		return users.findByNick(nick);
	}
}
