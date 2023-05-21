package es.codeurjc.books.service.user;

import es.codeurjc.books.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserClient {

    public void save(User user) {

    }

    public void replace(User user) {

    }

    public List<User> findAll() {
        return null;
    }

    public Optional<User> findById(long id) {
        return Optional.empty();
    }

    public boolean existsById(long id) {
        return true;
    }

    public void deleteById(long id) {

    }

    public Optional<User> findByNick(String nick) {
        return Optional.empty();
    }
}
