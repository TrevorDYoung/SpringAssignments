package com.promineotech.jeep.controller;

import com.promineotech.jeep.entitiy.Jeep;
import com.promineotech.jeep.entitiy.JeepModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//Integration Test
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
        ResponseEntity<List<Jeep>> response =
                restTemplate.getRestTemplate().exchange(uri, HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {});

        //Then: a success status code is returned
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        //And: The actual list is returned is the same as the expected list
        List<Jeep> expexted = buildExpected();
        assertThat(response.getBody()).isEqualTo(expexted);
    }

    protected List<Jeep> buildExpected(){
        List<Jeep> list = new LinkedList<>();
        list.add(Jeep.builder()
                .modelId(JeepModel.WRANGLER)
                .trimLevel("Sport")
                .numDoors(2)
                .wheelSize(17)
                .basePrice(new BigDecimal("2.00"))
                .build());

        list.add(Jeep.builder()
                .modelId(JeepModel.WRANGLER)
                .trimLevel("Sport")
                .numDoors(4)
                .wheelSize(17)
                .basePrice(new BigDecimal("2.50"))
                .build());
        return list;
    }
}