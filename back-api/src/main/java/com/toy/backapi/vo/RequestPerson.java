package com.toy.backapi.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
public class RequestPerson {

    private String name;
    private MultipartFile image;
    private String profile;

    public RequestPerson(String name, MultipartFile image, String profile){
        this.name = name;
        this.image = image;
        this.profile = profile;
    }

}
