package com.iss.dao.impl;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.jdbc.object.StoredProcedure;
import com.iss.constants.CarConstants;
import com.iss.service.CarRowMapper;


public class CarsStoredProcedure extends StoredProcedure {


  @SuppressWarnings("rawtypes")
  public CarsStoredProcedure(JdbcTemplate jdbcTemplate) {
    super(jdbcTemplate, "getCars");

    // Parameters should be declared in same order here that
    // they are declared in the stored procedure.
    //
    // Note: resultSet must be defined first!
    //
    // define params with syntax: param_name, param_type
    //
    RowMapper rowMapper = new CarRowMapper();
    declareParameter(new SqlReturnResultSet(CarConstants.RESULT_LIST, rowMapper));
    declareParameter(new SqlParameter(CarConstants.CAR_MAKE, Types.VARCHAR));
    declareParameter(new SqlParameter(CarConstants.CAR_MODEL, Types.VARCHAR));
    declareParameter(new SqlParameter(CarConstants.CAR_YEAR, Types.INTEGER));
    declareParameter(new SqlParameter(CarConstants.MAX_PRICE, Types.DOUBLE));
    declareParameter(new SqlOutParameter(CarConstants.RETURN_CODE, Types.INTEGER));
    declareParameter(new SqlOutParameter(CarConstants.RETURN_MESSAGE, Types.VARCHAR));

    // now compile stored proc
    compile();
  }

  /**
   * Execute stored procedure.
   *
   * @return Results of running stored procedure.
   */
  @SuppressWarnings({"unchecked", "rawtypes"})
  public Map getCars(String make, String model, int year, double maxPrice) {

    // set the input params
    Map inParameters = new HashMap();
    inParameters.put(CarConstants.CAR_MAKE, make);
    inParameters.put(CarConstants.CAR_MODEL, model);
    inParameters.put(CarConstants.CAR_YEAR, new Integer(year));
    inParameters.put(CarConstants.MAX_PRICE, new Double(maxPrice));

    // now execute
    Map out = execute(inParameters); // Call on parent class

    return out;
  }


}
