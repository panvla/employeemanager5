package com.vladimirpandurov.employeemanager5.service;


import com.vladimirpandurov.employeemanager5.exception.UserNotFoundException;
import com.vladimirpandurov.employeemanager5.model.Employee;
import com.vladimirpandurov.employeemanager5.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;


    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee addEmployee(Employee employee){
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return this.employeeRepository.save(employee);
    }

    public List<Employee> findAllEmployees(){
        return employeeRepository.findAll();
    }

    public Employee findEmployeeById(Long id){
        return employeeRepository.findEmployeeById(id).orElseThrow(()-> new UserNotFoundException("User by id " + id + " was not found"));
    }

    public Employee updateEmployee(Employee employee){
        return this.employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id){
        this.employeeRepository.deleteEmployeeById(id);
    }
}
