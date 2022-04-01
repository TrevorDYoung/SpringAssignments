package com.promineotech.jeep.entitiy.Entities;

import java.util.List;

import com.promineotech.jeep.entitiy.JeepModel;
import lombok.Data;

@Data
public class OrderRequest {
  private String customer;
  private JeepModel model;
  private String trim;
  private int doors;
  private String color;
  private String engine;
  private String tire;

  private List<String> options;
}
