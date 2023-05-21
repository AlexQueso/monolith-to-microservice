package es.codeurjc.users.controller;

import es.codeurjc.users.model.User;
import es.codeurjc.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService users;

    public UserController(UserService users) {
        this.users = users;
    }

//    public ResponseEntity<?> createUser(@RequestBody User user) {
//
//        try {
//
//            users.save(user);
//
//        } catch (DataIntegrityViolationException e) {
//            return ResponseEntity.badRequest()
//                    .body("User nick should be unique");
//        }
//
//        URI location = fromCurrentRequest().path("/{id}")
//                .buildAndExpand(user.getId()).toUri();
//
//        return ResponseEntity.created(location).body(user);
//    }

    @GetMapping("/")
    public List<User> getUsers() {
        return users.findAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable long id) {
        return users.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public User replaceUser(@RequestBody User newUser, @PathVariable long id) {

        newUser.setId(id);
        users.replace(newUser);
        return newUser;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable long id) {

        User user = users.findById(id).orElseThrow();

        users.deleteById(id);
        return ResponseEntity.ok(user);
    }
}