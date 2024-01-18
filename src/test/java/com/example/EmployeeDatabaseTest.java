package com.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeDatabaseTest {
    private EmployeeDatabase database;
    Employee e1;
    Employee e2;
    Employee e3;
    @BeforeEach
    public void setUp()
    {
        database = new EmployeeDatabase();
        e1 = new Employee("123",100_000.00);
        e2 = new Employee("456",200_000.00);
        e3 = new Employee("789",50_000.00);
        database.save(e1);
        database.save(e2);
        database.save(e3);
    }
    @Test
    @DisplayName("When method findAll is called, should return a list with the employees")
    void whenMethodFindAllIsCalledShouldReturnAListWithTheEmployees(){
        var result = database.findAll();

        assertThat(result).containsExactlyInAnyOrder(e1,e2,e3);
    }
    @Test
    @DisplayName("When given employee object with already existing Id, should replace old employee object with new employee object")
    void whenGivenEmployeeObjectWithAlreadyExistingIdShouldReplaceOldEmployeeObjectWithNewEmployeeObject(){
        var e4 = new Employee("456",10_000.00);
        database.save(e4);
        assertThat(database.findAll()).contains(e4);
        assertThat(database.findAll()).doesNotContain(e2);
    }
}