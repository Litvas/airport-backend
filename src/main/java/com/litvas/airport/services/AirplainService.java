package com.litvas.airport.services;

import com.litvas.airport.domains.Airplain;
import org.springframework.stereotype.Service;

@Service
public interface AirplainService {

    public Airplain toAir(Airplain airplain);

    public Airplain toRunway(Airplain airplain);

    public Airplain toHangar(Airplain airplain);

}
