package com.iss.dao.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.iss.constants.CarConstants;
import com.iss.dao.CarsDAO;

@Repository
public class CarsDAOImpl extends JdbcDaoSupport implements CarsDAO {

  @Autowired
  DataSource dataSource;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private CarsStoredProcedure carsStoredProcedure;

  public CarsDAOImpl() {}

  @PostConstruct
  private void initialize() {
    setDataSource(dataSource);
    carsStoredProcedure = new CarsStoredProcedure(jdbcTemplate);
  }

  @Override
  @SuppressWarnings("rawtypes")
  public List getCars(String make, String model, int year, double maxPrice) {
    List result = null;
    // Call the stored procedure
    Map data = carsStoredProcedure.getCars(make, model, year, maxPrice);

    // retrieve the list of objects
    result = (List) data.get(CarConstants.RESULT_LIST);

    // retrieve the status info
    Integer code = (Integer) data.get(CarConstants.RETURN_CODE);
    String message = (String) data.get(CarConstants.RETURN_MESSAGE);

    // just print the code and message…should log this
    System.out.println("Status Code=" + code);
    System.out.println("Status Messsage=" + message);

    return result;
  }

  public CarsStoredProcedure getCarsStoredProcedure() {
    return carsStoredProcedure;
  }

  public void setCarsStoredProcedure(CarsStoredProcedure carsStoredProcedure) {
    this.carsStoredProcedure = carsStoredProcedure;
  }

}
