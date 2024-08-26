package com.controllerAdvice.controller;

import com.controllerAdvice.exception.EmployeeNotFoundException;
import com.controllerAdvice.model.Employee;
import com.controllerAdvice.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeRepository repo;

    public EmployeeController(EmployeeRepository repo){
        this.repo = repo;
    }

    @GetMapping
    List<Employee> findAll(){
        return repo.findAll();
    }

    @PostMapping
    Employee newEmployee(@RequestBody Employee newEmployee){
        return repo.save(newEmployee);
    }

    @GetMapping("/{id}")
    Employee findById(@PathVariable Long id){
        return repo.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @PutMapping("/{id}")
    Employee remplaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id){

        return repo.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return repo.save(employee);
                })
                .orElseGet(() -> {
                    return repo.save(newEmployee);
                });
    }

    @DeleteMapping("/{id}")
    void deleteEmployee(@PathVariable Long id){
        repo.deleteById(id);
    }


}
