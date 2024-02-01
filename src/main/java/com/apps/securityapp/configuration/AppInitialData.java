package com.apps.securityapp.configuration;

import com.apps.securityapp.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppInitialData implements CommandLineRunner {
    private final UserRepository userRepository;

    @Autowired
    public AppInitialData(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();
    }
}
