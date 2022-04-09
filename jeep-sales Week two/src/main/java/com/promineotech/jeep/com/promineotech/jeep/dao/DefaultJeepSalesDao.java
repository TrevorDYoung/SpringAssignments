package com.promineotech.jeep.com.promineotech.jeep.dao;

import com.promineotech.jeep.entitiy.Jeep;
import com.promineotech.jeep.entitiy.JeepModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class DefaultJeepSalesDao implements JeepSalesDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Jeep> fetchJeeps(JeepModel model, String trim) {
        log.debug("Dao: model={}, trim={}", model, trim);

        String sql = "Select * from models where model id = :model_id and trim_level = :trim_level";

        Map<String, Object> params = new HashMap<>();
        params.put("model_id", model.toString());
        params.put("Trim_level", trim);

        return null;
    }
}
