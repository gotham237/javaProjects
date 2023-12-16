package com.example.demo.employee;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    void shouldGetEmployees() throws Exception {
        // Mocking the service behavior
        List<Employee> employees = Arrays.asList(new Employee(), new Employee());
        when(employeeService.getEmployees()).thenReturn(employees);

        // Performing the request and asserting the response
        mockMvc.perform(MockMvcRequestBuilders.get("/api/employee"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(employees)))
                .andDo(print());

        verify(employeeService).getEmployees();
    }

    @Test
    void shouldAddEmployee() throws Exception {
        Employee employee = new Employee(
                "SzymonTest",
                "TymTest",
                2002,
                EmployeeCondition.DELEGACJA,
                6500
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(employee)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());

        // ArgumentCaptor to capture the employee
        ArgumentCaptor<Employee> employeeCaptor = ArgumentCaptor.forClass(Employee.class);
        verify(employeeService).addEmployee(employeeCaptor.capture());

        Employee capturedEmployee = employeeCaptor.getValue();
        assertEquals("SzymonTest", capturedEmployee.getFirstName());
        assertEquals("TymTest", capturedEmployee.getLastName());
    }

    @Test
    void shouldDeleteEmployee() throws Exception {
        Integer employeeId = 1;

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/employee/{employeeId}", employeeId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());

        verify(employeeService).deleteEmployee(employeeId);
    }


}