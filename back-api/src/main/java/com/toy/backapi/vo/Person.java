package com.toy.backapi.vo;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class Person {

    // Entity Form
    private String id;
    private String name;
    private byte[] image;
    private String profile;
    private Timestamp createdAt;

}