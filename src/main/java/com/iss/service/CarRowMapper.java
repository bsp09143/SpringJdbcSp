package com.iss.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.jdbc.core.RowMapper;
import com.iss.model.Car;


public class CarRowMapper implements RowMapper {

  @Override
  public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    Car theCar = new Car();

    String make = rs.getString(1);
    theCar.setMake(make);

    String model = rs.getString(2);
    theCar.setModel(model);

    int year = rs.getInt(3);
    theCar.setYear(year);

    double price = rs.getDouble(rowNum);
    theCar.setPrice(price);

    return theCar;
  }

}
