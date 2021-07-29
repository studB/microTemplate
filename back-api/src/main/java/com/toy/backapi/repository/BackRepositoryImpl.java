package com.toy.backapi.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.toy.backapi.vo.Person;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class BackRepositoryImpl implements BackRepository {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    public BackRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("person");
    }

    @Override
    public List<Person> findAll() {
        return jdbcTemplate.query("SELECT * FROM person", (rs, rn) -> {
            return Person.builder().id(rs.getString("id")).name(rs.getString("name")).image(rs.getBytes("image"))
                    .profile(rs.getString("profile")).createdAt(rs.getTimestamp("createdAt")).build();
        });
    }

    @Override
    public Person save(Person person) {
        
        Map<String, Object> params = new HashMap<>();
        params.put("id", person.getId());
        params.put("name", person.getName());
        params.put("image", person.getImage());
        params.put("profile", person.getProfile());
        params.put("createdAt", person.getCreatedAt());
        simpleJdbcInsert.execute(params);
        return person;
    }

}
