package com.iss.dao.impl;

import java.sql.Types;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.GenericStoredProcedure;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Repository;
import com.iss.constants.CarConstants;
import com.iss.dao.StoredProcedureDAO;
import com.iss.service.CarRowMapper;

@Repository
public class StoredProcedureDAOImpl extends JdbcDaoSupport implements StoredProcedureDAO {

  @Autowired
  DataSource dataSource;

  private SimpleJdbcCall jdbcCall;

  @PostConstruct
  @SuppressWarnings("rawtypes")
  private void initialize() {
    setDataSource(dataSource);
  }

  @Override
  public List getCars(String make, String model, int year, double maxPrice) {
    JdbcTemplate template = new JdbcTemplate(dataSource);
    RowMapper rowMapper = new CarRowMapper();
    jdbcCall = new SimpleJdbcCall(template).withProcedureName("getCars")
        .useInParameterNames(CarConstants.CAR_MAKE).useInParameterNames(CarConstants.CAR_MODEL)
        .useInParameterNames(CarConstants.CAR_YEAR).useInParameterNames(CarConstants.MAX_PRICE)
        .declareParameters(new SqlReturnResultSet(CarConstants.RESULT_LIST, rowMapper),
            new SqlParameter(CarConstants.CAR_MAKE, Types.VARCHAR),
            new SqlParameter(CarConstants.CAR_MODEL, Types.VARCHAR),
            new SqlParameter(CarConstants.CAR_YEAR, Types.INTEGER),
            new SqlParameter(CarConstants.MAX_PRICE, Types.DOUBLE),
            new SqlOutParameter(CarConstants.RETURN_CODE, Types.INTEGER),
            new SqlOutParameter(CarConstants.RETURN_MESSAGE, Types.VARCHAR));

    SqlParameterSource in = new MapSqlParameterSource().addValue(CarConstants.CAR_MAKE, make)
        .addValue(CarConstants.CAR_MODEL, model).addValue(CarConstants.CAR_YEAR, new Integer(year))
        .addValue(CarConstants.MAX_PRICE, new Double(maxPrice));

    Map<String, Object> out = jdbcCall.execute(in);

    // retrieve the list of objects
    List result = (List) out.get(CarConstants.RESULT_LIST);

    // retrieve the status info
    Integer code = (Integer) out.get(CarConstants.RETURN_CODE);
    String message = (String) out.get(CarConstants.RETURN_MESSAGE);

    System.out.println("Status Code=" + code);
    System.out.println("Status Messsage=" + message);

    return result;
  }

}
