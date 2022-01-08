package com.filatov.rest_client.component;


import com.filatov.rest_client.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.Objects;

@Component
public class Communication {

    private final RestTemplate template;
    private final HttpHeaders headers;
    private String cookie = "";
    private final String URL = "http://91.241.64.178:7081/api/users";

    public Communication(RestTemplate template, HttpHeaders headers) {
        this.template = template;
        this.headers = headers;
    }

    public ResponseEntity<String> cookeUser() {
        ResponseEntity<String> response = template.getForEntity(URL, String.class);
        cookie = Objects.requireNonNull(response.getHeaders().getFirst("Set-Cookie"));
        System.out.println("Result method 'cookeUser' = " + response);
        System.out.println("Set-Cookie method 'cookeUser' = " + cookie);

        return response;
    }

    public ResponseEntity<String> newUser (User user) {
        headers.add("cookie",cookie);
        HttpEntity<User> entity = new HttpEntity<User>(user,headers);
        ResponseEntity<String> response = template.postForEntity(URL,entity,String.class);
        String result = response.getBody();
        System.out.println("Result method 'newUser' = " + result);

        return response;
    }

    public ResponseEntity<String> updateUser (User user) {
        HttpEntity<User> entity = new HttpEntity<User>(user,headers);
        ResponseEntity<String> response = template.exchange(URL,HttpMethod.PUT,entity,String.class);
        String result = response.getBody();
        System.out.println("Result method 'updateUser' = " + result);

        return response;
    }

    public ResponseEntity<String> deleteUser (int id) {
        HttpEntity<User> entity = new HttpEntity<User>(headers);
        ResponseEntity<String> response = template.exchange(URL + "/" + id,HttpMethod.DELETE,entity,String.class);
        String result = response.getBody();
        System.out.println("Result method 'deleteUser' = " + result);

        return response;
    }
}
