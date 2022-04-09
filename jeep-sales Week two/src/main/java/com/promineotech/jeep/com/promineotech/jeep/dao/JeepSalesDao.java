package com.promineotech.jeep.com.promineotech.jeep.dao;

import com.promineotech.jeep.entitiy.Jeep;
import com.promineotech.jeep.entitiy.JeepModel;

import java.util.List;

public interface JeepSalesDao {

    List<Jeep> fetchJeeps(JeepModel model, String trim);
}
