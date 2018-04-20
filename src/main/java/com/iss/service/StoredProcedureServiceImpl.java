package com.iss.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iss.dao.EmployeeDAO;
import com.iss.dao.StoredProcedureDAO;

@Service
public class StoredProcedureServiceImpl implements StoredProcedureService {

  @Autowired
  StoredProcedureDAO storedProcedureDAO;

  @Override
  public List getCars(String make, String model, int year, double maxPrice) {

    return storedProcedureDAO.getCars(make, model, year, maxPrice);
  }

}
