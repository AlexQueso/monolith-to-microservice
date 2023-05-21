package es.codeurjc.books.service.user;

import es.codeurjc.books.model.User;

import java.util.List;
import java.util.Optional;

public class MicroserviceUserService implements UserService {

    private final UserClient userClient;

    public MicroserviceUserService(UserClient userClient) {
        this.userClient = userClient;
    }

    @Override
    public void save(User user) {
        userClient.save(user);
    }

    @Override
    public void replace(User updatedUser) {
        userClient.replace(updatedUser);
    }

    @Override
    public List<User> findAll() {
        return userClient.findAll();
    }

    @Override
    public Optional<User> findById(long id) {
        return userClient.findById(id);
    }

    @Override
    public boolean existsById(long id) {
        return userClient.existsById(id);
    }

    @Override
    public void deleteById(long id) {
        userClient.deleteById(id);
    }

    @Override
    public Optional<User> findByNick(String nick) {
        return userClient.findByNick(nick);
    }
}
