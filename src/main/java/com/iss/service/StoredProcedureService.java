package com.iss.service;

import java.util.List;

public interface StoredProcedureService {
  public List getCars(String make, String model, int year, double maxPrice);
}
