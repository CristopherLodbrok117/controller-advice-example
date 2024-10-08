package com.controllerAdvice.config;

import com.controllerAdvice.model.Employee;
import com.controllerAdvice.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner inidDatabase(EmployeeRepository repo) {
        return args -> {
            log.info("Preloading " + repo.save(new Employee("Bilbo Baggins", "burglar")));
            log.info("Preloading " + repo.save(new Employee("Frodo Baggins", "thief")));
        };
    }
}
