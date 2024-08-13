package com.crud.configuration;


import com.crud.model.User;
import com.crud.repository.UserRepository;
import com.crud.enums.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;


@Slf4j
@Configuration
@RequiredArgsConstructor()
public class ApplicationInitConfig {


    private final PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                var roles = new HashSet<String>();
                roles.add(Role.ADMIN.name());
                User user = User.builder()
                        .username("admin")
                        .lastname("admin")
                        .firstname("admin")
                        .email("admin@admin.com")
//                        .roles()
                        .password(passwordEncoder.encode("admin123456"))
                        .build();
                userRepository.save(user);
            }
        };
    }
}
