package com.example.demo.classEmployee;

import com.example.demo.employee.Employee;
import com.example.demo.employee.EmployeeCondition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClassEmployeeServiceTest {

    @Mock private ClassEmployeeRepository classEmployeeRepository;
    private ClassEmployeeService underTest;

    @BeforeEach
    void setUp() {
        underTest = new ClassEmployeeService(classEmployeeRepository);
    }

    @Test
    void canGetGroups() {
        //when
        underTest.getGroups();
        //then
        verify(classEmployeeRepository).findAll();
    }

    @Test
    void canGetEmployeesFromGroup() {
        // given
        Integer groupId = 1;
        ClassEmployee classEmployee = new ClassEmployee();
        Set<Employee> employees = new HashSet<>();
        Employee employee = new Employee("John", "Doe", 1990, EmployeeCondition.OBECNY, 5000);
        employees.add(employee);
        classEmployee.setEmployees(employees);

        given(classEmployeeRepository.findById(groupId)).willReturn(Optional.of(classEmployee));

        // when
        Set<Employee> result = underTest.getEmployeesFromGroup(groupId);

        // then
        assertThat(result).isEqualTo(employees);
    }

    @Test
    void willThrowIfGroupDoesNotExistWhenGetEmployeesFromGroup() {
        // given
        Integer groupId = 1;
        given(classEmployeeRepository.findById(groupId)).willReturn(Optional.empty());

        // when/then
        assertThatThrownBy(() -> underTest.getEmployeesFromGroup(groupId))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Group with id " + groupId + " does not exist");
    }

    @Test
    void canGetFill() {
        // given
        Integer groupId = 1;
        ClassEmployee classEmployee = new ClassEmployee();
        classEmployee.setEmployees(new HashSet<>());

        given(classEmployeeRepository.findById(groupId)).willReturn(Optional.of(classEmployee));

        // when
        double result = underTest.getFill(groupId);

        // then
        assertThat(result).isEqualTo(0.0);
    }

    @Test
    void willThrowIfGroupDoesNotExistWhenGetFill() {
        // given
        Integer groupId = 1;
        given(classEmployeeRepository.findById(groupId)).willReturn(Optional.empty());

        // when/then
        assertThatThrownBy(() -> underTest.getFill(groupId))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Group with id " + groupId + " does not exist");
    }

    @Test
    void canAddGroup() {
        // given
        ClassEmployee classEmployee = new ClassEmployee("Group", 10);

        // when
        underTest.addGroup(classEmployee);

        // then
        ArgumentCaptor<ClassEmployee> classEmployeeArgumentCaptor = ArgumentCaptor.forClass(ClassEmployee.class);
        verify(classEmployeeRepository).save(classEmployeeArgumentCaptor.capture());
        ClassEmployee capturedClassEmployee = classEmployeeArgumentCaptor.getValue();

        assertThat(capturedClassEmployee).isEqualTo(classEmployee);
    }

    @Test
    void willThrowIfGroupAlreadyExistsWhenAddGroup() {
        // given
        ClassEmployee classEmployee = new ClassEmployee("Group", 10);
        given(classEmployeeRepository.existsByClassName(classEmployee.getClassName())).willReturn(true);

        // when/then
        assertThatThrownBy(() -> underTest.addGroup(classEmployee))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Group with name: " + classEmployee.getClassName() + " already exists");

        verify(classEmployeeRepository, never()).save(any());
    }

    @Test
    void canDeleteGroup() {
        // given
        Integer groupId = 1;
        ClassEmployee classEmployee = new ClassEmployee();
        given(classEmployeeRepository.findById(groupId)).willReturn(Optional.of(classEmployee));

        // when
        underTest.deleteGroup(groupId);

        // then
        verify(classEmployeeRepository).deleteById(groupId);
    }

    @Test
    void willThrowIfGroupDoesNotExistWhenDeleteGroup() {
        // given
        Integer groupId = 1;
        given(classEmployeeRepository.findById(groupId)).willReturn(Optional.empty());

        // when/then
        assertThatThrownBy(() -> underTest.deleteGroup(groupId))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Group with id " + groupId + " does not exist");

        verify(classEmployeeRepository, never()).deleteById(any());
    }

    @Test
    void canUpdateGroup() {
        // given
        Integer groupId = 1;
        ClassEmployee existingClassEmployee = new ClassEmployee("Group", 10);
        given(classEmployeeRepository.findById(groupId)).willReturn(Optional.of(existingClassEmployee));

        ClassEmployee updatedClassEmployee = new ClassEmployee("Updated Group", 15);

        // when
        underTest.updateGroup(groupId, updatedClassEmployee);

        // then
        assertThat(existingClassEmployee.getClassName()).isEqualTo(updatedClassEmployee.getClassName());
        assertThat(existingClassEmployee.getMaxNum()).isEqualTo(updatedClassEmployee.getMaxNum());
    }

    @Test
    void willThrowIfGroupDoesNotExistWhenUpdateGroup() {
        // given
        Integer groupId = 1;
        given(classEmployeeRepository.findById(groupId)).willReturn(Optional.empty());

        ClassEmployee updatedClassEmployee = new ClassEmployee("Updated Group", 15);

        // when/then
        assertThatThrownBy(() -> underTest.updateGroup(groupId, updatedClassEmployee))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("group with id " + groupId + " does not exist");
    }
}
