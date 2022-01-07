package com.filatov.rest_client.component;


import com.filatov.rest_client.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
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


    public void cookeAllUser() {
        ResponseEntity<String> forEntity = template.getForEntity(URL, String.class);
        cookie = Objects.requireNonNull(forEntity.getHeaders().get("Set-Cookie")).toString();
        System.out.println(cookie);

    }

    public ResponseEntity<String> newUser (User user) {
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity = new HttpEntity<User>(user,headers);
        ResponseEntity<String> response = template.exchange(URL,HttpMethod.POST,entity,String.class);
        cookie = response.getBody();
        System.out.println(cookie);
        return response;
    }

    public String updateUser (int id, User user) {
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity = new HttpEntity<User>(user,headers);
        String str = template.exchange(URL + "/" + id,HttpMethod.POST,entity,String.class).getBody();
        System.out.println(str);
        cookie = cookie + str;

        return cookie;
    }


    public User getUser (int id) {
        User user = template.getForObject(URL +"/" + id,
                User.class);
        return user;
    }




    public RestTemplate getTemplate() {
        return template;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }
}
