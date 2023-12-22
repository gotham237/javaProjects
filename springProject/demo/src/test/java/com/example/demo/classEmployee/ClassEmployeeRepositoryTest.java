package com.example.demo.classEmployee;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ClassEmployeeRepositoryTest {

    @Autowired
    private ClassEmployeeRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void shouldCheckIfExistsByClassName() {
        //given
        String groupName = "Group";
        ClassEmployee ce = new ClassEmployee();
        ce.setClassName(groupName);
        ce.setMaxNum(5);
        underTest.save(ce);

        //when
        boolean exists = underTest.existsByClassName(groupName);

        //then
        assertThat(exists).isTrue();
    }

    @Test
    void shouldCheckIfNotExistsByClassName() {

        String groupName = "Group";

        boolean exists = underTest.existsByClassName(groupName);

        assertThat(exists).isFalse();
    }
}
