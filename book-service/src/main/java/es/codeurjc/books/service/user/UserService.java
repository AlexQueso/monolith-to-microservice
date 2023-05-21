package es.codeurjc.books.service.user;

import es.codeurjc.books.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void save(User user);
    void replace(User updatedUser);
    List<User> findAll();
    Optional<User> findById(long id);
    boolean existsById(long id);
    void deleteById(long id);
    Optional<User> findByNick(String nick);
}
