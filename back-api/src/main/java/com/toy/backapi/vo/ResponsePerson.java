package com.toy.backapi.vo;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponsePerson {

    private String id;
    private String name;
    private byte[] image;
    private String profile;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    public ResponsePerson(Person person) {
        this.id = person.getId();
        this.image = person.getImage();
        if (person.getName().length() > 8) {
            this.name = person.getName().substring(0, 7) + "...";
        } else {
            this.name = person.getName();
        }

        if (person.getProfile().length() > 8) {
            this.profile = person.getProfile().substring(0, 7) + "...";
        } else {
            this.name = person.getProfile();
        }
        this.createdAt = person.getCreatedAt().toLocalDateTime();
    }
}
