package com.prod.willdo;

import com.prod.willdo.dto.TaskDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration test using PostgreSQL database
 *
 * Prerequisites: Start PostgreSQL using docker-compose-test.yml:
 *   docker-compose -f docker-compose-test.yml up -d
 *
 * To stop after tests:
 *   docker-compose -f docker-compose-test.yml down
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class TaskOldIntegrationTest {

    @LocalServerPort
    private int port;


    @Test
    void shouldCreateAndRetrieveTask() {
        // Build WebTestClient to connect to the running server
        WebTestClient webTestClient = WebTestClient.bindToServer()
                .baseUrl("http://localhost:" + port)
                .build();

        // 1. Create a Task via HTTP
        TaskDto newTask = new TaskDto(null, "Integration Test Task", false);

        TaskDto createdTask = webTestClient.post()
                .uri("/tasks")
                .bodyValue(newTask)
                .exchange()
                .expectStatus().isOk()
                .expectBody(TaskDto.class)
                .returnResult()
                .getResponseBody();

        assertThat(createdTask).isNotNull();
        assertThat(createdTask.id()).isNotNull();
        assertThat(createdTask.description()).isEqualTo("Integration Test Task");

        // 2. Retrieve it via HTTP
        TaskDto retrievedTask = webTestClient.get()
                .uri("/tasks/" + createdTask.id())
                .exchange()
                .expectStatus().isOk()
                .expectBody(TaskDto.class)
                .returnResult()
                .getResponseBody();

        assertThat(retrievedTask).isNotNull();
        assertThat(retrievedTask.description()).isEqualTo("Integration Test Task");
    }
}
