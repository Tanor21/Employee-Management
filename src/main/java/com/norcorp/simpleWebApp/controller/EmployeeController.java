package com.norcorp.simpleWebApp.controller;

import com.norcorp.simpleWebApp.model.Employee;
import com.norcorp.simpleWebApp.model.Product;
import com.norcorp.simpleWebApp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
public class EmployeeController {

    @Autowired
    EmployeeService service;

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return service.getEmployees();
    }

    @PostMapping("/employees")
    public void addEmployee(@RequestBody Employee employee) {
        service.addEmployee(employee);
    }

    @GetMapping("/employees/{empId}")
    public Employee getEmployeeById(@PathVariable int empId) {
        return service.getEmployeeById(empId);
    }

    @PutMapping("/employees/{empId}")
    public void updateEmployee(@RequestBody Employee employee, @PathVariable int empId) {
        service.updateEmployee(employee, empId);
    }

    @DeleteMapping("/employees/{empId}")
    public void deleteEmployee(@PathVariable int empId) {
        service.deleteEmployee(empId);
    }

}
