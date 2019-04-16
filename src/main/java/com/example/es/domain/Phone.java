package com.example.es.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@lombok.ToString
@Document(indexName = "test", type = "phone")
public class Phone {

    @Id
    private Long id;
    private String number;
    private String owner;
}
