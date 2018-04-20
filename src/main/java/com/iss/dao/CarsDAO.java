package com.iss.dao;

import java.util.List;


public interface CarsDAO {
  @SuppressWarnings("rawtypes")
  public List getCars(String make, String model, int year, double maxPrice);
}
