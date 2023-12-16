package com.example.demo.employee;

import com.example.demo.classEmployee.ClassEmployee;
import com.example.demo.classEmployee.ClassEmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock private EmployeeRepository employeeRepository;
    @Mock private ClassEmployeeRepository classEmployeeRepository;
    private EmployeeService underTest;

    @BeforeEach
    void setUp() {
        underTest = new EmployeeService(employeeRepository, classEmployeeRepository);
    }

    @Test
    void canGetEmployees() {
        //when
        underTest.getEmployees();
        //then
        verify(employeeRepository).findAll();
    }

    @Test
    void canAddEmployee() {
        //given
        Employee employee = new Employee(
                "Szymon",
                "Tym",
                2002,
                EmployeeCondition.DELEGACJA,
                6500
        );
        //when
        underTest.addEmployee(employee);

        //then
        ArgumentCaptor<Employee> employeeArgumentCaptor = 
                ArgumentCaptor.forClass(Employee.class);

        //catch the employee from addEmployee function
        verify(employeeRepository).save(employeeArgumentCaptor.capture());
        Employee capturedEmployee = employeeArgumentCaptor.getValue();

        assertThat(capturedEmployee).isEqualTo(employee);
    }

    @Test
    void willThrowWhenFirstNameAndLastNameIsTaken() {
        //given
        Employee employee = new Employee(
                "Szymon",
                "Tym",
                2002,
                EmployeeCondition.DELEGACJA,
                6500
        );

        given(employeeRepository.findEmployeeByFirstNameAndLastName(employee.getFirstName(), employee.getLastName()))
                .willReturn(Optional.of(employee));

        //when
        assertThatThrownBy(() -> underTest.addEmployee(employee))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining(
                        "Employee: " + employee.getFirstName() + " " + employee.getLastName() + " already exists.");

        verify(employeeRepository, never()).save(any());
    }

    @Test
    void canAddEmployeeToGroup() {
        // given
        Integer groupId = 1;
        Employee employee = new Employee(
                "Szymon",
                "Tym",
                2002,
                EmployeeCondition.DELEGACJA,
                6500
        );

        ClassEmployee classEmployee = new ClassEmployee();

        // Mock the behavior of findById
        given(classEmployeeRepository.findById(groupId)).willReturn(Optional.of(classEmployee));

        // when
        underTest.addEmployeeToGroup(groupId, employee);

        // then
        ArgumentCaptor<Employee> employeeCaptor = ArgumentCaptor.forClass(Employee.class);
        verify(employeeRepository).save(employeeCaptor.capture());
        Employee capturedEmployee = employeeCaptor.getValue();

        assertThat(capturedEmployee).isEqualTo(employee);
        assertThat(classEmployee.getEmployees().contains(employee)).isEqualTo(true);
    }

    @Test
    void canDeleteEmployee() {
        // given
        Integer employeeId = 1;
        given(employeeRepository.existsById(employeeId)).willReturn(true);

        // when
        underTest.deleteEmployee(employeeId);

        // then
        verify(employeeRepository).deleteById(employeeId);
    }

    @Test
    void willThrowIfEmployeeDoesNotExistWhenDeleteEmployee() {
        //given
        Integer id = 1;
        given(employeeRepository.existsById(1)).willReturn(false);

        //when
        assertThatThrownBy(() -> underTest.deleteEmployee(id))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining(
                        "employee with id " + id + " does not exist");

        //then
        verify(employeeRepository, never()).deleteById(any());
    }

    @Test
    @Disabled
    void updateEmployee() {
    }
}