package es.codeurjc.books.service.user;

import es.codeurjc.books.repository.UserRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserServiceConfig {

    @Bean(name = "monolithUserService")
    @ConditionalOnProperty(prefix = "user", name = "management", havingValue = "USERS_IN_MONOLITH")
    public UserService monolith(UserRepository userRepository){
        return new MonolithUserServiceImpl(userRepository);
    }

    @Bean(name = "microserviceUserService")
    @ConditionalOnProperty(prefix = "user", name = "management", havingValue = "USERS_IN_MICROSERVICE")
    public UserService microservice(UserClient userClient){
        return new MicroserviceUserService(userClient);
    }
}
