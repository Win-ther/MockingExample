package com.example;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDatabase implements EmployeeRepository{
    private List<Employee> employees;
    public EmployeeDatabase(){
        employees = new ArrayList<>();
    }
    @Override
    public List<Employee> findAll() {
        return employees;
    }

    @Override
    public Employee save(Employee e) {
        employees.stream()
                .filter(employee -> employee.getId().equals(e.getId()))
                .findFirst()
                .ifPresentOrElse(employee -> employees.set(employees.indexOf(employee),e), () -> employees.add(e));
        return e;
    }
}
