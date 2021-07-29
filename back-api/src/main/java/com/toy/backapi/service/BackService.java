package com.toy.backapi.service;

import java.io.IOException;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;
import java.util.UUID;

import com.toy.backapi.repository.BackRepository;
import com.toy.backapi.vo.Person;
import com.toy.backapi.vo.RequestPerson;
import com.toy.backapi.vo.ResponsePerson;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BackService {

    BackRepository backRepository;

    @Autowired
    public BackService(BackRepository backRepository) {
        this.backRepository = backRepository;
    }

    public List<ResponsePerson> findAll() {
        List<Person> persons = backRepository.findAll();
        return persons.stream().map(ResponsePerson::new).collect(Collectors.toList());
    }

    public ResponsePerson save(RequestPerson requestPerson, Timestamp timestamp){

        try {
            String personId = UUID.randomUUID().toString();
            Person inputPerson = Person.builder().id(personId).name(requestPerson.getName()).image(requestPerson.getImage().getBytes())
                    .profile(requestPerson.getProfile()).createdAt(timestamp).build();

            Person returnPerson = backRepository.save(inputPerson);
            return new ResponsePerson(returnPerson);
        } catch (IOException e) {
            return null;
        }

    }

}
