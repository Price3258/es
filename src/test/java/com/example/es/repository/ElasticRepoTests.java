package com.example.es.repository;


import com.example.es.api.ElasticSearchAPI;
import com.example.es.domain.Phone;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticRepoTests {


    @Autowired
    private ElasticSearchAPI elasticApi;

    private final String ELASTIC_INDEX = "test_index";
    private final String ELASTIC_TYPE = "test_type";




    @Test
    public void test() {

    }

    @Test
    public void sendPost() {
        String url = ELASTIC_INDEX + "/" + ELASTIC_TYPE;
//        Weather weather = new Weather();
//        weather.setCity("Seoul");
//        weather.setTemperature(10.2);
//        weather.setSeason("Winter");

        Phone phone = new Phone();

        phone.setId((long) 1);

        phone.setOwner("김원태");
        phone.setNumber("010-3314-6318");


        Map<String, Object> result = elasticApi.callElasticApi("POST", url, phone, null);
        System.out.println(result.get("resultCode"));
        System.out.println(result.get("resultBody"));
    }


}
