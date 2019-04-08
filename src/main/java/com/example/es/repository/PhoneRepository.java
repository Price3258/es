package com.example.es.repository;


import com.example.es.domain.Phone;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PhoneRepository extends ElasticsearchRepository<Phone,Long> {


}
