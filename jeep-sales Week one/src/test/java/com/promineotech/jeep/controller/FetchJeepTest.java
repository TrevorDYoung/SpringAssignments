package com.promineotech.jeep.controller;

import com.promineotech.jeep.entitiy.Jeep;
import com.promineotech.jeep.entitiy.JeepModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {
        "classpath:flyway/migrations/V1.0__Jeep_Schema.sql",
        "classpath:flyway/migrations/V1.1__Jeep_Data.sql"},
        config = @SqlConfig(encoding = "utf-8"))
class FetchJeepTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int serverPort;

    @Test
    void testThatJeepsAreReturnedWhenAValidModelAndTrimAreSupplied() {
        //Given: a valid model, trim and URI
        JeepModel model = JeepModel.WRANGLER;
        String trim = "sport";
        String uri = String.format("http://localhost:%d/jeeps?model=%s&trim=%s", serverPort, model, trim);

        //When: a connection is made to the URI
        ResponseEntity<Jeep> response =
                restTemplate.getRestTemplate().getForEntity(uri, Jeep.class);

        //Then: a success status code is returned
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}