package com.example.demo.employee;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldFindEmployeeByFirstNameAndLastName() {
        //given
        String firstName = "Szymon";
        String lastName = "Tym";
        Employee employee = new Employee(
                firstName,
                lastName,
                2002,
                EmployeeCondition.DELEGACJA,
                6500
        );
        underTest.save(employee);

        //when
        boolean exists = underTest.findEmployeeByFirstNameAndLastName(firstName, lastName).isPresent();

        //then
        assertThat(exists).isTrue();
    }

    @Test
    void itShouldNOTFindEmployeeByFirstNameAndLastName() {
        //given
        String firstName = "Szymon";
        String lastName = "Tym";

        //when
        boolean exists = underTest.findEmployeeByFirstNameAndLastName(firstName, lastName).isPresent();

        //then
        assertThat(exists).isFalse();
    }
}
