package com.example.demo.classEmployee;

import com.example.demo.employee.Employee;
import com.example.demo.employee.EmployeeCondition;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(ClassEmployeeController.class)
class ClassEmployeeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ClassEmployeeService classEmployeeService;

    @Test
    void shouldGetGroups() throws Exception{

        List<ClassEmployee> groups = Arrays.asList(new ClassEmployee(), new ClassEmployee());
        when(classEmployeeService.getGroups()).thenReturn(groups);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/group"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(groups)))
                .andDo(print());

        verify(classEmployeeService).getGroups();
    }

    @Test
    void getEmployeesFromGroup() throws Exception{

        Integer groupId = 1;
        Set<Employee> employees = Set.of(
                new Employee("Szymon", "Tym", 2002, EmployeeCondition.OBECNY, 6500),
                new Employee("Szymon2", "Tym2", 2003, EmployeeCondition.OBECNY, 6500));

        when(classEmployeeService.getEmployeesFromGroup(groupId)).thenReturn(employees);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/group/{groupId}/employee", groupId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(employees)))
                .andDo(print());

        // Assert
        verify(classEmployeeService).getEmployeesFromGroup(groupId);
    }

    @Test
    void getFill() {

    }

    @Test
    void addGroup() {
    }

    @Test
    void deleteGroup() {
    }
}