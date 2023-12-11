package com.example.demo.employee;

import com.example.demo.EmployeeCondition;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class EmployeeConfig {

    @Bean
    CommandLineRunner commandLineRunner(EmployeeRepository repository) {
        return args -> {
            if (!repository.findEmployeeByFirstNameAndLastName("test1", "test1").isPresent()) {
                Employee test1 = new Employee(
                        "test1",
                        "test1",
                        2000,
                        EmployeeCondition.CHORY,
                        10000
                );

                repository.save(test1);
            }

            if (!repository.findEmployeeByFirstNameAndLastName("test2", "test2").isPresent()) {
                Employee test2 = new Employee(
                        "test2",
                        "test2",
                        2004,
                        EmployeeCondition.OBECNY,
                        8000
                );

                repository.save(test2);
            }
        };
    }
}
