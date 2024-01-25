package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class EmployeeRepositoryImplementationTest {
    private EmployeeRepositoryImplementation repository;
    Employee e1;
    Employee e2;
    Employee e3;
    @BeforeEach
    public void setUpEmployees()
    {
        e1 = new Employee("123",100_000.00);
        e2 = new Employee("456",200_000.00);
        e3 = new Employee("789",50_000.00);
    }
    @Test
    @DisplayName("When method findAll is called, should return a list with the employees")
    void whenMethodFindAllIsCalledShouldReturnAListWithTheEmployees(){
        repository = new EmployeeRepositoryImplementation(List.of(e1,e2,e3));
        var result = repository.findAll();

        assertThat(result).containsExactlyInAnyOrder(e1,e2,e3);
    }
    @Test
    @DisplayName("When save is called with three different employees, the employees should be saved and findAll should return list with the employees")
    void whenSaveIsCalledWithThreeDifferentEmployeesTheEmployeesShouldBeSavedAndFindAllShouldReturnListWithTheEmployees(){
        repository = new EmployeeRepositoryImplementation();

        repository.save(e1);
        repository.save(e2);
        repository.save(e3);
        var result = repository.findAll();

        assertThat(result).containsExactlyInAnyOrder(e1,e2,e3);
    }
    @Test
    @DisplayName("When save is called with an already existing Id, should replace old employee object with new employee object")
    void whenSaveIsCalledWithAnAlreadyExistingIdShouldReplaceOldEmployeeObjectWithNewEmployeeObject(){
        repository = new EmployeeRepositoryImplementation(List.of(e1,e2,e3));
        var e4 = new Employee("456",10_000.00);

        repository.save(e4);
        var result = repository.findAll();

        assertThat(result).contains(e4);
        assertThat(result).doesNotContain(e2);
    }
}