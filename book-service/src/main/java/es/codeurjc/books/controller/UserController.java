package es.codeurjc.books.controller;

import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.books.model.Comment;
import es.codeurjc.books.model.User;
import es.codeurjc.books.service.CommentService;
import es.codeurjc.books.service.user.UserService;

@RestController
public class UserController {
	private final UserService users;

	private final CommentService comments;

	public UserController(UserService users, CommentService comments) {
		this.users = users;
		this.comments = comments;
	}

	@PutMapping("/users/{id}")
	public User replaceUser(@RequestBody User newUser, @PathVariable long id) {

		newUser.setId(id);
		users.replace(newUser);
		return newUser;
	}

	@GetMapping("/users/")
	public List<User> getUsers() {
		return users.findAll();
	}

	@GetMapping("/users/{id}")
	public User getUser(@PathVariable long id) {
		return users.findById(id).orElseThrow();
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable long id) {

		User user = users.findById(id).orElseThrow();

		List<Comment> comment = comments.findAllCommentsByUserId(id);
		if (comment.size() == 0) {
			users.deleteById(id);
			return ResponseEntity.ok(user);
		} else {
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
		}
	}
}