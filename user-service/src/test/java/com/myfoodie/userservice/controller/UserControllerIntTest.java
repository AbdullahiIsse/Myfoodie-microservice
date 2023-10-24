package com.myfoodie.userservice.controller;


import com.myfoodie.userservice.dto.UserRequest;
import com.myfoodie.userservice.dto.UserResponse;
import com.myfoodie.userservice.model.User;
import com.myfoodie.userservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.sql.Timestamp;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerIntTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"));

    @Autowired
    private TestRestTemplate testRestTemplate;


    @Autowired
    private UserRepository userRepository;

    @Test
    void connectionEstablished() {
        assertThat(postgreSQLContainer.isCreated()).isTrue();
        assertThat(postgreSQLContainer.isRunning()).isTrue();
    }

    @Test
    void shouldFindAllUsers() {
        userRepository.save(new User("test111", "test", "test@gmail.com", new Timestamp(System.currentTimeMillis())));
        var users = testRestTemplate.getForObject("/api/user", UserResponse[].class);
        assertThat(users).hasSize(1);
    }

    @Test
    void shouldCreateUser() {
        var userRequest = UserRequest.builder()
                .id("1")
                .username("username")
                .email("user@gmail.com")
                .lastSeen(new Timestamp(System.currentTimeMillis()))
                .build();

        var response = testRestTemplate.exchange("/api/user", HttpMethod.POST, new HttpEntity<>(userRequest), UserRequest.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }


}
