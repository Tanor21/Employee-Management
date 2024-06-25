package com.norcorp.simpleWebApp.service;


import com.norcorp.simpleWebApp.exception.EmployeeAlreadyExistException;
import com.norcorp.simpleWebApp.exception.EmployeeNotFoundException;
import com.norcorp.simpleWebApp.model.Employee;
import com.norcorp.simpleWebApp.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepo repo;

    public List<Employee> getEmployees() {
        return repo.findAll();
    }

    public Employee getEmployeeById(int empId) {
        //return repo.findById(empId).orElse(new Employee());
        return repo.findById(empId).orElseThrow(() -> new EmployeeNotFoundException("Sorry, no employee found with this Id :" + empId));
    }

    public void addEmployee(Employee employee) {
        if (employeeAlreadyExist(employee.getEmpEmail())) {
            throw new EmployeeAlreadyExistException(employee.getEmpEmail()+ " already exist!");
        }
        repo.save(employee);
    }

//    public void updateEmployee(Employee employee) {
//        repo.save(employee);
//    }

    public void updateEmployee(@RequestBody Employee employee, @PathVariable int empId) {
        repo.findById(empId).map(emp -> {
            emp.setEmpFirstName(employee.getEmpFirstName());
            emp.setEmpLastName(employee.getEmpLastName());
            emp.setEmpEmail(employee.getEmpEmail());
            emp.setEmpAddress(employee.getEmpAddress());
            return repo.save(emp);
        }).orElseThrow(() -> new EmployeeNotFoundException("Sorry, this employee could not be found"));
    }

    public void deleteEmployee(int empId) {
        if (!repo.existsById(empId)) {
            throw new EmployeeNotFoundException("Sorry, employee not found");
        }
        repo.deleteById(empId);
    }

    //Exception
    private boolean employeeAlreadyExist(String empEmail) {
        return repo.findByEmpEmail(empEmail).isPresent();
    }

}


