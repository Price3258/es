package com.example.es.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "document")
public class Document {


    int namespace;

    String document;

    String text;

}
