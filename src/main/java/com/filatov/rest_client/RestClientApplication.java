package com.filatov.rest_client;

import com.filatov.rest_client.component.Communication;
import com.filatov.rest_client.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestClientApplication.class, args);


        User user = new User(3L,"James","Brown", (byte) 32);
        User user2 = new User(3L,"Thomas","Shelby", (byte) 32);
        Communication communication = new Communication(getRestTemplate(),getHttp());
        communication.cookeAllUser();
        communication.newUser(user);
//        communication.updateUser(3,user2);

    }



    @Bean
    static public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    static public HttpHeaders getHttp() {
        return new HttpHeaders();
    }
}
