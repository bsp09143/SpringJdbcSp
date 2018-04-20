package com.iss.dao;

import java.util.List;

public interface StoredProcedureDAO {
  public List getCars(String make, String model, int year, double maxPrice);
}
