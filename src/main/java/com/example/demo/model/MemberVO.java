package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
// @Entity
// @Table(name = "member")
@Entity(name = "member")
public class MemberVO {

    @Id
    // @GeneratedValue(strategy=GenerationType.IDENTITY)
    private String id;
    private String name;
    private String address;

}
