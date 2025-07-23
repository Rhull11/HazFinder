package com.rhull.hazfinder.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.List;

import static java.nio.file.Paths.get;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ItemControllerIntegrationTest
{
    @LocalServerPort
    int randomServerPort;

    RestClient restClient;

    @BeforeEach
    void setup()
    {
        restClient = RestClient.create("http://localhost:" + randomServerPort);
    }

}
