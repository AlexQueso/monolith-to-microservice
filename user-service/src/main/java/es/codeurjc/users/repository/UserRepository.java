package es.codeurjc.users.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.users.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByNick(String nick);

}