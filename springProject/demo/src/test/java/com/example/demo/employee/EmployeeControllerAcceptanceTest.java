package com.example.demo.employee;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerAcceptanceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getEmployees() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/employee"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("test1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName").value("test1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].firstName").value("test2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].lastName").value("test2"))
                .andDo(print());
    }
}