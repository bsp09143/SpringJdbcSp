package com.iss.service;

import java.util.List;
import com.iss.model.Employee;

public interface EmployeeService {
  void insertEmployee(Employee emp);

  void insertEmployees(List<Employee> employees);

  void getAllEmployees();

  void getEmployeeById(String empid);
}
