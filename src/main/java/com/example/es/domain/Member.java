package com.example.es.domain;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Table(name = "tbl_member")
@Entity
@EqualsAndHashCode(of="uid")
public class Member {

    @Id
    private String uid;

    private String password;

    private String name;

}
