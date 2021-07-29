package com.toy.backapi.repository;

import java.util.List;

import com.toy.backapi.vo.Person;

import org.springframework.stereotype.Repository;

@Repository
public interface BackRepository {

    List<Person> findAll();

    Person save(Person person);

}
