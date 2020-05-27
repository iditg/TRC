package com.iditgraber.trc;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;

@RunWith(SpringJUnit4ClassRunner.class)
public class TestApplication {

    @Autowired
    RestTemplate restTemplate;

    @Test
    public void getClients() {
        final String uri = "http://localhost:8080/";

        String result = restTemplate.getForObject(uri, String.class);
        ResponseEntity<String> response = restTemplate.getForEntity(uri+"?clientId=1" , String.class);
        //Assert.assertThat(response.getStatusCode(), is(equalTo(200)));

    }

}