package com.promineotech.jeep.service;

import com.promineotech.jeep.entitiy.Jeep;
import com.promineotech.jeep.entitiy.JeepModel;

import java.util.List;

public interface JeepSalesService {
    List<Jeep> fetchJeeps(JeepModel model, String trim);
}
