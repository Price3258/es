package com.example.es;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.junit.Test;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.*;

public class HighlevelClientTest {

    @Test
    public void test() throws IOException {

        RestClient lowLevelRestClient = RestClient.builder(new HttpHost("192.168.53.130", 9200, "http")).build();

        RestHighLevelClient client =
                new RestHighLevelClient(lowLevelRestClient);

        /*

        IndexRequest request = new IndexRequest(
                "posts",
                "doc",
                "2");
        String jsonString = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";
        request.source(jsonString, XContentType.JSON);
        */

        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("user", "kimchy123");
        jsonMap.put("postDate", new Date());
        jsonMap.put("message", "trying out Elasticsearch");
        IndexRequest indexRequest = new IndexRequest("posts", "doc", "1")
                .source(jsonMap);


        IndexResponse indexResponse = client.index(indexRequest);


        String index = indexResponse.getIndex();

        System.out.println(index);


    }
}
