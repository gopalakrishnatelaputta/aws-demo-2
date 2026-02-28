package com.example.aws_demo_2;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private Map<Long, Employee> employeeDB = new HashMap<>();
    private Long idCounter = 1L;

    // CREATE
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        employee.setId(idCounter++);
        employeeDB.put(employee.getId(), employee);
        return employee;
    }

    // READ ALL
    @GetMapping
    public Collection<Employee> getAllEmployees() {
        return employeeDB.values();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeDB.get(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id,
                                   @RequestBody Employee updatedEmployee) {

        Employee existing = employeeDB.get(id);

        if (existing == null) {
            throw new RuntimeException("Employee not found with id " + id);
        }

        existing.setName(updatedEmployee.getName());
        existing.setEmail(updatedEmployee.getEmail());
        existing.setDepartment(updatedEmployee.getDepartment());

        return existing;
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeDB.remove(id);
        return "Employee deleted with id " + id;
    }
}
