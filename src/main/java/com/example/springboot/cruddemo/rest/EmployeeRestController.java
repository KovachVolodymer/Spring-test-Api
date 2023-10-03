package com.example.springboot.cruddemo.rest;

import com.example.springboot.cruddemo.entity.Employee;
import com.example.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employee/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId)
    {
        Employee theEmployee=employeeService.findById(employeeId);
        if (theEmployee==null)
        {
            throw new RuntimeException("Employee id not found"+employeeId);
        }
        return  theEmployee;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee)
    {
        theEmployee.setId(0);

        Employee dbEmployee=employeeService.save(theEmployee);

        return  dbEmployee;
    }

    @PostMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee)
    {
        Employee dbEmployee=employeeService.save(theEmployee);
        return dbEmployee;

    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId)
    {
        Employee theEmployee=employeeService.findById(employeeId);

        if (theEmployee==null)
        {
            throw new RuntimeException("Employee id is not found"+employeeId);
        }
        employeeService.deleteById(employeeId);
        return "Deleted employee id"+employeeId;
    }

}
// new repo