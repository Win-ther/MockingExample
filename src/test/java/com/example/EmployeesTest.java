package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeesTest {
    @Mock
    EmployeeRepository employeeRepository;
    Employees employees;
    @BeforeEach
    void setUpEmployeeRepository(){
        Employee e1 = new Employee("1",10000);
        Employee e2 = new Employee("2",20000);
        Employee e3 = new Employee("3",30000);
        when(employeeRepository.findAll()).thenReturn(List.of(e1,e2,e3));
    }
    @Test
    @DisplayName("given EmployeeRepository with three employees, payEmployees should return three")
    void givenEmployeeRepositoryWithThreeEmployeesPayEmployeesShouldReturnThree(){
        BankServiceSpy bankService = new BankServiceSpy();
        employees = new Employees(employeeRepository, bankService);

        var result = employees.payEmployees();

        assertThat(result).isEqualTo(3);
        assertThat(bankService.payCalled).isTrue();
    }
    @Test
    @DisplayName("Given null instead of bankservice, payEmployees should return zero")
    void givenNullInsteadOfBankservicePayEmployeesShouldReturnZero(){
        employees = new Employees(employeeRepository,null);

        var result = employees.payEmployees();

        assertThat(result).isZero();
    }
}