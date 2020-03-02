package com.litvas.airport.facades.impl;

import com.litvas.airport.domains.Airplain;
import com.litvas.airport.domains.RunwayStatus;
import com.litvas.airport.facades.AirportFacade;
import com.litvas.airport.services.AirplainService;
import com.litvas.airport.services.AirportService;
import com.litvas.airport.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;

public class AirportFacadeImpl implements AirportFacade {

    @Autowired
    private AirplainService airplainService;

    @Autowired
    private FlightService flightService;

    @Autowired
    private AirportService airportService;

    @Override
    public void startFly(Airplain airplain) {
        airplainService.toRunway(airplain);
        flightService.fly(airplain);
    }

    @Override
    public void finishFly(Airplain airplain) {
        airplainService.toHangar(airplain);
        airportService.changeRunwayStatus(RunwayStatus.AVAILABLE);
    }
}
