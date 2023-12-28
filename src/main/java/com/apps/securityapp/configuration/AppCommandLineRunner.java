package com.apps.securityapp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AppCommandLineRunner implements CommandLineRunner {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public AppCommandLineRunner(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void run(String... args) throws Exception {
        jdbcTemplate.update("delete from user_roles");
        jdbcTemplate.update("delete from roles");
        jdbcTemplate.update("delete from users");
    }
}
