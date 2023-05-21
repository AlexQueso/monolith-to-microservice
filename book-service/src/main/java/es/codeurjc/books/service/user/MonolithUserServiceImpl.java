package es.codeurjc.books.service.user;

import es.codeurjc.books.model.User;
import es.codeurjc.books.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class MonolithUserServiceImpl implements UserService {

    private final UserRepository users;

    public MonolithUserServiceImpl(UserRepository users) {
        this.users = users;
    }

    @Override
    public void save(User user) {
        users.save(user);
    }

    @Override
    public void replace(User updatedUser) {
        users.findById(updatedUser.getId()).orElseThrow();
        users.save(updatedUser);
    }

    @Override
    public List<User> findAll() {
        return users.findAll();
    }

    @Override
    public Optional<User> findById(long id) {
        return users.findById(id);
    }

    @Override
    public boolean existsById(long id) {
        return users.existsById(id);
    }

    @Override
    public void deleteById(long id) {
        users.deleteById(id);
    }

    @Override
    public Optional<User> findByNick(String nick) {
        return users.findByNick(nick);
    }
}
