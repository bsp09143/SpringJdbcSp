package com.iss.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iss.dao.EmployeeDAO;
import com.iss.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  @Autowired
  EmployeeDAO employeeDao;

  @Override
  public void insertEmployee(Employee employee) {
    employeeDao.insertEmployee(employee);
  }

  @Override
  public void insertEmployees(List<Employee> employees) {
    employeeDao.insertEmployees(employees);
  }

  @Override
  public void getAllEmployees() {
    List<Employee> employees = employeeDao.getAllEmployees();
    for (Employee employee : employees) {
      System.out.println(employee.toString());
    }
  }

  @Override
  public void getEmployeeById(String empId) {
    Employee employee = employeeDao.getEmployeeById(empId);
    System.out.println(employee);
  }

}
