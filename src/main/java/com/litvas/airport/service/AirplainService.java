package com.litvas.airport.service;

import com.litvas.airport.domain.Airplain;
import org.springframework.stereotype.Service;

@Service
public interface AirplainService {

    public Airplain toAir(Airplain airplain);

    public Airplain toRunway(Airplain airplain);

    public Airplain toHangar(Airplain airplain);

}
