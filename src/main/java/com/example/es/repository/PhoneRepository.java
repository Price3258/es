package com.example.es.repository;


import com.example.es.domain.Phone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface PhoneRepository extends ElasticsearchRepository<Phone,Long> {


    Page<Phone> findByOwner(String author, Pageable pageable);
    List<Phone> findByNumber(String number);

}
