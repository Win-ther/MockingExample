package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EmployeeTest {
    private Employee employee;

    @BeforeEach
    public void setUp()
    {
        this.employee = new Employee("123456",100_000.00);
    }
    @Test
    @DisplayName("When employee setPaid is called with true, isPaid should return true")
    void whenEmployeeSetPaidIsCalledWithTrueIsPaidShouldReturnTrue(){
        employee.setPaid(true);

        assertThat(employee.isPaid()).isTrue();
    }
    @Test
    @DisplayName("When employee setPaid is called with false, isPaid should return false")
    void whenEmployeeSetPaidIsCalledWithFalseIsPaidShouldReturnFalse(){
        employee.setPaid(false);

        assertThat(employee.isPaid()).isFalse();
    }
    @Test
    @DisplayName("When setSalary is called with value, getSalary should return same value")
    void whenSetSalaryIsCalledWithValueGetSalaryShouldReturnSameValue(){
        employee.setSalary(50_000.00);

        assertThat(employee.getSalary()).isEqualTo(50_000.00);
    }
    @Test
    @DisplayName("When setId is called with String Id, getId should return same Id")
    void whenSetIdIsCalledWithStringIdGetIdShouldReturnSameId(){
        employee.setId("42");

        assertThat(employee.getId()).isEqualTo("42");
    }
    @Test
    @DisplayName("When calling employee toString method should return correct String")
    void whenCallingEmployeeToStringMethodShouldReturnCorrectString(){
        var result = employee.toString();

        assertThat(result).isEqualTo("Employee [id=123456, salary=100000.0]");
    }
}