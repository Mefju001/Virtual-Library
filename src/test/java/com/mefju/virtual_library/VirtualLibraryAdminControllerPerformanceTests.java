package com.mefju.virtual_library;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(locations="classpath:application.properties")
@SpringJUnitConfig
class VirtualLibraryAdminControllerPerformanceTests {
        //Test wydajnościowy
    private final String BASE_URL = "http://localhost:8080"; // Ustaw adres lokalny Twojej aplikacji

    @Test
    void testPerformanceOfAdminController() throws IOException {
        int expectedResponseCode = HttpURLConnection.HTTP_OK;
        int numberOfRequests = 200; // Definiuje liczbę żądań do wykonania

        for (int i = 0; i < numberOfRequests; i++) {
            URL url = new URL(BASE_URL + "/MenuAdmin");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            assertEquals(expectedResponseCode, responseCode);
        }
    }
}