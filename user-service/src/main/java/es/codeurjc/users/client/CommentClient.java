package es.codeurjc.users.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CommentClient {

    @Value("${BOOKS_HOST}")
    private String BOOKS_HOST;
    @Value("${BOOKS_PORT}")
    private int BOOKS_PORT;

    public boolean userHasComments(Long id){
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://"+ BOOKS_HOST +":"+ BOOKS_PORT + "/users/" + id + "/comments/";
        ResponseEntity<Object[]> response= restTemplate.getForEntity(url, Object[].class);
        Object[] comments = response.getBody();

        if (comments != null) {
            return response.getBody().length > 0;
        }
        return false;
    }

}
