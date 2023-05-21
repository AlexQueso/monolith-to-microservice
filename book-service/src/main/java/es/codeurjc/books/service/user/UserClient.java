package es.codeurjc.books.service.user;

import es.codeurjc.books.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserClient {

    @Value("${USERS_HOST}")
    private String USERS_HOST;
    @Value("${USERS_PORT}")
    private int USERS_PORT;

    public void save(User user) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<User> request = new HttpEntity<>(user);
        URI location = restTemplate.postForLocation(getUrl(), request);
        assert location != null;
    }

    public void replace(User user) {
        RestTemplate restTemplate = new RestTemplate();
        String url = getUrl() + user.getId();
        HttpEntity<User> request = new HttpEntity<>(user);
        ResponseEntity<User> response = restTemplate.exchange(url, HttpMethod.PUT, request, User.class);
        assert response.getBody() != null;
    }

    public List<User> findAll() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User[]> response = restTemplate.getForEntity(getUrl(), User[].class);
        User[] users = response.getBody();
        assert users != null;
        return List.of(users);
    }

    public Optional<User> findById(long id) {
        RestTemplate restTemplate = new RestTemplate();
        User response = restTemplate.getForObject(getUrl() + id, User.class);
        return Optional.ofNullable(response);
    }

    public boolean existsById(long id) {
        return this.findById(id).isPresent();
    }

    public void deleteById(long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(getUrl() + id, HttpMethod.DELETE);
    }

    public Optional<User> findByNick(String nick) {
        return Optional.empty();
    }


    private String getUrl() {
        return "http://" + USERS_HOST + ":" + USERS_PORT + "/users/";
    }

    private class UserList {
        private List<User> users;

        public UserList() {
            users = new ArrayList<>();
        }

        public UserList(List<User> users) {
            this.users = users;
        }

        public List<User> getUsers() {
            return users;
        }

        public void setUsers(List<User> users) {
            this.users = users;
        }
    }
}
