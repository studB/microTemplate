package com.toy.backapi.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;
import java.io.InputStream;

import com.toy.backapi.service.BackService;
import com.toy.backapi.vo.Person;
import com.toy.backapi.vo.RequestPerson;
import com.toy.backapi.vo.ResponsePerson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/")
public class ApiController {

    Environment env;
    BackService backService;

    @Autowired
    public ApiController(Environment env, BackService backService) {
        this.env = env;
        this.backService = backService;
    }

    @GetMapping("/healtz")
    public String status() {
        return String.format("Server on PORT:%s", env.getProperty("local.server.port"));
    }

    @GetMapping("/persons")
    @CrossOrigin(allowedHeaders = "*")
    public List<ResponsePerson> getPersons() {
        List<ResponsePerson> result = backService.findAll();
        // return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
        return result;
    }

    // @PostMapping(path = "/person", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    // public ResponseEntity<ResponsePerson> savePerson(@RequestBody RequestPerson requestPerson) {
    @PostMapping(path = "/person", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @CrossOrigin(allowedHeaders = "*")
    public ResponsePerson savePerson(@RequestParam("name") String name, @RequestParam("image") MultipartFile image, @RequestParam("profile") String profile){
        RequestPerson requestPerson = new RequestPerson(name, image, profile);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        ResponsePerson result = backService.save(requestPerson, timestamp);
        return result;
    }

}
