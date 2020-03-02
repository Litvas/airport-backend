package com.litvas.airport.services;

import com.litvas.airport.domains.Airplain;

import java.util.List;

public interface AirplainService {

    Airplain toAir(Airplain airplain);

    Airplain toRunway(Airplain airplain);

    Airplain toHangar(Airplain airplain);

    List<Airplain> getAllAirplains();

}
