package com.iss;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.iss.dao.CarsDAO;
import com.iss.dao.impl.CarsDAOImpl;
import com.iss.dao.impl.StoredProcedureDAOImpl;
import com.iss.model.Employee;
import com.iss.service.EmployeeService;

@SpringBootApplication
public class SpringbootSecurityJdbcLog4jAopApplication {
  @Autowired
  EmployeeService employeeService;

  public static void main(String[] args) {
    ApplicationContext context =
        SpringApplication.run(SpringbootSecurityJdbcLog4jAopApplication.class, args);
    EmployeeService employeeService = context.getBean(EmployeeService.class);


    Employee emp = new Employee();
    emp.setEmpId("emp");
    emp.setEmpName("emp");

    Employee emp1 = new Employee();
    emp1.setEmpId("emp1");
    emp1.setEmpName("emp1");

    Employee emp2 = new Employee();
    emp2.setEmpId("emp2");
    emp2.setEmpName("emp2");


    employeeService.insertEmployee(emp);

    List<Employee> employees = new ArrayList<>();
    employees.add(emp1);
    employees.add(emp2);
    // employeeService.insertEmployees(employees);

    employeeService.getAllEmployees();

    employeeService.getEmployeeById(emp1.getEmpId());


    /* Procedure Wala part */
    CarsDAO carsDAO = context.getBean("carsDAOImpl", CarsDAOImpl.class);
    List carsList = carsDAO.getCars("Nissan", "Altima", 2014, 50000.00);
    for (Object temp : carsList) {
      System.out.println(temp);
    }

    StoredProcedureDAOImpl storedProcedureDAO =
        context.getBean("storedProcedureDAOImpl", StoredProcedureDAOImpl.class);
    List carListApproach2 = storedProcedureDAO.getCars("Nissan", "Altima", 2014, 50000.00);
    for (Object temp : carListApproach2) {
      System.out.println(temp);
    }

  }
}
