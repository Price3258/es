package com.example.es;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EsApplicationTests {

    @Value("${elasticsearch.host}")
    private String host;

    @Value("${elasticsearch.port}")
    private int port;

    @Test
    public void test() {

        RestClient restClient = RestClient.builder(
                new HttpHost(host, port, "http")).build();

        try {
            Response response = restClient.performRequest("GET", "/");

            System.out.println(response);
            restClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2(){

        RestClient restClient = RestClient.builder(
                new HttpHost(host, port, "http")).build();


        Map<String, String> params = Collections.emptyMap();
        String jsonString = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";
        HttpEntity entity = new NStringEntity(jsonString, ContentType.APPLICATION_JSON);

        try {
            Response response = restClient.performRequest("PUT", "/posts/doc/1", params, entity);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
