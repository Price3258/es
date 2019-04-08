package com.example.es.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "docs")
public class Documents {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    int namespace;

    String document;

    String text;

}
