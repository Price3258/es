package com.example.es.domain;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString(exclude = "member")
//exclude 는 member 의 내용을 노출하고 싶지 않기 때문에 사용
@Table(name = "tbl_profile")
@Entity
@EqualsAndHashCode(of="fname")
public class Profile {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fname;

    private boolean current;

    @ManyToOne
    private Member member;

}
