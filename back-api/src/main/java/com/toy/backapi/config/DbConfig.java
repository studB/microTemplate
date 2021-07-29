package com.toy.backapi.config;

import javax.sql.DataSource;

import com.toy.backapi.repository.BackRepository;
import com.toy.backapi.repository.BackRepositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbConfig {

    private final DataSource dataSource;

    @Autowired
    public DbConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public BackRepository backRepository() {
        return new BackRepositoryImpl(this.dataSource);
    }
}
