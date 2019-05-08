package com.example.es;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class HighlevelClientTest {

    private RestHighLevelClient client =
            new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.53.130", 9200, "http")
            ));


    @Test
    public void test() throws IOException {



//        RestHighLevelClient client =
//                new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.53.130", 9200, "http")));


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
        jsonMap.put("user", "kimchy12");
        jsonMap.put("postDate", new Date());
        jsonMap.put("message", "trying out Elasticsearch");
        IndexRequest indexRequest = new IndexRequest("posts", "doc", "2")
                .source(jsonMap);


        IndexResponse indexResponse = client.index(indexRequest);


        String index = indexResponse.getIndex();

        System.out.println(index);

        client.close();

    }

    @Test
    public void testGet() throws IOException {

        //RestClient lowLevelRestClient = RestClient.builder(new HttpHost("192.168.53.130", 9200, "http")).build();
//
//        RestHighLevelClient client =
//                new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.53.130", 9200, "http")
//                ));


        GetRequest getRequest = new GetRequest("posts","doc","1");

        GetResponse getResponse = client.get(getRequest);


        if(getResponse.isExists()){
            System.out.println(getResponse.getVersion());

            String sourceAsString = getResponse.getSourceAsString();
            System.out.println(sourceAsString);


            Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();

            // can get Map

            byte[] sourceAsBytes = getResponse.getSourceAsBytes();
            // also byte map



        }else{
            client.close();
        }

        client.close();
    }

    @Test
    public void testExists() throws IOException {

//        RestHighLevelClient client =
//                new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.53.130", 9200, "http")
//                ));

        GetRequest getRequest = new GetRequest(
                "posts",
                "doc",
                "1");


        boolean exists = client.exists(getRequest);

        System.out.println(exists);

        client.close();
    }


    @Test
    public void testDelete() throws IOException {

//        RestHighLevelClient client =
////                new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.53.130", 9200, "http")
////                ));

        DeleteRequest request = new DeleteRequest(
                "posts",
                "doc",
                "2");

        request.timeout("3s");


        DeleteResponse response = client.delete(request);


        String index = response.getIndex();
        String type = response.getType();
        String id = response.getId();
        long version = response.getVersion();



        System.out.println( response.toString() );

        if(response.getResult() == DocWriteResponse.Result.NOT_FOUND){
            System.out.println("Doc is not found ");
        }

        client.close();
    }


    @Test
    public void testUpdate() throws IOException{

        /*
        UpdateRequest request = new UpdateRequest(
                "posts",
                "doc",
                "1");
        String date = "\"" +LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+"\"";
        String jsonString = "{" +
                "\"updated\":"+ date + ","+
                "\"reason\":\"daily update\"" +
                "}";
        request.doc(jsonString, XContentType.JSON);

        */
        //Json String 을 이용해 update 하는 법 .


        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("updated", new Date());
        jsonMap.put("reason", "daily update");
        UpdateRequest request = new UpdateRequest("posts", "doc", "1")
                .doc(jsonMap).timeout("2s");


        UpdateResponse updateResponse = client.update(request, RequestOptions.DEFAULT);

        System.out.println(" response : "+ updateResponse);

        client.close();
    }

    @Test
    public void bulkTest() throws IOException {

        BulkRequest request = new BulkRequest();

        request.add(new IndexRequest("posts", "doc", "1")
                .source(XContentType.JSON,"field", "foo"));
        request.add(new IndexRequest("posts", "doc", "2")
                .source(XContentType.JSON,"field", "bar"));
        request.add(new IndexRequest("posts", "doc", "3")
                .source(XContentType.JSON,"field", "baz"));


        BulkResponse bulkResponse = client.bulk(request, RequestOptions.DEFAULT);


        for (BulkItemResponse bulkItemResponse : bulkResponse) {
            DocWriteResponse itemResponse = bulkItemResponse.getResponse();

            if (bulkItemResponse.getOpType() == DocWriteRequest.OpType.INDEX
                    || bulkItemResponse.getOpType() == DocWriteRequest.OpType.CREATE) {
                IndexResponse indexResponse = (IndexResponse) itemResponse;

            } else if (bulkItemResponse.getOpType() == DocWriteRequest.OpType.UPDATE) {
                UpdateResponse updateResponse = (UpdateResponse) itemResponse;

            } else if (bulkItemResponse.getOpType() == DocWriteRequest.OpType.DELETE) {
                DeleteResponse deleteResponse = (DeleteResponse) itemResponse;
            }
        }

        client.close();

    }



}
